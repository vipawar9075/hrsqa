package com.altres.projects.ats.clienttestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.projects.ats.pages.AtsAddNewPositionPage;
import com.altres.projects.ats.pages.AtsPositionListingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.FakeDataGenerator;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Validate First Name,Last Name after entering same email id for creating another position.
 */
public class TC09_ValidateDuplicateDataForAnotherPosition extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateDuplicateDataForAnotherPosition() throws IOException {
    String nextButtonId = propertyValueProvider.getPropertyValueByKey("nextButtonId");
    String nextButton = propertyValueProvider.getPropertyValueByKey("nextButton");
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsAddNewPositionPage.clickOnApplyUrl();
    SeleniumWrapper.switchToWindowByIndex(1);
    String email = FakeDataGenerator.getEmail();
    AtsAddNewPositionPage.setEmail(email);
    AtsAddNewPositionPage.clickOnNext(nextButtonId);
    String firstName = FakeDataGenerator.getRandomFirstName();
    String lastName = FakeDataGenerator.getRandomLastName();
    String phoneNumber = FakeDataGenerator.getRandomNumber(10);
    AtsAddNewPositionPage.setContactInfo(firstName, lastName, phoneNumber);
    AtsAddNewPositionPage.clickOnNext(nextButton);
    AtsAddNewPositionPage.clickOnNext(nextButton);
    AtsAddNewPositionPage.clickOnNext(nextButton);
    AtsAddNewPositionPage.clickOnAgree();
    DriverManager.closeDriver();
    SeleniumWrapper.switchToWindowByIndex(0);
    createNewPosition();
    AtsAddNewPositionPage.clickOnApplyUrl();
    SeleniumWrapper.switchToWindowByIndex(1);
    AtsAddNewPositionPage.setEmail(email);
    AtsAddNewPositionPage.clickOnNext(nextButtonId);
    AtsAddNewPositionPage.clickOnYes();
    String actualFirstName = AtsAddNewPositionPage.getFirstName();
    String actualLastName = AtsAddNewPositionPage.getLastName();
    Assert.assertEquals(firstName, actualFirstName, "First name mismatch");
    Assert.assertEquals(lastName, actualLastName, "Last name mismatch");
    DriverManager.closeDriver();
    SeleniumWrapper.switchToWindowByIndex(0);
  }

  /**
   * Create Add New Position and validate success message.
   */
  public void createNewPosition() throws IOException {

    String active = propertyValueProvider.getPropertyValueByKey("activeCheckbox");
    AtsPositionListingPage.clickOnPosition();
    AtsPositionListingPage.clickOnAddNewPosition();
    AtsAddNewPositionPage.setActiveInactive(active);
    String positionTitle = FakeDataGenerator.getRandomTitle();
    AtsAddNewPositionPage.setTitle(positionTitle);
    AltresGeneralUtil.clickOnSaveButton();
    Assert.assertTrue(AltresGeneralUtil.isSuccessMessageDisplayed(), "Error: Success message not displayed");

  }

}

