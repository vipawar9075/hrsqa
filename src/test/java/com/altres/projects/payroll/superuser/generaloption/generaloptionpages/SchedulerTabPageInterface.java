package com.altres.projects.payroll.superuser.generaloption.generaloptionpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.altres.util.SeleniumWrapper;

/**
 * Interface to contain all locators and reusable methods for scheduler tab.
 */
public interface SchedulerTabPageInterface {

  By SCHEDULE_BEFORE_START_YES_BUTTON = By.id("scheduled_before_start_0");
  By SCHEDULE_BEFORE_START_NO_BUTTON = By.id("scheduled_before_start_1");
  By SCHEDULE_BEFORE_START_BUFFER_MINUTES_TEXT_FIELD = By.name("scheduled_before_start_buffer");
  By SCHEDULE_AFTER_START_YES_BUTTON = By.id("scheduled_after_start_0");
  By SCHEDULE_AFTER_START_NO_BUTTON = By.id("scheduled_after_start_1");
  By SCHEDULE_AFTER_START_BUFFER_MINUTES_TEXT_FIELD = By.name("scheduled_after_start_buffer");
  By SCHEDULE_BEFORE_END_YES_BUTTON = By.id("scheduled_before_end_0");
  By SCHEDULE_BEFORE_END_NO_BUTTON = By.id("scheduled_before_end_1");
  By SCHEDULE_BEFORE_END_BUFFER_MINUTES_TEXT_FIELD = By.name("scheduled_before_end_buffer");
  By SCHEDULE_AFTER_END_YES_BUTTON = By.id("scheduled_after_end_0");
  By SCHEDULE_AFTER_END_NO_BUTTON = By.id("scheduled_after_end_1");
  By SCHEDULE_AFTER_END_BUFFER_MINUTES_TEXT_FIELD = By.name("scheduled_after_end_buffer");
  By PAY_CODE_FOR_THE_SCHEDULE_TARDY_DROPDOWN_SELECTION = By.xpath("//select[@name='scheduled_tardy_paycode']");
  By BUFFER_FOR_THE_SCHEDULE_TARDY_TEXT_FIELD = By.xpath("//input[@name='scheduled_tardy_buffer']");

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
