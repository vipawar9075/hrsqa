package com.altres.projects.ats.clienttestcases;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.ats.pages.AtsAddNewPositionPage;
import com.altres.projects.ats.pages.AtsPositionListingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.Date;
import com.altres.util.FakeDataGenerator;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Validate tabs,button names on add new position screen. Also validated creating add new position
 */
public class TC08_ValidateAddNewPosition extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateAddNewPosition() throws IOException {
    String position = propertyValueProvider.getPropertyValueByKey("position");
    String applicationQuestions = propertyValueProvider.getPropertyValueByKey("applicationQuestions");
    String headerPermissionNotifications = propertyValueProvider.getPropertyValueByKey("headerPermissionNotifications");
    String headerAutoResponses = propertyValueProvider.getPropertyValueByKey("headerAutoResponses");
    String headerPostToRealJobs = propertyValueProvider.getPropertyValueByKey("headerPostToRealJobs");
    String headerPostToHireMaui = propertyValueProvider.getPropertyValueByKey("headerPostToHireMaui");
    String headerAdditionalSettings = propertyValueProvider.getPropertyValueByKey("headerAdditionalSettings");
    String saveButton = propertyValueProvider.getPropertyValueByKey("saveButton");
    String returnToPositionListButton = propertyValueProvider.getPropertyValueByKey("returnToPositionListButton");
    String copyButton = propertyValueProvider.getPropertyValueByKey("copyButton");
    String deleteButton = propertyValueProvider.getPropertyValueByKey("deleteButton");
    String addNewPositionTitle = propertyValueProvider.getPropertyValueByKey("addNewPositionTitle");
    String editPositionTitle = propertyValueProvider.getPropertyValueByKey("editPositionTitle");
    String activeCheckbox = propertyValueProvider.getPropertyValueByKey("activeCheckbox");
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsPositionListingPage.clickOnPosition();
    AtsPositionListingPage.clickOnAddNewPosition();
    List<String> expectedElementNamesBeforeSave = Arrays.asList(position, applicationQuestions,
        headerPermissionNotifications, headerAutoResponses, headerPostToRealJobs, headerPostToHireMaui,
        headerAdditionalSettings, saveButton,
        returnToPositionListButton);
    boolean allActualElementsDisplayedBeforeSave = AtsAddNewPositionPage.areElementsDisplayedByName(
        expectedElementNamesBeforeSave);
    Assert.assertTrue(allActualElementsDisplayedBeforeSave, "Not all elements are displayed");
    AtsAddNewPositionPage.setActiveInactive(activeCheckbox);
    String date = Date.getFutureDate(Date.getCurrentDate(), 100);
    AtsAddNewPositionPage.setExpirationDate(date);
    String positionTitle = FakeDataGenerator.getRandomTitle();
    AtsAddNewPositionPage.setTitle(positionTitle);
    SeleniumWrapper.switchToDefaultContent(driver);
    Assert.assertEquals(AltresGeneralUtil.getPageTitle(), addNewPositionTitle);
    SeleniumWrapper.switchToInteriorContent(driver);
    AltresGeneralUtil.clickOnSaveButton();
    Assert.assertTrue(AltresGeneralUtil.isSuccessMessageDisplayed(), "Error: Success message not displayed");
    List<String> expectedElementNamesAfterSave = Arrays.asList(position, applicationQuestions,
        headerPermissionNotifications, headerAutoResponses, headerPostToRealJobs, headerPostToHireMaui,
        headerAdditionalSettings, saveButton,
        returnToPositionListButton, copyButton, deleteButton);
    boolean allActualElementsDisplayedAfterSave = AtsAddNewPositionPage.areElementsDisplayedByName(
        expectedElementNamesAfterSave);
    Assert.assertTrue(allActualElementsDisplayedAfterSave, "Not all elements are displayed");
    SeleniumWrapper.switchToDefaultContent(driver);
    Assert.assertEquals(AltresGeneralUtil.getPageTitle(), editPositionTitle);
  }


}

