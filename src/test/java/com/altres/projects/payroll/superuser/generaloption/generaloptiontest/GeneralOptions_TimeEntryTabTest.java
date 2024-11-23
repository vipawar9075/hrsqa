package com.altres.projects.payroll.superuser.generaloption.generaloptiontest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.exception.SeleniumExceptions;
import com.altres.login.LoginPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.GeneralTabPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.OverTimeTabPageInterface;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.TimeEntryTabPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.TimeEntryTabPageInterface;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.GeneralOptionRadioButtons;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollConstants;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollHelperUtil;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollMenusAndLinksUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Selenium scripts to test general options time entry tab.
 */
public class GeneralOptions_TimeEntryTabTest extends Base {

  static PropertyValueProvider expected = new ConfigPropertiesProviderImpl(Constants.GENERAL_OPTION_PATH);

  /**
   * This test will test the allow separate time entry. This will also check for the additional selection for the Time
   * entry methods when this setting is set to yes.
   */
  @Test(priority = 1)
  public void testAllowSeparateTimeEntry() throws Exception {
    LoginPage.loginAsSuperUser(driver);
    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
    PayrollMenusAndLinksUtil.clickTimeEntryTab();
    if (GeneralOptionRadioButtons.isSelectedGeneralOptionRadioButton("Allow Separate Time Entry", "Y")) {
      GeneralOptionRadioButtons.clickGeneralOptionsRadioButton("Allow Separate Time Entry", "N");
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
      Assert.assertTrue(GeneralOptionRadioButtons.isSelectedGeneralOptionRadioButton("Allow Separate Time Entry", "N"));
      Map<String, String> getOptions = SeleniumWrapper.getAvailableDropdownOptions(
          TimeEntryTabPageInterface.getWebElement(driver,
              TimeEntryTabPageInterface.TIME_ENTRY_METHOD_NON_EXEMPT_DROPDOWN));
      Assert.assertFalse(getOptions.containsKey("S"));
      Assert.assertFalse(getOptions.containsKey("SSV"));
      Assert.assertFalse(getOptions.containsKey("SS"));
    }

    GeneralOptionRadioButtons.clickGeneralOptionsRadioButton("Allow Separate Time Entry", "Y");
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    Assert.assertTrue(GeneralOptionRadioButtons.isSelectedGeneralOptionRadioButton("Allow Separate Time Entry", "Y"));
    Map<String, String> getoptions = SeleniumWrapper.getAvailableDropdownOptions(
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.TIME_ENTRY_METHOD_NON_EXEMPT_DROPDOWN));
    Assert.assertTrue(getoptions.containsKey("S"));
    Assert.assertTrue(getoptions.containsKey("SSV"));
    Assert.assertTrue(getoptions.containsKey("SS"));
  }

  /**
   * This test will test the time entry none-exempt methods by selecting each option available and saving the
   * selection.
   */
  @Test(priority = 2)
  public static void testTimeEntryNonExemptMethods() throws Exception {

    if (GeneralOptionRadioButtons.isSelectedGeneralOptionRadioButton("Allow Separate Time Entry", "N")) {
      GeneralOptionRadioButtons.clickGeneralOptionsRadioButton("Allow Separate Time Entry", "Y");
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
      Assert.assertTrue(GeneralOptionRadioButtons.isSelectedGeneralOptionRadioButton("Allow Separate Time Entry", "Y"));
    }
    TimeEntryTabPage.selectAndSaveAndVerifyNonExemptTimeEntryMethods(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.TIME_ENTRY_METHOD_NON_EXEMPT_DROPDOWN));
  }

  /**
   * This test will test the time entry exempt methods by selecting each option available and saving the selection.
   */
  @Test(priority = 3)
  public static void testTimeEntryExemptMethods() throws Exception {
    if (GeneralOptionRadioButtons.isSelectedGeneralOptionRadioButton("Allow Separate Time Entry", "Y")) {
      GeneralOptionRadioButtons.clickGeneralOptionsRadioButton("Allow Separate Time Entry", "N");
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
      Assert.assertTrue(GeneralOptionRadioButtons.isSelectedGeneralOptionRadioButton("Allow Separate Time Entry", "N"));
    }
    TimeEntryTabPage.selectAndSaveAndVerifyExemptTimeEntryMethods(
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.TIME_ENTRY_METHOD_EXEMPT_DROPDOWN));
  }

  /**
   * This will test the allow employee-level time entry method override and the employee level selection for this field.
   * This will test setting the allow employee-level time entry method override to no and checks that the employee level
   * dropdown doesn't show and that the employee group table is not hidden.
   */
  @Test(priority = 4)
  public static void testAllowEmployeeLevelTimeEntryMethodOverride() {

    if (GeneralOptionRadioButtons.isSelectedGeneralOptionRadioButton("Allow Employee-level Time Entry Method Override",
        "N")) {
      setSettingToYes("Allow Employee-level Time Entry Method Override", "Y");
    }
    if (GeneralOptionRadioButtons.isSelectedGeneralOptionRadioButton("Allow Employee-level Time Entry Method Override",
        "Y")) {
      setSettingToNo("Allow Employee-level Time Entry Method Override", "N");
    }
    Assert.assertFalse(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.TIME_ENTRY_METHOD_OVERRIDE_EMPLOYEE_LEVEL_DROPDOWN).isDisplayed());
    Assert.assertTrue(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_TABLE).isDisplayed());
    setSettingToYes("Allow Employee-level Time Entry Method Override", "Y");
    PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.TIME_ENTRY_METHOD_OVERRIDE_EMPLOYEE_LEVEL_DROPDOWN));
    TimeEntryTabPage.selectEmployeeLevelOption("Test: Enabled");
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
  }

  /**
   * This will test setting the allow employee-level time entry method override to no and test the time entry method by
   * employee group table and select each of the method saving the form on each selection.This will test adding a new
   * row with the same data as the first row and test the error message for duplicate rows Test deleting a row.
   */
  @Test(priority = 5)
  public static void testTimeEntryMethodByEmployeeGroup() throws Exception {

    if (GeneralOptionRadioButtons.isSelectedGeneralOptionRadioButton("Allow Employee-level Time Entry Method Override",
        "N")) {
      setSettingToYes("Allow Employee-level Time Entry Method Override", "Y");
    }
    setSettingToNo("Allow Employee-level Time Entry Method Override", "N");
    Assert.assertTrue(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_TABLE).isDisplayed());
    if (TimeEntryTabPageInterface.getWebElements(driver,
        TimeEntryTabPageInterface.TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_ROW).isEmpty()) {
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_ADD_ROW).click();
    }
    SeleniumWrapper.setDropDownByIndex(
        TimeEntryTabPage.getTimeEntryMethodByEmployeeGroupEmployeeGroupSelection(driver, 1), 1);
    TimeEntryTabPage.selectAndSaveAndVerifyTimeEntryMethodsByEmployeeGroupTable();
    int currentNumberOfRows = PayrollHelperUtil.getNumberOfRows(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_TABLE));
    String currentMethodSelectedRow1 = TimeEntryTabPage.getCurrentSelectedOptionValue();
    TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_ADD_ROW).click();
    SeleniumWrapper.selectDropdownByVisibleText(
        By.xpath("//select[@name='time_entry_method_by_employee_group_code_" + 2 + "']"), currentMethodSelectedRow1);
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    String errorMessageDisplayed = TimeEntryTabPage.getNoticeMessageText(driver);
    String errorActualMessage = expected.getPropertyValueByKey("time_entry_duplicate_employee_group");
    Assert.assertEquals(errorActualMessage, errorMessageDisplayed);
    PayrollHelperUtil.deleteTableRow(driver, TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_TABLE), 2);
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    int getRowNumber = PayrollHelperUtil.getNumberOfRows(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_TABLE));
    Assert.assertEquals(currentNumberOfRows, getRowNumber);
  }

  /**
   * Test selecting disabled and check that none of the subfields are visible.
   */
  @Test(priority = 6)
  public static void testSettingTheStrictClockingFieldsToDisabled() {

    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.STRICT_CLOCKING_SELECT));
    SeleniumWrapper.selectDropdownByVisibleText(TimeEntryTabPage.STRICT_CLOCKING, "Disabled");
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.STRICT_CLOCKING_SELECT));
    Assert.assertTrue(!GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Require Supervisor to enter Clock-out time after maximum number of hours worked is reached", "Y")
        && !GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Allow punches on mobile app when Strict Clocking is on (Ability to disable on Employee Setting)", "Y")
        && !GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Require Employees to enter Notes when Clocking Out", "Y")
        && !GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Allow Employees to Switch to different costings when clocking out", "Y"));
  }

  /**
   * Test selecting the in option and also test the allow punches on mobile app when strict clocking subfield.
   */
  @Test(priority = 7)
  public static void testSettingTheStrictClockingFieldsToIn() {

    PayrollHelperUtil.setTimeEntryMethodToDetail();
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.STRICT_CLOCKING_SELECT));
    SeleniumWrapper.selectDropdownByVisibleText(TimeEntryTabPage.STRICT_CLOCKING, "In");
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.STRICT_CLOCKING_SELECT));
    Assert.assertTrue(!GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Require Supervisor to enter Clock-out time after maximum number of hours worked is reached", "Y")
        && !GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Require Employees to enter Notes when Clocking Out", "Y")
        && !GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Allow Employees to Switch to different costings when clocking out", "Y"));
    GeneralOptionRadioButtons.clickGeneralOptionsRadioButton(
        "Allow punches on mobile app when Strict Clocking is on (Ability to disable on Employee Setting)", "Y");
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.STRICT_CLOCKING_SELECT));
    PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.ALLOW_MOBILE_PUNCH_FOR_STRICT_DROPDOWN));
    SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.ALLOW_MOBILE_PUNCH_FOR_STRICT_DROPDOWN), 2);
  }

  /**
   * Test selecting the out option and also test the require supervisor to enter clock-out time after maximum number of
   * hours worked is reached.
   */
  @Test(priority = 8)
  public static void testSettingTheStrictClockingFieldsToOut() {

    PayrollHelperUtil.setTimeEntryMethodToDetail();
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.STRICT_CLOCKING_SELECT));
    SeleniumWrapper.selectDropdownByVisibleText(TimeEntryTabPage.STRICT_CLOCKING, "Out");
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.STRICT_CLOCKING_SELECT));
    Assert.assertTrue(GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Allow punches on mobile app when Strict Clocking is on (Ability to disable on Employee Setting)", "Y"));
    Assert.assertTrue(GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Require Supervisor to enter Clock-out time after maximum number of hours worked is reached", "Y"));
    Assert.assertTrue(!GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Require Employees to enter Notes when Clocking Out", "Y")
        && !GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Allow Employees to Switch to different costings when clocking out", "Y"));
    GeneralOptionRadioButtons.clickGeneralOptionsRadioButton(
        "Require Supervisor to enter Clock-out time after maximum number of hours worked is reached", "Y");
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.STRICT_CLOCKING_SELECT));
    PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.REQUIRE_SUPERVISOR_TO_ENTER_CLOCK_OUT_TIME_EMPLOYEE_LEVEL_SELECTION));
    SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.REQUIRE_SUPERVISOR_TO_ENTER_CLOCK_OUT_TIME_EMPLOYEE_LEVEL_SELECTION), 2);
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
  }

  /**
   * Test selecting the both option and also test the allow employees to switch to different costings as well as the
   * require employees to enter notes.
   */
  @Test(priority = 9)
  public static void testSettingTheStrictClockingFieldsToBoth() {

    PayrollHelperUtil.setTimeEntryMethodToDetail();
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.STRICT_CLOCKING_SELECT));
    SeleniumWrapper.selectDropdownByVisibleText(TimeEntryTabPage.STRICT_CLOCKING, "Both");
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.STRICT_CLOCKING_SELECT));
    Assert.assertTrue(GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Require Employees to enter Notes when Clocking Out", "Y"));
    Assert.assertTrue(GeneralOptionRadioButtons.isGeneralOptionsRadioButtonDisplayed(
        "Require Supervisor to enter Clock-out time after maximum number of hours worked is reached", "Y"));
    GeneralOptionRadioButtons.clickGeneralOptionsRadioButton("Require Employees to enter Notes when Clocking Out", "Y");
    GeneralOptionRadioButtons.clickGeneralOptionsRadioButton(
        "Allow Employees to Switch to different costings when clocking out", "Y");
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.STRICT_CLOCKING_SELECT));
    PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.REQUIRE_EMPLOYEES_TO_ENTER_NOTES_WHEN_CLOCKING_OUT_EMPLOYEE_LEVEL_SELECTION));
    SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.REQUIRE_EMPLOYEES_TO_ENTER_NOTES_WHEN_CLOCKING_OUT_EMPLOYEE_LEVEL_SELECTION), 2);
    PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.ALLOW_EMPLOYEES_TO_SWITCH_COSTINGS_WHEN_CLOCKING_OUT_EMPLOYEE_LEVEL_SELECTION));
    SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.ALLOW_EMPLOYEES_TO_SWITCH_COSTINGS_WHEN_CLOCKING_OUT_EMPLOYEE_LEVEL_SELECTION), 2);
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
  }

  /**
   * Test the clock-in rounding yes, no radio buttons and employee group selection.
   */
  @Test(priority = 10)
  public static void testClockInRoundingField() {

    PayrollHelperUtil.setTimeEntryMethodToDetail();
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON));
    if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON)
        .isSelected()) {
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_NO_BUTTON).click();
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    }
    Assert.assertTrue(!TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_EMPLOYEE_LEVEL_SELECTION).isDisplayed()
        && !TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_INCREMENT_SELECTION).isDisplayed()
        && !TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_POINT_TEXT_FIELD).isDisplayed());
    TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON).click();
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON));
    Assert.assertTrue(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_EMPLOYEE_LEVEL_SELECTION).isDisplayed()
        && TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_INCREMENT_SELECTION).isDisplayed()
        && TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_POINT_TEXT_FIELD)
        .isDisplayed());
    PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_EMPLOYEE_LEVEL_SELECTION));
    SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.ALLOW_EMPLOYEES_TO_SWITCH_COSTINGS_WHEN_CLOCKING_OUT_EMPLOYEE_LEVEL_SELECTION), 2);
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
  }

  /**
   * Test the clock-in rounding increment options.
   */
  @Test(priority = 11)
  public static void testClockInRoundingIncrementField() {
    PayrollHelperUtil.setTimeEntryMethodToDetail();
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON));
    if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_NO_BUTTON)
        .isSelected()) {
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    }
    TimeEntryTabPage.selectIncrementOptionAndVerify();
  }

  /**
   * This test will check clock in rounding rounding point messages.
   */
  @Test(priority = 12)
  public static void testClockInRoundingRoundingPointMessages() {
    PayrollHelperUtil.setTimeEntryMethodToDetail();
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON));
    if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_NO_BUTTON)
        .isSelected()) {
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    }
    SeleniumWrapper.selectDropdownByOptionValue(TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_INCREMENT_SELECTION, "6");
    SeleniumWrapper.sendKeyWithClear(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_POINT_TEXT_FIELD),
        "8");
    String alertMessage = SeleniumWrapper.getAlertText(driver);
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertEquals(alertMessage, "The rounding point value must be less than the increment value.");
    SeleniumWrapper.selectDropdownByOptionValue(TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_INCREMENT_SELECTION, "15");
    SeleniumWrapper.sendKeyWithClear(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_POINT_TEXT_FIELD),
        "17");
    SeleniumWrapper.acceptAlertIfPresent(driver);
    SeleniumWrapper.selectDropdownByOptionValue(TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_INCREMENT_SELECTION, "20");
    SeleniumWrapper.sendKeyWithClear(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_POINT_TEXT_FIELD),
        "23");
    SeleniumWrapper.acceptAlertIfPresent(driver);
    SeleniumWrapper.selectDropdownByOptionValue(TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_INCREMENT_SELECTION, "30");
    SeleniumWrapper.sendKeyWithClear(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_POINT_TEXT_FIELD),
        "32");
    SeleniumWrapper.acceptAlertIfPresent(driver);
    SeleniumWrapper.selectDropdownByOptionValue(TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_INCREMENT_SELECTION, "6");
    SeleniumWrapper.sendKeyWithClear(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_POINT_TEXT_FIELD),
        "5");
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
  }

  /**
   * Test the round clocked-in time to schedule field.
   */
  @Test(priority = 13)
  public static void testClockInRoundingRoundInTimeToSchedule() {

    PayrollHelperUtil.setTimeEntryMethodToDetail();
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON));
    if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_NO_BUTTON)
        .isSelected()) {
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    }
    if (TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.ROUND_CLOCKED_IN_TIME_TO_SCHEDULE_YES_BUTTON).isSelected()) {
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ROUND_CLOCKED_IN_TIME_TO_SCHEDULE_NO_BUTTON).click();
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    }
    Assert.assertTrue(
        !TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BEFORE_START_TIME_MINUTES)
            .isDisplayed() && !TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.AFTER_START_TIME_MINUTES).isDisplayed());
    TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.ROUND_CLOCKED_IN_TIME_TO_SCHEDULE_YES_BUTTON).click();
    JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.ROUND_CLOCKED_IN_TIME_TO_SCHEDULE_YES_BUTTON));
    SeleniumWrapper.sendKeyWithClear(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BEFORE_START_TIME_MINUTES), "10");
    SeleniumWrapper.sendKeyWithClear(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AFTER_START_TIME_MINUTES), "10");
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.ROUND_CLOCKED_IN_TIME_TO_SCHEDULE_YES_BUTTON));
    String getBeforeStartTimeValue = TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.BEFORE_START_TIME_MINUTES).getAttribute("value");
    String getAfterStartTimeValues = TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.AFTER_START_TIME_MINUTES).getAttribute("value");
    Assert.assertEquals(getBeforeStartTimeValue, "10");
    Assert.assertEquals(getAfterStartTimeValues, "10");
  }

  /**
   * Test the clock-out rounding yes, no radio buttons and employee group selection.
   */
  @Test(priority = 14)
  public static void testClockOutRoundingField() {
    PayrollHelperUtil.setTimeEntryMethodToDetail();
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON));
    if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_YES_BUTTON)
        .isSelected()) {
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_NO_BUTTON).click();
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    }
    Assert.assertTrue(!TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_EMPLOYEE_LEVEL_SELECTION).isDisplayed()
        && !TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_INCREMENT_SELECTION).isDisplayed()
        && !TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_POINT_TEXT_FIELD).isDisplayed()
        && !TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.ROUND_CLOCKED_OUT_TIME_TO_SCHEDULE_NO_BUTTON).isDisplayed());
    TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_YES_BUTTON).click();
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON));
    Assert.assertTrue(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_EMPLOYEE_LEVEL_SELECTION).isDisplayed()
        && TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_INCREMENT_SELECTION).isDisplayed()
        && TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_POINT_TEXT_FIELD).isDisplayed()
        && TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.ROUND_CLOCKED_OUT_TIME_TO_SCHEDULE_NO_BUTTON).isDisplayed());
    PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_EMPLOYEE_LEVEL_SELECTION));
    SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
        TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_EMPLOYEE_LEVEL_SELECTION), 2);
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
  }

  /**
   * Test the clock-out rounding increment options.
   */
  @Test(priority = 15)
  public static void testClockOutRoundingIncrementField() {

    PayrollHelperUtil.setTimeEntryMethodToDetail();
    JavascriptExecutorWrapper.scrollToElement(driver,
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_IN_ROUNDING_YES_BUTTON));
    if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_NO_BUTTON)
        .isSelected()) {
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    }
    TimeEntryTabPage.selectClockOutRoundingIncrementOptionAndVerify();
  }

  /**
   * Test the pop-up messages from the clock-out rounding point field.
   *
   * @throws Exception
   */
  @Test(priority = 16)
  public void testClockOutRoundingPointMessages() throws Exception {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      PayrollHelperUtil.setTimeEntryMethodToDetail();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_NO_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_YES_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_INCREMENT_SELECTION));
      SeleniumWrapper.selectDropdownByOptionValue(TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_INCREMENT_SELECTION,
          "6");
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_POINT_TEXT_FIELD), "8");
      Assert.assertEquals(SeleniumWrapper.getAlertText(driver),
          "The rounding point value must be less than the increment value.");
      SeleniumWrapper.acceptAlertIfPresent(driver);
      PayrollMenusAndLinksUtil.clickOnSaveButton();

      SeleniumWrapper.selectDropdownByOptionValue(TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_INCREMENT_SELECTION,
          "15");
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_POINT_TEXT_FIELD), "17");
      SeleniumWrapper.acceptAlertIfPresent(driver);
      PayrollMenusAndLinksUtil.clickOnSaveButton();

      SeleniumWrapper.selectDropdownByOptionValue(TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_INCREMENT_SELECTION,
          "20");
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_POINT_TEXT_FIELD), "23");
      SeleniumWrapper.acceptAlertIfPresent(driver);
      PayrollMenusAndLinksUtil.clickOnSaveButton();

      SeleniumWrapper.selectDropdownByOptionValue(TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_INCREMENT_SELECTION,
          "30");
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_POINT_TEXT_FIELD), "32");
      SeleniumWrapper.acceptAlertIfPresent(driver);
      PayrollMenusAndLinksUtil.clickOnSaveButton();

      SeleniumWrapper.selectDropdownByOptionValue(TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_INCREMENT_SELECTION,
          "6");
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_POINT_TEXT_FIELD), "5");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * Test the round clocked-out time to schedule field.
   *
   * @throws Exception
   */
  @Test(priority = 17)
  public void testRoundClockedOutTimeToSchedule() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      PayrollHelperUtil.setTimeEntryMethodToDetail();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_NO_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_YES_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }

      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ROUND_CLOCKED_OUT_TIME_TO_SCHEDULE_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.ROUND_CLOCKED_OUT_TIME_TO_SCHEDULE_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.BEFORE_END_TIME_MINUTES)
          && SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.AFTER_END_TIME_MINUTES));

      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ROUND_CLOCKED_OUT_TIME_TO_SCHEDULE_YES_BUTTON).click();
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(TimeEntryTabPageInterface.BEFORE_END_TIME_MINUTES);
      SeleniumWrapper.sendKeyWithClear(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BEFORE_END_TIME_MINUTES), "10");
      SeleniumWrapper.sendKeyWithClear(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AFTER_END_TIME_MINUTES), "10");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ROUND_CLOCKED_OUT_TIME_TO_SCHEDULE_YES_BUTTON));
      String beforeEndTimeValue = TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.BEFORE_END_TIME_MINUTES).getAttribute("value");
      String afterEndTimeValues = TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AFTER_END_TIME_MINUTES).getAttribute("value");
      Assert.assertEquals(beforeEndTimeValue, "10");
      Assert.assertEquals(afterEndTimeValues, "10");
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * Test how should we display the in/out time and daily total hours fields.
   *
   * @throws Exception
   */
  @Test(priority = 18)
  public void testHowToDisplayInOutTimeAndDailyHoursFields() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      PayrollHelperUtil.setTimeEntryMethodToDetail();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_NO_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CLOCK_OUT_ROUNDING_YES_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.HOW_TO_DISPLAY_IN_AND_OUT_TIME_AND_DAILY_TOTAL_HOURS_FIELD_ROUNDED_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.HOW_TO_DISPLAY_IN_AND_OUT_TIME_AND_DAILY_TOTAL_HOURS_FIELD_ROUNDED_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      Assert.assertFalse(TimeEntryTabPageInterface.getWebElement(driver,
              TimeEntryTabPageInterface.HOW_TO_DISPLAY_IN_AND_OUT_TIME_AND_DAILY_TOTAL_HOURS_FIELD_ACTUAL_BUTTON)
          .isSelected());
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.HOW_TO_DISPLAY_IN_AND_OUT_TIME_AND_DAILY_TOTAL_HOURS_FIELD_ROUNDED_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.HOW_TO_DISPLAY_IN_AND_OUT_TIME_AND_DAILY_TOTAL_HOURS_FIELD_ACTUAL_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      Assert.assertFalse(TimeEntryTabPageInterface.getWebElement(driver,
              TimeEntryTabPageInterface.HOW_TO_DISPLAY_IN_AND_OUT_TIME_AND_DAILY_TOTAL_HOURS_FIELD_ROUNDED_BUTTON)
          .isSelected());
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * Test the date entry restriction for non-exempt and exempt radio buttons.
   *
   * @throws Exception
   */
  @Test(priority = 19)
  public void testDateEntryRestriction() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_NON_EXEMPT_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_NON_EXEMPT_NO_BUTTON).click();
      }
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_EXEMPT_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_EXEMPT_NO_BUTTON).click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_BY_SELECTION)
              && SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_FOR_SELECTION));

      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_NON_EXEMPT_YES_BUTTON).click();
      Assert.assertTrue(
          SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_BY_SELECTION)
              && SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_FOR_SELECTION));
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_NON_EXEMPT_NO_BUTTON).click();
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_EXEMPT_YES_BUTTON).click();
      Assert.assertTrue(
          SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_BY_SELECTION)
              && SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_FOR_SELECTION));
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * Test the date entry restriction by day.
   *
   * @throws Exception
   */
  @Test(priority = 20)
  public void testDateEntryRestrictionByDay() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_NON_EXEMPT_NO_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_NON_EXEMPT_YES_BUTTON).click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      SeleniumWrapper.selectDropdownByVisibleText(TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_BY_SELECTION, "Day");
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_BUFFER_TEXT_FIELD), "24");
      SeleniumWrapper.selectDropdownByVisibleText(TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_FOR_SELECTION,
          "Employee");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_BY_SELECTION));
      String currentBySelection = SeleniumWrapper.getCurrentSelectedOptionText(
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_BY_SELECTION);
      String currentForSelection = SeleniumWrapper.getCurrentSelectedOptionText(
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_FOR_SELECTION);
      Assert.assertEquals(currentBySelection, "Day");
      Assert.assertEquals(currentForSelection, "Employee");

      SeleniumWrapper.selectDropdownByVisibleText(TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_FOR_SELECTION,
          "Employee & Supervisor");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      currentForSelection = SeleniumWrapper.getCurrentSelectedOptionText(
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_FOR_SELECTION);
      Assert.assertEquals(currentForSelection, "Employee & Supervisor");
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * Test the date entry restriction by week.
   *
   * @throws Exception
   */
  @Test(priority = 21)
  public void testDateEntryRestrictionByWeek() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_NON_EXEMPT_NO_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_NON_EXEMPT_YES_BUTTON).click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      SeleniumWrapper.selectDropdownByVisibleText(TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_BY_SELECTION,
          "Week");
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_BUFFER_TEXT_FIELD), "32");
      SeleniumWrapper.selectDropdownByVisibleText(TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_FOR_SELECTION,
          "Employee");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_BY_SELECTION));
      String currentBySelection = SeleniumWrapper.getCurrentSelectedOptionText(
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_BY_SELECTION);
      String currentForSelection = SeleniumWrapper.getCurrentSelectedOptionText(
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_FOR_SELECTION);
      Assert.assertEquals(currentBySelection, "Week");
      Assert.assertEquals(currentForSelection, "Employee");

      SeleniumWrapper.selectDropdownByVisibleText(TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_FOR_SELECTION,
          "Employee & Supervisor");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      currentForSelection = SeleniumWrapper.getCurrentSelectedOptionText(
          TimeEntryTabPageInterface.DATE_ENTRY_RESTRICTION_FOR_SELECTION);
      Assert.assertEquals(currentForSelection, "Employee & Supervisor");
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * Test the allow timesheet fill all (exempt employees \W summary time entry method).
   *
   * @throws Exception
   */
  @Test(priority = 22)
  public void testAllowTimesheetFillAll() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TIMESHEET_FILL_ALL_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_TIMESHEET_FILL_ALL_YES_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_TIMESHEET_FILL_ALL_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TIMESHEET_FILL_ALL_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_TIMESHEET_FILL_ALL_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      Assert.assertTrue(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_TIMESHEET_FILL_ALL_YES_BUTTON)
              .isSelected());
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * Test the allow salary non-exempt employees to receive base salary [proration] and receive overtime. Uses the time
   * entry method for exempt employees when company is not computing overtime via peel off.
   *
   * @throws Exception
   */
  @Test(priority = 23)
  public void testAllowSalaryNonExemptEmployeesToReceiveBaseSalaryField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_EMPLOYEE_LEVEL_SELECTION)
          && SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.REMOVE_ADDITIONAL_REGULAR_HOURS_YES_BUTTON)
          && SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.ALLOW_EMPLOYEE_AUTO_PAY_YES_BUTTON));
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_YES_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_EMPLOYEE_LEVEL_SELECTION)
          && SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.REMOVE_ADDITIONAL_REGULAR_HOURS_YES_BUTTON)
          && SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.ALLOW_EMPLOYEE_AUTO_PAY_YES_BUTTON));

      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
              TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_EMPLOYEE_LEVEL_SELECTION),
          2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * Test the remove additional regular hours field. If timesheet contains none time worked hours, the regular hours
   * will always adjust down to their pay period hours. This is a subfield of allow salary to non-exempt employees to
   * receive base salary.
   *
   * @throws Exception
   */
  @Test(priority = 24)
  public void testRemoveAdditionalRegularHoursField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_NO_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_YES_BUTTON).click();
      }
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.REMOVE_ADDITIONAL_REGULAR_HOURS_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.REMOVE_ADDITIONAL_REGULAR_HOURS_NO_BUTTON).click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.REMOVE_ADDITIONAL_REGULAR_HOURS_YES_BUTTON));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.REMOVE_ADDITIONAL_REGULAR_HOURS_EMPLOYEE_LEVEL_SELECTION));
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.REMOVE_ADDITIONAL_REGULAR_HOURS_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.REMOVE_ADDITIONAL_REGULAR_HOURS_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.REMOVE_ADDITIONAL_REGULAR_HOURS_YES_BUTTON));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.REMOVE_ADDITIONAL_REGULAR_HOURS_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.REMOVE_ADDITIONAL_REGULAR_HOURS_EMPLOYEE_LEVEL_SELECTION), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * Test the allow employee auto pay field, a subfield of allow salary non-exempt employees to receive base salary.
   *
   * @throws Exception
   */
  @Test(priority = 25)
  public void testAllowEmployeeAutoPayField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_NO_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.ALLOW_SALARY_NON_EXEMPT_EMPLOYEES_TO_RECEIVE_BASE_SALARY_YES_BUTTON).click();
      }
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_EMPLOYEE_AUTO_PAY_YES_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_EMPLOYEE_AUTO_PAY_NO_BUTTON)
            .click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EMPLOYEE_AUTO_PAY_YES_BUTTON));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.ALLOW_EMPLOYEE_AUTO_PAY_EMPLOYEE_LEVEL_SELECTION));

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EMPLOYEE_AUTO_PAY_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_EMPLOYEE_AUTO_PAY_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EMPLOYEE_AUTO_PAY_YES_BUTTON));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EMPLOYEE_AUTO_PAY_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EMPLOYEE_AUTO_PAY_EMPLOYEE_LEVEL_SELECTION), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * Test the uses tip credit field.
   *
   * @throws Exception
   */
  @Test(priority = 26)
  public void testUsesTipCreditField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.USES_TIP_CREDIT_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.USES_TIP_CREDIT_YES_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.USES_TIP_CREDIT_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.USES_TIP_CREDIT_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.USES_TIP_CREDIT_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      Assert.assertTrue(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.USES_TIP_CREDIT_YES_BUTTON)
              .isSelected());
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the certified payroll field.
   *
   * @throws Exception
   */
  @Test(priority = 27)
  public void testCertifiedPayrollField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      PayrollMenusAndLinksUtil.clickOvertimeTab();
      if (OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_DAILY_OVERTIME_YES_BUTTON)
          .isSelected()) {
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_NO_BUTTON)
            .click();
      }
      PayrollMenusAndLinksUtil.clickTimeEntryTab();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_YES_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertTrue(!SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.CERTIFIED_PAYROLL_DROPDOWN)
          && !SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.COMPUTE_OVER_TIME_FOR_CERTIFIED_EMPLOYEES_YES_BUTTON));
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_YES_BUTTON).click();
      if (TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON).click();
        SeleniumWrapper.acceptAlertIfPresent(driver);
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_YES_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.CERTIFIED_PAYROLL_DROPDOWN)
          && SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.COMPUTE_OVER_TIME_FOR_CERTIFIED_EMPLOYEES_YES_BUTTON));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_DROPDOWN));
      SeleniumWrapper.setDropDownByIndex(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_DROPDOWN), 2);
      SeleniumWrapper.acceptAlertIfPresent(driver);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the certified payroll error messages.
   *
   * @throws Exception
   */
  @Test(priority = 28)
  public void testCertifiedPayrollErrorMessages() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      PayrollMenusAndLinksUtil.clickOvertimeTab();
      if (OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_DAILY_OVERTIME_YES_BUTTON)
          .isSelected()) {
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_NO_BUTTON)
            .click();
      }
      PayrollMenusAndLinksUtil.clickTimeEntryTab();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_NO_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_YES_BUTTON).click();
      }
      SeleniumWrapper.setDropDownByIndex(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_DROPDOWN), 0);
      SeleniumWrapper.acceptAlertIfPresent(driver);
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_YES_BUTTON).click();
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON).click();
      }
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_EMPLOYEE_LEVEL), 0);
      SeleniumWrapper.acceptAlertIfPresent(driver);
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON).click();
      }
      PayrollMenusAndLinksUtil.clickOnSaveButton();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      new WebDriverWait(driver, Duration.ofSeconds(10));
      Assert.assertEquals(TimeEntryTabPage.getNoticeMessageText(driver),
          "You cannot use a default employee group for Certified Payroll in combination with a "
              + "default employee group for Mobile Time Clock Entry.");
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_EMPLOYEE_LEVEL), 3);
      SeleniumWrapper.acceptAlertIfPresent(driver);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the compute overtime for certified employees. This is a subfield from certified payroll
   * field.
   *
   * @throws Exception
   */
  @Test(priority = 29)
  public void testComputeOvertimeForCertifiedEmployeesField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      PayrollMenusAndLinksUtil.clickOvertimeTab();
      if (OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_DAILY_OVERTIME_YES_BUTTON)
          .isSelected()) {
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_NO_BUTTON)
            .click();
      }
      PayrollMenusAndLinksUtil.clickTimeEntryTab();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_NO_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_YES_BUTTON).click();
      }
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.COMPUTE_OVER_TIME_FOR_CERTIFIED_EMPLOYEES_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.COMPUTE_OVER_TIME_FOR_CERTIFIED_EMPLOYEES_NO_BUTTON).click();
      }
      if (TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON).isSelected();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.COMPUTE_OVER_TIME_FOR_CERTIFIED_EMPLOYEES_NO_BUTTON));
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.CERTIFIED_DETAILED_ENTRY_DROPDOWN));
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.COMPUTE_OVER_TIME_FOR_CERTIFIED_EMPLOYEES_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.COMPUTE_OVER_TIME_FOR_CERTIFIED_EMPLOYEES_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.COMPUTE_OVER_TIME_FOR_CERTIFIED_EMPLOYEES_YES_BUTTON));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_DETAILED_ENTRY_DROPDOWN));
      SeleniumWrapper.setDropDownByIndex(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_DETAILED_ENTRY_DROPDOWN),
          2);
      SeleniumWrapper.acceptAlertIfPresent(driver);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the compute overtime for certified employees. This is a subfield from certified payroll
   * field.
   *
   * @throws Exception
   */
  @Test(priority = 30)
  public void testAllowMobileTimeClockEntryField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          SeleniumWrapper.findElement(driver, By.id("certified_payroll_0")));
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_YES_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.CERTIFIED_PAYROLL_NO_BUTTON).click();
      }
      if (TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON).click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON));
      Assert.assertFalse(!SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_EMPLOYEE_LEVEL)
          && !SeleniumWrapper.isLocatorDisplayed(
          PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN)
          && !SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.MOBILE_TIP_PAY_CODE));
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_EMPLOYEE_LEVEL), 1);
      TimeEntryTabPage.setDropdownByText(TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN), "Via separate menu");

      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_EMPLOYEE_LEVEL)
          && SeleniumWrapper.isLocatorDisplayed(
          PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN)
          && SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.MOBILE_TIP_PAY_CODE));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_EMPLOYEE_LEVEL), 2);
      SeleniumWrapper.acceptAlertIfPresent(driver);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow tip recording via mobile time clock.
   *
   * @throws Exception
   */
  @Test(priority = 31)
  public void testAllowTipRecordingViaMobileTimeClock() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      TimeEntryTabPage.setDropdownByText(TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN), "Via separate menu");
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.MOBILE_TIP_PAY_CODE));
      TimeEntryTabPage.addFirstOptionToSelectedTable(driver,
          TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.MOBILE_TIP_PAY_CODES_AVAILABLE));
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN));
      Map<String, String> tipMethodAvailableOptions = SeleniumWrapper.getAvailableDropdownOptions(
          TimeEntryTabPageInterface.getWebElement(driver,
              PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN));
      List<String> allowTipRecordingOptionsValues = new ArrayList<>(tipMethodAvailableOptions.keySet());
      for (String allowTipRecordingOptionsValue : allowTipRecordingOptionsValues) {
        WebElement allowTipRecordingOptionsWebElement = TimeEntryTabPageInterface.getWebElement(driver,
            PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN);
        Select allowTipRecordingOptions = new Select(allowTipRecordingOptionsWebElement);
        allowTipRecordingOptions.selectByValue(allowTipRecordingOptionsValue);
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the tip pay code multi-selector table.
   *
   * @throws Exception
   */
  @Test(priority = 32)
  public void testTipPayCodeTable() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN));
      TimeEntryTabPage.setDropdownByText(TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN), "Via separate menu");
      TimeEntryTabPage.addAllOptionToSelectedTable(driver);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MOBILE_TIP_PAY_CODE));
      TimeEntryTabPage.removeAllOptionsFromSelectedTable(driver);
      TimeEntryTabPage.addFirstOptionToSelectedTable(driver,
          TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.MOBILE_TIP_PAY_CODES_AVAILABLE));
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the tip pay code multi-selector table error message.
   *
   * @throws Exception
   */
  @Test(priority = 33)
  public void testTipPayCodeTableErrorMessage() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN));
      TimeEntryTabPage.setDropdownByText(TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_TIP_RECORDING_VIA_MOBILE_TIME_CLOCK_DROPDOWN), "Via separate menu");
      TimeEntryTabPage.removeAllOptionsFromSelectedTable(driver);
      PayrollMenusAndLinksUtil.clickOnSaveButton();
      String expectedErrorMessage = TimeEntryTabPage.getNoticeMessageText(driver);
      String actualErrorMessage = "You must select at least one tip pay code.";
      Assert.assertEquals(actualErrorMessage, expectedErrorMessage);
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MOBILE_TIP_PAY_CODE));

      TimeEntryTabPage.addFirstOptionToSelectedTable(driver,
          TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.MOBILE_TIP_PAY_CODES_AVAILABLE));
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the disable favorites field a subfield of allow mobile time clock entry.
   *
   * @throws Exception
   */
  @Test(priority = 34)
  public void testDisableFavoriteField() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_NO_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON).click();
      }
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MOBILE_DISABLE_FAVORITES_YES_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MOBILE_DISABLE_FAVORITES_NO_BUTTON)
            .click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.MOBILE_DISABLE_FAVORITES_YES_BUTTON));

      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.MOBILE_DISABLE_FAVORITES_DROPDOWN));

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.MOBILE_DISABLE_FAVORITES_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MOBILE_DISABLE_FAVORITES_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.MOBILE_DISABLE_FAVORITES_YES_BUTTON));

      PayrollHelperUtil.checkForDefaultEmployeeGroup(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MOBILE_DISABLE_FAVORITES_DROPDOWN));
      SeleniumWrapper.setDropDownByIndex(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MOBILE_DISABLE_FAVORITES_DROPDOWN),
          2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow text messaging time entry field.
   *
   * @throws Exception
   */
  @Test(priority = 35)
  public void testAllowTextMessagingTimeEntryField() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.ALLOW_SMS_ACCESS_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.ALLOW_SMS_ACCESS_YES_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_SMS_ACCESS_NO_BUTTON).click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.ALLOW_SMS_ACCESS_YES_BUTTON));

      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.ALLOW_SMS_ACCESS_DROPDOWN));

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.ALLOW_SMS_ACCESS_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.ALLOW_SMS_ACCESS_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.ALLOW_SMS_ACCESS_YES_BUTTON));

      PayrollHelperUtil.checkForDefaultEmployeeGroup(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_SMS_ACCESS_DROPDOWN));
      SeleniumWrapper.setDropDownByIndex(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_SMS_ACCESS_DROPDOWN), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the auto populates the employee's phone number and pin when they are turn onto time and
   * attendance field.
   *
   * @throws Exception
   */
  @Test(priority = 36)
  public void testAutoPopulateEmployeesPhoneNumberAndPinField() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_POPULATE_THE_EMPLOYEES_PHONE_NUMBER_AND_PIN_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_POPULATE_THE_EMPLOYEES_PHONE_NUMBER_AND_PIN_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.AUTO_POPULATE_THE_EMPLOYEES_PHONE_NUMBER_AND_PIN_NO_BUTTON).click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_POPULATE_THE_EMPLOYEES_PHONE_NUMBER_AND_PIN_YES_BUTTON));

      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.AUTO_POPULATE_MOBILE_DROPDOWN));

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_POPULATE_THE_EMPLOYEES_PHONE_NUMBER_AND_PIN_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_POPULATE_THE_EMPLOYEES_PHONE_NUMBER_AND_PIN_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_POPULATE_THE_EMPLOYEES_PHONE_NUMBER_AND_PIN_YES_BUTTON));

      PayrollHelperUtil.checkForDefaultEmployeeGroup(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AUTO_POPULATE_MOBILE_DROPDOWN));
      SeleniumWrapper.setDropDownByIndex(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AUTO_POPULATE_MOBILE_DROPDOWN), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }

    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow to ignore timesheet hours field.
   *
   * @throws Exception
   */
  @Test(priority = 37)
  public void testAllowIgnoreTimesheetHoursField() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_IGNORE_TIMESHEET_HOURS_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_IGNORE_TIMESHEET_HOURS_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.ALLOW_IGNORE_TIMESHEET_HOURS_NO_BUTTON).click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_IGNORE_TIMESHEET_HOURS_YES_BUTTON));

      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.ALLOW_IGNORE_TIMESHEET_HOURS_DROPDOWN));

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_IGNORE_TIMESHEET_HOURS_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_IGNORE_TIMESHEET_HOURS_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_IGNORE_TIMESHEET_HOURS_YES_BUTTON));

      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_IGNORE_TIMESHEET_HOURS_DROPDOWN));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_IGNORE_TIMESHEET_HOURS_DROPDOWN), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }

    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the field.
   *
   * @throws Exception
   */
  @Test(priority = 38)
  public static void testAllowTimeClockCheckboxes() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      WebElement[] timeClockElementList = TimeEntryTabPage.getTimeClockElementList();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.FINGER_PRINT_CLOCK_CHECK_BOX));
      for (WebElement timeClock : timeClockElementList) {
        if (timeClock.isSelected()) {
          timeClock.click();
        }
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      Assert.assertFalse(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.FINGER_PRINT_CLOCK_CHECK_BOX)
              .isSelected() && TimeEntryTabPageInterface.getWebElement(driver,
              TimeEntryTabPageInterface.HAND_PRINT_CLOCK_CHECK_BOX).isSelected()
              && TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.FACE_CLOCK_CHECK_BOX)
              .isSelected());
      WebElement[] timeClockElementList2 = TimeEntryTabPage.getTimeClockElementList();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.FINGER_PRINT_CLOCK_CHECK_BOX));
      for (WebElement timeClock : timeClockElementList2) {
        if (!timeClock.isSelected()) {
          timeClock.click();
        }
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      Assert.assertTrue(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.FINGER_PRINT_CLOCK_CHECK_BOX)
              .isSelected() && TimeEntryTabPageInterface.getWebElement(driver,
              TimeEntryTabPageInterface.HAND_PRINT_CLOCK_CHECK_BOX).isSelected()
              && TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.FACE_CLOCK_CHECK_BOX)
              .isSelected());

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.FINGER_PRINT_CLOCK_CHECK_BOX));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AVERO_POS_CHECK_BOX).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.FINGER_PRINT_CLOCK_CHECK_BOX));

      Assert.assertFalse(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.HAND_PRINT_CLOCK_CHECK_BOX)
              .isSelected() && TimeEntryTabPageInterface.getWebElement(driver,
              TimeEntryTabPageInterface.FACE_CLOCK_CHECK_BOX).isSelected());
      Assert.assertTrue(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.FACE_CLOCK_CHECK_BOX).isSelected()
              && TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AVERO_POS_CHECK_BOX)
              .isSelected());
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.HAND_PRINT_CLOCK_CHECK_BOX).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }

    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the auto set clock number field.
   *
   * @throws Exception
   */
  @Test(priority = 39)
  public static void testAutoSetClockNumberField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      if (!TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.HAND_PRINT_CLOCK_CHECK_BOX)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.HAND_PRINT_CLOCK_CHECK_BOX).click();
      }
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AUTO_SET_CLOCK_NUMBER_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AUTO_SET_CLOCK_NUMBER_YES_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AUTO_SET_CLOCK_NUMBER_NO_BUTTON)
            .click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AUTO_SET_CLOCK_NUMBER_YES_BUTTON));
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.AUTO_SET_CLOCK_NUMBER_EMPLOYEE_LEVEL_SELECTION));
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AUTO_SET_CLOCK_NUMBER_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AUTO_SET_CLOCK_NUMBER_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.AUTO_SET_CLOCK_NUMBER_YES_BUTTON));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_SET_CLOCK_NUMBER_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_SET_CLOCK_NUMBER_EMPLOYEE_LEVEL_SELECTION), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the daily hours table.
   *
   * @throws Exception
   */
  @Test(priority = 40)
  public static void testDailyHoursTable() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      new WebDriverWait(driver, Duration.ofSeconds(10));
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
      List<WebElement> rowNumbers = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.DAILY_HOURS_TABLE_ROWS);
      rowNumbers.forEach(webElement -> {
        JavascriptExecutorWrapper.scrollToElement(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
        PayrollHelperUtil.deleteTableRows(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE), 1);
      });
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE_ADD_ROW).click();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPage.getDailyHoursDefaultHoursRowXPathByRowNumber(1));
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPage.getDailyHoursDefaultHoursTextField(driver, 1), "40");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPage.getDailyHoursEmployeeGroupSelection(driver, 1), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE_ADD_ROW).click();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
      int rowNumber = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.DAILY_HOURS_TABLE_ROWS).size();
      Assert.assertEquals(rowNumber, 1);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the daily hours table delete rows.
   *
   * @throws Exception
   */
  @Test(priority = 41)
  public static void testDailyHoursTableDeleteRows() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      new WebDriverWait(driver, Duration.ofSeconds(10));
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
      List<WebElement> rowNumbers = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.DAILY_HOURS_TABLE_ROWS);
      rowNumbers.forEach(webElement -> {
        JavascriptExecutorWrapper.scrollToElement(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
        PayrollHelperUtil.deleteTableRows(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE), 1);
      });
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE_ADD_ROW).click();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPage.getDailyHoursDefaultHoursRowXPathByRowNumber(1));
      TimeEntryTabPage.getDailyHoursDefaultHoursTextField(driver, 1).click();
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPage.getDailyHoursDefaultHoursTextField(driver, 1), "50");
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPage.getDailyHoursEmployeeGroupSelection(driver, 1), 1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      int rowNumber1 = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.DAILY_HOURS_TABLE_ROWS).size();
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE_ADD_ROW).click();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPage.getDailyHoursDefaultHoursRowXPathByRowNumber(2));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPage.getDailyHoursEmployeeGroupSelection(driver, 2), 2);
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPage.getDailyHoursDefaultHoursTextField(driver, 2), "40");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
      int rowNumber2 = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.DAILY_HOURS_TABLE_ROWS).size();
      Assert.assertTrue(rowNumber1 < rowNumber2);
      PayrollHelperUtil.deleteTableRows(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
      int rowNumber3 = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.DAILY_HOURS_TABLE_ROWS).size();
      Assert.assertTrue(rowNumber3 < rowNumber2);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the max daily hours table.
   *
   * @throws Exception
   */
  @Test(priority = 42)
  public static void testMaxDailyHoursTable() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      new WebDriverWait(driver, Duration.ofSeconds(10));
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE));
      List<WebElement> rowNumbers = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE_ROWS);
      rowNumbers.forEach(webElement -> {
        JavascriptExecutorWrapper.scrollToElement(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE));
        PayrollHelperUtil.deleteTableRows(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.DAILY_HOURS_TABLE), 1);
      });
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE_ADD_ROW).click();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPage.getMaxDailyHoursDefaultHoursRowXPathByRowNumber(1));
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPage.getMaxDailyHoursDefaultHoursTextField(driver, 1), "40");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPage.getMaxDailyHoursEmployeeGroupSelection(driver, 1), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE_ADD_ROW).click();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE));
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE));
      int rowNumber = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE_ROWS).size();
      Assert.assertEquals(rowNumber, 1);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the max daily hours table delete rows.
   *
   * @throws Exception
   */
  @Test(priority = 43)
  public static void testMaxDailyHoursTableDeleteRows() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      new WebDriverWait(driver, Duration.ofSeconds(10));
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE));
      List<WebElement> rowNumbers = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE_ROWS);
      rowNumbers.forEach(webElement -> {
        JavascriptExecutorWrapper.scrollToElement(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE));
        PayrollHelperUtil.deleteTableRows(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE), 1);
      });
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE_ADD_ROW).click();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPage.getMaxDailyHoursDefaultHoursRowXPathByRowNumber(1));
      TimeEntryTabPage.getMaxDailyHoursDefaultHoursTextField(driver, 1).click();
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPage.getMaxDailyHoursDefaultHoursTextField(driver, 1), "50");
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPage.getMaxDailyHoursEmployeeGroupSelection(driver, 1), 1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      int rowNumber1 = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE_ROWS).size();
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE_ADD_ROW).click();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPage.getMaxDailyHoursDefaultHoursRowXPathByRowNumber(2));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPage.getMaxDailyHoursEmployeeGroupSelection(driver, 2), 2);
      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPage.getMaxDailyHoursDefaultHoursTextField(driver, 2), "40");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE));
      int rowNumber2 = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE_ROWS).size();
      Assert.assertTrue(rowNumber1 < rowNumber2);
      PayrollHelperUtil.deleteTableRows(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE));
      int rowNumber3 = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.MAX_DAILY_HOURS_TABLE_ROWS).size();
      Assert.assertTrue(rowNumber3 < rowNumber2);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow automatic break minutes field.
   *
   * @throws Exception
   */
  @Test(priority = 44)
  public static void testAllowAutomaticBreakMinutesField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_AUTOMATIC_BREAK_MINUTES_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_AUTOMATIC_BREAK_MINUTES_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.ALLOW_AUTOMATIC_BREAK_MINUTES_NO_BUTTON).click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_AUTOMATIC_BREAK_MINUTES_YES_BUTTON));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.ALLOW_AUTOMATIC_BREAK_MINUTES_EMPLOYEE_LEVEL_SELECTION)
          && SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.MINUTES_WORKED_BEFORE_BREAK_IS_AUTOMATICALLY_ENTERED_TEXT_FIELD));

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_AUTOMATIC_BREAK_MINUTES_YES_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_AUTOMATIC_BREAK_MINUTES_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_AUTOMATIC_BREAK_MINUTES_YES_BUTTON));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPageInterface.MINUTES_WORKED_BEFORE_BREAK_IS_AUTOMATICALLY_ENTERED_TEXT_FIELD);
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.MINUTES_WORKED_BEFORE_BREAK_IS_AUTOMATICALLY_ENTERED_TEXT_FIELD));

      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_AUTOMATIC_BREAK_MINUTES_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_AUTOMATIC_BREAK_MINUTES_EMPLOYEE_LEVEL_SELECTION), 2);

      SeleniumWrapper.sendKeyWithClear(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.MINUTES_WORKED_BEFORE_BREAK_IS_AUTOMATICALLY_ENTERED_TEXT_FIELD), "10");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the break minutes override table.
   *
   * @throws Exception
   */
  @Test(priority = 45)
  public static void testBreakMinutesOverrideTable() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE));

      List<WebElement> breakMinutesOverrideTableRows = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE_ROWS);
      breakMinutesOverrideTableRows.forEach(element -> {
        JavascriptExecutorWrapper.scrollToElement(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE));
        PayrollHelperUtil.deleteTableRows(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE), 1);
      });
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE_ADD_ROW)
          .click();

      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPage.getXPathLocatorForRowOfBreakMinutesOverrideTable(1));
      SeleniumWrapper.sendKeyWithClear(driver,
          TimeEntryTabPage.getDefaultBreakMinutesOverrideBreakMinutesTextField(driver, 1), "40");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE));

      if (SeleniumWrapper.isElementDisplayed(
          TimeEntryTabPage.getBreakMinutesOverrideClassificationSelection(driver, 1))) {
        SeleniumWrapper.setDropDownByIndex(TimeEntryTabPage.getBreakMinutesOverrideClassificationSelection(driver, 1),
            2);
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE));

      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE_ADD_ROW)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE));
      int secondRowSize = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE_ROWS).size();
      Assert.assertEquals(secondRowSize, 1);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the break minutes override table rows.
   *
   * @throws Exception
   */
  @Test(priority = 46)
  public static void testBreakMinutesOverrideRows() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE));
      List<WebElement> breakMinutesOverrideTableRows = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE_ROWS);
      breakMinutesOverrideTableRows.forEach(element -> {
        JavascriptExecutorWrapper.scrollToElement(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE));
        PayrollHelperUtil.deleteTableRows(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE), 1);
      });
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE_ADD_ROW)
          .click();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPage.getXPathLocatorForRowOfBreakMinutesOverrideTable(1));
      SeleniumWrapper.sendKeyWithClear(driver,
          TimeEntryTabPage.getDefaultBreakMinutesOverrideBreakMinutesTextField(driver, 1), "50");
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPage.getBreakMinutesOverrideClassificationSelection(driver, 1), 1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      if (SeleniumWrapper.getDropdownOptionsSize(TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE_ROW_GROUPS)
          > 1) {
        int firstRowSize = TimeEntryTabPageInterface.getWebElements(driver,
            TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE_ROWS).size();
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE_ADD_ROW)
            .click();
        JavascriptExecutorWrapper.scrollToElement(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE));
        SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
            TimeEntryTabPage.getXPathLocatorForRowOfBreakMinutesOverrideTable(2));
        SeleniumWrapper.setDropDownByIndex(TimeEntryTabPage.getBreakMinutesOverrideClassificationSelection(driver, 2),
            2);
        SeleniumWrapper.sendKeyWithClear(driver,
            TimeEntryTabPage.getDefaultBreakMinutesOverrideBreakMinutesTextField(driver, 2), "40");
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

        JavascriptExecutorWrapper.scrollToElement(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE));
        int secondRowSize = TimeEntryTabPageInterface.getWebElements(driver,
            TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE_ROWS).size();
        Assert.assertTrue(firstRowSize < secondRowSize);
        PayrollHelperUtil.deleteTableRows(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE), 2);
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

        JavascriptExecutorWrapper.scrollToElement(driver,
            TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE));
        int thirdRowSize = TimeEntryTabPageInterface.getWebElements(driver,
            TimeEntryTabPageInterface.BREAK_MINUTES_OVERRIDE_TABLE_ROWS).size();
        Assert.assertTrue(thirdRowSize < secondRowSize);
      }
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow time entry widget field.
   *
   * @throws Exception
   */
  @Test(priority = 47)
  public static void testAllowTimeEntryWidgetField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TIME_ENTRY_WIDGET_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_TIME_ENTRY_WIDGET_YES_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_TIME_ENTRY_WIDGET_NO_BUTTON)
            .click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TIME_ENTRY_WIDGET_YES_BUTTON));
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.ALLOW_TIME_ENTRY_WIDGET_EMPLOYEE_LEVEL_SELECTION)
              && SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.ALLOW_GPS_NO_BUTTON));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_TIME_ENTRY_WIDGET_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TIME_ENTRY_WIDGET_YES_BUTTON));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(TimeEntryTabPageInterface.ALLOW_GPS_NO_BUTTON);
      Assert.assertTrue(
          SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.ALLOW_TIME_ENTRY_WIDGET_EMPLOYEE_LEVEL_SELECTION)
              && SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.ALLOW_GPS_NO_BUTTON));

      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TIME_ENTRY_WIDGET_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_AUTOMATIC_BREAK_MINUTES_EMPLOYEE_LEVEL_SELECTION), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow GPS field, a subfield of the time entry widget field.
   *
   * @throws Exception
   */
  @Test(priority = 48)
  public static void testAllowGPSForTimeEntryWidgetField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TIME_ENTRY_WIDGET_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_TIME_ENTRY_WIDGET_NO_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_TIME_ENTRY_WIDGET_YES_BUTTON)
            .click();
      }
      if (TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_GPS_YES_BUTTON)
          .isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_GPS_NO_BUTTON).click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_GPS_NO_BUTTON));
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.ALLOW_GPS_EMPLOYEE_LEVEL_SELECTION));
      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_GPS_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_GPS_YES_BUTTON));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(TimeEntryTabPageInterface.ALLOW_GPS_EMPLOYEE_LEVEL_SELECTION);
      Assert.assertTrue(
          SeleniumWrapper.isLocatorDisplayed(TimeEntryTabPageInterface.ALLOW_GPS_EMPLOYEE_LEVEL_SELECTION));

      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_GPS_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(
          TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_GPS_EMPLOYEE_LEVEL_SELECTION),
          2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow exempt employee auto pay field.
   *
   * @throws Exception
   */
  @Test(priority = 49)
  public static void testAllowExemptEmployeeAutoPayField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_NO_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_NO_BUTTON).click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_NO_BUTTON));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_EMPLOYEE_LEVEL_SELECTION));
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_YES_BUTTON));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPageInterface.ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_EMPLOYEE_LEVEL_SELECTION);
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_EMPLOYEE_LEVEL_SELECTION));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EXEMPT_EMPLOYEE_AUTO_PAY_EMPLOYEE_LEVEL_SELECTION), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow telephone time clock field.
   *
   * @throws Exception
   */
  @Test(priority = 50)
  public static void testAllowTelephoneTimeClockField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TELEPHONE_TIME_CLOCK_YES_BUTTON));
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TELEPHONE_TIME_CLOCK_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_TELEPHONE_TIME_CLOCK_NO_BUTTON)
            .click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TELEPHONE_TIME_CLOCK_NO_BUTTON));

      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.ALLOW_TELEPHONE_TIME_CLOCK_EMPLOYEE_LEVEL_SELECTION));

      TimeEntryTabPageInterface.getWebElement(driver, TimeEntryTabPageInterface.ALLOW_TELEPHONE_TIME_CLOCK_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TELEPHONE_TIME_CLOCK_YES_BUTTON));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPageInterface.ALLOW_TELEPHONE_TIME_CLOCK_EMPLOYEE_LEVEL_SELECTION);
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          TimeEntryTabPageInterface.ALLOW_TELEPHONE_TIME_CLOCK_EMPLOYEE_LEVEL_SELECTION));

      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TELEPHONE_TIME_CLOCK_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_TELEPHONE_TIME_CLOCK_EMPLOYEE_LEVEL_SELECTION), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the auto pay amount based on hours worked table.
   *
   * @throws Exception
   */
  @Test(priority = 51)
  public static void testAutoPayAmountBasedOnHoursWorkedTable() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE));
      List<WebElement> hoursWorkedTableRows = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ROWS);
      hoursWorkedTableRows.forEach(element -> {
        JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE));
        PayrollHelperUtil.deleteTableRows(driver, TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE), 1);
      });
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ADD_ROW).click();
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPage.getAutoPayAmountBasedOnHoursWorkedRowByRowNumber(1));
      List<WebElement> costingsSelections = TimeEntryTabPage.getAutoPayAmountBasedOnHoursWorkedAllSelection(driver, 1);
      for (WebElement costingSelection : costingsSelections) {
        SeleniumWrapper.setDropDownByIndex(costingSelection, 2);
      }
      List<WebElement> autoPayAmountTexFields = TimeEntryTabPage.getAutoPayAmountBasedOnHoursWorkedAllTextField(driver,
          1);
      for (WebElement autoPayAmountTextField : autoPayAmountTexFields) {
        SeleniumWrapper.sendKeyWithClear(driver, autoPayAmountTextField, "20");
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE));
      int actualHoursWorkedTableRowsSize = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ROWS).size();
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ADD_ROW).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE));
      int expectedHoursWorkedTableRowsSize = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ROWS).size();
      Assert.assertEquals(actualHoursWorkedTableRowsSize, expectedHoursWorkedTableRowsSize);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test adding and deleting a second row for the auto pay amount based on hours worked table.
   *
   * @throws Exception
   */
  @Test(priority = 52)
  public static void testAddingAndDeletingSecondRowToTheAutoPayAmountBasedOnHoursWorkedTable() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE));
      List<WebElement> hoursWorkedTableRows = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ROWS);
      List<WebElement> costingsSelections;
      List<WebElement> autoPayAmountTexFields;
      if (hoursWorkedTableRows.isEmpty()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ADD_ROW).click();
        JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE));
        SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
            TimeEntryTabPage.getAutoPayAmountBasedOnHoursWorkedRowByRowNumber(1));
        costingsSelections = TimeEntryTabPage.getAutoPayAmountBasedOnHoursWorkedAllSelection(driver, 1);
        for (WebElement costingSelection : costingsSelections) {
          SeleniumWrapper.setDropDownByIndex(costingSelection, 2);
        }
        autoPayAmountTexFields = TimeEntryTabPage.getAutoPayAmountBasedOnHoursWorkedAllTextField(driver, 1);
        for (WebElement autoPayAmountTextField : autoPayAmountTexFields) {
          SeleniumWrapper.sendKeyWithClear(driver, autoPayAmountTextField, "25");
        }
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }

      int rowNumber1 = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ROWS).size();
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ADD_ROW).click();
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPage.getAutoPayAmountBasedOnHoursWorkedRowByRowNumber(2));
      costingsSelections = TimeEntryTabPage.getAutoPayAmountBasedOnHoursWorkedAllSelection(driver, 2);
      for (WebElement costingSelection : costingsSelections) {
        SeleniumWrapper.setDropDownByIndex(costingSelection, 1);
      }
      autoPayAmountTexFields = TimeEntryTabPage.getAutoPayAmountBasedOnHoursWorkedAllTextField(driver, 2);
      for (WebElement autoPayAmountTextField : autoPayAmountTexFields) {
        SeleniumWrapper.sendKeyWithClear(driver, autoPayAmountTextField, "30");
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE));
      int rowNumber2 = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ROWS).size();
      Assert.assertTrue(rowNumber1 < rowNumber2);
      PayrollHelperUtil.deleteTableRows(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE));
      int rowNumber3 = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.AUTO_PAY_AMOUNT_BASED_ON_HOURS_WORKED_TABLE_ROWS).size();
      Assert.assertTrue(rowNumber3 < rowNumber2);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the excluded costings from timesheet totals table.
   *
   * @throws Exception
   */
  @Test(priority = 53)
  public static void testExcludedCostingFromTimesheetTotalsTable() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE));
      List<WebElement> excludeCostingsFromTimesheetTotalsTableRows = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE_ROWS);
      excludeCostingsFromTimesheetTotalsTableRows.forEach(element -> {
        JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE));
        PayrollHelperUtil.deleteTableRows(driver, TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE), 1);
      });
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_ADD_ROW).click();
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPage.getExcludeCostingFromTimesheetTotalsRowByRowNumber(1));
      List<WebElement> costingsSelections = TimeEntryTabPage.getExcludeCostingFromTimesheetTotalsAllSelection(driver,
          1);
      for (WebElement costingSelection : costingsSelections) {
        SeleniumWrapper.setDropDownByIndex(costingSelection, 2);
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test adding and deleting a second row for the excluded costings from timesheet totals table.
   *
   * @throws Exception
   */
  @Test(priority = 54)
  public static void testAddingAndDeletingSecondRowForExcludedCostingFromTimesheetTotalsTable() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE));
      List<WebElement> excludeCostingsFromTimesheetTotalsTableRows = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE_ROWS);
      if (excludeCostingsFromTimesheetTotalsTableRows.isEmpty()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_ADD_ROW).click();
        JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE));
        SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
            TimeEntryTabPage.getExcludeCostingFromTimesheetTotalsRowByRowNumber(1));
        List<WebElement> costingsSelections = TimeEntryTabPage.getExcludeCostingFromTimesheetTotalsAllSelection(driver,
            1);
        for (WebElement costingSelection : costingsSelections) {
          SeleniumWrapper.setDropDownByIndex(costingSelection, 2);
        }
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }

      int rowNumber1 = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE_ROWS).size();
      TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_ADD_ROW).click();
      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE));
      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(
          TimeEntryTabPage.getExcludeCostingFromTimesheetTotalsRowByRowNumber(2));
      List<WebElement> costingsSelections = TimeEntryTabPage.getExcludeCostingFromTimesheetTotalsAllSelection(driver,
          2);
      for (WebElement costingSelection : costingsSelections) {
        SeleniumWrapper.setDropDownByIndex(costingSelection, 1);
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE));
      int rowNumber2 = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE_ROWS).size();
      Assert.assertTrue(rowNumber1 < rowNumber2);
      PayrollHelperUtil.deleteTableRows(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE));
      int rowNumber3 = TimeEntryTabPageInterface.getWebElements(driver,
          TimeEntryTabPageInterface.EXCLUDE_COSTING_FROM_TIMESHEET_TOTALS_TABLE_ROWS).size();
      Assert.assertTrue(rowNumber3 < rowNumber2);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * Method set setting to No.
   *
   * @param heading
   * @param value
   */
  public static void setSettingToNo(String heading, String value) {

    GeneralOptionRadioButtons.clickGeneralOptionsRadioButton(heading, value);
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(heading, value));
  }

  /**
   * Method set setting to Yes.
   *
   * @param heading
   * @param value
   */
  public static void setSettingToYes(String heading, String value) {

    GeneralOptionRadioButtons.clickGeneralOptionsRadioButton(heading, value);
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(heading, value));
  }

  /**
   * Tear down method to quit the WebDriver after all tests in this module have run.
   */
  @AfterClass(alwaysRun = true)
  public void tearDownClass() {
    DriverManager.quitDriver();
  }
}