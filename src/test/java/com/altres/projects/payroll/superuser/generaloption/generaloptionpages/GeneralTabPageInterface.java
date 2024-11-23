package com.altres.projects.payroll.superuser.generaloption.generaloptionpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.altres.util.SeleniumWrapper;

/**
 * Interface to contain all locators and reusable methods for general tab.
 */
public interface GeneralTabPageInterface {

  By ALLOW_EMAIL_TO_SUPERVISORS_FOR_OVERAGE_HOURS_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name" + "='allow_email_for_overage_minutes_dropdown']");
  By ALLOW_EXEMPT_PAY_WITHOUT_HOURS_ADJUSTMENT_EMPLOYEE_LEVEL = By.xpath(
      "//select[@name" + "='allow_exempt_pay_without_hours_adjustment_dropdown']");
  By ALLOW_CROSS_PAY_PERIOD_SHIFT_EMPLOYEE_LEVEL = By.xpath("//select[@name='allow_cross_pay_period_shift_dropdown']");
  By ALLOW_IN_AND_OUT_TIMES_FOR_LEAVE_CODES_THAT_DO_NOT_REQUIRE_APPROVAL_NO_BUTTON = By.id(
      "allow_vertical_leave_without_approval_in_out_times_1");
  By ALLOW_IN_AND_OUT_TIMES_FOR_LEAVE_CODES_THAT_DO_NOT_REQUIRE_APPROVAL_YES_BUTTON = By.id(
      "allow_vertical_leave_without_approval_in_out_times_0");
  By USE_VERTICAL_TIMESHEET_EMPLOYEE_LEVEL = By.xpath("//select[@name='vertical_timesheet_dropdown']");
  By ALSO_SEND_EMAIL_TO_EMPLOYEE_FOR_OVERAGE_HOURS_NO_BUTTON = By.id("allow_email_for_overage_minutes_to_employee_1");
  By EMAIL_ON_OVERAGE_HOURS = By.xpath("//input[@name='email_payroll_overage_minutes']");
  By ALSO_SEND_EMAIL_TO_EMPLOYEE_FOR_OVERAGE_HOURS_YES_BUTTON = By.id("allow_email_for_overage_minutes_to_employee_0");
  By SAVE_BUTTON = By.xpath("(//input[@type='submit'])[1]");
  By DISPLAY_PAY_RATE_EMPLOYEE_LEVEL = By.xpath("//select[@name='allow_pay_rate_override_dropdown']");
  By TEXT_AREA = By.xpath("//textarea[@name=\"auto_enable_new_hire_note\"]");
  By DAVISON_RETURN_BATCH_STATUS = By.xpath("//select[@name='davison_return_batch_status']");
  By TOTAL_TIMESHEET = By.xpath("//input[@name='number_of_future_timesheet']");
  By TIMESHEET_UNEDITABLE_PAY_PERIOD = By.xpath("//input[@name='timesheet_uneditable_" + "pay_period']");
  By ADMIN_TYPE_SELECTION_DROPDOWN = By.xpath(
      "//div[@id='generalTab']/div[10]/table//tbody/tr[1]/td[2]//select[@class='dropDown']");
  By ADMIN_PAYROLL_NOTIFICATION = By.xpath("//tbody/tr[1]/td[3]//input[@type='checkbox']");
  By ALLOW_TIMESHEET_ATTACHMENTS = By.xpath("//select[@name='allow_timesheet_attachments_dropdown']");
  By DISPLAY_PAY_RATE_EMPLOYEE_LEVEL_OPTION = By.xpath("//select[@name='allow_pay_rate_override_dropdown']/option");
  By ALLOW_TIMESHEET_ATTACHMENTS_OPTIONS = By.xpath("//select[@name='allow_timesheet_attachments_dropdown']/option");
  By USE_VERTICAL_TIMESHEET_YES_BUTTON = By.xpath("//input[@id='vertical_timesheet_0']");
  By USE_VERTICAL_TIMESHEET_NO_BUTTON = By.xpath("//input[@id='vertical_timesheet_1']");
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
