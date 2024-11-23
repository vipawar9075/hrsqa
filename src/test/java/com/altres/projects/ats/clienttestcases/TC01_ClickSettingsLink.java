package com.altres.projects.ats.clienttestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.login.LoginPage;
import com.altres.login.UserProperties;
import com.altres.projects.ats.pages.AtsSettingsPage;

/**
 * validate the Settings link is displayed.
 */
public class TC01_ClickSettingsLink extends Base {

  LoginPage login;
  UserProperties userProps;

  public TC01_ClickSettingsLink() {
    try {
      userProps = new UserProperties(ADMIN);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test
  public void ATS1() throws ConfigurationNotLoadedException {
    navigateToURL("clienturl");
    login = new LoginPage(driver);
    LoginPage.login(userProps, ADMIN);
    driver.navigate().refresh();
    AtsSettingsPage.clickSettingsLink();
    Assert.assertEquals(AtsSettingsPage.getSettingsPageTitle(), "Settings");
  }
}
