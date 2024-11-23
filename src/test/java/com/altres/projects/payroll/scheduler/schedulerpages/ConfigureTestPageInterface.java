package com.altres.projects.payroll.scheduler.schedulerpages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.altres.util.SeleniumWrapper;

/**
 * Interface to contain all locators and reusable methods for scheduler configure screen.
 */
public interface ConfigureTestPageInterface {

  By SCHEDULER_TABLE = By.xpath("//form[@onsubmit='return validateCustomerSettings(this)']//table");
  By SCHEDULER_CHECKBOXES = By.xpath("//input[@type='checkbox']");
  By SCHEDULER_DROPDOWNS = By.xpath("//tbody/tr/td/select");

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
