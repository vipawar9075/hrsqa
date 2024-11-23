package com.altres.projects.quickhire.admintestcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.quickhire.quickhirepage.QuickHireListingPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireHomePage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.SeleniumWrapper;

/**
 * Validate scenario when customer number is not found.
 */
public class TC02_CustomerNumberNotFoundScenario extends Base {

  @Test(enabled = true)
  public void validateNewHireForNoCustomers() {
    SeleniumWrapper.click(driver, QuickhireHomePage.getUsernameFieldLocator());
    QuickhireHomePage.clickNewHireButton();
    QuickHireListingPage.setCustomerNumber();
    AltresGeneralUtil.clickOnFilterButton();
    String actualMessage = QuickHireListingPage.getMessageText();
    Assert.assertEquals(actualMessage, "0 results found.",
        "Error: The expected message was not displayed.");
    AltresGeneralUtil.clickOnResetButton();
  }
}
