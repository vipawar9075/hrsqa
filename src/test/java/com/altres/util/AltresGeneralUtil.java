package com.altres.util;


import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;

/**
 * AltresGeneralUtil is a utility class that provides common methods which are independent of any specific project. This class
 * is designed to be used across multiple projects to improve the efficiency of test cases and facilitate reusability.
 */
public class AltresGeneralUtil extends Base {

  public static final By COPY_BUTTON = By.xpath("//*[@value='Copy']");
  private static final By PAGE_TITLE = By.xpath("//h1[@id='interiorContentTitle']");
  private static final By ERROR_NOTICES = By.className("errorNotice");
  private static final By SUCCESS_NOTICE = By.className("successNotice");
  public static final By DELETE_BUTTON = By.xpath("//*[@value='Delete']");
  public static final By FILTER_BUTTON = By.xpath("//*[@name='filter' or @value='Filter']");
  public static final By RESET_BUTTON = By.xpath("//*[@id='reset' or @name='reset' or @value='Reset']");
  private static final By SAVE_BUTTON = By.xpath("//*[@value='Save']");
  private static final By SUBMIT_BUTTON = By.xpath("//input[@value='Submit']");
  private static final By CONTINUE_BUTTON = By.xpath("//input[@value='Continue' and @type='submit']");

  /**
   * This method will click on the save button.
   */
  public static void clickOnSaveButton() {
    SeleniumWrapper.clickOnElement(SAVE_BUTTON);
  }

  /**
   * This method will click on the submit button.
   */
  public static void clickOnSubmitButton() {
    SeleniumWrapper.clickOnElement(SUBMIT_BUTTON);
  }

  /**
   * This method will click on the continue button.
   */
  public static void clickOnContinueButton() {
    SeleniumWrapper.clickOnElement(CONTINUE_BUTTON);
  }


  /**
   * This method will click on the copy button.
   */
  public static void clickOnCopyButton() {
    SeleniumWrapper.clickOnElement(COPY_BUTTON);
  }

  /**
   * This method will click on the delete button.
   */
  public static void clickOnDeleteButton() {
    SeleniumWrapper.clickOnElement(DELETE_BUTTON);
  }

  /**
   * This method will click on the filter button.
   */
  public static void clickOnFilterButton() {
    SeleniumWrapper.clickOnElement(FILTER_BUTTON);
  }

  /**
   * This method will click on the reset button.
   */
  public static void clickOnResetButton() {
    SeleniumWrapper.clickOnElement(RESET_BUTTON);
  }

  /**
   * Gets the displayed text of the page title.
   *
   * @return
   */
  public static String getPageTitle() {
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PAGE_TITLE);
    return SeleniumWrapper.findElement(driver, PAGE_TITLE).getText().trim();
  }

  /**
   * Retrieves the error message displayed on the page.
   *
   * @return
   */
  public static String getErrorMessage() {
    WebElement errorMessageElement = driver.findElement(ERROR_NOTICES);
    return errorMessageElement.findElement(By.tagName("div")).getText();
  }

  /**
   * Retrieves the success notice message from the page.
   *
   * @return
   */
  public static String getSuccessNotice() {
    WebElement successMessageElement = driver.findElement(SUCCESS_NOTICE);
    return successMessageElement.getText().trim();
  }

  /**
   * Checks if the success message is displayed.
   *
   * @return
   */
  public static boolean isSuccessMessageDisplayed() {
    try {
      WebElement successMessage = driver.findElement(SUCCESS_NOTICE);
      return successMessage.isDisplayed();
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  /**
   * Clicks on the buttons within Page.
   *
   * @param buttonName
   */
  public static void clickOnButton(String buttonName) {
    By element = By.xpath("//input[@value='" + buttonName + "']");
    SeleniumWrapper.clickOnElement(element);
  }


  /**
   * Retrieves the error messages displayed on the page.
   *
   * @return
   */
  public static List<String> getDisplayedErrorMessages() {
    List<WebElement> errorElements = driver.findElements(ERROR_NOTICES);
    return errorElements.stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  /**
   * Captures the text values from a list of WebElements.
   *
   * @param elements List of WebElements to capture text from.
   * @return List of text values.
   */
  public static List<String> captureFieldValues(List<WebElement> elements) {
    return elements.stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }
}
