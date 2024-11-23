package com.altres.projects.ats.clienttestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.ats.pages.AtsApplicationPage;
import com.altres.projects.ats.pages.AtsApplicationsListingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * To validate, first add an attachment and save it. Next, add a second attachment, remove the previous one, and save
 * again. Finally, verify the list of attachments.
 */
public class TC17_ValidateDeletedAttachments extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateDeletedAttachments() throws IOException {
    String selectStatus = propertyValueProvider.getPropertyValueByKey("selectStatus");
    String selectInterviewed = propertyValueProvider.getPropertyValueByKey("selectInterviewed");
    String dentalWaiverForm = propertyValueProvider.getPropertyValueByKey("dentalWaiverForm");
    String textFile = propertyValueProvider.getPropertyValueByKey("textFile");
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsApplicationsListingPage.clickOnApplications();
    SeleniumWrapper.switchToInteriorContent(driver);
    AltresGeneralUtil.clickOnResetButton();
    AtsApplicationsListingPage.clickAtsApplicationsRecord(1);
    AtsApplicationPage.uploadPdf(Constants.DENTAL_WAIVER_PDF_PATH);
    AtsApplicationPage.selectDropdown(selectStatus, selectInterviewed);
    AltresGeneralUtil.clickOnSaveButton();
    Assert.assertTrue(AtsApplicationPage.isUploadedFilePresent(dentalWaiverForm));
    AtsApplicationPage.removeUploadedFile(dentalWaiverForm);
    SeleniumWrapper.acceptAlert();
    AtsApplicationPage.uploadPdf(Constants.TEXT_FILE_PATH);
    AltresGeneralUtil.clickOnSaveButton();
    Assert.assertTrue(AtsApplicationPage.isUploadedFilePresent(textFile));
    Assert.assertFalse(AtsApplicationPage.isUploadedFilePresent(dentalWaiverForm));
    AtsApplicationPage.removeUploadedFile(textFile);
    SeleniumWrapper.acceptAlert();
    AltresGeneralUtil.clickOnSaveButton();
  }
}

