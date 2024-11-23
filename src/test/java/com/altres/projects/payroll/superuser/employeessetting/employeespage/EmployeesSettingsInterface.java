package com.altres.projects.payroll.superuser.employeessetting.employeespage;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.altres.util.SeleniumWrapper;

public interface EmployeesSettingsInterface {

  By FIRST_TABLE_RADIO_BUTTONS = By.xpath("(//table)[1]//input[contains(@type,'radio')]");
  By MOBILE_TIP_CODES_SELECTED = By.name("mobile_tip_pay_codes");
  By MOBILE_TIP_CODES_ADD = By.name("mobile_tip_pay_codes_add");
  By EXCLUDE_FROM_HOLIDAY = By.xpath("//select[@name='exclude_from_holiday']");
  By PHONE_NUMBER_FIELD = By.name("phone_number");
  By NEW_PIN_FIELD = By.name("new_pin");
  By RETYPE_PIN_FIELD = By.name("retype_new_pin");
  By FIRST_TABLE_TEXT_FIELDS = By.xpath("(//table)[1]//input[contains(@type,'text')]");
  By NEXT_LINK = By.linkText("Next >");
  By PREVIOUS_LINK = By.linkText("< Previous");
  By VERTICAL_TIMESHEET_NO_BUTTON = By.id("vertical_timesheet_1");
  By LABOR_COST_CENTER_DEFAULT_ALL_ROWS = By.xpath(
      ".//tbody[(contains(@id,'defaultJobCostings'))]/tr[last() and not(contains(@style, 'display:none'))]");
  By LABOR_COST_CENTER_DEFAULT_TABLE = By.xpath("//*[@id='defaultJobCostings']");
  By EMPLOYEE_MULTIPLE_RATE_OVERRIDE_ROWS = By.xpath(
      ".//tbody[(contains(@id,'jobCostingPayRate'))]/tr[last() and not(contains(@style, 'display:none'))]");
  By EMPLOYEE_MULTIPLE_RATE_OVERRIDE_TABLE = By.xpath("//*[@id='jobCostingPayRates']");
  By LABOR_COST_CENTER_LIMITS_TABS = By.xpath(
      "//div[@class='tabs noPrint']/div[contains(@class, 'tab') or contains(@class, 'activeTab')]/a");
  By EMPLOYEE_MULTIPLE_RATE_OVERRIDE_ADD_ROW_LINK = By.xpath("//a[@onclick=\"addJobCostingRow(document."
      + "etimeSetupEmployeeSettings, 'relationships_template','jobCostingPayRates','jobCostingRates', true, false);"
      + "return false;\"]");
  By LABOR_COST_CENTER_DEFAULT_ADD_ROW_LINK = By.xpath(
      "//a[@onclick=\"addJobCostingRow(document.etimeSetupEmployeeSettings,"
          + "'template','defaultJobCostings','default',false, true);return false;\"]");
  By EMPLOYEE_MULTIPLE_RATE_OVERRIDE_RATE_TEXT_FIELD = By.xpath("//input[@name='jobCostingRates_rate_1']");
  By OTHER_PAY_FLAT_RATES_TEXT_FIELD = By.xpath("//table[@id='otherEarnings']//input[@class='textField']");
  By OTHER_PAY_FLAT_RATES_EFFECTIVE_DATE_FIELDS = By.xpath(
      "//table[@id='otherEarnings']//input[@class='textField longDateField']");
  By FIRST_DATE_FIELD_FOR_OTHER_PAY_FLAT_RATES_TABLE = By.xpath(
      "//table[@id='otherEarnings']/tbody[1]/tr[1]/td[4]/input");
  By FIRST_ROW_PAY_CODE_DESCRIPTION_FOR_OTHER_PAY_FLAT_RATES_TABLE = By.xpath(
      "//table[@id='otherEarnings']/tbody[1]/tr[1]/td[1]");
  By EMPLOYEES_COUNT_MESSAGE = By.xpath("//*[@id=\"contentWrapper\"]/div[3]/p");

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
