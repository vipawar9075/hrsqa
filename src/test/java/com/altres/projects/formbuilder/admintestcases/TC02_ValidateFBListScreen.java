package com.altres.projects.formbuilder.admintestcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBHome;
import com.altres.projects.formbuilder.pages.FBListing;
import com.altres.projects.formbuilder.pages.FBListingPage;

/**
 * Validate fields on form builder listing screen.
 */
public class TC02_ValidateFBListScreen extends Base {

  @Test
  public void validateListScreen() {
    FBAddEdit.getWebElement(FBHome.USERNAME_FIELD).click();
    validateColumnHeaders();
    validateActiveFilter();
    validateButtons();
  }

  /**
   * Validates the column headers on the FB Listing Page.
   */
  private void validateColumnHeaders() {
    Assert.assertEquals(FBAddEdit.getWebElement(FBListing.CUSTOMER_NAME_FIELD).getText(), "Customer Name",
        "Customer Name mismatch");
    Assert.assertEquals(FBAddEdit.getWebElement(FBListing.CUSTOMER_NUMBER_FIELD).getText(), "Cust #",
        "Customer Number mismatch");
    Assert.assertEquals(FBAddEdit.getWebElement(FBListing.MODULE).getText(), "Module", "Module mismatch");
  }

  /**
   * Validates the active filter functionality on the FB Listing Page.
   */
  private void validateActiveFilter() {
    FBListingPage.setActiveFilter("No");
    String selectedActiveStatus = FBListingPage.getSelectedFilterValue();
    Assert.assertEquals(selectedActiveStatus, "No", "The active filter value is not set to 'No'!");
  }

  /**
   * Validates the button texts on the FB Listing Page.
   */
  private void validateButtons() {
    Assert.assertEquals(FBListingPage.getFilterButton(), "Filter", "Filter button text mismatch");
    Assert.assertEquals(FBListingPage.getResetButton(), "Reset", "Reset button text mismatch");
  }
}

