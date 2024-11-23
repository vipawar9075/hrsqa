package com.altres.projects.quickhire.quickhirepage;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.util.ConfigurationManager;
import com.altres.util.FakeDataGenerator;
import com.altres.util.SeleniumWrapper;


public class QuickHireListingPage extends Base {

  private static final By CUSTOMER_NUMBER_TEXT_FIELD = By.name(
      "QuickhireCustomerListControllercustomer_number");
  private static final By RESULTS = By.xpath(
      "//*[@id='customerLayout']/div[contains(., 'results found')]");
  private static final By DROPDOWN_LIST = By.xpath(
      "//select[@name='QuickhireNewHireListControllerstatus']/option");
  private static final By ENTER_COMPANY_NAME = By.xpath(
      "//input[@name='QuickhireCustomerListControllercustomer']");
  private static final By SELECT_FIRST_ROW = By.xpath(
      "//*[@id='QuickhireCustomerListController']/tbody/tr");

  /**
   * Checks if a specific message is displayed.
   *
   * @return
   */
  public static String getMessageText() {
    try {
      WebElement title = SeleniumWrapper.findElement(driver, RESULTS);
      return title.getText();
    } catch (NoSuchElementException e) {
      return null;
    }
  }

  /**
   * Sets a random customer number.
   */
  public static void setCustomerNumber() {
    SeleniumWrapper.enterText(CUSTOMER_NUMBER_TEXT_FIELD, FakeDataGenerator.getRandomNumber(5));
  }

  /**
   * Sets the company name.
   */
  public static void setCompanyName() throws ConfigurationNotLoadedException {
    String companyName = ConfigurationManager.getProperty("company_name");
    SeleniumWrapper.enterText(ENTER_COMPANY_NAME, companyName);
  }

  /**
   * Fill text within textboxes in QH details.
   *
   * @param field
   * @param value
   */
  public static void setValueInTextboxesOnQHDetails(String field, String value) {
    By element = By.xpath("//input[@name='" + field + "']");
    SeleniumWrapper.enterText(element, value);
  }

  /**
   * Clicks on the customer name in the list.
   */
  public static void clickOnCustomerName() {
    SeleniumWrapper.clickOnElement(SELECT_FIRST_ROW);
  }

  /**
   * Retrieves the options from a dropdown menu.
   *
   * @return
   */
  public static List<String> getDropDownOptions() {
    List<WebElement> options = SeleniumWrapper.findElements(driver, DROPDOWN_LIST);
    List<String> optionText = new ArrayList<>();
    for (WebElement option : options) {
      String text = option.getText().trim();
      if (!text.isEmpty()) {
        optionText.add(text);
      }
    }
    return optionText;
  }
}
