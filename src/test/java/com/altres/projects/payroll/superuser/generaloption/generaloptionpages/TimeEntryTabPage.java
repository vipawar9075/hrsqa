package com.altres.projects.payroll.superuser.generaloption.generaloptionpages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.altres.base.Base;
import com.altres.exception.SeleniumExceptions;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollConstants;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollHelperUtil;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

/**
 * Class to contain all business logic methods for time entry tab.
 */
public class TimeEntryTabPage extends Base implements TimeEntryTabPageInterface {

  /**
   * This method will set the time entry method to Detail and Summary.
   */
  public static void setTimeEntryMethodToDetailAndSummary() {
    SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.TIME_ENTRY_METHOD_EXEMPT_SELECTION,
        "Summary or Detail");
    SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.TIME_ENTRY_METHOD_NON_EXEMPT_SELECTION,
        "Summary or Detail");
  }

  /**
   * This method will set the time entry method to Summary.
   */
  public static void setTimeEntryMethodToSummaryInEmployeeSetting() {
    SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.TIME_ENTRY_METHOD, "Summary");
  }

  /**
   * This method returns the classification selection for the default break minutes override table.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getBreakMinutesOverrideClassificationSelection(WebDriver driver, int rowNumber) {
    return driver.findElement(By.xpath("//*[@id='breakMinutesJobHoursOverride']/tr[" + rowNumber + "]//select"));
  }

  /**
   * This method returns the text field for the default break minutes override table.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getDefaultBreakMinutesOverrideBreakMinutesTextField(WebDriver driver, int rowNumber) {
    return driver.findElement(
        By.xpath("//*[@id='breakMinutesJobHoursOverride']/tr[" + rowNumber + "]//input[@type='text']"));
  }

  /**
   * This method returns the xpath locator for break minutes override table by row number.
   *
   * @param rowNumber
   * @return
   */
  public static By getXPathLocatorForRowOfBreakMinutesOverrideTable(int rowNumber) {
    return By.xpath("//*[@id='breakMinutesJobHoursOverride']/tr[" + rowNumber + "]");
  }

  /**
   * This method returns all the selection for the auto pay amount based on hours worked table.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static List<WebElement> getAutoPayAmountBasedOnHoursWorkedAllSelection(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElements(driver, By.xpath("//*[@id='autoPayAmount']/tr[" + rowNumber + "]//select"));
  }

  /**
   * This method returns all the text fields for the auto pay amount based on hours worked table.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static List<WebElement> getAutoPayAmountBasedOnHoursWorkedAllTextField(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElements(driver,
        By.xpath("//*[@id='autoPayAmount']/tr[" + rowNumber + "]//input[@type='text']"));
  }

  /**
   * This method returns the row from the auto pay amount based on hours worked table.
   *
   * @param rowNumber
   * @return
   */
  public static By getAutoPayAmountBasedOnHoursWorkedRowByRowNumber(int rowNumber) {
    return By.xpath("//*[@id='autoPayAmount']/tr[" + rowNumber + "]");
  }

  /**
   * This method returns all the selection for the exclude costings from timesheet totals table.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static List<WebElement> getExcludeCostingFromTimesheetTotalsAllSelection(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElements(driver,
        By.xpath("//*[@id='excludeCostingsFromTimesheetTotals']/tr[" + rowNumber + "]//select"));
  }

  /**
   * This method returns the row from the exclude costings from timesheet totals table.
   *
   * @param rowNumber
   * @return
   */
  public static By getExcludeCostingFromTimesheetTotalsRowByRowNumber(int rowNumber) {
    return By.xpath("//*[@id='excludeCostingsFromTimesheetTotals']/tr[" + rowNumber + "]");
  }

  /**
   * This method will set the dropdown field to the given text.
   *
   * @param element
   * @param selectOptionText
   */
  public static void setDropdownByText(WebElement element, String selectOptionText) {
    Select dropdownSelect = new Select(element);
    WebElement selectedOption = dropdownSelect.getFirstSelectedOption();
    String selectedOptionText = selectedOption.getText();
    List<WebElement> options = dropdownSelect.getOptions();
    boolean optionFound = false;
    if (!selectedOptionText.contains(selectOptionText)) {
      for (WebElement option : options) {
        String optionText = option.getText();
        if (optionText.contains(selectOptionText)) {
          optionFound = true;
          dropdownSelect.selectByVisibleText(optionText);
          break;
        }
      }

      if (!optionFound) {
        throw new NoSuchElementException("No selection option '" + selectOptionText + "' was found.");
      }
    }
  }

  /**
   * This method adds the first option from the available table on a multi-selector table.
   *
   * @param driver
   * @param availableTableElement
   */
  public static void addFirstOptionToSelectedTable(WebDriver driver,
      WebElement availableTableElement) {
    new WebDriverWait(driver, Duration.ofSeconds(2));
    WebElement firstOption = availableTableElement.findElement(By.tagName("option"));
    String optionValue = firstOption.getAttribute("value");
    Select selectedTableSelect = new Select(availableTableElement);
    selectedTableSelect.selectByValue(optionValue);
    JavascriptExecutorWrapper.clickOnElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, MOBILE_TIP_PAY_CODES_ADD));
  }

  /**
   * This method removes all available options from the selected table on a multi-selector table.
   *
   * @param driver
   */
  public static void removeAllOptionsFromSelectedTable(WebDriver driver) {
    new WebDriverWait(driver, Duration.ofSeconds(2));
    JavascriptExecutorWrapper.clickOnElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, MOBILE_TIP_PAY_CODES_REMOVE_ALL));
    SeleniumWrapper.acceptAlertIfPresent(driver);
  }

  /**
   * This method adds all available options from the available table on a multi-selector table.
   *
   * @param driver
   */
  public static void addAllOptionToSelectedTable(WebDriver driver) {
    new WebDriverWait(driver, Duration.ofSeconds(2));
    JavascriptExecutorWrapper.clickOnElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, MOBILE_TIP_PAY_CODES_ADD));
    SeleniumWrapper.acceptAlertIfPresent(driver);
  }

  /**
   * Method returns error message text.
   *
   * @param driver
   * @return
   */
  public static String getNoticeMessageText(WebDriver driver) {
    return SeleniumWrapper.findElement(driver, ERROR_NOTICE).getText().replace("close", "");
  }

  /**
   * This method returns the row from the daily hours.
   *
   * @param rowNumber
   * @return
   */
  public static By getDailyHoursDefaultHoursRowXPathByRowNumber(int rowNumber) {
    return By.xpath("//*[@id='dailyHoursTable']/tr[" + rowNumber + "]");
  }

  /**
   * This method returns the employee group selection for the daily hours.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getDailyHoursDefaultHoursTextField(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElement(driver,
        By.xpath("//*[@id='dailyHoursTable']/tr[" + rowNumber + "]//input[@type='text']"));
  }

  /**
   * This method returns the employee group selection for the daily hours.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getDailyHoursEmployeeGroupSelection(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElement(driver, By.xpath("//*[@id='dailyHoursTable']/tr[" + rowNumber + "]//select"));
  }

  /**
   * This method returns the row from the daily hours.
   *
   * @param rowNumber
   * @return
   */
  public static By getMaxDailyHoursDefaultHoursRowXPathByRowNumber(int rowNumber) {
    return By.xpath("//*[@id='maxDailyHoursTable']/tr[" + rowNumber + "]");
  }

  /**
   * This method returns the text field for the max daily hours.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getMaxDailyHoursDefaultHoursTextField(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElement(driver,
        By.xpath("//*[@id='maxDailyHoursTable']/tr[" + rowNumber + "]//input[@type='text']"));
  }

  /**
   * This method returns the Employee group selection for the max daily hours.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getMaxDailyHoursEmployeeGroupSelection(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElement(driver, By.xpath("//*[@id='maxDailyHoursTable']/tr[" + rowNumber + "]//select"));
  }

  /**
   * Method to get time clock element list.
   *
   * @return
   */
  public static WebElement[] getTimeClockElementList() {
    return new WebElement[]{TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.FINGER_PRINT_CLOCK_CHECK_BOX),
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.HAND_PRINT_CLOCK_CHECK_BOX),
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.FACE_CLOCK_CHECK_BOX)};
  }

  /**
   * Method select and save and verify non-exempt time entry methods.
   *
   * @param dropdownElement
   */
  public static void selectAndSaveAndVerifyExemptTimeEntryMethods(WebElement dropdownElement) throws Exception {
    Map<String, String> nonExemptTimeEntryMethodOptions = SeleniumWrapper.getAvailableDropdownOptions(dropdownElement);
    List<String> nonExemptTimeEntryMethods = new ArrayList<>(nonExemptTimeEntryMethodOptions.keySet());

    for (String nonExemptTimeEntryMethod : nonExemptTimeEntryMethods) {
      try {
        SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.TIME_ENTRY_METHOD_EXEMPT_SELECTION,
            nonExemptTimeEntryMethod);
        PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
      } catch (Throwable e) {
        SeleniumExceptions.handleException(e, driver);
      }
    }
  }

  /**
   * Method select and save and verify exempt time entry methods.
   *
   * @param dropdownElement
   */
  public static void selectAndSaveAndVerifyNonExemptTimeEntryMethods(WebElement dropdownElement) throws Exception {
    Map<String, String> nonExemptTimeEntryMethodOptions = SeleniumWrapper.getAvailableDropdownOptions(dropdownElement);
    List<String> nonExemptTimeEntryMethods = new ArrayList<>(nonExemptTimeEntryMethodOptions.keySet());

    for (String nonExemptTimeEntryMethod : nonExemptTimeEntryMethods) {
      try {
        SeleniumWrapper.selectDropdownByVisibleText(TIME_ENTRY_METHOD_NON_EXEMPT_DROPDOWN, nonExemptTimeEntryMethod);
        PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
      } catch (Throwable e) {
        SeleniumExceptions.handleException(e, driver);
      }
    }
  }

  /**
   * Selects EmployeeLevelOption drop-down base on the value provided.
   *
   * @param value
   */
  public static void selectEmployeeLevelOption(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(TIME_ENTRY_METHOD_OVERRIDE_EMPLOYEE_LEVEL_DROPDOWN, value);
  }

  /**
   * This method returns the element for the employee group selection for the TimeEntry method by employee group table.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getTimeEntryMethodByEmployeeGroupEmployeeGroupSelection(WebDriver driver, int rowNumber) {
    return driver.findElement(By.xpath("//select[@name='time_entry_method_by_employee_group_code_" + rowNumber + "']"));
  }

  /**
   * This method returns the element for the method selection for the TimeEntry method by employee group table.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getTimeEntryMethodByEmployeeGroupMethodSelection(WebDriver driver, int rowNumber) {
    return driver.findElement(
        By.xpath("//select[@name='time_entry_method_by_employee_group_method_" + rowNumber + "']"));
  }

  /**
   * Method select option from the timeEntry method by employee group table.
   */
  public static void selectAndSaveAndVerifyTimeEntryMethodsByEmployeeGroupTable() {

    Map<String, String> methodAvailableOptions = SeleniumWrapper.getAvailableDropdownOptions(
        TimeEntryTabPage.getTimeEntryMethodByEmployeeGroupMethodSelection(driver, 1));
    List<String> methodOptionsValues = new ArrayList<>(methodAvailableOptions.keySet());
    for (String methodOptionValue : methodOptionsValues) {
      SeleniumWrapper.selectDropdownByVisibleText(
          By.xpath("//select[@name" + "='time_entry_method_by_employee_group_method_" + 1 + "']"), methodOptionValue);
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    }
  }

  /**
   * Method select and verify a clock in an incremental option.
   */
  public static void selectIncrementOptionAndVerify() {
    Map<String, String> methodAvailableOptions = SeleniumWrapper.getAvailableDropdownOptions(
        TimeEntryTabPageInterface.getWebElement(driver, CLOCK_IN_ROUNDING_INCREMENT_SELECTION));
    List<String> incrementOptionsValues = new ArrayList<>(methodAvailableOptions.keySet());
    for (String incrementOptionValue : incrementOptionsValues) {
      SeleniumWrapper.selectDropdownByVisibleText(CLOCK_IN_ROUNDING_INCREMENT_SELECTION, incrementOptionValue);
      SeleniumWrapper.sendKeyWithClear(driver,
          TimeEntryTabPageInterface.getWebElement(driver, CLOCK_IN_ROUNDING_POINT_TEXT_FIELD), "5");
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    }
  }

  /**
   * Method select and verify clock out Rounding an incremental option.
   */
  public static void selectClockOutRoundingIncrementOptionAndVerify() {
    Map<String, String> methodAvailableOptions = SeleniumWrapper.getAvailableDropdownOptions(
        TimeEntryTabPageInterface.getWebElement(driver, CLOCK_OUT_ROUNDING_INCREMENT_SELECTION));
    List<String> incrementOptionsValues = new ArrayList<>(methodAvailableOptions.keySet());
    for (String incrementOptionValue : incrementOptionsValues) {
      SeleniumWrapper.selectDropdownByVisibleText(CLOCK_OUT_ROUNDING_INCREMENT_SELECTION, incrementOptionValue);
      SeleniumWrapper.sendKeyWithClear(driver,
          TimeEntryTabPageInterface.getWebElement(driver, CLOCK_OUT_ROUNDING_POINT_TEXT_FIELD), "5");
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    }
  }

  /**
   * Method to return current selected option value from a dropdown field.
   *
   * @return
   */
  public static String getCurrentSelectedOptionValue() {
    return SeleniumWrapper.getFirstSelectedOptionText(
        (By.xpath("//select[@name='time_entry_method_by_employee_group_code_" + 1 + "']")));
  }
}