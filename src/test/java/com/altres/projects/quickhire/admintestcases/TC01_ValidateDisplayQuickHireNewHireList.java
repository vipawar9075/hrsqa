package com.altres.projects.quickhire.admintestcases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBListing;
import com.altres.projects.quickhire.quickhirepage.QuickHireListingPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireHomePage;
import com.altres.util.SeleniumWrapper;

/**
 * Validate the display of QuickHire New Hire list functionality.
 */
public class TC01_ValidateDisplayQuickHireNewHireList extends Base {

  @Test
  public void validateNewHireList() throws ConfigurationNotLoadedException {
    navigateToURL("adminurl");
    driver.navigate().refresh();
    QuickhireHomePage.verifyFieldDisplayed();
    SeleniumWrapper.click(driver, QuickhireHomePage.getUsernameFieldLocator());
    validateFields();
  }

  /**
   * Verify the listing page fields.
   */
  private void validateFields() {
    Assert.assertEquals(QuickhireHomePage.getMessageText(), "QuickHire: New Hire List");
    Assert.assertEquals(FBAddEdit.getWebElement(FBListing.CUSTOMER_NUMBER_FIELD).getText(), "Cust #");
    Assert.assertEquals(FBAddEdit.getWebElement(FBListing.CUSTOMER_NAME_FIELD).getText(), "Customer Name");
    Assert.assertEquals(QuickhireHomePage.getServiceRequestLabel(), "Service Request #");
    Assert.assertEquals(QuickhireHomePage.getNhaLabel(), "NHA");
    Assert.assertTrue(QuickhireHomePage.isAddNewButtonDisplayed(), "Add New Button is not displayed");
    List<String> actualDropDownOptions = QuickHireListingPage.getDropDownOptions();
    List<String> expectedDropDownOptions = Arrays.asList("New", "Ready for Employee", "Started by Employee",
        "Completed by Employee", "Pending Processing", "Processed", "Cancelled");
    Assert.assertEquals(actualDropDownOptions, expectedDropDownOptions, "Dropdown does not match");

  }
}
