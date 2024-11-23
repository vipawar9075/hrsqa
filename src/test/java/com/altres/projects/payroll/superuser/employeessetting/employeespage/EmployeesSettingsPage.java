package com.altres.projects.payroll.superuser.employeessetting.employeespage;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.altres.base.Base;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollConstants;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollHelperUtil;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollMenusAndLinksUtil;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

public class EmployeesSettingsPage extends Base implements EmployeesSettingsInterface {

  /**
   * This method will set up general options for the employee settings.
   *
   * @param selectOvertimePayCode
   * @param computeOvertimeHours
   * @param computeWeeklyOvertime
   * @param excludeCompanyHolidays
   * @param excludeCompanyHolidaysEmployeeOverride
   * @param allowETimeEntryMethodOverride
   * @param overtimeMethod
   * @param usesSupervisors
   * @param allowLimitLaborCostCenterChoices
   * @param allowLimitLaborCostCenterUseEmployeeGroup
   * @param allowMultiPayRateChange
   * @param allowMobileTimeClock
   * @param allowTextMessagingTimeEntry
   */
  public static void setUpGeneralOptionsForEmployeeSettings(String selectOvertimePayCode, boolean computeOvertimeHours,
      boolean computeWeeklyOvertime, boolean excludeCompanyHolidays, boolean excludeCompanyHolidaysEmployeeOverride,
      boolean allowETimeEntryMethodOverride, String overtimeMethod, boolean usesSupervisors,
      boolean allowLimitLaborCostCenterChoices, boolean allowLimitLaborCostCenterUseEmployeeGroup,
      boolean allowMultiPayRateChange, boolean allowMobileTimeClock, boolean allowTextMessagingTimeEntry) {

    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    PayrollHelperUtil.setTimeEntryMethodToDetail();
    SeleniumWrapper.selectDropdownByOptionValue(PayrollConstants.OVERTIME_PAY_CODE, selectOvertimePayCode);
    EmployeesSettingsInterface.getWebElement(driver,
        By.xpath("//input[@id='compute_overtime_hours_" + getYesNoRadioButtons(computeOvertimeHours) + "']")).click();
    EmployeesSettingsInterface.getWebElement(driver,
        By.xpath("//input[@id='compute_overtime_" + getYesNoRadioButtons(computeWeeklyOvertime) + "']")).click();
    EmployeesSettingsInterface.getWebElement(driver,
        By.xpath("//input[@id='exclude_from_holiday_" + getYesNoRadioButtons(excludeCompanyHolidays) + "']")).click();
    EmployeesSettingsInterface.getWebElement(driver, By.xpath(
        "//input[@id='excl_from_holiday_employee_override_" + getYesNoRadioButtons(
            excludeCompanyHolidaysEmployeeOverride) + "']")).click();
    EmployeesSettingsInterface.getWebElement(driver, By.xpath(
            "//input[@id='allow_time_entry_method_override_" + getYesNoRadioButtons(allowETimeEntryMethodOverride) + "']"))
        .click();
    PayrollHelperUtil.setOvertimeMethodOverrideByValueGeneralOptionsSettings(driver, overtimeMethod);
    EmployeesSettingsInterface.getWebElement(driver,
        By.xpath("//input[@id='uses_supervisors_" + getYesNoRadioButtons(usesSupervisors) + "']")).click();
    EmployeesSettingsInterface.getWebElement(driver, By.xpath(
            "//input[@id='allow_job_costing_pay_rate_change_" + getYesNoRadioButtons(allowMultiPayRateChange) + "']"))
        .click();
    EmployeesSettingsInterface.getWebElement(driver, PayrollConstants.ALLOW_PERCENTAGE_ALLOCATION_NO_BUTTON).click();
    EmployeesSettingsInterface.getWebElement(driver, By.xpath(
            "//input[@id='allow_limit_job_costing_" + getYesNoRadioButtons(allowLimitLaborCostCenterChoices) + "']"))
        .click();
    EmployeesSettingsInterface.getWebElement(driver, By.xpath(
        "//input[@id='allow_limit_job_costing_employee_groups_" + getYesNoRadioButtons(
            allowLimitLaborCostCenterUseEmployeeGroup) + "']")).click();
    EmployeesSettingsInterface.getWebElement(driver,
        By.xpath("//input[@id='allow_mobile_access_" + getYesNoRadioButtons(allowMobileTimeClock) + "']")).click();
    addMobileTipPayCodes(driver);
    EmployeesSettingsInterface.getWebElement(driver,
        By.xpath("//input[@id='allow_sms_access_" + getYesNoRadioButtons(allowTextMessagingTimeEntry) + "']")).click();
    PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    SeleniumWrapper.switchToDefaultContent(driver);
    PayrollMenusAndLinksUtil.clickOnHomeMenu();
  }

