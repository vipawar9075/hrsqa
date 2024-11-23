package com.altres.projects.formbuilder.admintestcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBHome;
import com.altres.projects.formbuilder.pages.FBListing;
import com.altres.util.SeleniumWrapper;

/**
 * Validate anchor checkbox and position radio button is selected.
 */
public class TC03_VerifyCustomerFormAnchorField extends Base {

  @Test
  public void validateAnchor() {
    FBAddEdit.getWebElement(FBHome.USERNAME_FIELD).click();
    addNewFormAndSelectCheckbox();
    validateRadioButtonSelection();
    FBAddEdit.getWebElement(FBAddEdit.RETURN_BUTTON).click();
    SeleniumWrapper.acceptAlert();
  }

  /**
   * Adds a new form and selects the checkbox on the FB Add/Edit Page.
   */
  private void addNewFormAndSelectCheckbox() {
    FBAddEdit.getWebElement(FBListing.ADD_FORM_BUTTON).click();
    FBAddEdit.getWebElement(FBAddEdit.CHECKBOX_LOCATOR).click();
  }

  /**
   * Validates the radio button selection on the FB Add/Edit Page.
   */
  private void validateRadioButtonSelection() {
    boolean isBeforeSelected = FBAddEdit.getWebElement(FBAddEdit.BEFORE_RADIO_BUTTON).isSelected();
    Assert.assertTrue(isBeforeSelected, "Error: Before radio button is not selected");
  }
}
