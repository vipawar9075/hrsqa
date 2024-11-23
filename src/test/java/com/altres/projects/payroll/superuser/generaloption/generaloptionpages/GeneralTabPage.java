package com.altres.projects.payroll.superuser.generaloption.generaloptionpages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

/**
 * Class to contain all business logic methods for general tab.
 */
public class GeneralTabPage extends Base implements GeneralTabPageInterface {

  /**
   * Clicks the general option save button.
   */
  public static void clickGeneralOptionsSaveButton() {
    SeleniumWrapper.clickOnElement(SAVE_BUTTON);
  }

  /**
   * Clicks on any radio button by its heading, value passing into parameter.
   *
   * @param heading
   * @param value
   */
  public static void clickGeneralOptionsRadioButton(String heading, String value) {
    By generalRadioButton = By.xpath("//div[contains(text(),'" + heading + "')]//input[@value='" + value + "']");
    SeleniumWrapper.clickOnElement(generalRadioButton);
  }

  /**
   * Is Selected general option radio button by its heading, Value passing into parameter.
   *
   * @param heading
   * @param value
   * @return
   */
  public static boolean isSelectedGeneralOptionRadioButton(String heading, String value) {
    By generalRadioButton = By.xpath("//div[contains(text(),'" + heading + "')]//input[@value='" + value + "']");
    try {
      return driver.findElement(generalRadioButton).isSelected();
    } catch (StaleElementReferenceException e) {
      return driver.findElement(generalRadioButton).isSelected();
    }
  }

  /**
   * Method to return the employee level drop-down first selected option text for the display pay rate setting.
   *
   * @return
   */
  public static String getFirstSelectedOptionText() {
    return SeleniumWrapper.getCurrentSelectedOptionText(DISPLAY_PAY_RATE_EMPLOYEE_LEVEL);
  }

  /**
   * Method to return the employee level drop-down get all options size for the Display Pay Rate setting.
   *
   * @return
   */
  public static int getEmployeeLevelOptionsSize() {
    return SeleniumWrapper.getDropdownOptionsSize(DISPLAY_PAY_RATE_EMPLOYEE_LEVEL);
  }

  /**
   * Selects EmployeeLevelOption drop-down base on the value provided.
   *
   * @param value
   */
  public static void selectEmployeeLevelOption(int value) {
    SeleniumWrapper.selectByIndex(DISPLAY_PAY_RATE_EMPLOYEE_LEVEL_OPTION, value);
  }

  /**
   * Enters the name in the name field.
   *
   * @return
   */
  public static WebElement enterText() {
    WebElement text = driver.findElement(TEXT_AREA);
    text.click();
    return text;
  }

  /**
   * Selects Davison return batch status drop-down base on the value provided.
   *
   * @param value
   */
  public static void selectDavisonReturnBatchStatusOption(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(DAVISON_RETURN_BATCH_STATUS, value);
  }

  /**
   * Method to return the Davison return batch status drop-down first selected option text for the Davison return batch
   * status setting.
   *
   * @return
   */
  public static String getFirstSelectedOptionTextOfDavisonReturnBatch() {
    return SeleniumWrapper.getFirstSelectedOptionText(DAVISON_RETURN_BATCH_STATUS);
  }

  /**
   * Enters Total Timesheet in the field.
   *
   * @return
   */
  public static WebElement enterTimesheet() {
    WebElement text = driver.findElement(TOTAL_TIMESHEET);
    text.click();
    return text;
  }

  /**
   * Method to return the text field for the timesheet-uneditable pay period setting.
   *
   * @return
   */
  public static WebElement getTimesheetUnEditablePayPeriods() {
    WebElement text = driver.findElement(TIMESHEET_UNEDITABLE_PAY_PERIOD);
    text.click();
    return text;
  }

  /**
   * Selects Admin type drop-down base on the value provided.
   *
   * @param value
   */
  public static void getAdminTypeSelection(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(ADMIN_TYPE_SELECTION_DROPDOWN, value);
  }


  /**
   * Method to return the Admin type drop-down first selected option text.
   *
   * @return
   */
  public static String getFirstSelectedOptionTextOfAdminType() {
    return SeleniumWrapper.getFirstSelectedOptionText(ADMIN_TYPE_SELECTION_DROPDOWN);
  }

  /**
   * Method to return the admin type drop-down link for the display pay rate setting.
   *
   * @param driver
   * @return
   */
  public static boolean isAdminPayrollNotificationDisplayed(WebDriver driver) {
    return driver.findElement(ADMIN_PAYROLL_NOTIFICATION).isDisplayed();
  }

  /**
   * Method to return the employee level drop-down first selected option text for the allow timesheet attachments.
   * setting.
   *
   * @return
   */
  public static String getFirstSelectedOptionTextOfAllowTimesheetAttachments() {
    return SeleniumWrapper.getFirstSelectedOptionText(ALLOW_TIMESHEET_ATTACHMENTS);
  }

  /**
   * Method to return the employee level drop-down get all options size for the allow timesheet attachments setting.
   *
   * @return
   */
  public static int getAllowTimesheetAttachmentsEmployeeLevelOptionsSize() {
    return SeleniumWrapper.getDropdownOptionsSize(ALLOW_TIMESHEET_ATTACHMENTS);
  }

  /**
   * Selects EmployeeLevelOption drop-down base on the value provided for the allow timesheet attachments setting.
   *
   * @param value
   */
  public static void selectEmployeeLevelOptionOfAllowTimesheetAttachments(int value) {
    SeleniumWrapper.selectByIndex(ALLOW_TIMESHEET_ATTACHMENTS_OPTIONS, value);
  }
}
