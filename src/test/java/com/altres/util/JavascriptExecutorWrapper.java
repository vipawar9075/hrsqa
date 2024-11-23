package com.altres.util;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Utility class for JavascriptExecutor methods.
 */
public class JavascriptExecutorWrapper {

  /**
   * Scrolls to bring the specified element into view.
   *
   * @param driver
   * @param element
   */
  public static void scrollToElement(WebDriver driver, WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].scrollIntoView();", element);
  }

  /**
   * Scrolls to the bottom.
   *
   * @param driver
   */
  public static void scrollToBottom(WebDriver driver) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    WebElement bottomElement = driver.findElement(By.cssSelector("body > *:last-child"));
    js.executeScript("arguments[0].scrollIntoView(false);", bottomElement);
  }

  /**
   * Clicks on WebElement using JavascriptExecutor.
   *
   * @param driver
   * @param element
   */
  public static void clickOnElement(WebDriver driver, WebElement element) {
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", element);
  }
}