  /**
   * Method to get the yes or no radio buttons. Yes = 0 and No = 1.
   *
   * @param isRadioSettings
   * @return
   */
  private static String getYesNoRadioButtons(boolean isRadioSettings) {
    return isRadioSettings ? "0" : "1";
  }

  /**
   * Method to add mobile tip pay codes.
   *
   * @param driver
   */
  private static void addMobileTipPayCodes(WebDriver driver) {
    new WebDriverWait(driver, Duration.ofSeconds(2));

    SeleniumWrapper.selectDropdownByOptionValue(PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN,
        "EE");
    WebElement mobileTipCodesSelected = EmployeesSettingsInterface.getWebElement(driver, MOBILE_TIP_CODES_SELECTED);
    Select selectedPayCodes = new Select(mobileTipCodesSelected);
    if (selectedPayCodes.getOptions().isEmpty()) {
      WebElement mobileTipPayCodes = EmployeesSettingsInterface.getWebElement(driver,
          PayrollConstants.MOBILE_TIP_PAY_CODES_AVAILABLE);
      Select availablePayCodes = new Select(mobileTipPayCodes);
      List<WebElement> availableMobileTipPayCodes = availablePayCodes.getOptions();
      availableMobileTipPayCodes.stream()
          .filter(WebElement::isDisplayed)
          .forEach(option -> {
            option.click();
            SeleniumWrapper.click(driver, MOBILE_TIP_CODES_ADD);
          });
    }
  }

