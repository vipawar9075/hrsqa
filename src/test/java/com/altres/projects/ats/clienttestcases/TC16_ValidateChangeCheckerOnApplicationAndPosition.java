package com.altres.projects.ats.clienttestcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.ats.pages.AtsAddNewPositionPage;
import com.altres.projects.ats.pages.AtsApplicationPage;
import com.altres.projects.ats.pages.AtsApplicationsListingPage;
import com.altres.projects.ats.pages.AtsPositionListingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.FakeDataGenerator;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Validate Change Checker On Application And Position screens.
 */
public class TC16_ValidateChangeCheckerOnApplicationAndPosition extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateChangeCheckerOnApplicationAndPosition() throws IOException {
    String returnToPositionListButton = propertyValueProvider.getPropertyValueByKey("returnToPositionListButton");
    String returnToApplicationListButton = propertyValueProvider.getPropertyValueByKey("returnToApplicationListButton");
    SeleniumWrapper.refreshBrowser(driver);
    AtsPositionListingPage.clickOnPosition();
    SeleniumWrapper.switchToInteriorContent(driver);
    AltresGeneralUtil.clickOnResetButton();
    AtsPositionListingPage.clickPositionRecord(1);
    AtsAddNewPositionPage.setTitle(FakeDataGenerator.getRandomTitle());
    AltresGeneralUtil.clickOnButton(returnToPositionListButton);
    SeleniumWrapper.acceptAlert();
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsApplicationsListingPage.clickOnApplications();
    SeleniumWrapper.switchToInteriorContent(driver);
    AltresGeneralUtil.clickOnResetButton();
    AtsApplicationsListingPage.clickAtsApplicationsRecord(1);
    AtsApplicationPage.setFirstName(FakeDataGenerator.getRandomFirstName());
    AltresGeneralUtil.clickOnButton(returnToApplicationListButton);
    SeleniumWrapper.acceptAlert();
  }
}

