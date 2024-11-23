package com.altres.projects.payroll.superuser.generaloption.generaloptionpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.altres.util.SeleniumWrapper;

/**
 * Interface to contain all locators and reusable methods for time off tab.
 */
public interface TimeOffTabPageInterface {

  By WORKS_SATURDAY_FIELD = By.id("works_saturday");
  By WORKS_SUNDAY_FIELD = By.id("works_sunday");
  By ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_YES_BUTTON = By.id("allow_time_off_as_percentage_0");
  By ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_NO_BUTTON = By.id("allow_time_off_as_percentage_1");
  By ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name='allow_time_off_as_percentage_dropdown']");
  By ALLOW_TIME_OFF_ON_HOLIDAY_YES_BUTTON = By.id("allow_leave_on_holiday_0");
  By ALLOW_TIME_OFF_ON_HOLIDAY_NO_BUTTON = By.id("allow_leave_on_holiday_1");
  By ALLOW_TIME_OFF_ON_HOLIDAY_EMPLOYEE_LEVEL = By.xpath("//select[@name='allow_leave_on_holiday_dropdown']");
  By NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_YES_BUTTON = By.id("allow_holiday_worked_paid_0");
  By NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_NO_BUTTON = By.id("allow_holiday_worked_paid_1");
  By NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name='allow_holiday_worked_paid_dropdown']");
  By NON_REG_PAY_CODE_SELECTION = By.xpath("//select[@name='holiday_worked_paid_pay_code']");
  By INCLUDE_EXEMPT_EMPLOYEES_YES_BUTTON = By.id("allow_holiday_worked_paid_exempt_0");
  By INCLUDE_EXEMPT_EMPLOYEES_NO_BUTTON = By.id("allow_holiday_worked_paid_exempt_1");
  By HOLIDAY_SPECIFIC_PERCENTAGE_LIMITS_YES_BUTTON = By.id("allow_holiday_limits_0");
  By HOLIDAY_SPECIFIC_PERCENTAGE_LIMITS_NO_BUTTON = By.id("allow_holiday_limits_1");
  By MAXIMUM_HOLIDAYS_PAY_HOURS_TEXT_FIELD = By.name("max_holiday_pay_hours");
  By EXCLUDE_COMPANY_HOLIDAYS_YES_BUTTON = By.id("exclude_from_holiday_0");
  By EXCLUDE_COMPANY_HOLIDAYS_NO_BUTTON = By.id("exclude_from_holiday_1");
  By EXCLUDE_COMPANY_HOLIDAYS_TABLE = By.xpath("//table/tbody[contains(@id,'excludeFromHolidayTable')]");
  By ADD_ROW_FOR_THE_EXCLUDE_COMPANY_HOLIDAYS_TABLE = By.xpath(
      "//a[@onclick='addExcludeFromHolidayRow();return false;']");
  By ROWS_EXCLUDE_COMPANY_HOLIDAYS_TABLE = By.xpath("//table/tbody[contains(@id,'excludeFromHolidayTable')]/tr");
  By ALLOW_EMPLOYEE_SETTINGS_OVERRIDE_YES_BUTTON = By.id("excl_from_holiday_employee_override_0");
  By ALLOW_EMPLOYEE_SETTINGS_OVERRIDE_NO_BUTTON = By.id("excl_from_holiday_employee_override_1");
  By COMPANY_OBSERVE_HOLIDAY_SHOW_AUTO_PAY_YES_BUTTON = By.id("excl_from_holiday_holiday_level_override_0");
  By COMPANY_OBSERVE_HOLIDAY_SHOW_AUTO_PAY_NO_BUTTON = By.id("excl_from_holiday_holiday_level_override_1");
  By ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_YES_BUTTON = By.id("nondaily_hours_holiday_0");
  By ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_NO_BUTTON = By.id("nondaily_hours_holiday_1");
  By ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name='nondaily_hours_holiday_dropdown']");
  By OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE = By.xpath(
      "//table/tbody[contains(@id,'overrideDailyHolidayHoursTable')]");
  By ROWS_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE = By.xpath(
      "//table/tbody[contains(@id,'overrideDailyHolidayHoursTable')]/tr");
  By ROWS_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE_EMPLOYEE_GROUPS = By.xpath(
      "//select[@name='override_daily_holiday_hours_group_code_1']");
  By ADD_ROW_FOR_THE_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE = By.xpath(
      "//a[@onclick='addOverrideAlternativeHolidayHoursRow();return false;']");
  By ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_YES_BUTTON = By.id("nondaily_hours_timeoffrequest_0");
  By ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_NO_BUTTON = By.id("nondaily_hours_timeoffrequest_1");
  By ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name='nondaily_hours_timeoffrequest_dropdown']");
  By OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE = By.xpath(
      "//table/tbody[contains(@id,'overrideDailyTimeOffHoursTable')]");
  By OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE_ROWS = By.xpath(
      "//table/tbody[contains(@id,'overrideDailyTimeOffHoursTable')]/tr");
  By ADD_ROW_FOR_THE_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE = By.xpath(
      "//a[@onclick='addOverrideAlternativeTimeOffHoursRow();return false;']");
  By HIDE_REMAINING_HOURS_FROM_ACCURED_BALANCE_YES_BUTTON = By.id("hide_remaining_accured_balance_0");
  By HIDE_REMAINING_HOURS_FROM_ACCURED_BALANCE_NO_BUTTON = By.id("hide_remaining_accured_balance_1");
  String EXCLUDE_FROM_HOLIDAY_SELECTION_XPATH = "//select[@name='exclude_from_holiday_";
  String EXCLUDE_FROM_HOLIDAY_EMPLOYEE_GROUP_SELECTION_XPATH = "//select[@name='exclude_from_holiday_group_code_";
  String OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_EMPLOYEE_GROUP_SELECTION_XPATH =
      "//select[@name='override_daily_holiday_hours_group_code_";
  String OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_DEFAULT_HOURS_TEXT_FIELD_XPATH =
      "//input[@name='override_daily_holiday_hours_";
  String OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_EMPLOYEE_GROUP_SELECTION_XPATH =
      "//select[@name='override_daily_time_off_hours_group_code_";
  String OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_DEFAULT_HOURS_TEXT_FIELD_XPATH =
      "//input[@name='override_daily_time_off_hours_";

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

  /**
   * Method to get a web element by driver, relative xpath and row number.
   *
   * @param driver
   * @param relativeXPathTillRowNumber
   * @param rowNumber
   * @return
   */
  static WebElement getWebElementByRelativeXPath(WebDriver driver, String relativeXPathTillRowNumber, int rowNumber) {
    return SeleniumWrapper.findElement(driver, By.xpath(relativeXPathTillRowNumber + rowNumber + "']"));
  }
}