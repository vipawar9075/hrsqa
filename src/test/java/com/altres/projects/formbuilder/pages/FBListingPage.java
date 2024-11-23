package com.altres.projects.formbuilder.pages;

import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

/**
 * All the xpaths and methods required to perform specific actions to validate the test cases on the FormBuilder listing
 * page are listed here.
 */
public class FBListingPage extends Base {

  /**
   * Set the name of the created custom/shared form.
   *
   * @return
   */
  public static WebElement setFormName() {
    WebElement formName = SeleniumWrapper.findElement(driver, FBListing.NAME_FIELD);
    formName.click();
    return formName;
  }

  /**
   * Clicks on the Filtered form.
   *
   * @param value
   */
  public static void clickOnFilteredForm(String value) {
    SeleniumWrapper.sendKeyWithClear(driver, setFormName(), value);
    SeleniumWrapper.click(driver, FBListing.FILTER_BUTTON);
    SeleniumWrapper.click(driver, FBListing.FIRST_FORM);
  }

  /**
   * Gets the value of the filter button.
   *
   * @return
   */
  public static String getFilterButton() {
    return SeleniumWrapper.findElement(driver, FBListing.FILTER_BUTTON).getAttribute("value");
  }

  /**
   * Gets the value of the reset button.
   *
   * @return
   */
  public static String getResetButton() {
    return SeleniumWrapper.findElement(driver, FBListing.RESET_BUTTON).getAttribute("Value");
  }

  /**
   * Sets the active filter value.
   *
   * @param activeValue
   */
  public static void setActiveFilter(String activeValue) {
    SeleniumWrapper.selectDropdownByVisibleText(FBListing.ACTIVE_DROPDOWN, activeValue);
  }

  /**
   * Gets the selected filter value.
   *
   * @return
   */
  public static String getSelectedFilterValue() {
    return SeleniumWrapper.getSelectedDropdownValue(FBListing.ACTIVE_DROPDOWN);
  }
}
