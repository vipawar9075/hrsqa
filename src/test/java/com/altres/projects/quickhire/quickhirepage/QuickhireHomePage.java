package com.altres.projects.quickhire.quickhirepage;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;


public class QuickhireHomePage extends Base {

  private static final By USERNAME_FIELD = By.xpath("(//a[normalize-space()='QuickHire'])[1]");
  private static final By SERVICE_REQUEST_LABEL = By.xpath(
      "//div[@class='filter']/span[starts-with(text(),'Service " + "Request #')]");
  private static final By NHA_LABEL = By.xpath(" //div[@class='filter']/span[starts-with(text(),'NHA')]");
  private static final By ADD_NEW_HIRE_BUTTON = By.xpath(" //input[starts-with(@value,'Add New Hire')]");

  /**
   * Checks if an error message is displayed.
   *
   * @return
   */
  public static String getMessageText() {
    try {
      WebElement title = driver.findElement(By.id("admin-title"));
      return title.getText();
    } catch (NoSuchElementException e) {
      return null;
    }
  }

  /**
   * Checks if the "Add New" button is displayed.
   *
   * @return
   */
  public static boolean isAddNewButtonDisplayed() {
    return driver.findElement(ADD_NEW_HIRE_BUTTON).isDisplayed();
  }


  /**
   * Retrieves the label of the service request.
   *
   * @return
   */
  public static String getServiceRequestLabel() {
    return SeleniumWrapper.getElementText(SERVICE_REQUEST_LABEL);
  }

  /**
   * Retrieves the NHA label.
   *
   * @return
   */
  public static String getNhaLabel() {
    return SeleniumWrapper.getElementText(NHA_LABEL);
  }

  /**
   * Gets the locator for the username field.
   *
   * @return By locator for the username field.
   */
  public static By getUsernameFieldLocator() {
    return USERNAME_FIELD;
  }

  /**
   * Verifies if the username field is displayed.
   */
  public static void verifyFieldDisplayed() {
    driver.findElement(getUsernameFieldLocator()).isDisplayed();
  }

  /**
   * Clicks on the "New Hire" button.
   */
  public static void clickNewHireButton() {
    SeleniumWrapper.clickOnElement(ADD_NEW_HIRE_BUTTON);
  }

  /**
   * Clicks on the Left Pane Menus in Altres admin home screen.
   *
   * @param name
   */
  public static void navigateToLeftPaneMenu(String name) {
    By element = By.xpath("//a[contains(text(),'" + name + "')]");
    SeleniumWrapper.clickOnElement(element);
  }
}
