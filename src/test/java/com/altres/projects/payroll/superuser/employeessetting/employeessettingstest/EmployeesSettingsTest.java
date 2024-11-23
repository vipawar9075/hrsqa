package com.altres.projects.payroll.superuser.employeessetting.employeessettingstest;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.exception.SeleniumExceptions;
import com.altres.login.LoginPage;
import com.altres.projects.payroll.superuser.employeessetting.employeespage.EmployeesSettingsInterface;
import com.altres.projects.payroll.superuser.employeessetting.employeespage.EmployeesSettingsPage;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollConstants;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollHelperUtil;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollMenusAndLinksUtil;
import com.altres.util.FakeDataGenerator;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

public class EmployeesSettingsTest extends Base {

  private static int remainingNextEmployees;
  private final String MISMATCH_ERROR_MESSAGE = "Error message do not match";

  /**
   * The purpose of this test is to select each of the exclude from company observed holiday options and save each of
   * them.
   *
   * @throws ConfigurationNotLoadedException
   */
  @Test(priority = 1)
  public void testExcludeFromCompanyObservedHolidaysOptions() throws ConfigurationNotLoadedException {
    LoginPage.loginAsSuperUser(driver);
    EmployeesSettingsPage.setUpGeneralOptionsForEmployeeSettings("O/T", true, true, false, true, true, "row", true,
        true, false, true, true, true);
    PayrollMenusAndLinksUtil.clickSettingsMenu();
    PayrollMenusAndLinksUtil.clickEmployeesMenu();
    remainingNextEmployees = EmployeesSettingsPage.getNumberOfEmployees();
    PayrollHelperUtil.openFirstEmployeeSettingFromEmployees(driver);
    remainingNextEmployees = remainingNextEmployees - 1;
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollHelperUtil.setTimeEntryMethodToDetail();
    String[] excludeOptionsList = new String[]{"N", "TW", "LH", "Y"};
    for (String excludeOption : excludeOptionsList) {
      SeleniumWrapper.selectDropdownByOptionValue(EmployeesSettingsInterface.EXCLUDE_FROM_HOLIDAY, excludeOption);
      EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
    }
  }

