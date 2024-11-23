package com.altres.projects.payroll.superuser.generaloption.generaloptionpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.altres.util.SeleniumWrapper;

/**
 * Interface to contain all locators and reusable methods for time entry tab.
 */
public interface TimeEntryTabPageInterface {

  By TIME_ENTRY_TAB = By.xpath("//a[@id='timeEntryButton']");
  By TIME_ENTRY_METHOD_NON_EXEMPT_DROPDOWN = By.xpath(
      "//select[@name='time_entry_method_non_exempt']");
  By TIME_ENTRY_METHOD_EXEMPT_DROPDOWN = By.xpath("//select[@name='time_entry_method_exempt']");
  By TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_TABLE = By.xpath(
      "//tbody[@id='timeEntryMethodByEmployeeGroupTable']");
  By TIME_ENTRY_METHOD_OVERRIDE_EMPLOYEE_LEVEL_DROPDOWN = By.xpath(
      "//select[@name='allow_time_entry_method_override_dropdown']");
  By TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_ROW = By.xpath(
      "//tbody[@id='timeEntryMethodByEmployeeGroupTable']/tr");
  By TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_ADD_ROW = By.xpath(
      "//a[@onclick='addTimeEntryMethodByEmployeeGroupRow();return false;']");
  By ERROR_NOTICE = By.xpath("//div[@id='notices']/div[@class='errorNotice']");
  By STRICT_CLOCKING = By.xpath("//select[@name='strict_clocking_select']");
  By ALLOW_MOBILE_PUNCH_FOR_STRICT_DROPDOWN = By.xpath(
      "//select[@name='allow_mobile_punch_for_strict_dropdown']");
  By CLOCK_OUT_ROUNDING_YES_BUTTON = By.id("clockout_rounding_0");
  By CLOCK_OUT_ROUNDING_NO_BUTTON = By.id("clockout_rounding_1");
  By CLOCK_OUT_ROUNDING_INCREMENT_SELECTION = By.xpath("//select[@name='clockout_rounding_increment']");
  By CLOCK_OUT_ROUNDING_POINT_TEXT_FIELD = By.id("clockout_rounding_point");
  By DATE_ENTRY_RESTRICTION_BY_SELECTION = By.xpath("//select[@name='date_entry_restriction_by']");
  By DATE_ENTRY_RESTRICTION_FOR_SELECTION = By.xpath("//select[@name='date_entry_restriction_for']");
  By ROUND_CLOCKED_OUT_TIME_TO_SCHEDULE_YES_BUTTON = By.id("round_to_schedule_end_0");
  By BEFORE_END_TIME_MINUTES = By.name("round_to_schedule_before_end");
  By AFTER_END_TIME_MINUTES = By.name("round_to_schedule_after_end");
  By HOW_TO_DISPLAY_IN_AND_OUT_TIME_AND_DAILY_TOTAL_HOURS_FIELD_ROUNDED_BUTTON = By.xpath(
      "//input[@name='display_rounded_time' and @value='Y']");
  By HOW_TO_DISPLAY_IN_AND_OUT_TIME_AND_DAILY_TOTAL_HOURS_FIELD_ACTUAL_BUTTON = By.xpath(
      "//input[@name='display_rounded_time' and @value='N']");
  By ROUND_CLOCKED_OUT_TIME_TO_SCHEDULE_NO_BUTTON = By.id("round_to_schedule_end_1");
  By DATE_ENTRY_RESTRICTION_NON_EXEMPT_YES_BUTTON = By.id("date_entry_restriction_non_exempt_0");
  By DATE_ENTRY_RESTRICTION_NON_EXEMPT_NO_BUTTON = By.id("date_entry_restriction_non_exempt_1");
  By DATE_ENTRY_RESTRICTION_EXEMPT_YES_BUTTON = By.id("date_entry_restriction_exempt_0");
  By DATE_ENTRY_RESTRICTION_EXEMPT_NO_BUTTON = By.id("date_entry_restriction_exempt_1");
  By DATE_ENTRY_RESTRICTION_BUFFER_TEXT_FIELD = By.name("date_entry_restriction_buffer");
  By ALLOW_TIMESHEET_FILL_ALL_YES_BUTTON = By.id("allow_timesheet_fill_all_0");
  By ALLOW_TIMESHEET_FILL_ALL_NO_BUTTON = By.id("allow_timesheet_fill_all_1");
  By ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_YES_BUTTON = By.id("allow_salary_non_exempt_0");
  By ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_NO_BUTTON = By.id("allow_salary_non_exempt_1");
  By ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "//select[@name='allow_salary_non_exempt_dropdown']");
  By REMOVE_ADDITIONAL_REGULAR_HOURS_YES_BUTTON = By.id("allow_salary_non_exempt_remove_addl_reg_0");
  By REMOVE_ADDITIONAL_REGULAR_HOURS_NO_BUTTON = By.id("allow_salary_non_exempt_remove_addl_reg_0");
  By REMOVE_ADDITIONAL_REGULAR_HOURS_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "//select[@name='allow_salary_non_exempt_remove_addl_reg_dropdown']");
  By ALLOW_EMPLOYEE_AUTO_PAY_YES_BUTTON = By.id("allow_nonexempt_auto_default_pay_0");
  By ALLOW_EMPLOYEE_AUTO_PAY_NO_BUTTON = By.id("allow_nonexempt_auto_default_pay_1");
  By ALLOW_EMPLOYEE_AUTO_PAY_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "//select[@name='allow_nonexempt_auto_default_pay_dropdown']");
  By USES_TIP_CREDIT_YES_BUTTON = By.id("uses_tip_credit_0");
  By USES_TIP_CREDIT_NO_BUTTON = By.id("uses_tip_credit_1");
  By ALLOW_EMPLOYEE_LEVEL_TIME_ENTRY_METHOD_OVERRIDE_YES_BUTTON = By.id("allow_time_entry_method_override_0");
  By ALLOW_EMPLOYEE_LEVEL_TIME_ENTRY_METHOD_OVERRIDE_NO_BUTTON = By.id("allow_time_entry_method_override_1");
  By ALLOW_AUTOMATIC_BREAK_MINUTES_YES_BUTTON = By.id("allow_automatic_break_0");
  By ALLOW_AUTOMATIC_BREAK_MINUTES_NO_BUTTON = By.id("allow_automatic_break_1");
  By ALLOW_AUTOMATIC_BREAK_MINUTES_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "//select[@name='allow_automatic_break_dropdown']");
  By MINUTES_WORKED_BEFORE_BREAK_IS_AUTOMATICALLY_ENTERED_TEXT_FIELD = By.name("automatic_break_minimum_work_minutes");
  By BREAK_MINUTES_OVERRIDE_TABLE = By.xpath("//tbody[@id='breakMinutesJobHoursOverride']");
  By BREAK_MINUTES_OVERRIDE_TABLE_ROWS = By.xpath("//tbody[@id='breakMinutesJobHoursOverride']/tr");
  By BREAK_MINUTES_OVERRIDE_TABLE_ROW_GROUPS = By.xpath("//*[@id='breakMinutesJobHoursOverride']/tr[1]//select");
  By BREAK_MINUTES_OVERRIDE_TABLE_ADD_ROW = By.xpath("//a[@onclick='addBreakMinutesJobHoursOverride();return false;']");
  By ALLOW_TIME_ENTRY_WIDGET_YES_BUTTON = By.id("allow_etime_widget_configuration_0");
  By ALLOW_TIME_ENTRY_WIDGET_NO_BUTTON = By.id("allow_etime_widget_configuration_1");
  By ALLOW_TIME_ENTRY_WIDGET_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "//select[@name='allow_etime_widget_configuration_dropdown']");
  By ALLOW_GPS_YES_BUTTON = By.id("allow_gps_widget_0");
  By ALLOW_GPS_NO_BUTTON = By.id("allow_gps_widget_1");
  By ALLOW_GPS_EMPLOYEE_LEVEL_SELECTION = By.xpath("//select[@name='allow_gps_widget_dropdown']");
  By ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_YES_BUTTON = By.id("allow_exempt_auto_default_pay_0");
  By ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_NO_BUTTON = By.id("allow_exempt_auto_default_pay_1");
  By ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "(//select[@name='allow_exempt_auto_default_pay_dropdown'])");
  By ALLOW_TELEPHONE_TIME_CLOCK_YES_BUTTON = By.id("allow_ivr_0");
  By ALLOW_TELEPHONE_TIME_CLOCK_NO_BUTTON = By.id("allow_ivr_1");
  By ALLOW_TELEPHONE_TIME_CLOCK_EMPLOYEE_LEVEL_SELECTION = By.xpath("(//select[@name='allow_ivr_dropdown'])");
  By AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE = By.xpath("//tbody[@id='autoPayAmount']");
  By AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ROWS = By.xpath("//tbody[@id='autoPayAmount']/tr");
  By AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ADD_ROW = By.xpath("//a[@onclick='addAutoPayAmount();return false;']");
  By EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE = By.xpath("//tbody[@id='excludeCostingsFromTimesheetTotals']");
  By EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE_ROWS = By.xpath(
      "//tbody[@id='excludeCostingsFromTimesheetTotals']/tr");
  By EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_ADD_ROW = By.xpath(
      "//a[@onclick='addExcludeCostingsFromTimesheetTotal();return false;']");
  By CERTIFIED_PAYROLL_DROPDOWN = By.xpath("//select[@name='certified_payroll_dropdown']");
  By CERTIFIED_DETAILED_ENTRY_DROPDOWN = By.xpath("//select[@name" + "='certified_detailed_entry_dropdown']");
  By MOBILE_TIP_PAY_CODE = By.xpath("//div[@id='mobile_tip_pay_code']");
  By ALLOW_IGNORE_TIMESHEET_HOURS_DROPDOWN = By.xpath("//select[@name" + "='allow_ignore_timesheet_hours_dropdown']");
  By AUTO_POPULATE_MOBILE_DROPDOWN = By.xpath("//select[@name='auto_populate_mobile_dropdown']");
  By ALLOW_SMS_ACCESS_DROPDOWN = By.xpath("//select[@name='allow_sms_access_dropdown']");
  By MOBILE_DISABLE_FAVORITES_DROPDOWN = By.xpath("//select[@name" + "='mobile_disable_favorites_dropdown']");
  By MOBILE_DISABLE_FAVORITES_YES_BUTTON = By.id("mobile_disable_favorites_0");
  By MOBILE_DISABLE_FAVORITES_NO_BUTTON = By.id("mobile_disable_favorites_1");
  By ALLOW_SMS_ACCESS_NO_BUTTON = By.id("allow_sms_access_1");
  By AUTO_POPULATE_THE_EMPLOYEES_PHONE_NUMBER_AND_PIN_YES_BUTTON = By.id("auto_populate_mobile_0");
  By AUTO_POPULATE_THE_EMPLOYEES_PHONE_NUMBER_AND_PIN_NO_BUTTON = By.id("auto_populate_mobile_1");
  By ALLOW_IGNORE_TIMESHEET_HOURS_YES_BUTTON = By.id("allow_ignore_timesheet_hours_0");
  By ALLOW_IGNORE_TIMESHEET_HOURS_NO_BUTTON = By.id("allow_ignore_timesheet_hours_1");
  By COMPUTE_OVER_TIME_FOR_CERTIFIED_EMPLOYEES_NO_BUTTON = By.id("certified_detailed_entry_1");
  By COMPUTE_OVER_TIME_FOR_CERTIFIED_EMPLOYEES_YES_BUTTON = By.id("certified_detailed_entry_0");
  By ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_EMPLOYEE_LEVEL = By.name("allow_mobile_access_dropdown");
  By ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON = By.id("allow_mobile_access_1");
  By CERTIFIED_PAYROLL_YES_BUTTON = By.id("certified_payroll_0");
  By CERTIFIED_PAYROLL_NO_BUTTON = By.id("certified_payroll_1");
  By MAX_DAILY_HOURS_TABLE_ROWS = By.xpath("//tbody[@id='maxDailyHoursTable']/tr");
  By MAX_DAILY_HOURS_TABLE = By.xpath("//tbody[@id='maxDailyHoursTable']");
  By MAX_DAILY_HOURS_TABLE_ADD_ROW = By.xpath(
      "//a[@onclick" + "='addOverrideAlternativeMaxDailyHoursRow();return false;']");
  By DAILY_HOURS_TABLE_ADD_ROW = By.xpath("//a[@onclick='addOverrideAlternativeDailyHoursRow();return false;']");
  By DAILY_HOURS_TABLE_ROWS = By.xpath("//tbody[@id='dailyHoursTable']/tr");
  By DAILY_HOURS_TABLE = By.xpath("//tbody[@id='dailyHoursTable']");
  By AUTO_SET_CLOCK_NUMBER_EMPLOYEE_LEVEL_SELECTION = By.xpath("//select[@name" + "='auto_set_clock_number_dropdown']");
  By AUTO_SET_CLOCK_NUMBER_NO_BUTTON = By.id("auto_set_clock_number_1");
  By AUTO_SET_CLOCK_NUMBER_YES_BUTTON = By.id("auto_set_clock_number_0");
  By AVERO_POS_CHECK_BOX = By.id("allow_time_clock_A");
  By FACE_CLOCK_CHECK_BOX = By.id("allow_time_clock_U");
  By HAND_PRINT_CLOCK_CHECK_BOX = By.id("allow_time_clock_G");
  By FINGER_PRINT_CLOCK_CHECK_BOX = By.id("allow_time_clock_Y");
  By TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP = By.xpath("//div[@id='time_entry_method_by_employee_group']");
  By TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_HEADERS = By.xpath(
      "//div[@id='time_entry_method_by_employee_group']//table[@class='grid']//th");
  By MOBILE_TIME_CLOCK_HEADING = By.xpath(
      "//form[@name='etimeSetupEmployeeSettings']//b[contains(text(), 'Mobile Time Clock')]");
  By MOBILE_TIME_CLOCK_HEADING_FIELDS = By.xpath("//table[@class='grid formGrid noStripes']//label");
  By CLOCK_OUT_ROUNDING_EMPLOYEE_LEVEL_SELECTION = By.xpath("//select[@name='clockout_rounding_dropdown']");
  By AFTER_START_TIME_MINUTES = By.name("round_to_schedule_after_start");
  By ROUND_CLOCKED_IN_TIME_TO_SCHEDULE_NO_BUTTON = By.id("round_to_schedule_start_1");
  By BEFORE_START_TIME_MINUTES = By.name("round_to_schedule_before_start");
  By ROUND_CLOCKED_IN_TIME_TO_SCHEDULE_YES_BUTTON = By.id("round_to_schedule_start_0");
  By CLOCK_IN_ROUNDING_NO_BUTTON = By.id("clockin_rounding_1");
  By CLOCK_IN_ROUNDING_YES_BUTTON = By.id("clockin_rounding_0");
  By CLOCK_IN_ROUNDING_POINT_TEXT_FIELD = By.id("clockin_rounding_point");
  By CLOCK_IN_ROUNDING_INCREMENT_SELECTION = By.xpath("//select[@name='clockin_rounding_increment']");
  By CLOCK_IN_ROUNDING_EMPLOYEE_LEVEL_SELECTION = By.xpath("//select[@name='clockin_rounding_dropdown']");
  By ALLOW_EMPLOYEES_TO_SWITCH_COSTINGS_WHEN_CLOCKING_OUT_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "//select[@name" + "='allow_switch_costing_when_clocking_out_dropdown']");
  By REQUIRE_EMPLOYEES_TO_ENTER_NOTES_WHEN_CLOCKING_OUT_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "//select[@name" + "='require_note_on_punch_out_on_mobile_and_widget_dropdown']");
  By REQUIRE_SUPERVISOR_TO_ENTER_CLOCK_OUT_TIME_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "//select[@name='no_clock_in_without_clock_out_dropdown']");
  By STRICT_CLOCKING_SELECT = By.xpath("//select[@name='strict_clocking_select']");
  By MOBILE_TIP_PAY_CODES_ADD = By.xpath("//input[@name='mobile_tip_pay_codes_add']");
  By MOBILE_TIP_PAY_CODES_REMOVE_ALL = By.xpath("//input[@name='mobile_tip_pay_codes_remove_all']");

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