package com.altres.projects.payroll.superuser.generaloption.payrollutil;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.altres.base.Base;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

public class PayrollMenusAndLinksUtil extends Base {

  /**
   * Method to click on home link.
   */
  public static void clickOnHomeMenu() {
    SeleniumWrapper.switchToDefaultContent(driver);
    JavascriptExecutorWrapper.scrollToElement(driver, SeleniumWrapper.findElement(driver, By.linkText("Home")));
    SeleniumWrapper.clickOnElement(By.linkText("Home"));
  }

  /**
   * Method to click on the settings menu and then general option menu.
   */
  public static void clickGeneralOptionMenu() {
    clickSettingsMenu();
    SeleniumWrapper.clickOnElement(PayrollConstants.GENERAL_OPTION_MENU_FIELD);
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.GENERAL_TAB);
  }

  /**
   * Clicks the Settings menu. Clicks the general option menu.
   */
  public static void clickSettingsMenu() {
    SeleniumWrapper.clickOnElement(PayrollConstants.SETTINGS_MENU_FIELD);
    SeleniumWrapper.switchToInteriorContent(driver);
  }

  /**
   * Method to click on payroll -> employees menu.
   */
  public static void clickEmployeesMenu() {
    SeleniumWrapper.clickOnElement(PayrollConstants.EMPLOYEE_SETTING_MENU);
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.EMPLOYEES_MENU_EMPLOYEE_LOOKUP);
  }

  /**
   * Method to click on the payroll menu.
   */
  public static void clickPayrollMenu() {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.clickOnElement(PayrollConstants.PAYROLL_MENU);
  }

  /**
   * Method to click on the Timesheet Approval menu.
   */
  public static void clickTimesheetApprovalMenu() {
    SeleniumWrapper.clickOnElement(PayrollConstants.TIMESHEET_APPROVAL_MENU);
    SeleniumWrapper.switchToInteriorContent(driver);
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.FILTER_BUTTON);
  }

  /**
   * Method to click on the first row of timesheet approval screen.
   */
  public static void clickOnFirstEmployeeOfTimesheetApproval() {
    SeleniumWrapper.clickOnElement(PayrollConstants.EMPLOYEE_TIMESHEET_FIRST_ROW);
  }

  /**
   * This method will click on job costing/labor allocation tab.
   */
  public static void clickJobCostingLaborAllocationTab() {
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.JOB_COSTING_LABOR_ALLOCATION_TAB);
    SeleniumWrapper.clickOnElement(PayrollConstants.JOB_COSTING_LABOR_ALLOCATION_TAB);
  }

  /**
   * This method will click on other pay tab.
   */
  public static void clickOtherPayTab() {
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.OTHER_PAY_TAB);
    SeleniumWrapper.clickOnElement(PayrollConstants.OTHER_PAY_TAB);
  }

  /**
   * This method will click on overtime tab.
   */
  public static void clickOvertimeTab() {
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.OVERTIME_TAB);
    SeleniumWrapper.clickOnElement(PayrollConstants.OVERTIME_TAB);
  }

  /**
   * This method will click on time entry.
   */
  public static void clickTimeEntryTab() {
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.TIME_ENTRY_TAB);
    SeleniumWrapper.clickOnElement(PayrollConstants.TIME_ENTRY_TAB);
  }

  /**
   * This method will click on time off tab.
   */
  public static void clickTimeOffTab() {
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.TIME_OFF_TAB);
    SeleniumWrapper.clickOnElement(PayrollConstants.TIME_OFF_TAB);
  }

  /**
   * This method will click on the save button.
   */
  public static void clickOnSaveButton() {
    SeleniumWrapper.clickOnElement(PayrollConstants.SAVE_BUTTON);
  }

  /**
   * This method will click on the add new link.
   */
  public static void clickOnAddNewLink() {
    SeleniumWrapper.clickOnElement(PayrollConstants.ADD_NEW_LINK);
  }

  /**
   * This method will click on the add row link.
   */
  public static void clickOnAddRowLink() {
    SeleniumWrapper.clickOnElement(PayrollConstants.ADD_ROW_LINK);
  }

  /**
   * This method will click on the return button.
   */
  public static void clickOnReturnButton() {
    SeleniumWrapper.clickOnElement(PayrollConstants.RETURN_BUTTON);
  }

  /**
   * This method will click on the copy button.
   */
  public static void clickOnCopyButton() {
    SeleniumWrapper.clickOnElement(PayrollConstants.COPY_BUTTON);
  }

  /**
   * This method will click on the delete button.
   */
  public static void clickOnDeleteButton() {
    SeleniumWrapper.clickOnElement(PayrollConstants.DELETE_BUTTON);
  }

  /**
   * This method will click on the filter button.
   */
  public static void clickOnFilterButton() {
    SeleniumWrapper.clickOnElement(PayrollConstants.FILTER_BUTTON);
  }

  /**
   * This method will click on the reset button.
   */
  public static void clickOnResetButton() {
    SeleniumWrapper.clickOnElement(PayrollConstants.RESET_BUTTON);
  }

  /**
   * This method will click on expand tabs button in general options.
   *
   * @param driver
   */
  public static void clickOnExpandTabsButton(WebDriver driver) {
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.EXPAND_TAB_BUTTON);
    String expandTab = SeleniumWrapper.findElement(driver, PayrollConstants.EXPAND_TAB_BUTTON).getAttribute("value");
    if (StringUtils.equals("Expand Tabs", expandTab)) {
      SeleniumWrapper.clickOnElement(PayrollConstants.EXPAND_TAB_BUTTON);
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.USES_SUPERVISOR_YES_BUTTON);
    }
  }

  /**
   * This method will click on scheduler tab.
   */
  public static void clickSchedulerTab() {
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.SCHEDULER_TAB);
    SeleniumWrapper.clickOnElement(PayrollConstants.SCHEDULER_TAB);
  }

  /**
   * This method will click on the scheduler -> configure menu.
   */
  public static void clickSchedulerConfigureMenu() {
    SeleniumWrapper.clickOnElement(PayrollConstants.SCHEDULER_CONFIGURE_MENU_FIELD);
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.ENABLE_SCHEDULER_YES_BUTTON);
  }
}