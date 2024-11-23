package com.altres.projects.careers.careersclienttestcases;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.careers.carrerspages.CareersAddEditPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Validates the error messages on branding tab.
 */
public class TC02_ValidateErrorMessagesOnBrandingScreen extends Base {

  private final PropertyValueProvider propertyValueProvider;
  private final String uploadPDF;
  private final String pdfUploadLimitExceededMessage;

  public TC02_ValidateErrorMessagesOnBrandingScreen() {
    propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.CAREERS_ELEMENT_PROPERTIES_PATH);
    uploadPDF = Constants.PDF_ATTACHMENT;
    pdfUploadLimitExceededMessage = Constants.FILE_SIZE_EXCEEDS_LIMIT;
  }

  /**
   * Validates error messages on branding tab for background section.
   *
   * @throws IOException
   */
  @Test(priority = 1)
  public void errorMessageOnBrandingScreen() throws IOException {
    validateErrorMessageOnUpload(
        "expected.message.backgorundImageType",
        () -> {
          CareersAddEditPage.clickBrandingTab();
          CareersAddEditPage.clickCareersBackgroundCustomRadiobutton();
          CareersAddEditPage.uploadPdf(uploadPDF);
        }
    );
  }

  /**
   * Validates error messages for uploading the file size more than 5mb.
   *
   * @throws IOException
   */
  @Test(priority = 2)
  public void verifyUploadSizeLimitOnBackground() throws IOException {
    validateErrorMessageOnUpload("expected.message.maxsize", () -> {
      CareersAddEditPage.clickBrandingTab();
      CareersAddEditPage.clickCareersBackgroundCustomRadiobutton();
      CareersAddEditPage.uploadPdf(pdfUploadLimitExceededMessage);
    });
  }

  /**
   * Validates error messages for uploading the file size more than 5mb for "Header" section.
   *
   * @throws IOException
   */
  @Test(priority = 3)
  public void validateUploadedFileInHeader() throws IOException {
    validateErrorMessageOnUpload(
        "expected.message.backgorundImageType",
        () -> {
          CareersAddEditPage.clickBrandingTab();
          CareersAddEditPage.clickCareersHeaderCustomRadiobutton();
          CareersAddEditPage.uploadHeaderPdf(uploadPDF);
        }
    );
  }

  /**
   * Validates error messages from Footer section.
   *
   * @throws IOException
   */
  @Test(priority = 4)
  public void validateErrorMessagesForFooter() throws IOException {
    CareersAddEditPage.clickCareersFooterCustomRadiobutton();
    CareersAddEditPage.enterDataInRows();
    AltresGeneralUtil.clickOnSaveButton();
    List<String> keys = Arrays.asList(
        "expected.message.facebookUrl",
        "expected.message.linkedInUrl",
        "expected.message.twitterUrl",
        "expected.message.instagramUrl",
        "expected.message.companyUrl",
        "expected.message.actionUrl"
    );
    Map<String, String> expectedErrorMessagesMap = propertyValueProvider.getPropertyValuesByKeys(keys);
    List<String> actualErrorMessages = AltresGeneralUtil.getDisplayedErrorMessages();
    List<String> expectedErrorMessages = keys.stream()
        .map(expectedErrorMessagesMap::get)
        .collect(Collectors.toList());
    List<String> filteredActualErrorMessages = actualErrorMessages.stream()
        .map(e -> e.trim().toLowerCase())
        .filter(e -> expectedErrorMessages.stream()
            .map(String::trim)
            .map(String::toLowerCase)
            .collect(Collectors.toSet())
            .contains(e))
        .collect(Collectors.toList());
    Assert.assertEquals(new HashSet<>(filteredActualErrorMessages),
        new HashSet<>(expectedErrorMessages.stream()
            .map(String::trim)
            .map(String::toLowerCase)
            .collect(Collectors.toSet())),
        "Error messages do not match.");
    Assert.assertTrue(filteredActualErrorMessages.containsAll(expectedErrorMessages.stream()
            .map(String::trim)
            .map(String::toLowerCase)
            .collect(Collectors.toList())),
        "Filtered actual error messages do not contain all expected error messages.");
    CareersAddEditPage.clickReturnToCompaniesButton();
    SeleniumWrapper.acceptAlert();
  }

  /**
   * Validates that the correct error message is displayed after attempting to upload data using the provided upload
   * steps and checks if the expected error message is displayed.
   *
   * @param errorMessageKey
   * @param uploadSteps
   * @throws IOException
   */
  private void validateErrorMessageOnUpload(String errorMessageKey, Runnable uploadSteps) throws IOException {
    uploadSteps.run();
    AltresGeneralUtil.clickOnSaveButton();
    String expectedErrorMessage = propertyValueProvider.getPropertyValueByKey(errorMessageKey);
    List<String> actualErrorMessages = CareersAddEditPage.getSpecificErrorMessages(Arrays.asList(expectedErrorMessage));
    Assert.assertEquals(actualErrorMessages.size(), 1, "Multiple or no error messages found.");
    Assert.assertEquals(actualErrorMessages.get(0), expectedErrorMessage, "Error message does not match.");
  }
}

