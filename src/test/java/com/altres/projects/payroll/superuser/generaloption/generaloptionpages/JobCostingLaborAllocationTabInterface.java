package com.altres.projects.payroll.superuser.generaloption.generaloptionpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.altres.util.SeleniumWrapper;

public interface JobCostingLaborAllocationTabInterface {

  By ALLOW_OVERRIDE_OVERTIME_ALLOCATION = By.name("allow_override_overtime_allocation");
  By CLICK_ON_YES_RADIO_BUTTON_FOR_USE_DESCRIPTION = By.xpath(
      "//input[@id='use_free_form_description_0' and @type='radio']");
  By CLICK_ON_LOCN_CHECK_BOX = By.id("free_form_LOCN");
  By TIMESHEET_FLOW_RESTRICTIONS_ROWS = By.xpath("//tbody[@id='timesheetFlowRestrictions']/tr");
  By TIMESHEET_FLOW_RESTRICTIONS_ADD_ROW_BUTTON = By.xpath(
      "//a[@onclick" + "='addTimesheetFlowRestrictions();return false;']");
  By LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_TABLE = By.xpath("//tbody[@id" + "='descriptionOverrideTable']/tr");
  By LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_TABLE_ROW = By.xpath("//tbody[@id" + "='descriptionOverrideTable']");
  By HIDDEN_LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_TABLE_ROW = By.xpath("//tbody[@id" + "='displayOverrideTable']");
  By LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_ADD_ROW_BUTTON = By.xpath(
      "//a[@onclick" + "='addDescriptionOverrideRow();return false;']");
  By USES_EMPLOYEE_JOB_RATES_EMPLOYEE_LEVEL = By.xpath("//select[@name" + "='allow_employee_job_rates_dropdown']");
  By WITHOUT_PROJ_ASSOCIATION_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name" + "='allow_job_change_without_proj_dropdown']");
  By ALLOW_WORK_CLASSIFICATION_CHANGE_EMPLOYEE_LEVEL = By.xpath("//select[@name" + "='allow_job_change_dropdown']");
  By TIMESHEET_FLOW_RESTRICTIONS_TABLE = By.xpath("//tbody[@id='timesheetFlowRestrictions']");
  By FREE_FROM_LABOR_COST_CENTRE_ALL_CHECK_BOXES = By.xpath("//input[contains(@id, 'free_form') and @type='checkbox']");
  By USES_EMPLOYEE_JOB_RATES_YES_BUTTON = By.id("allow_employee_job_rates_0");
  By WITHOUT_PROJ_ASSOCIATION_YES_BUTTON = By.id("allow_job_change_without_proj_0");
  By USES_EMPLOYEE_JOB_RATES_NO_BUTTON = By.id("allow_employee_job_rates_1");
  By WITHOUT_PROJ_ASSOCIATION_NO_BUTTON = By.id("allow_job_change_without_proj_1");
  By ALLOW_WORK_CLASSIFICATION_CHANGE_NO_BUTTON = By.id("allow_job_change_1");
  By ALLOW_WORK_CLASSIFICATION_CHANGE_YES_BUTTON = By.id("allow_job_change_0");
  By USE_TIME_CARD_TAB_RATE_IF_RATE_IS_DIFFERENT_THAN_THE_EMPLOYEE_DEFAULT_COSTING_RATE_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name='allocation_paycode_rate_dropdown']");
  By USE_TIME_CARD_TAB_RATE_IF_RATE_IS_DIFFERENT_THAN_THE_EMPLOYEE_DEFAULT_COSTING_RATE_YES_BUTTON = By.xpath(
      "//select[@name='employee_allocation_read_only_dropdown']");
  By ENABLE_READ_ONLY_EMPLOYEE_VIEW_FOR_TIMESHEET_ALLOCATION_TAB_YES_BUTTON = By.id("employee_allocation_read_only_0");
  By SET_DEFAULT_ALLOCATION_BASED_ON_PAY_PERIOD_HOUR_PERCENTAGE_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name='employee_allocation_by_percentage_dropdown']");
  By SET_DEFAULT_ALLOCATION_BASED_ON_PAY_PERIOD_HOUR_PERCENTAGE_YES_BUTTON = By.id(
      "employee_allocation_by_percentage_0");
  By ALLOW_PERCENTAGE_ALLOCATION_EMPLOYEE_LEVEL = By.xpath("//select[@name" + "='allow_employee_allocation_dropdown']");
  By USE_TIME_CARD_TAB_RATE_IF_RATE_IS_DIFFERENT_THAN_THE_EMPLOYEE_DEFAULT_COSTING_RATE_NO_BUTTON = By.id(
      "allocation_paycode_rate_1");
  By ENABLE_READ_ONLY_EMPLOYEE_VIEW_FOR_TIMESHEET_ALLOCATION_TAB_NO_BUTTON = By.id("employee_allocation_read_only_1");
  By SET_DEFAULT_ALLOCATION_BASED_ON_PAY_PERIOD_HOUR_PERCENTAGE_NO_BUTTON = By.id(
      "employee_allocation_by_percentage_1");
  By ALLOW_PERCENTAGE_ALLOCATION_YES_BUTTON = By.id("allow_employee_allocation_0");
  By ALLOW_MULTI_PAY_RATE_CHANGE_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name" + "='allow_job_costing_pay_rate_change_dropdown']");
  By ALLOW_MULTI_PAY_RATE_CHANGE_NO_BUTTON = By.id("allow_job_costing_pay_rate_change_1");
  By ALLOW_MULTI_PAY_RATE_CHANGE_YES_BUTTON = By.id("allow_job_costing_pay_rate_change_0");
  By HIDDEN_LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_TABLE = By.xpath("//tbody[@id" + "='displayOverrideTable']");
  By HIDDEN_LABOR_COST_CENTERS_ADD_ROW = By.xpath("//a[@onclick='addDisplayOverrideRow();return false;']");
  By HIDDEN_LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_ALL_ROWS = By.xpath("//tbody[@id" + "='displayOverrideTable']/tr");
  By ENABLE_EMPLOYEES_READ_ONLY_LABOR_COST_CENTERS_YES_BUTTON = By.id("enable_employees_read_only_labor_cost_0");
  By ENABLE_EMPLOYEES_READ_ONLY_LABOR_COST_CENTERS_NO_BUTTON = By.id("enable_employees_read_only_labor_cost_1");
  By ENABLE_EMPLOYEES_READ_ONLY_LABOR_COST_CENTERS_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name" + "='enable_employees_read_only_labor_cost_dropdown']");
  By ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_YES_BUTTON = By.id("allow_limit_job_costing_0");
  By ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_NO_BUTTON = By.id("allow_limit_job_costing_1");
  By ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_USE_EMPLOYEE_GROUPS_NO_BUTTON = By.id(
      "allow_limit_job_costing_employee_groups_1");
  By ALLOW_LABOR_COST_CENTER_FILTERS_ON_TIMESHEET_APPROVAL_YES_BUTTON = By.id(
      "allow_timesheet_approval_costing_filter_0");
  By ALLOW_LABOR_COST_CENTER_FILTERS_ON_TIMESHEET_APPROVAL_NO_BUTTON = By.id(
      "allow_timesheet_approval_costing_filter_1");
  By ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name" + "='allow_limit_job_costing_dropdown']");
  By ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_USE_EMPLOYEE_GROUPS_YES_BUTTON = By.id(
      "allow_limit_job_costing_employee_groups_0");
  By HIDDEN_LABOR_COST_CENTER_DESCRIPTION_OVERRIDES_DROPDOWN = By.xpath("//select[@name='display_override_name_1']");
  By LABOR_COST_CENTER_DESCRIPTION_OVERRIDES_DROPDOWN = By.xpath("//select[@name='description_override_name_1']");

  /**
   * Method to get a web element by driver and locator element.
   *
   * @param driver
   * @param locatorElement
   * @return
   */
  static WebElement getWebElement(WebDriver driver, By locatorElement) {
    return SeleniumWrapper.findElement(driver, locatorElement);
  }

  /**
   * Method to get a list of web elements by driver and locator element.
   *
   * @param driver
   * @param locatorElement
   * @return
   */
  static List<WebElement> getWebElements(WebDriver driver, By locatorElement) {
    return SeleniumWrapper.findElements(driver, locatorElement);
  }
}
