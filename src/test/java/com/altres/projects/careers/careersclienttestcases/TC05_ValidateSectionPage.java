package com.altres.projects.careers.careersclienttestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.projects.careers.carrerspages.CareersAddEditPage;
import com.altres.projects.careers.carrerspages.CareersDetailsPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Validates the section page functionalities on the Careers Details Page. Adding a new section, verifying error and
 * success messages, and validating page titles.
 */
public class TC05_ValidateSectionPage extends Base {

  private PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.CAREERS_ELEMENT_PROPERTIES_PATH);

  /**
   * Test method to validate the section page.
   *
   * @throws IOException
   */
  @Test
  public void validateSectionPage() throws IOException {
    CareersAddEditPage.clickCareersPageDetailsButton();
    CareersDetailsPage.clickAddNewSection();
    AltresGeneralUtil.clickOnSaveButton();
    String expectedErrorNotice = propertyValueProvider.getPropertyValueByKey(
        "expected.message.errorNotice");
    String actualErrorNotice = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualErrorNotice, expectedErrorNotice, "Error: The message is incorrect.");
    CareersDetailsPage.clickActiveRadioButton(0);
    CareersDetailsPage.enterTitleText();
    AltresGeneralUtil.clickOnSaveButton();
    String expectedSuccessMessage = propertyValueProvider.getPropertyValueByKey(
        "expected.message.sectionDetailsSuccessNotice");
    String actualSuccessMessage = AltresGeneralUtil.getSuccessNotice();
    Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "Error: The message is incorrect.");
    CareersDetailsPage.clickPencilEditIcon();
    CareersDetailsPage.clickActiveRadioButton(1);
    AltresGeneralUtil.clickOnSaveButton();
    boolean isDeleteButtonDisplayed = CareersDetailsPage.isDeleteButtonDisplayed();
    Assert.assertTrue(CareersDetailsPage.isDeleteButtonDisplayed(), "Add Career Button is not displayed");
    if (isDeleteButtonDisplayed) {
      CareersDetailsPage.clickDeleteButton();
    }
    SeleniumWrapper.acceptAlert();
    String expectedDeleteSuccessMessage = propertyValueProvider.getPropertyValueByKey(
        "expected.message.successDeleteMessage");

    String actualDeleteSuccessMessage = AltresGeneralUtil.getSuccessNotice();
    Assert.assertEquals(actualDeleteSuccessMessage, expectedDeleteSuccessMessage, "Error: The message is incorrect.");

  }

  /**
   * Tear down method to quit the WebDriver after all tests in this module have run.
   */
  @AfterClass(alwaysRun = true)
  public void tearDownClass() {
    DriverManager.quitDriver();
  }

}