  /**
   * The purpose of this test is to select each of the time entry method options and save each of them.
   */
  @Test(priority = 2)
  public void testTimeEntryMethodOptions() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    SeleniumWrapper.selectDropdownByOptionValue(PayrollConstants.OVERTIME_METHOD_SELECTOR, "row");
    String[] timeEntryMethodOverrideOptionsList = new String[]{"GD", "G", "D", "OE"};
    for (String timeEntryMethodOverrideOption : timeEntryMethodOverrideOptionsList) {
      SeleniumWrapper.selectDropdownByOptionValue(PayrollConstants.TIME_ENTRY_METHOD, timeEntryMethodOverrideOption);
      EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
    }
    SeleniumWrapper.selectDropdownByOptionValue(PayrollConstants.TIME_ENTRY_METHOD, "D");
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is to select each of the overtime method override and save each of them.
   */
  @Test(priority = 3)
  public void testOvertimeMethodOverrideOptions() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    String[] overtimeMethodOverrideOptionsList = new String[]{"peel", "row", "day", "week"};
    SeleniumWrapper.selectDropdownByOptionValue(PayrollConstants.TIME_ENTRY_METHOD, "D");
    for (String overtimeMethodOverrideOption : overtimeMethodOverrideOptionsList) {
      SeleniumWrapper.selectDropdownByOptionValue(PayrollConstants.OVERTIME_METHOD_SELECTOR,
          overtimeMethodOverrideOption);
      EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
    }
  }

  /**
   * The purpose of this test is to test the error message when not having the detail time entry method selected and
   * selecting the peel off option for the overtime method override.
   */
  @Test(priority = 4)
  public void testOvertimeMethodOverrideErrorMessage() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    for (int i = remainingNextEmployees; i > 0; i--) {
      if (EmployeesSettingsInterface.getWebElements(driver, PayrollConstants.OVERTIME_METHOD_SELECTOR).isEmpty()) {
        remainingNextEmployees = EmployeesSettingsPage.selectNextAndReturnRemainingEmployees(remainingNextEmployees);
      } else {
        SeleniumWrapper.selectDropdownByOptionValue(PayrollConstants.TIME_ENTRY_METHOD, "G");
        JavascriptExecutorWrapper.scrollToElement(driver,
            EmployeesSettingsInterface.getWebElement(driver, PayrollConstants.OVERTIME_METHOD_SELECTOR));
        SeleniumWrapper.selectDropdownByOptionValue(PayrollConstants.OVERTIME_METHOD_SELECTOR, "peel");
        JavascriptExecutorWrapper.scrollToElement(driver,
            EmployeesSettingsInterface.getWebElement(driver, PayrollConstants.SAVE_BUTTON));
        PayrollMenusAndLinksUtil.clickOnSaveButton();
        String errorActual = PayrollHelperUtil.getErrorNoticeMessageText(driver);
        String errorExpected = "Peel off overtime calculation method is not allowed without a detailed time entry.";
        Assert.assertEquals(errorActual, errorExpected, "Error message is not present");
        SeleniumWrapper.selectDropdownByOptionValue(PayrollConstants.TIME_ENTRY_METHOD, "D");
        SeleniumWrapper.selectDropdownByOptionValue(PayrollConstants.OVERTIME_METHOD_SELECTOR, "row");
        EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
        break;
      }
    }
  }

  /**
   * The purpose of this test is to set all the radio buttons to yes and then to no.
   */
  @Test(priority = 5)
  public void testToUpdateAllRadioButtons() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    String radioButtonValue;
    List<WebElement> radioYesButtons = EmployeesSettingsInterface.getWebElements(driver,
        EmployeesSettingsInterface.FIRST_TABLE_RADIO_BUTTONS);
    for (WebElement radioYesButton : radioYesButtons) {
      radioButtonValue = radioYesButton.getAttribute("value");
      if ("Y".equals(radioButtonValue) && !radioYesButton.isSelected()) {
        radioYesButton.click();
      }
    }
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);

    List<WebElement> radioNoButtons = EmployeesSettingsInterface.getWebElements(driver,
        EmployeesSettingsInterface.FIRST_TABLE_RADIO_BUTTONS);
    for (WebElement radioNoButton : radioNoButtons) {
      radioButtonValue = radioNoButton.getAttribute("value");
      if ("N".equals(radioButtonValue) && (!radioNoButton.isSelected())) {
        radioNoButton.click();
      }
    }
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is to sent up to seven numbers to all the text fields.
   */
  @Test(priority = 6)
  public void testToUpdateAllTextFieldsFromFirstTable() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    List<WebElement> textFieldsTableFirst = EmployeesSettingsInterface.getWebElements(driver,
        EmployeesSettingsInterface.FIRST_TABLE_TEXT_FIELDS);
    for (WebElement textFields : textFieldsTableFirst) {
      if (textFields.isDisplayed() && textFields.isEnabled()) {
        String sendKey = String.valueOf(FakeDataGenerator.randomNumberIgnoringSpecificNumber(9999999, 1000, 0));
        SeleniumWrapper.sendKeyWithClear(driver, textFields, sendKey);
      }
    }
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is to select each of the mobile time clock tip entry override options and save each of
   * them.
   */
  @Test(priority = 7)
  public void testMobileTimeClockTipEntryOverrideOptions() {

    String[] mobileTimeClockTipEntryOverrideOptionList = new String[]{"N", "Y", "CO"};
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    for (String option : mobileTimeClockTipEntryOverrideOptionList) {
      WebElement mobileTimeClockTipEntryOverrideOptions = EmployeesSettingsInterface.getWebElement(driver,
          PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN);
      Select mobileTimeClockTipEntryOverrideOption = new Select(mobileTimeClockTipEntryOverrideOptions);
      mobileTimeClockTipEntryOverrideOption.selectByValue(option);
      EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
    }
  }

  /**
   * The purpose of this test is to select a timesheet and time off supervisor from the dropdown.
   */
  @Test(priority = 8)
  private void testTimeSheetAndTimeOffSupervisors() throws Exception {

    try {
      SeleniumWrapper.switchToDefaultContent(driver);
      SeleniumWrapper.switchToInteriorContent(driver);
      WebElement[] supervisorDropdownElementList = EmployeesSettingsPage.getSupervisorElementArraysList(driver);

      for (WebElement supervisorSelection : supervisorDropdownElementList) {
        Select employeeSupervisorSelection = new Select(supervisorSelection);
        String selectedSupervisor = employeeSupervisorSelection.getFirstSelectedOption().getText();
        List<WebElement> supervisorOptions = employeeSupervisorSelection.getOptions();
        if (!supervisorOptions.isEmpty()) {
          employeeSupervisorSelection.selectByIndex(1);
          String firstSupervisor = employeeSupervisorSelection.getFirstSelectedOption().getText();
          if ((selectedSupervisor.equals(firstSupervisor)) && (supervisorOptions.size() >= 2)) {
            employeeSupervisorSelection.selectByIndex(2);
          }
        }
      }
      EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);

      WebElement[] supervisorDropdownElementList2 = EmployeesSettingsPage.getSupervisorElementArraysList(driver);
      for (WebElement supervisorSelection2 : supervisorDropdownElementList2) {
        Select adminSupervisorSelection = new Select(supervisorSelection2);
        List<WebElement> supervisorOptions2 = adminSupervisorSelection.getOptions();
        if (!supervisorOptions2.isEmpty()) {
          WebElement lastAdmin = supervisorOptions2.get(supervisorOptions2.size() - 1);
          adminSupervisorSelection.selectByVisibleText(lastAdmin.getText());
        }
      }
      EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * The purpose of this test is to test the mobile time clock/text messaging account fields.
   */
  @Test(priority = 9)
  private void testMobileTimeClockAndTextMessagingFields() {

    SeleniumWrapper.switchToInteriorContent(driver);
    WebElement mobileTimeClockYesButton = EmployeesSettingsInterface.getWebElement(driver,
        PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON);
    if (!mobileTimeClockYesButton.isSelected()) {
      mobileTimeClockYesButton.click();
      EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
    }
    WebElement phoneNumberTextField = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.PHONE_NUMBER_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, phoneNumberTextField, FakeDataGenerator.getRandomPhoneNumber());
    WebElement newPinTextField = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.NEW_PIN_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, newPinTextField, "1234");
    WebElement reTypeNewPinTextField = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.RETYPE_PIN_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, reTypeNewPinTextField, "1234");
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is to test the mobile time clock/text messaging account phone number and pin error
   * messages.
   */
  @Test(priority = 10)
  private void testMobileTimeClockAndTextMessagingPhoneNumberErrorMessages() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    WebElement mobileTimeClockYesButton = EmployeesSettingsInterface.getWebElement(driver,
        PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON);
    if (!mobileTimeClockYesButton.isSelected()) {
      mobileTimeClockYesButton.click();
    }

    WebElement phoneNumberTextField = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.PHONE_NUMBER_FIELD);
    phoneNumberTextField.clear();
    phoneNumberTextField.sendKeys("12344");
    PayrollMenusAndLinksUtil.clickOnSaveButton();

    String errorMessageActual = PayrollHelperUtil.getErrorNoticeMessageText(driver);
    String errorMessageExpected = "Phone number (12344) is not formatted correctly. Correct format is (999) 999-9999.";
    Assert.assertEquals(errorMessageActual, errorMessageExpected, MISMATCH_ERROR_MESSAGE);

    JavascriptExecutorWrapper.scrollToElement(driver,
        EmployeesSettingsInterface.getWebElement(driver, EmployeesSettingsInterface.PHONE_NUMBER_FIELD));
    WebElement phoneNumberTextField2 = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.PHONE_NUMBER_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, phoneNumberTextField2, "12344987891");
    PayrollMenusAndLinksUtil.clickOnSaveButton();

    errorMessageActual = PayrollHelperUtil.getErrorNoticeMessageText(driver);
    errorMessageExpected = "Phone number (12344987891) is not formatted correctly. Correct format is (999) 999-9999.";
    Assert.assertEquals(errorMessageActual, errorMessageExpected, MISMATCH_ERROR_MESSAGE);
    WebElement phoneNumberTextField3 = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.PHONE_NUMBER_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, phoneNumberTextField3, "1234498789");
    PayrollMenusAndLinksUtil.clickOnSaveButton();

    WebElement newPinTextField = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.NEW_PIN_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, newPinTextField, "1234");
    WebElement reTypeNewPinTextField = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.RETYPE_PIN_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, reTypeNewPinTextField, "1234");
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is to test the mobile time clock/text messaging account phone number and PIN error
   * messages.
   */
  @Test(priority = 11)
  private void testMobileTimeClockAndTextMessagingPINNumberErrorMessages() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    WebElement mobileTimeClockYesButton = EmployeesSettingsInterface.getWebElement(driver,
        PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON);
    WebElement allowTextMessagesYesButton = EmployeesSettingsInterface.getWebElement(driver,
        PayrollConstants.ALLOW_SMS_ACCESS_YES_BUTTON);
    if (!mobileTimeClockYesButton.isSelected() && allowTextMessagesYesButton.isSelected()) {
      mobileTimeClockYesButton.click();
      allowTextMessagesYesButton.click();
    }

    WebElement phoneNumberTextField = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.PHONE_NUMBER_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, phoneNumberTextField, "1234498789");
    WebElement newPinTextField = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.NEW_PIN_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, newPinTextField, "123");
    WebElement reTypeNewPinTextField = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.RETYPE_PIN_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, reTypeNewPinTextField, "123");
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    String errorMessage = PayrollHelperUtil.getErrorNoticeMessageText(driver);
    String errorExpected = "Pin must be 4 to 8 numeric characters.";
    Assert.assertEquals(errorMessage, errorExpected, MISMATCH_ERROR_MESSAGE);

    WebElement newPinTextField2 = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.NEW_PIN_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, newPinTextField2, "123456789");
    WebElement reTypeNewPinTextField2 = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.RETYPE_PIN_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, reTypeNewPinTextField2, "123456789");
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    String errorMessage2 = PayrollHelperUtil.getErrorNoticeMessageText(driver);
    String errorExpected2 = "Pin must be 4 to 8 numeric characters.";
    Assert.assertEquals(errorMessage2, errorExpected2, MISMATCH_ERROR_MESSAGE);

    WebElement newPinTextField3 = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.NEW_PIN_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, newPinTextField3, "1234");
    WebElement reTypeNewPinTextField3 = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.RETYPE_PIN_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, reTypeNewPinTextField3, "12345");
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    String errorMessage3 = PayrollHelperUtil.getErrorNoticeMessageText(driver);
    String errorExpected3 = "Pins must match.";
    Assert.assertEquals(errorMessage3, errorExpected3, MISMATCH_ERROR_MESSAGE);
    WebElement newPinTextField4 = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.NEW_PIN_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, newPinTextField4, "1234");
    WebElement reTypeNewPinTextField4 = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.RETYPE_PIN_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, reTypeNewPinTextField4, "1234");
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is to test the mobile time clock/text messaging account phone number error messages when
   * the phone number already exists with another employee.
   */
  @Test(priority = 12)
  private void testMobileTimeClockPhoneNumberExistErrorMessages() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    remainingNextEmployees = EmployeesSettingsPage.selectNextAndReturnRemainingEmployees(remainingNextEmployees);
    WebElement mobileTimeClockYesButton = EmployeesSettingsInterface.getWebElement(driver,
        PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON);
    JavascriptExecutorWrapper.scrollToElement(driver, mobileTimeClockYesButton);
    WebElement allowTextMessagesYesButton = EmployeesSettingsInterface.getWebElement(driver,
        PayrollConstants.ALLOW_SMS_ACCESS_YES_BUTTON);
    if (!mobileTimeClockYesButton.isSelected()) {
      mobileTimeClockYesButton.click();
    }
    if (!allowTextMessagesYesButton.isSelected()) {
      allowTextMessagesYesButton.click();
    }
    if (!EmployeesSettingsInterface.getWebElement(driver, EmployeesSettingsInterface.VERTICAL_TIMESHEET_NO_BUTTON)
        .isSelected()) {
      EmployeesSettingsInterface.getWebElement(driver, EmployeesSettingsInterface.VERTICAL_TIMESHEET_NO_BUTTON).click();
    }
    EmployeesSettingsPage.deleteLaborCostCenterDefaultAllRows(driver);
    EmployeesSettingsPage.deleteRowsForMultipleRateOverride(driver);
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    WebElement phoneNumberTextField = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.PHONE_NUMBER_FIELD);
    JavascriptExecutorWrapper.scrollToElement(driver, phoneNumberTextField);
    SeleniumWrapper.sendKeyWithClear(driver, phoneNumberTextField, "1234498789");
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    String errorMessage = PayrollHelperUtil.getErrorNoticeMessageText(driver);
    String[] employeeName = errorMessage.split(" ");
    String employeeLastName = employeeName[5].trim();
    String employeeFirstAndMiddleName;
    String employeeNumber;
    if (employeeName.length > 8) {
      employeeFirstAndMiddleName = employeeName[6].trim() + " " + employeeName[7].trim();
      employeeNumber = employeeName[8].trim();
    } else {
      employeeFirstAndMiddleName = employeeName[6].trim();
      employeeNumber = employeeName[7].trim();
    }

    String errorExpected =
        "Phone number already exists by " + employeeLastName + " " + employeeFirstAndMiddleName + " " + employeeNumber;
    Assert.assertEquals(errorMessage, errorExpected, MISMATCH_ERROR_MESSAGE);
    EmployeesSettingsInterface.getWebElement(driver, EmployeesSettingsInterface.PREVIOUS_LINK).click();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    String employeeNumber2 = "(" + PayrollHelperUtil.getEmployeeNumberFromEmployeesSetting(driver) + ").";
    Assert.assertEquals(employeeNumber, employeeNumber2);
    if (!employeeNumber.equals(employeeNumber2)) {
      PayrollMenusAndLinksUtil.clickOnReturnButton();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      PayrollHelperUtil.openFirstEmployeeSettingFromEmployees(driver);
      String employeeNumber3 = PayrollHelperUtil.getEmployeeNumberFromEmployeesSetting(driver);
      Assert.assertEquals(employeeNumber, employeeNumber3);
    }
  }

  /**
   * The purpose of this test is to add rows and select costings for the employee multiple rate override.
   */
  @Test(priority = 13)
  private void testAddingAllLaborCostCenterLimits() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    for (int i = remainingNextEmployees; i > 0; i--) {
      List<WebElement> laborCostCenterLimitsTabs = EmployeesSettingsInterface.getWebElements(driver,
          EmployeesSettingsInterface.LABOR_COST_CENTER_LIMITS_TABS);
      if (laborCostCenterLimitsTabs.isEmpty()) {
        remainingNextEmployees = EmployeesSettingsPage.selectNextAndReturnRemainingEmployees(remainingNextEmployees);
      } else {
        laborCostCenterLimitsTabs.get(0).click();
        for (WebElement laborCostCenterLimitTab : laborCostCenterLimitsTabs) {
          laborCostCenterLimitTab.click();
          String costingTab = laborCostCenterLimitTab.getAttribute("id");
          String[] splitTabName = costingTab.split("B");
          String tabName = splitTabName[0].trim();
          if (tabName.equals("JO")) {
            tabName = "JOBTab";
          }
          EmployeesSettingsPage.getLaborCostCenterLimitsWorkClassificationAddAllButton(driver, tabName).click();
          SeleniumWrapper.acceptAlertIfPresent(driver);
        }
        break;
      }
    }
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is removing all labor cost center limits.
   */
  @Test(priority = 14)
  private void testRemovingAllLaborCostCenterLimits() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    for (int i = remainingNextEmployees; i > 0; i--) {
      List<WebElement> laborCostCenterLimitsTabs = EmployeesSettingsInterface.getWebElements(driver,
          EmployeesSettingsInterface.LABOR_COST_CENTER_LIMITS_TABS);
      if (laborCostCenterLimitsTabs.isEmpty()) {
        remainingNextEmployees = EmployeesSettingsPage.selectNextAndReturnRemainingEmployees(remainingNextEmployees);
      } else {
        laborCostCenterLimitsTabs.get(0).click();
        for (WebElement laborCostCenterLimitTab : laborCostCenterLimitsTabs) {
          laborCostCenterLimitTab.click();
          String costingTab = laborCostCenterLimitTab.getAttribute("id");
          String[] splitTabName = costingTab.split("B");
          String tabName = splitTabName[0].trim();
          if (tabName.equals("JO")) {
            tabName = "JOBTab";
          }
          EmployeesSettingsPage.getLaborCostCenterLimitsWorkClassificationRemoveAllButton(driver, tabName).click();
          SeleniumWrapper.acceptAlertIfPresent(driver);
        }
        break;
      }
    }
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is to add rows and select costings for the employee multiple rate override.
   */
  @Test(priority = 15)
  private void testAddingRowAndSelectingCostingsForEmployeeMultipleRateOverride() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    for (int i = remainingNextEmployees; i > 0; i--) {
      if (EmployeesSettingsInterface.getWebElements(driver,
          EmployeesSettingsInterface.EMPLOYEE_MULTIPLE_RATE_OVERRIDE_ADD_ROW_LINK).isEmpty()) {
        remainingNextEmployees = EmployeesSettingsPage.selectNextAndReturnRemainingEmployees(remainingNextEmployees);
      } else {
        if (EmployeesSettingsInterface.getWebElements(driver,
            EmployeesSettingsInterface.EMPLOYEE_MULTIPLE_RATE_OVERRIDE_ROWS).isEmpty()) {
          EmployeesSettingsInterface.getWebElement(driver,
              EmployeesSettingsInterface.EMPLOYEE_MULTIPLE_RATE_OVERRIDE_ADD_ROW_LINK).click();
          EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
        }
        break;
      }
    }
    EmployeesSettingsPage.updateEmployeeMultipleRateOverrideCostings(driver, 1);
    EmployeesSettingsPage.deleteEmployeeMultipleRateOverrideRow(driver, 1);
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is to test the error messages when adding duplicate rows for the employee multiple rate
   * override.
   */
  @Test(priority = 16)
  private void testAddingDuplicateRowForEmployeeMultipleRateOverride() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    EmployeesSettingsPage.deleteRowsForMultipleRateOverride(driver);
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.EMPLOYEE_MULTIPLE_RATE_OVERRIDE_ADD_ROW_LINK).click();
    EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.EMPLOYEE_MULTIPLE_RATE_OVERRIDE_ADD_ROW_LINK).click();
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    String errorMessage = PayrollHelperUtil.getErrorNoticeMessageText(driver);
    String[] rowNumbers = EmployeesSettingsPage.getTheRowNumbersFromErrorMessage(errorMessage);
    String errorExpected = "Rows " + rowNumbers[0] + " and " + rowNumbers[1]
        + " of Employee Multiple Rate Override are identical. Please update or remove the duplicate row.";
    Assert.assertEquals(errorMessage, errorExpected, MISMATCH_ERROR_MESSAGE);
    WebElement updateDivision = EmployeesSettingsPage.getEmployeeMultipleRateOverrideSingleCostingsDropdown(driver,
        "DIV", 2);
    Select divisionCosting = new Select(updateDivision);
    divisionCosting.selectByIndex(1);
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
    PayrollHelperUtil.deleteTableRows(driver, EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.EMPLOYEE_MULTIPLE_RATE_OVERRIDE_TABLE), 2);
    SeleniumWrapper.acceptAlertIfPresent(driver);
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is to test the updating the rate text field for the employee multiple rate override.
   */
  @Test(priority = 17)
  private void testUpdatingRateTextFieldForEmployeeMultipleRateOverride() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    List<WebElement> employeeMultipleRateOverrideRows = EmployeesSettingsInterface.getWebElements(driver,
        EmployeesSettingsInterface.EMPLOYEE_MULTIPLE_RATE_OVERRIDE_ROWS);
    if (employeeMultipleRateOverrideRows.isEmpty()) {
      EmployeesSettingsInterface.getWebElement(driver,
          EmployeesSettingsInterface.EMPLOYEE_MULTIPLE_RATE_OVERRIDE_ADD_ROW_LINK).click();
      EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
    }
    WebElement updateEmployeeMultipleRateOverrideRate = EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.EMPLOYEE_MULTIPLE_RATE_OVERRIDE_RATE_TEXT_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, updateEmployeeMultipleRateOverrideRate, "60");
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
    JavascriptExecutorWrapper.scrollToElement(driver, EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.EMPLOYEE_MULTIPLE_RATE_OVERRIDE_RATE_TEXT_FIELD));
    Assert.assertEquals(EmployeesSettingsInterface.getWebElement(driver,
        EmployeesSettingsInterface.EMPLOYEE_MULTIPLE_RATE_OVERRIDE_RATE_TEXT_FIELD).getAttribute("value"), "60");
  }

  /**
   * The purpose of this test is to add rows and select costings for the labor cost center defaults.
   */
  @Test(priority = 18)
  private void testAddingNewRowForLaborCostCenterDefaults() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollHelperUtil.setHorizontalTimesheetEmployeeLevel(driver);
    for (int i = remainingNextEmployees; i > 0; i--) {
      if (EmployeesSettingsInterface.getWebElements(driver, EmployeesSettingsInterface.LABOR_COST_CENTER_DEFAULT_TABLE)
          .isEmpty()) {
        remainingNextEmployees = EmployeesSettingsPage.selectNextAndReturnRemainingEmployees(remainingNextEmployees);
      } else {
        List<WebElement> laborCostCenterDefaultsRows = EmployeesSettingsInterface.getWebElements(driver,
            EmployeesSettingsInterface.LABOR_COST_CENTER_DEFAULT_ALL_ROWS);
        if (laborCostCenterDefaultsRows.isEmpty()) {
          EmployeesSettingsInterface.getWebElement(driver,
              EmployeesSettingsInterface.LABOR_COST_CENTER_DEFAULT_ADD_ROW_LINK).click();
          EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
        }
        JavascriptExecutorWrapper.scrollToElement(driver, EmployeesSettingsInterface.getWebElement(driver,
            EmployeesSettingsInterface.LABOR_COST_CENTER_DEFAULT_TABLE));
        EmployeesSettingsPage.updateLaborCostCenterDefaultDropDowns(driver, 1);
        EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
        EmployeesSettingsPage.deleteLaborCostCenterDefaultAddedRows(driver);
        break;
      }
    }
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is to test the error messages when adding duplicate rows for the labor cost center
   * defaults.
   */
  @Test(priority = 19)
  private void testAddingDuplicateRowForLaborCostCenterDefaults() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    if (PayrollHelperUtil.isEmployeeHasVerticalTimesheet(driver)) {
      PayrollHelperUtil.setHorizontalTimesheetEmployeeLevel(driver);
    }
    EmployeesSettingsPage.deleteLaborCostCenterDefaultAddedRows(driver);
    for (int i = remainingNextEmployees; i > 0; i--) {
      if (EmployeesSettingsInterface.getWebElements(driver, EmployeesSettingsInterface.LABOR_COST_CENTER_DEFAULT_TABLE)
          .isEmpty()) {
        remainingNextEmployees = EmployeesSettingsPage.selectNextAndReturnRemainingEmployees(remainingNextEmployees);
      } else {
        EmployeesSettingsInterface.getWebElement(driver,
            EmployeesSettingsInterface.LABOR_COST_CENTER_DEFAULT_ADD_ROW_LINK).click();
        EmployeesSettingsInterface.getWebElement(driver,
            EmployeesSettingsInterface.LABOR_COST_CENTER_DEFAULT_ADD_ROW_LINK).click();
        PayrollMenusAndLinksUtil.clickOnSaveButton();
        String actualErrorMessage = PayrollHelperUtil.getErrorNoticeMessageText(driver);
        String[] rowNumbers = EmployeesSettingsPage.getTheRowNumbersFromErrorMessage(actualErrorMessage);
        String errorExpected = "Rows " + rowNumbers[0] + " and " + rowNumbers[1]
            + " of Labor Cost Center Defaults are identical. Please update or remove the duplicate row.";
        Assert.assertEquals(actualErrorMessage, errorExpected, MISMATCH_ERROR_MESSAGE);
        EmployeesSettingsPage.deleteLaborCostCenterDefaultAddedRows(driver);
        break;
      }
    }
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is to test adding values to the text and date fields for the other pay rate table.
   */
  @Test(priority = 20)
  private void testUpdatingOtherPayRateTextFields() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    List<WebElement> otherFieldsTextFields = EmployeesSettingsInterface.getWebElements(driver,
        EmployeesSettingsInterface.OTHER_PAY_FLAT_RATES_TEXT_FIELD);
    for (WebElement otherFieldsTextField : otherFieldsTextFields) {
      if (otherFieldsTextField.isDisplayed()) {
        SeleniumWrapper.sendKeyWithClear(driver, otherFieldsTextField,
            String.valueOf(FakeDataGenerator.getRandomNumber(1, 9)));
      }
    }
    List<WebElement> otherPayDateFields = EmployeesSettingsInterface.getWebElements(driver,
        EmployeesSettingsInterface.OTHER_PAY_FLAT_RATES_EFFECTIVE_DATE_FIELDS);
    for (WebElement otherPayDateField : otherPayDateFields) {
      SeleniumWrapper.sendKeyWithClear(driver, otherPayDateField, EmployeesSettingsPage.getTodaysDate());
    }
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * The purpose of this test is to test the incorrect date value error message for the other pay rate table date field.
   */
  @Test(priority = 21)
  private void testInvalidDateInputForOtherPayRateTableDateTextField() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    for (int i = remainingNextEmployees; i > 0; i--) {
      if (EmployeesSettingsInterface.getWebElements(driver, EmployeesSettingsInterface.LABOR_COST_CENTER_DEFAULT_TABLE)
          .isEmpty()) {
        remainingNextEmployees = EmployeesSettingsPage.selectNextAndReturnRemainingEmployees(remainingNextEmployees);
      } else {
        WebElement otherPayFirstDateField = EmployeesSettingsInterface.getWebElement(driver,
            EmployeesSettingsInterface.FIRST_DATE_FIELD_FOR_OTHER_PAY_FLAT_RATES_TABLE);
        JavascriptExecutorWrapper.scrollToElement(driver, otherPayFirstDateField);
        SeleniumWrapper.sendKeyWithClear(driver, otherPayFirstDateField, "1478");
        PayrollMenusAndLinksUtil.clickOnSaveButton();
        String errorMessage = PayrollHelperUtil.getErrorNoticeMessageText(driver);
        WebElement firstPayCodeDescription = EmployeesSettingsInterface.getWebElement(driver,
            EmployeesSettingsInterface.FIRST_ROW_PAY_CODE_DESCRIPTION_FOR_OTHER_PAY_FLAT_RATES_TABLE);
        String payCodeDescription = firstPayCodeDescription.getText();
        String errorExpected = "Please enter a valid effective date for " + payCodeDescription + ".";
        Assert.assertEquals(errorMessage, errorExpected, MISMATCH_ERROR_MESSAGE);
        otherPayFirstDateField = EmployeesSettingsInterface.getWebElement(driver,
            EmployeesSettingsInterface.FIRST_DATE_FIELD_FOR_OTHER_PAY_FLAT_RATES_TABLE);
        JavascriptExecutorWrapper.scrollToElement(driver, otherPayFirstDateField);
        otherPayFirstDateField.clear();
        break;
      }
    }
    EmployeesSettingsPage.clickSaveAndVerifyEmployeeSettingSuccessNotice(driver);
  }

  /**
   * Tear down method to quit the WebDriver after all tests in this module have run.
   */
  @AfterClass(alwaysRun = true)
  public void tearDownClass() {
    DriverManager.quitDriver();
  }
}