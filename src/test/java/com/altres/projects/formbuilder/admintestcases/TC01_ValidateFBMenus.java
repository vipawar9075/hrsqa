package com.altres.projects.formbuilder.admintestcases;

import java.io.IOException;
import java.util.Properties;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.projects.formbuilder.fbutil.FBPropertyLoader;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBHome;

/**
 * Validate Page title of form builder screen.
 */
public class TC01_ValidateFBMenus extends Base {

  Properties properties;

  public TC01_ValidateFBMenus() {
    try {
      properties = FBPropertyLoader.loadProperties();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Test(enabled = true)
  public void validateFBFields() throws ConfigurationNotLoadedException {
    navigateToURL("adminurl");
    driver.navigate().refresh();
    FBAddEdit.getWebElement(FBHome.USERNAME_FIELD).click();
    String expectedPageTitle = properties.getProperty("expected.title.pageTitle");
    String actualPageTitle = FBAddEdit.getWebElement(FBHome.LISTING_SCREEN_TITLE).getText();
    Assert.assertEquals(actualPageTitle, expectedPageTitle, "Page title does not match");
  }
}
