package com.altres.projects.payroll.superuser.generaloption.generaloptionpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.altres.util.SeleniumWrapper;

/**
 * Interface to contain all locators and reusable methods for other pay tab.
 */
public interface OtherPayTabPageInterface {

  By ALLOW_SEPARATE_CHECKS_YES_BUTTON = By.id("allow_separate_checks_0");
  By ALLOW_SEPARATE_CHECKS_NO_BUTTON = By.id("allow_separate_checks_1");
  By ALLOW_SEPARATE_CHECKS_EMPLOYEE_LEVEL_SELECTION = By.xpath("//select[@name='allow_separate_checks_dropdown']");
  By ALLOW_OTHER_PAY_WITH_HOURS_YES_BUTTON = By.id("allow_other_earnings_with_hours_0");
  By ALLOW_OTHER_PAY_WITH_HOURS_NO_BUTTON = By.id("allow_other_earnings_with_hours_1");
  By ALLOW_OTHER_PAY_WITH_HOURS_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "//select[@name='allow_other_earnings_with_hours_dropdown']");
  By RESTRICT_QUANTITY_TO_HOURS_YES_BUTTON = By.id("restrict_quantity_to_hours_0");
  By RESTRICT_QUANTITY_TO_HOURS_NO_BUTTON = By.id("restrict_quantity_to_hours_1");
  By RESTRICT_QUANTITY_TO_HOURS_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "//select[@name='restrict_quantity_to_hours_dropdown']");
  By ALLOW_OTHER_PAY_WITH_DATE_YES_BUTTON = By.id("allow_other_earnings_with_date_0");
  By ALLOW_OTHER_PAY_WITH_DATE_NO_BUTTON = By.id("allow_other_earnings_with_date_1");
  By ALLOW_OTHER_PAY_WITH_DATE_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "//select[@name='allow_other_earnings_with_date_dropdown']");
  By USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_YES_BUTTON = By.id("use_other_pay_date_0");
  By USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_EMPLOYEE_LEVEL_SELECTION = By.xpath(
      "//select[@name='use_other_pay_date_dropdown']");
  By ALL_CHECKBOXES_FROM_THE_OTHER_PAY_SUMMARY_UNGROUPING_TABLE = By.xpath(
      "//table[@class='grid']/tbody/tr/td/input[@type='checkbox']");

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