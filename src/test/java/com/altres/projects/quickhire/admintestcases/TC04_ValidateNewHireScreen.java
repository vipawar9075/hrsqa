package com.altres.projects.quickhire.admintestcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.quickhire.quickhirepage.QuickHireListingPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireAddEditPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireHomePage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

/**
 * Validate the functionality of the New Hire screen.
 */
public class TC04_ValidateNewHireScreen extends Base {

  @Test(enabled = true)
  public void validateNewHirePage() {
    SeleniumWrapper.click(driver, QuickhireHomePage.getUsernameFieldLocator());
    QuickhireHomePage.clickNewHireButton();
    AltresGeneralUtil.clickOnFilterButton();
    QuickHireListingPage.clickOnCustomerName();
    QuickhireAddEditPage.setSocialSecurityNumber();
    QuickhireAddEditPage.setFirstName();
    QuickhireAddEditPage.setLastName();
    QuickhireAddEditPage.setPrimaryPhone();
    QuickhireAddEditPage.setEmail();
    QuickhireAddEditPage.setAddressNumber();
    QuickhireAddEditPage.setDropdown(1);
    JavascriptExecutorWrapper.scrollToBottom(driver);
    QuickhireAddEditPage.setBenefitGroup(1);
    QuickhireAddEditPage.setRequestedBy(1);
    AltresGeneralUtil.clickOnSaveButton();
    Assert.assertEquals(QuickhireAddEditPage.getMessageDisplayed(),
        "The new hire has been successfully saved.");

  }
}
