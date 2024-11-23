package com.altres.projects.ats.clienttestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.projects.ats.pages.AtsPositionListingPage;
import com.altres.projects.ats.pages.AtsSettingsPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.ConfigurationManager;
import com.altres.util.Constants;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Validates position listing screen displays when new admin is selected from SSO dropdown. HAD-949 covered this JIRA
 * issue.Company name admin and Payroll company admin should be configured by user for single sign on.
 */
public class TC20_ValidatePositionScreenForDifferentCompanyAdmins extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validatePositionScreenForDifferentCompanyAdmins() throws IOException, ConfigurationNotLoadedException {
    String atsPositionTitle = propertyValueProvider.getPropertyValueByKey("atsPositionTitle");
    SeleniumWrapper.refreshBrowser(driver);
    AtsSettingsPage.clickSettingsLink();
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsSettingsPage.selectSingleSignOn("Admin", ConfigurationManager.getProperty("payroll_company"));
    AtsPositionListingPage.clickOnPosition();
    Assert.assertEquals(AltresGeneralUtil.getPageTitle(), atsPositionTitle);
    SeleniumWrapper.refreshBrowser(driver);
    AtsSettingsPage.clickSettingsLink();
    AtsSettingsPage.selectSingleSignOn("Admin", ConfigurationManager.getProperty("company_name"));
    AtsPositionListingPage.clickOnPosition();
    Assert.assertEquals(AltresGeneralUtil.getPageTitle(), atsPositionTitle);
  }

  /**
   * Tear down method to quit the WebDriver after all tests in this module have run.
   */
  @AfterClass(alwaysRun = true)
  public void tearDownClass() {
    DriverManager.quitDriver();
  }
}

