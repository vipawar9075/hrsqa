package com.altres.projects.payroll.superuser.generaloption.payrollutil;

import java.time.Duration;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

public class PayrollHelperUtil {

  /**
   * This method will click on the general options save button and verify if the success notice is present.
   *
   * @param driver
   */
  public static void clickSaveAndVerifySuccessNotice(WebDriver driver) {
    SeleniumWrapper.clickOnElement(PayrollConstants.SAVE_BUTTON);
    SeleniumWrapper.acceptAlertIfPresent(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(PayrollConstants.GENERAL_OPTIONS_SUCCESS_MESSAGE));
    WebElement successNotice = SeleniumWrapper.findElement(driver, PayrollConstants.GENERAL_OPTIONS_SUCCESS_MESSAGE);
    if (!successNotice.isDisplayed()) {
      SeleniumWrapper.switchToDefaultContent(driver);
    }
    Assert.assertTrue(successNotice.isDisplayed(), "Error: Success message not displayed");
  }

  /**
   * Method to verify for the default employee group of "Default: Enabled".
   *
   * @param employeeGroupDropdown
   */
  public static void checkForDefaultEmployeeGroup(WebElement employeeGroupDropdown) {
    if (employeeGroupDropdown != null && employeeGroupDropdown.isDisplayed()) {
      Select employeeGroup = new Select(employeeGroupDropdown);
      String selectedEmployeeGroup = employeeGroup.getFirstSelectedOption().getText();
      Assert.assertEquals(selectedEmployeeGroup, "Default: Enabled");
    }
  }

  /**
   * This method will be used to delete rows on tables.
   *
   * @param driver
   * @param element
   * @param rowNumber
   */
  public static void deleteTableRows(WebDriver driver, WebElement element, int rowNumber) {
    SeleniumWrapper.findChildElement(driver, element, "./tr[" + rowNumber + "]/td[1]/a").click();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    // Do not remove the below line for accepting alert.
    SeleniumWrapper.acceptAlertIfPresent(driver);
  }

  /**
   * This method will get all the rows from a table.
   *
   * @param table
   * @return
   */
  public static int getNumberOfRows(WebElement table) {
    return SeleniumWrapper.findRelativeElements(table, ".//tr").size();
  }

  /**
   * Method returns error message text.
   *
   * @param driver
   * @return String
   */
  public static String getErrorNoticeMessageText(WebDriver driver) {
    WebElement errorNoticeDisplayed = SeleniumWrapper.findElement(driver, PayrollConstants.ERROR_NOTICE);
    return StringUtils.trim(errorNoticeDisplayed.getText().replace("close", "").trim());
  }

  /**
   * This method will be use to disable vertical timesheet in general tab at company level.
   *
   * @param driver
   */
  public static void disableVerticalTimesheetCompanyLevel(WebDriver driver) {

    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
        PayrollConstants.VERTICAL_TIMESHEET_EMPLOYEE_SETTING_RADIO_BUTTON_YES);

