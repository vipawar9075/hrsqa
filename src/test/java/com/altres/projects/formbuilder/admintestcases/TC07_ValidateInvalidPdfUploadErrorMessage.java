package com.altres.projects.formbuilder.admintestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBAddEditPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;

/**
 * Validate the error message when the uploaded file extension is other than PDF and the uploaded PDF is more than 5
 * MB.
 */
public class TC07_ValidateInvalidPdfUploadErrorMessage extends Base {

  ConfigPropertiesProviderImpl propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.FORMBUILDER_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateErrorMessage() throws IOException {
    FBAddEditPage.selectFormPDFFromDropdown("Upload PDF");
    FBAddEditPage.uploadPdf(Constants.INVALID_PDF_PATH);
    FBAddEditPage.uploadSupplementPdf(Constants.INVALID_PDF_PATH);
    String actualMessage = AltresGeneralUtil.getErrorMessage();
    String expectedErrorMessage = propertyValueProvider.getPropertyValueByKey("error.message.fileSize");
    Assert.assertEquals(expectedErrorMessage, actualMessage, "Error: The error message is incorrect.");
    FBAddEditPage.uploadPdf(Constants.TEXT_FILE_PATH);
    FBAddEditPage.uploadSupplementPdf(Constants.TEXT_FILE_PATH);
    actualMessage = AltresGeneralUtil.getErrorMessage();
    String expectedInvalidFileError = propertyValueProvider.getPropertyValueByKey("error.message.invalidFile");
    Assert.assertEquals(expectedInvalidFileError, actualMessage, "Error: The error message is incorrect.");
    FBAddEditPage.uploadPdf(Constants.ENCRYPT_PDF_PATH);
    AltresGeneralUtil.clickOnSaveButton();
    actualMessage = AltresGeneralUtil.getErrorMessage();
    String expectedEncryptPdfError = propertyValueProvider.getPropertyValueByKey("error.message.encryptPdfUploaderror");
    Assert.assertEquals(expectedEncryptPdfError, actualMessage, "Error: The error message is incorrect.");
    AltresGeneralUtil.clickOnSaveButton();
    actualMessage = AltresGeneralUtil.getErrorMessage();
    String expNoPdfUploadMessage = propertyValueProvider.getPropertyValueByKey("error.message.noPdfUploaded");
    Assert.assertEquals(actualMessage, expNoPdfUploadMessage, "Error: The error message is incorrect.");
    FBAddEditPage.uploadPdf(Constants.AUTHORIZATION_FOR_CONSUMER_REPORT_PDF);
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBAddEdit.UPLOAD_TO_IMIGIT_CHECKBOX).click();
    AltresGeneralUtil.clickOnSaveButton();
    String expectedImigitDocTypeError = propertyValueProvider.getPropertyValueByKey(
        "error.message.unselectedImigitDocumentType");
    actualMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(expectedImigitDocTypeError, actualMessage, "Error: The error message is incorrect.");
    FBAddEditPage.selectImigitDocumentType("EDP");
    AltresGeneralUtil.clickOnSaveButton();
    String expectedImigitDocIndexError = propertyValueProvider.getPropertyValueByKey(
        "error.message.unselectedImigitDocumentIndex");
    actualMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualMessage, expectedImigitDocIndexError, "Error: The error message is incorrect.");
    FBAddEdit.getWebElement(FBAddEdit.UPLOAD_TO_IMIGIT_CHECKBOX).click();
    AltresGeneralUtil.clickOnSaveButton();
  }
}
