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
 * Validates that the title field can accept a maximum(100) character limit. Title is correctly displayed on the Apply
 * page.
 */
public class TC18_ValidateTitleFieldCharacterLimit extends Base {

  PropertyValueProvider expected = new ConfigPropertiesProviderImpl(
      Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateTitleFieldExceedsCharacterLimit() throws IOException {
    String activeCheckboxLabel = expected.getPropertyValueByKey("activeCheckbox");
    SeleniumWrapper.refreshBrowser(driver);
    AtsPositionListingPage.clickOnPosition();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsPositionListingPage.clickPositionRecord(1);
    AtsAddNewPositionPage.setActiveInactive(activeCheckboxLabel);
    String positionTitle = FakeDataGenerator.generateRandomStringWithCount(100);
    AtsAddNewPositionPage.clearAndSetTitle(positionTitle);
    AltresGeneralUtil.clickOnSaveButton();
    Assert.assertTrue(AltresGeneralUtil.isSuccessMessageDisplayed(), "Error: Success message not displayed");
    AtsAddNewPositionPage.clickOnApplyUrl();
    SeleniumWrapper.switchToWindowByIndex(1);
    String positionTitleFromApplyPage = AtsAddNewPositionPage.getPositionTitleFromApplyPage();
    Assert.assertEquals(positionTitle, positionTitleFromApplyPage,
        "Error: Position title does not match on the Apply page");
    DriverManager.closeDriver();
    SeleniumWrapper.switchToWindowByIndex(0);
  }

}