  /**
   * This method will click on the save button and verify if the success notice is present.
   *
   * @param driver
   */
  public static void clickSaveAndVerifyEmployeeSettingSuccessNotice(WebDriver driver) {
    JavascriptExecutorWrapper.scrollToElement(driver,
        EmployeesSettingsInterface.getWebElement(driver, PayrollConstants.SAVE_BUTTON));
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    SeleniumWrapper.acceptAlertIfPresent(driver);
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.SUCCESS_NOTICE);
    WebElement successNotice = EmployeesSettingsInterface.getWebElement(driver, PayrollConstants.SUCCESS_NOTICE);
    if (!successNotice.isDisplayed()) {
      SeleniumWrapper.switchToDefaultContent(driver);
    }
    Assert.assertTrue(successNotice.isDisplayed(), "Success notice is not displayed.");
  }

  /**
   * This method will get all the supervisors dropdown and add them to an array.
   *
   * @param driver
   * @return
   */
  public static WebElement[] getSupervisorElementArraysList(WebDriver driver) {
    String employeeNumber = PayrollHelperUtil.getEmployeeNumberFromEmployeesSetting(driver);
    return new WebElement[]{
        EmployeesSettingsInterface.getWebElement(driver, By.xpath("//select[@name='" + employeeNumber + "_pri_ts']")),
        EmployeesSettingsInterface.getWebElement(driver, By.xpath("//select[@name='" + employeeNumber + "_sec_ts']")),
        EmployeesSettingsInterface.getWebElement(driver, By.xpath("//select[@name='" + employeeNumber + "_tri_ts']")),
        EmployeesSettingsInterface.getWebElement(driver, By.xpath("//select[@name='" + employeeNumber + "_pri_lr']")),
        EmployeesSettingsInterface.getWebElement(driver, By.xpath("//select[@name='" + employeeNumber + "_sec_lr']")),
        EmployeesSettingsInterface.getWebElement(driver, By.xpath("//select[@name='" + employeeNumber + "_tri_lr']"))};
  }

  /**
   * This method will click on the save button and verify if the success notice is present.
   *
   * @param driver
   */
  public static void deleteLaborCostCenterDefaultAllRows(WebDriver driver) {
    List<WebElement> rowNumbers = EmployeesSettingsInterface.getWebElements(driver, LABOR_COST_CENTER_DEFAULT_ALL_ROWS);
    rowNumbers.forEach(webElement -> {
      JavascriptExecutorWrapper.scrollToElement(driver,
          EmployeesSettingsInterface.getWebElement(driver, LABOR_COST_CENTER_DEFAULT_TABLE));
      PayrollHelperUtil.deleteTableRows(driver,
          EmployeesSettingsInterface.getWebElement(driver, LABOR_COST_CENTER_DEFAULT_TABLE), 1);
    });
  }

  /**
   * This method will click on the save button and verify if the success notice is present.
   *
   * @param driver
   */
  public static void deleteRowsForMultipleRateOverride(WebDriver driver) {
    List<WebElement> rowNumbers = EmployeesSettingsInterface.getWebElements(driver,
        EMPLOYEE_MULTIPLE_RATE_OVERRIDE_ROWS);
    rowNumbers.forEach(webElement -> {
      JavascriptExecutorWrapper.scrollToElement(driver,
          EmployeesSettingsInterface.getWebElement(driver, EMPLOYEE_MULTIPLE_RATE_OVERRIDE_TABLE));
      PayrollHelperUtil.deleteTableRows(driver,
          EmployeesSettingsInterface.getWebElement(driver, EMPLOYEE_MULTIPLE_RATE_OVERRIDE_TABLE), 1);
    });
  }

  /**
   * Method to return the labor cost center limits add all button.
   *
   * @param driver
   * @Return
   */
  public static WebElement getLaborCostCenterLimitsWorkClassificationAddAllButton(WebDriver driver, String costingTab) {
    return EmployeesSettingsInterface.getWebElement(driver,
        By.cssSelector("#" + costingTab + " input[name$='add_all']"));
  }

  /**
   * Method to return the labor cost center limits remove all button.
   *
   * @param driver
   * @Return
   */
  public static WebElement getLaborCostCenterLimitsWorkClassificationRemoveAllButton(WebDriver driver,
      String costingTab) {
    return EmployeesSettingsInterface.getWebElement(driver,
        By.cssSelector("#" + costingTab + " input[name$='remove_all']"));
  }

  /**
   * This method will update each dropdown for the employee multiple rate override costing table.
   *
   * @param driver
   * @param rowNumber
   */
  public static void updateEmployeeMultipleRateOverrideCostings(WebDriver driver, int rowNumber) {
    List<WebElement> costingRowDropdowns = EmployeesSettingsInterface.getWebElements(driver,
        By.xpath("//*[@id='jobCostingPayRates']/tr[" + rowNumber + "]//select"));
    for (WebElement costingRowDropdown : costingRowDropdowns) {
      Select costingDropdown = new Select(costingRowDropdown);
      List<WebElement> costingOptions = costingDropdown.getOptions();
      if (!costingOptions.isEmpty()) {
        WebElement getLastCosting = costingOptions.get(costingOptions.size() - 2);
        costingDropdown.selectByVisibleText(getLastCosting.getText());
      }
    }
  }

  /**
   * Method to delete the employee multiple rate override row.
   *
   * @param driver
   * @param rowNumber
   * @Return
   */
  public static void deleteEmployeeMultipleRateOverrideRow(WebDriver driver, int rowNumber) {
    EmployeesSettingsInterface.getWebElement(driver,
        By.xpath("//*[@id='jobCostingPayRates']/tr[" + rowNumber + "]/td[1]/a")).click();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    SeleniumWrapper.acceptAlertIfPresent(driver);
  }

  /**
   * This method will find the row number present on the error messages.
   *
   * @param errorMessage
   * @return
   */
  public static String[] getTheRowNumbersFromErrorMessage(String errorMessage) {

    String[] messageText = errorMessage.split(" ");
    String rowNumber1 = messageText[1].trim();
    String rowNumber2 = messageText[3].trim();
    return new String[]{rowNumber1, rowNumber2};
  }

  /**
   * This method will return the count of employees found.
   *
   * @return
   */
  public static int getNumberOfEmployees() {
    String employeesFoundMessage = EmployeesSettingsInterface.getWebElement(driver, EMPLOYEES_COUNT_MESSAGE).getText();

    return Integer.parseInt(employeesFoundMessage.split(" ")[0].trim());
  }

  /**
   * This method will click on the next employee link and return the remaining count of next available employees.
   *
   * @param remainingEmployees
   * @return
   */
  public static int selectNextAndReturnRemainingEmployees(int remainingEmployees) {
    WebElement nextEmployee = EmployeesSettingsInterface.getWebElement(driver, NEXT_LINK);
    JavascriptExecutorWrapper.scrollToElement(driver, nextEmployee);
    nextEmployee.click();
    return remainingEmployees - 1;
  }

  /**
   * Method to return the employee multiple rate override each costing dropdown selection. The costing codes are: LOCN,
   * DIV, DEPT, SHIFT, PROJ, JOB.
   *
   * @param driver
   * @param costingCode
   * @param rowNumber
   * @Return
   */
  public static WebElement getEmployeeMultipleRateOverrideSingleCostingsDropdown(WebDriver driver, String costingCode,
      int rowNumber) {
    return EmployeesSettingsInterface.getWebElement(driver,
        By.xpath("//select[@name='jobCostingRates_" + costingCode + "_" + rowNumber + "']"));
  }

  /**
   * This method will update each dropdown for the labor cost center default table.
   *
   * @param driver
   * @param rowNumber
   */
  public static void updateLaborCostCenterDefaultDropDowns(WebDriver driver, int rowNumber) {
    List<WebElement> defaultCostingRowDropdowns = EmployeesSettingsInterface.getWebElements(driver,
        By.xpath("//*[@id='defaultJobCostings']/tr[" + rowNumber + "]//select"));
    for (WebElement defaultCostingRowDropdown : defaultCostingRowDropdowns) {
      Select defaultCostingDropdown = new Select(defaultCostingRowDropdown);
      List<WebElement> costingOptions = defaultCostingDropdown.getOptions();
      if (!costingOptions.isEmpty()) {
        WebElement getLastCosting = costingOptions.get(costingOptions.size() - 1);
        defaultCostingDropdown.selectByVisibleText(getLastCosting.getText());
      }
    }
  }

  /**
   * Method to delete the rows in the costing tables.
   *
   * @param driver
   */
  public static void deleteLaborCostCenterDefaultAddedRows(WebDriver driver) {
    List<WebElement> tableRows = EmployeesSettingsInterface.getWebElements(driver, LABOR_COST_CENTER_DEFAULT_ALL_ROWS);
    if (!tableRows.isEmpty()) {
      for (int i = tableRows.size() - 1; i >= 0; i--) {
        WebElement rowToDelete = tableRows.get(i);
        if (isWebElementValid(rowToDelete)) {
          SeleniumWrapper.findChildElement(driver, rowToDelete, ".//td[1]").click();
          SeleniumWrapper.acceptAlertIfPresent(driver);
          SeleniumWrapper.acceptAlertIfPresent(driver);
        }
      }
    }
  }

  /**
   * The purpose of this method is to check if the webElement exists, displayed and editable field.
   *
   * @param element
   * @return
   */
  public static boolean isWebElementValid(WebElement element) {
    return element != null && element.isDisplayed() && PayrollHelperUtil.isFieldEditable(element);
  }

  /**
   * Method to return today's date in "MM/dd/yyyy" format.
   *
   * @return
   */
  public static String getTodaysDate() {
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    Date date = new Date();
    return dateFormat.format(date);
  }
}