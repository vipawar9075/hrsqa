package com.altres.projects.payroll.superuser.generaloption.generaloptionpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.altres.util.SeleniumWrapper;

public interface OverTimeTabPageInterface {

  By COMPUTE_WEEKLY_OVERTIME_YES_BUTTON = By.xpath("//input[@id='compute_overtime_0']");
  By COMPUTE_DAILY_OVERTIME_YES_BUTTON = By.xpath("//input[@id='allow_daily_overtime_0']");
  By COMPUTE_DOUBLE_OVERTIME_YES_BUTTON = By.id("allow_double_overtime_0");
  By COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_YES_BUTTON = By.id("allow_overtime_after_consecutive_days_0");
  By OVERTIME_METHOD_MESSAGE = By.xpath("//span[@id='overtimeMessage']");
  By COMPUTE_OVERTIME_YES_BUTTON = By.id("compute_overtime_hours_0");
  By OVERTIME_GRACE_MINUTES_TEXT_FIELD = By.xpath("//input[@name='overtime_grace_minutes']");
  By LOCK_TIMESHEET_EMPLOYEE_LEVEL = By.xpath("//select[@name='required_overtime_approval_lock_dropdown']");
  By COMPUTE_WEEKLY_OVERTIME_NO_BUTTON = By.xpath("//input[@id='compute_overtime_1']");
  By USES_SUPERVISORS = By.id("uses_supervisors_0");
  By LOCK_TIMESHEET_YES_BUTTON = By.id("required_overtime_approval_lock_0");
  By OVERTIME_REQUIRES_APPROVAL_EMPLOYEE_LEVEL = By.xpath("//select[@name='required_overtime_approval_dropdown']");
  By OVERTIME_REQUIRES_APPROVAL_NO_BUTTON = By.id("required_overtime_approval_1");
  By OVERTIME_REQUIRES_APPROVAL_YES_BUTTON = By.id("required_overtime_approval_0");
  By SHOW_OVERTIME_PROMPT_SELECTOR = By.xpath("(//select[@name='allow_overtime_prompt'])[1]");
  By MINIMUM_REST_HOURS_TEXT_FIELD = By.xpath("//input[@name='overtime_minimum_rest_minutes']");
  By SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name='allow_overtime_minimum_rest_minutes_dropdown']");
  By SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_NO_BUTTON = By.id("allow_overtime_minimum_rest_minutes_1");
  By SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_YES_BUTTON = By.id("allow_overtime_minimum_rest_minutes_0");
  By GET_COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_NO_BUTTON = By.id("allow_overtime_after_consecutive_days_1");
  By NUMBER_OF_CONSECUTIVE_DAYS_TEXT_FIELD = By.xpath("//input[@name='overtime_after_consecutive_days']");
  By COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name='allow_overtime_after_consecutive_days_dropdown']");
  By REQUIRE_CONSECUTIVE_HOURS_FOR_DAILY_DOUBLE_OVERTIME_NO_BUTTON = By.id("consecutive_daily_overtime_1");
  By REQUIRE_CONSECUTIVE_HOURS_FOR_DAILY_DOUBLE_OVERTIME_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name='consecutive_daily_overtime_dropdown']");
  By GROUP_CONSECUTIVE_HOURS_ON_OVERNIGHT_SHIFTS_YES_BUTTON = By.id("group_consecutive_hours_0");
  By REQUIRE_CONSECUTIVE_HOURS_FOR_DAILY_DOUBLE_OVERTIME_YES_BUTTON = By.id("consecutive_daily_overtime_0");
  By GROUP_CONSECUTIVE_HOURS_ON_OVERNIGHT_SHIFTS_NO_BUTTON = By.id("group_consecutive_hours_1");
  By COMPUTE_DOUBLE_OVERTIME_HOURS_NO_BUTTON = By.id("allow_double_overtime_1");
  By DOUBLE_OVERTIME_MINIMUM_DAILY_HOURS = By.xpath("//input[@name='double_overtime_daily_minutes']");
  By DOUBLE_OVERTIME_PAY_CODE = By.xpath("//select[@name='double_overtime_pay_code']");
  By DOUBLE_OVERTIME_HOURS_EMPLOYEE_LEVEL = By.xpath("//select[@name='allow_double_overtime_dropdown']");
  By DAILY_OVERTIME_JOB_HOURS_OVERRIDE_ROWS = By.xpath("//tbody[contains(@id,'dailyOvertimeJobHoursOverride')]/tr");
  By JOB_OVERTIME_OVERRIDE_ADD_ROW = By.xpath("//a[@onclick='addDailyOvertimeJobHoursOverride();return false;']");
  By DAILY_OVERTIME_HOURS = By.xpath("//input[@name='daily_overtime_hours']");
  By ALLOW_DAILY_OVERTIME_EMPLOYEE_LEVEL = By.xpath("//select[@name='allow_daily_overtime_dropdown']");
  By ALLOW_WEIGHTED_AVERAGE_OVERTIME_SEPARATE_PREMIUM_PAY_CODE = By.xpath(
      "//select[@name='weighted_average_overtime_separate_premium_pay_code']");
  By ALLOW_WEIGHTED_AVERAGE_OVERTIME_SEPARATE_PREMIUM_NO_BUTTON = By.xpath(
      "//input[@id='allow_weighted_average_overtime_separate_premium_1']");
  By ALLOW_WEIGHTED_AVERAGE_OVERTIME_RATE_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name='allow_weighted_average_overtime_dropdown']");
  By ALLOW_WEIGHTED_AVERAGE_OVERTIME_SEPARATE_PREMIUM_YES_BUTTON = By.xpath(
      "//input[@id='allow_weighted_average_overtime_separate_premium_0']");
  By ALLOW_WEIGHTED_AVERAGE_OVERTIME_RATE_NO_BUTTON = By.xpath("//input[@id='allow_weighted_average_overtime_1']");
  By ALLOW_WEIGHTED_AVERAGE_OVERTIME_RATE_YES_BUTTON = By.xpath("//input[@id='allow_weighted_average_overtime_0']");
  By COMPUTE_WEEKLY_OVERTIME_EMPLOYEE_LEVEL = By.xpath("//select[@name='compute_overtime_dropdown']");
  By CERTIFIED_PAYROLL_NO_BUTTON = By.id("certified_payroll_1");
  By JOB_OVERTIME_OVERRIDE = By.xpath("//*[@id='dailyOvertimeJobHoursOverride']");

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
