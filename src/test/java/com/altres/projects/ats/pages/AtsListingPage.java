package com.altres.projects.ats.pages;

import static com.altres.util.SeleniumWrapper.webDriverWaitForVisibiltyOfElement;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

public class AtsListingPage extends Base {

  private static final By ENTRIES_FOUND_XPATH = By.xpath(
      "//div[contains(@id,'contentWrapper')]//p[contains(text(), " +
          "'entries found.')]");
  private static final By FILTER_BUTTON_LOCATOR = By.xpath("//button[contains(text(), 'Filter')]");
  private static final By RESET_BUTTON = By.xpath("//button[@id='reset']");
  private static final By CREATE_QUESTION_TEMPLATE = By.xpath(
      "//a[normalize-space()='+ Add New Question Template']");
  private static final By TABLE_HEADER = By.xpath("//table/thead/tr/th");
  private static final By NAME_FIELD = By.id("name");


  /**
   * Gets the locator for the filter button.
   *
   * @return
   */
  public static By getFilterButtonLocator() {
    return FILTER_BUTTON_LOCATOR;
  }


  /**
   * Gets the locator for the reset button.
   *
   * @return
   */
  public static By getResetButtonLocator() {
    return RESET_BUTTON;
  }

  /**
   * Sets the template name in the name field.
   *
   * @param templateName
   */
  public static void setTemplateName(String templateName) {
    webDriverWaitForVisibiltyOfElement(NAME_FIELD);
    WebElement templateNameElement = SeleniumWrapper.findElement(driver, NAME_FIELD);
    templateNameElement.clear();
    templateNameElement.sendKeys(templateName);
  }

  /**
   * Gets the XPath locator for the entries found.
   *
   * @return
   */
  public static By getEntriesFoundXPath() {
    return ENTRIES_FOUND_XPATH;
  }

  /**
   * Clicks the add new question template button.
   */
  public static void clickAddNewQuestionTemp() {
    SeleniumWrapper.clickOnElement(CREATE_QUESTION_TEMPLATE);
  }

  /**
   * Gets the table header elements.
   *
   * @return
   */
  public static List<WebElement> getTableHeaderElements() {
    return driver.findElements(TABLE_HEADER);
  }

  /**
   * Clicks on ATS Template Record by its row number.
   *
   * @param rowNumber
   */
  public static void clickATSTemplateRecord(int rowNumber) {
    By atsTemplate = By.xpath("//tbody/tr[" + rowNumber + "]/td[1]");
    SeleniumWrapper.clickOnElement(atsTemplate);
  }

}




