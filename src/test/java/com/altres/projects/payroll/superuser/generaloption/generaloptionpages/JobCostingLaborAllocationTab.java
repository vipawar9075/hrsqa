package com.altres.projects.payroll.superuser.generaloption.generaloptionpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

/**
 * Class to contain all business logic methods for job costing labor allocation tab.
 */
public class JobCostingLaborAllocationTab extends Base implements JobCostingLaborAllocationTabInterface {

  /**
   * This method returns the element for the Step selection in timesheet flow restrictions.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getTimeSheetFlowRestrictionsStepSelection(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElement(driver, By.xpath("//Select[@name='step_" + rowNumber + "']"));
  }

  /**
   * This method will check if element for the labor cost center selection in the timesheet flow restrictions is present
   * or not.
   * @param driver
   * @param rowNumber
   * @return
   */
  public static boolean isTimeSheetFlowRestrictionsStepSelectionDisplayed(WebDriver driver, int rowNumber) {
    return !SeleniumWrapper.findElements(driver, By.xpath("//Select[@name='step_" + rowNumber + "']")).isEmpty();
  }

  /**
   * This method returns the element for the labor cost center selection in the timesheet flow restrictions.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getTimeSheetFlowRestrictionsLaborCostCenterSelection(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElement(driver, By.xpath("//Select[@name='level_" + rowNumber + "']"));
  }

  /**
   * This method will check if element for the labor cost center selection in the timesheet flow restrictions is present
   * or not.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static boolean isTimeSheetFlowRestrictionsLaborCostCenterSelectionDisplayed(WebDriver driver,
      int rowNumber) {
    return !SeleniumWrapper.findElements(driver, By.xpath("//Select[@name='level_" + rowNumber + "']")).isEmpty();
  }

  /**
   * This method returns the element for the Cost Code text field in timesheet flow restrictions.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getTimeSheetFlowRestrictionsCostFieldTextSelection(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElement(driver, By.xpath("//input[@name='value_" + rowNumber + "']"));
  }

  /**
   * This method will check if element for the labor cost center selection in the timesheet flow restrictions is present
   * or not.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static boolean isTimeSheetFlowRestrictionsCostFieldTextSelectionDisplayed(WebDriver driver, int rowNumber) {
    return !SeleniumWrapper.findElements(driver, By.xpath("//input[@name='value_" + rowNumber + "']")).isEmpty();
  }

  /**
   * This method returns the element for the Employee Group selection for labor cost center description overrides.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getLaborCostCenterDescriptionOverrideEmployeeGroupSelection(WebDriver driver,
      int rowNumber) {
    return SeleniumWrapper.findElement(driver,
        By.xpath("//select[@name='description_override_name_" + rowNumber + "']"));
  }

  /**
   * This method returns all text fields for the description override table.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static List<WebElement> getLaborCostCenterDescriptionOverrideAllTextFields(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElements(driver,
        By.xpath("//tbody[@id='descriptionOverrideTable']/tr[" + rowNumber + "]/td/input" + "[@type='text']"));
  }

  /**
   * This method returns all dropdown fields for the hidden labor cost centers table.
   *
   * @param driver
   * @return
   */
  public static List<WebElement> getHiddenLaborCostCenterDescriptionOverrideAllDropdownFields(WebDriver driver,
      int rowNumber) {
    return SeleniumWrapper.findElements(driver,
        By.xpath("//tbody[@id='displayOverrideTable']/tr[" + rowNumber + "]//Select"));
  }

  /**
   * This method returns the element for the employee group selection hidden labor cost centers.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getHiddenLaborCostCentersEmployeeGroupSelection(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElement(driver, By.xpath("//select[@name='display_override_name_" + rowNumber + "']"));
  }

  /**
   * This method returns the element for the hide location selection for the hidden labor cost centers.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getHiddenLaborCostCentersLocationSelection(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElement(driver, By.xpath("//select[@name='display_override_LOCN_" + rowNumber + "']"));
  }
}