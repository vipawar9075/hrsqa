package com.altres.projects.payroll.superuser.generaloption.payrollutil;

import org.openqa.selenium.By;

/**
 * Constants class holds constant values used throughout the application.
 */
public class PayrollConstants {

  public static final By SETTINGS_MENU_FIELD = By.xpath("//a[contains(text(),'Settings')]");
  public static final By GENERAL_OPTION_MENU_FIELD = By.xpath("//a[contains(text(),'General Options')]");
  public static final By SAVE_BUTTON = By.xpath("//input[@type='submit']");
  public static final By GENERAL_OPTIONS_SUCCESS_MESSAGE = By.xpath(
      "//div[contains(text(),'The general options have been successfully saved.')]");
  public static final By SUCCESS_NOTICE = By.xpath("//div[@id='notices']/div[@class='successNotice']");
  public static final By ERROR_NOTICE = By.xpath("//div[@id='notices']/div[@class='errorNotice']");
  public static final By GENERAL_TAB = By.xpath("//a[@id='generalButton']");
  public static final By PAYROLL_MENU = By.xpath("//div[@class='primary']//a[contains(text(), 'Payroll')]");
  public static final By TIMESHEET_APPROVAL_MENU = By.xpath(
      "//ul[@id='payrollCol2']//a[contains(text(), 'Timesheet Approval')]");
  public static final By EMPLOYEE_SETTING_MENU = By.xpath("//a[contains(text(), '  Employees')]");
  public static final By TIMESHEET_SAVE_BUTTON = By.xpath("//input[@id='saveTimesheet']");
  public static final By VERTICAL_TIMESHEET_HEADERS = By.xpath("//table[@id='verticalTimesheetTable']//tr//th");
  public static final By EMPLOYEE_FIRST_ROW = By.xpath("//table[@class='grid']//tbody//tr");
  public static final By HORIZONTAL_TIMESHEET_HEADERS = By.xpath(
      "//table[@id='addHoursTable']//table[1]//table//td[1]");
  public static final By TIMESHEET_DATE_ROW_FIELD = By.xpath("//table[@id='timesheetDaysTable']//tbody//tr//td[1]");
  public static final By VERTICAL_TIMESHEET_EMPLOYEE_SETTING_RADIO_BUTTON_NO = By.xpath(
      "//input[@id='vertical_timesheet_1']");
  public static final By VERTICAL_TIMESHEET_EMPLOYEE_SETTING_RADIO_BUTTON_YES = By.xpath(
      "//input[@id='vertical_timesheet_0']");
  public static final By EMPLOYEES_MENU_EMPLOYEE_LOOKUP = By.xpath("//input[@id='payrollEmployeeLookup']");
  public static final By TIMESHEET_FIRST_ROW_EMPLOYEE_NAME = By.xpath("//td[@class='nameColumn']");
  public static final By EMPLOYEE_TIMESHEET_FIRST_ROW = By.xpath("//table[@class='grid tight']//tbody//tr[1]");
  public static final By ADD_NEW_LINK = By.xpath("//a[contains(text(), '+ Add New')]");
  public static final By ADD_ROW_LINK = By.xpath("//a[contains(text(), '+ Add Row')]");
  public static final By RETURN_BUTTON = By.xpath("//input[@type='button' and contains(@value, 'Return to')]");
  public static final By COPY_BUTTON = By.xpath("//input[@value='Copy']");
  public static final By DELETE_BUTTON = By.xpath("//input[@value='Delete']");
  public static final By FILTER_BUTTON = By.xpath("//input[@value='Filter']");
  public static final By RESET_BUTTON = By.xpath("//input[@value='Reset']");
  public static final By JOB_COSTING_LABOR_ALLOCATION_TAB = By.id("jobCostingLaborAllocationButton");
  public static final By OTHER_PAY_TAB = By.xpath("//a[@id='otherPayButton']");
  public static final By OVERTIME_TAB = By.xpath("//a[@id='overtimeButton']");
  public static final By TIME_ENTRY_TAB = By.xpath("//a[@id='timeEntryButton']");
  public static final By TIME_OFF_TAB = By.xpath("//a[@id='timeOffButton']");
  public static final By EXPAND_TAB_BUTTON = By.id("Expand Tabs");
  public static final By USES_SUPERVISOR_YES_BUTTON = By.id("uses_supervisors_0");
  public static final By TIME_ENTRY_NON_SELECTION = By.xpath("//select[@name='time_entry_method_non_exempt']");
  public static final By TIME_ENTRY_EXEMPT = By.xpath("//select[@name='time_entry_method_exempt']");
  public static final By OVERTIME_PAY_CODE = By.xpath("//select[@name='overtime_code']");
  public static final By COMPUTE_OVERTIME_YES_BUTTON = By.id("compute_overtime_hours_0");
  public static final By OVERTIME_METHOD_SELECTOR = By.xpath("//select[@name='overtime_compute_method']");
  public static final By TIME_ENTRY_METHOD = By.xpath("//select[@name='time_entry_method']");
  public static final By MOBILE_TIP_PAY_CODES_AVAILABLE = By.xpath("//select[@name='mobile_tip_pay_codes_available']");
  public static final By ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON = By.id("allow_mobile_access_0");
  public static final By ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN = By.name("allow_mobile_tip_recording");
  public static final By EMPLOYEE_NUMBER = By.xpath(
      "//form[@name='etimeSetupEmployeeSettings']//input[@name='employee_number']");
  public static final By EMPLOYEES_LIST = By.xpath("//form[@name='etimeSetupEmployeeSettings']");
  public static final By FIRST_EMPLOYEE_FROM_EMPLOYEES_LIST = By.xpath("//tbody/tr[1]/td[1]");
  public static final By ALLOW_SMS_ACCESS_YES_BUTTON = By.id("allow_sms_access_0");
  public static final By SCHEDULER_TAB = By.xpath("//a[@id='schedulerButton']");
  public static final By SCHEDULER_CONFIGURE_MENU_FIELD = By.xpath(
      "//a[@href='#' and contains(@onclick, '/route/scheduler/settings/customerAdmin')]");
  public static final By ENABLE_SCHEDULER_YES_BUTTON = By.id("enabled1");
  public static final By ALLOW_EMPLOYEE_LEVEL_TIME_ENTRY_METHOD_OVERRIDE_YES_BUTTON = By.id(
      "allow_time_entry_method_override_0");
  public static final By ALLOW_PERCENTAGE_ALLOCATION_NO_BUTTON = By.id("allow_employee_allocation_1");
  public static final By TIME_ENTRY_METHOD_EXEMPT_SELECTION = By.xpath("//select[@name='time_entry_method_exempt']");
  public static final By TIME_ENTRY_METHOD_NON_EXEMPT_SELECTION = By.xpath(
      "//select[@name='time_entry_method_non_exempt']");
}