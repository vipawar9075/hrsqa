package com.altres.projects.formbuilder.admintestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBAddEditPage;
import com.altres.projects.formbuilder.pages.FBFormFields;
import com.altres.projects.formbuilder.pages.FBFormFieldsPage;
import com.altres.projects.formbuilder.pages.FBPdfMapping;
import com.altres.projects.formbuilder.pages.FBPdfMappingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.ConfigurationManager;
import com.altres.util.Constants;
import com.altres.util.SeleniumWrapper;

/**
 * Validate the error message when a supplement is not uploaded and the user clicks on the supplement link created with
 * the HTML tag.
 */
public class TC08_ValidateErrorMessageForSupplementTag extends Base {

  public static ConfigPropertiesProviderImpl propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.FORMBUILDER_ELEMENT_PROPERTIES_PATH);

  public static void customTemplateSupplementDownload() throws IOException, ConfigurationNotLoadedException {
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("customerRadioButton"));
    FBAddEditPage.setCustomerNumber(ConfigurationManager.getProperty("company_number"));
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    SeleniumWrapper.dismissAlert();
    FBAddEditPage.selectPositionOfForm("Before Employee Information");
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    FBAddEdit.getWebElement(FBFormFields.PREVIEW_FORM_TAB).click();
    FBAddEdit.getWebElement(FBFormFields.SUPPLEMENT_LINK).click();
    Assert.assertTrue(FBFormFieldsPage.isFileDownloaded(), "Error: File is not downloaded");
    FBAddEdit.getWebElement(FBFormFields.RETURN_TO_FORM_BUTTON).click();
    SeleniumWrapper.acceptAlert();
    FBAddEdit.getWebElement(FBAddEdit.ACTIVE_CHECKBOX).click();
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    FBAddEdit.getWebElement(FBFormFields.PREVIEW_FORM_TAB).click();
    FBAddEdit.getWebElement(FBFormFields.SUPPLEMENT_LINK).click();
    Assert.assertTrue(FBFormFieldsPage.isFileDownloaded(), "Error: File is not downloaded");

  }

  @Test
  public void validateHtmlErrorMessage() throws IOException, ConfigurationNotLoadedException {
    FBAddEdit.getWebElement(FBAddEdit.PDF_MAPPING_BUTTON).click();
    String expectedMessage = propertyValueProvider.getPropertyValueByKey("noFormFieldMessage");
    String actualMessage = FBFormFieldsPage.getFBErrorMessage(expectedMessage);
    Assert.assertEquals(actualMessage, expectedMessage, "Error: The error message is incorrect.");
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormButton"));
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    FBFormFieldsPage.addHtmlFieldWithsupplementTag(
        propertyValueProvider.getPropertyValueByKey("formbuilderUploadedSupplementName"));
    String expectedSupplementTagError = propertyValueProvider.getPropertyValueByKey("error.message.supplementTag");
    String actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(expectedSupplementTagError, actualErrorMessage, "Error: The error message is incorrect.");
    FBAddEdit.getWebElement(FBFormFields.RETURN_TO_FORM_BUTTON).click();
    FBAddEditPage.uploadSupplementPdf(Constants.DENTAL_WAIVER_PDF_PATH);
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    FBFormFieldsPage.addHtmlFieldWithsupplementTag(
        propertyValueProvider.getPropertyValueByKey("formbuilderUploadedSupplementName"));
    FBFormFieldsPage.createSingleInputField();
    FBAddEdit.getWebElement(FBFormFields.PREVIEW_FORM_TAB).click();
    FBAddEdit.getWebElement(FBFormFields.SUPPLEMENT_LINK).click();
    Assert.assertTrue(FBFormFieldsPage.isFileDownloaded(), "Error: File is not downloaded.");
    FBAddEdit.getWebElement(FBFormFields.RETURN_TO_FORM_BUTTON).click();
    SeleniumWrapper.acceptAlert();
    FBAddEdit.getWebElement(FBAddEdit.PDF_MAPPING_BUTTON).click();
    FBPdfMappingPage.mapFormFields("pdf-field-first_name", "question2");
    FBPdfMappingPage.mapFormFields("pdf-field-last_name", "question2");
    AltresGeneralUtil.clickOnSaveButton();
    String expectedValidation = propertyValueProvider.getPropertyValueByKey("error.message.duplicateFormField");
    String actualValidation = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualValidation, expectedValidation, "Error: The error message is incorrect.");
    FBPdfMappingPage.mapFormFields("pdf-field-last_name", "");
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBPdfMapping.RETURN_TO_FORM_BUTTON).click();
    FBAddEdit.getWebElement(FBAddEdit.ACTIVE_CHECKBOX).click();
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    FBAddEdit.getWebElement(FBFormFields.PREVIEW_FORM_TAB).click();
    FBAddEdit.getWebElement(FBFormFields.SUPPLEMENT_LINK).click();
    Assert.assertTrue(FBFormFieldsPage.isFileDownloaded(), "Error: File is not downloaded.");
    FBAddEdit.getWebElement(FBFormFields.RETURN_TO_FORM_BUTTON).click();
    SeleniumWrapper.acceptAlert();
    customTemplateSupplementDownload();
    FBAddEdit.getWebElement(FBFormFields.RETURN_TO_FORM_BUTTON).click();
    SeleniumWrapper.acceptAlert();
    FBAddEdit.getWebElement(FBAddEdit.DELETE_SUPPLEMENT_DOC).click();
    SeleniumWrapper.acceptAlert();
    AltresGeneralUtil.clickOnSaveButton();
    String deleteSupplement = propertyValueProvider.getPropertyValueByKey("error.message.deleteSupplementDoc");
    String actualSupplementErrorMsg = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualSupplementErrorMsg, deleteSupplement, "Error: The error message is incorrect.");
  }
}
