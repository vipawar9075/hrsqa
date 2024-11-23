package com.altres.exception;

import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import com.altres.util.SeleniumWrapper;

public class SeleniumExceptions {

  /**
   * This method will handle all exceptions from Selenium test cases.
   */
  public static void handleException(Throwable e, WebDriver driver) throws Exception {

    if (e instanceof AssertionError) {
      System.out.println("One of the assertions failed. Switching back to default content " + e.getMessage());
      SeleniumWrapper.switchToDefaultContent(driver);
      throw (AssertionError) e;
    } else if (e instanceof NoSuchElementException) {
      System.out.println("NoSuchElementException occurred: " + e.getMessage());
      SeleniumWrapper.switchToDefaultContent(driver);
      throw (NoSuchElementException) e;
    } else if (e instanceof TimeoutException) {
      System.out.println("TimeoutException occurred: " + e.getMessage());
      SeleniumWrapper.switchToDefaultContent(driver);
      throw (TimeoutException) e;
    } else if (e instanceof StaleElementReferenceException) {
      System.out.println("StaleElementReferenceException occurred: " + e.getMessage());
      SeleniumWrapper.switchToDefaultContent(driver);
      throw (StaleElementReferenceException) e;
    } else if (e instanceof ElementNotVisibleException) {
      System.out.println("ElementNotVisibleException occurred: " + e.getMessage());
      SeleniumWrapper.switchToDefaultContent(driver);
      throw (ElementNotVisibleException) e;
    } else if (e instanceof ElementNotInteractableException) {
      System.out.println("ElementNotInteractableException occurred: " + e.getMessage());
      SeleniumWrapper.switchToDefaultContent(driver);
      throw (ElementNotInteractableException) e;
    } else if (e instanceof WebDriverException) {
      System.out.println("WebDriverException occurred. Switching back to default content " + e.getMessage());
      SeleniumWrapper.switchToDefaultContent(driver);
      throw (WebDriverException) e;
    } else if (e instanceof NullPointerException) {
      System.out.println("The element is null. Switching back to default content.");
      SeleniumWrapper.switchToDefaultContent(driver);
      throw (NullPointerException) e;
    } else {
      System.out.println("An unexpected exception occurred: " + e.getMessage());
      SeleniumWrapper.switchToDefaultContent(driver);
      throw (Exception) e;
    }
  }
}