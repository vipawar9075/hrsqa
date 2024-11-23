package com.altres.projects.formbuilder.admintestcases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBAddEditPage;
import com.altres.projects.formbuilder.pages.FBHome;
import com.altres.projects.formbuilder.pages.FBListing;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.FakeDataGenerator;

/**
 * Validate the error messages on Add from screen.
 */
public class TC05_ValidateErrorMessageOnAddForm extends Base {

  public static ConfigPropertiesProviderImpl propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.FORMBUILDER_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateErrorMessages() throws ConfigurationNotLoadedException, IOException {
    navigateToURL("adminurl");
    driver.navigate().refresh();
    FBAddEdit.getWebElement(FBHome.USERNAME_FIELD).click();
    FBAddEdit.getWebElement(FBListing.ADD_FORM_BUTTON).click();
    AltresGeneralUtil.clickOnSaveButton();
    String actualMessage = AltresGeneralUtil.getErrorMessage();
    String expectedMessage = propertyValueProvider.getPropertyValueByKey("error.message.blankModuleName");
    Assert.assertEquals(actualMessage, expectedMessage, "Error: The error message is incorrect.");
    FBAddEditPage.selectModuleFromDropdown("Quickhire");
    AltresGeneralUtil.clickOnSaveButton();
    actualMessage = AltresGeneralUtil.getErrorMessage();
    String expectedFormNameMessage = propertyValueProvider.getPropertyValueByKey("error.message.formNameBlank");
    Assert.assertEquals(actualMessage, expectedFormNameMessage, "Error: The error message is incorrect.");
    FBAddEditPage.setFirstName(FakeDataGenerator.getRandomFirstName());
    AltresGeneralUtil.clickOnSaveButton();
    actualMessage = AltresGeneralUtil.getErrorMessage();
    String expectedDisplayNameMessage = propertyValueProvider.getPropertyValueByKey("error.message.displayNameBlank");
    Assert.assertEquals(actualMessage, expectedDisplayNameMessage, "Error: The error message is incorrect.");
  }
}