    if (SeleniumWrapper.findElement(driver, PayrollConstants.VERTICAL_TIMESHEET_EMPLOYEE_SETTING_RADIO_BUTTON_YES)
        .isSelected()) {
      SeleniumWrapper.findElement(driver, PayrollConstants.VERTICAL_TIMESHEET_EMPLOYEE_SETTING_RADIO_BUTTON_NO).click();
      PayrollMenusAndLinksUtil.clickOnSaveButton();
    }
  }

  /**
   * This method will be use to open employee setting.
   *
   * @param driver
   * @param employeeName
   */
  public static void openEmployeeSetting(WebDriver driver, String employeeName) {
    PayrollMenusAndLinksUtil.clickSettingsMenu();
    PayrollMenusAndLinksUtil.clickEmployeesMenu();

    SeleniumWrapper.sendKeyWithClear(driver,
        SeleniumWrapper.findElement(driver, PayrollConstants.EMPLOYEES_MENU_EMPLOYEE_LOOKUP), employeeName);

    SeleniumWrapper.clickOnElement(By.xpath(
        "((//div[contains(@id,'payrollEmployeeLookup')])/div[@class='employeeName noOverFlow' and contains(.,'"
            + employeeName + "')])"));
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
        PayrollConstants.VERTICAL_TIMESHEET_EMPLOYEE_SETTING_RADIO_BUTTON_YES);
  }

  /**
   * This method will be use to verify employees timesheet is set to vertical or not.
   *
   * @param driver
   * @return
   */
  public static boolean isEmployeeHasVerticalTimesheet(WebDriver driver) {

    return SeleniumWrapper.findElement(driver, PayrollConstants.VERTICAL_TIMESHEET_EMPLOYEE_SETTING_RADIO_BUTTON_YES)
        .isSelected();
  }

  /**
   * Method to get a name of employee from first row by locator element.
   *
   * @return
   */
  public static String getFirstEmployeeNameFromTimesheetApproval() {
    return SeleniumWrapper.getElementText(PayrollConstants.TIMESHEET_FIRST_ROW_EMPLOYEE_NAME);
  }

  /**
   * Method returns true if the webElement is enabled and readonly attribute is null.
   *
   * @param element
   * @return
   */
  public static boolean isFieldEditable(WebElement element) {
    return element.isEnabled() && element.getAttribute("readonly") == null;
  }

  /**
   * This method is used to open setting of first employee from employees screen.
   *
   * @param driver
   */
  public static void openFirstEmployeeSettingFromEmployees(WebDriver driver) {
    WebElement form = SeleniumWrapper.findElement(driver, PayrollConstants.EMPLOYEES_LIST);
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.FIRST_EMPLOYEE_FROM_EMPLOYEES_LIST);
    WebElement tableRow = form.findElement(PayrollConstants.FIRST_EMPLOYEE_FROM_EMPLOYEES_LIST);
    if (tableRow != null) {
      tableRow.click();
    }
  }

  /**
   * Method to return the employee number.
   *
   * @param driver
   * @Return Element
   */
  public static String getEmployeeNumberFromEmployeesSetting(WebDriver driver) {
    return SeleniumWrapper.findElement(driver, PayrollConstants.EMPLOYEE_NUMBER).getAttribute("value");
  }

  /**
   * This method will set the time entry method to detail.
   */
  public static void setTimeEntryMethodToDetail() {
    SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.TIME_ENTRY_METHOD_EXEMPT_SELECTION, "Detail");
    SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.TIME_ENTRY_METHOD_NON_EXEMPT_SELECTION, "Detail");
  }

  /**
   * This method will set the time entry method to summary.
   */
  public static void setTimeEntryMethodToSummary() {
    SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.TIME_ENTRY_METHOD_EXEMPT_SELECTION, "Summary");
    SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.TIME_ENTRY_METHOD_NON_EXEMPT_SELECTION, "Summary");
  }

  /**
   * This method will set the overtime method override field overtime value options: peel (peel off), row (peel off by
   * row), day (prorate backwards by day), week (prorate by week).
   *
   * @param driver
   * @param overtimeMethodOption
   */
  public static void setOvertimeMethodOverrideByValueGeneralOptionsSettings(WebDriver driver,
      String overtimeMethodOption) {
    WebElement overtimePayCodeSelection = SeleniumWrapper.findElement(driver, PayrollConstants.OVERTIME_PAY_CODE);
    JavascriptExecutorWrapper.scrollToElement(driver, overtimePayCodeSelection);
    Select overtimePayCodeOptions = new Select(overtimePayCodeSelection);
    String overtimeCode = overtimePayCodeOptions.getFirstSelectedOption().getText();
    if (!"O/T".equals(overtimeCode)) {
      overtimePayCodeOptions.selectByValue("O/T");
    }
    if (!SeleniumWrapper.findElement(driver, PayrollConstants.COMPUTE_OVERTIME_YES_BUTTON).isSelected()) {
      SeleniumWrapper.findElement(driver, PayrollConstants.COMPUTE_OVERTIME_YES_BUTTON).click();
    }
    WebElement overtimeMethodElement = SeleniumWrapper.findElement(driver, PayrollConstants.OVERTIME_METHOD_SELECTOR);
    Select overtimeOverrideOptions = new Select(overtimeMethodElement);
    String overtimeOverrideOptionSelected = overtimeOverrideOptions.getFirstSelectedOption().getAttribute("value");
    if (!overtimeOverrideOptionSelected.equals(overtimeMethodOption)) {
      overtimeOverrideOptions.selectByValue(overtimeMethodOption);
      SeleniumWrapper.acceptAlertIfPresent(driver);
    }
  }

  /**
   * Method to click on save button and check if success method is displayed.
   *
   * @param driver
   */
  public static void clickSaveAndCheckForSuccessMessageIsDisplayed(WebDriver driver) {
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(PayrollConstants.SUCCESS_NOTICE));
    WebElement successNotice = SeleniumWrapper.findElement(driver, PayrollConstants.SUCCESS_NOTICE);
    if (!successNotice.isDisplayed()) {
      SeleniumWrapper.switchToDefaultContent(driver);
    }
    Assert.assertTrue(successNotice.isDisplayed(), "Error: Success message not displayed");
  }

  /**
   * This method will be use to delete rows on tables.
   *
   * @param driver
   * @param element
   * @param rowNumber
   */
  public static void deleteTableRow(WebDriver driver, WebElement element, int rowNumber) {
    SeleniumWrapper.findChildElement(driver, element, "./tr[" + rowNumber + "]/td[1]/a").click();
    SeleniumWrapper.acceptAlertIfPresent(driver);
  }

  /**
   * This method will be use to set horizontal timesheet in employee setting at employee level.
   *
   * @param driver
   */
  public static void setHorizontalTimesheetEmployeeLevel(WebDriver driver) {
    if (isEmployeeHasVerticalTimesheet(driver)) {
      SeleniumWrapper.findElement(driver, PayrollConstants.VERTICAL_TIMESHEET_EMPLOYEE_SETTING_RADIO_BUTTON_NO).click();
      JavascriptExecutorWrapper.scrollToElement(driver,
          SeleniumWrapper.findElement(driver, PayrollConstants.SAVE_BUTTON));
      PayrollMenusAndLinksUtil.clickOnSaveButton();
    }
  }

  /**
   * Method to check if the scheduler is enabled and if not then to enable it.
   *
   * @param driver
   * @return
   */
  public static void enableScheduler(WebDriver driver) {
    WebElement enableScheduler = SeleniumWrapper.findElement(driver, PayrollConstants.ENABLE_SCHEDULER_YES_BUTTON);
    if (enableScheduler != null && enableScheduler.isDisplayed()) {
      JavascriptExecutorWrapper.scrollToElement(driver, enableScheduler);
      if (!enableScheduler.isSelected()) {
        enableScheduler.click();
      }
      PayrollMenusAndLinksUtil.clickOnSaveButton();
    }
  }
}