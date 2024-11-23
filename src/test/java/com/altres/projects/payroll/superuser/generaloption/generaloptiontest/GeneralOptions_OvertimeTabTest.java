package com.altres.projects.payroll.superuser.generaloption.generaloptiontest;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

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
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.OverTimeTabPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.OverTimeTabPageInterface;
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
 * Selenium scripts to test general options Over time tab.
 */
public class GeneralOptions_OvertimeTabTest extends Base {

  static PropertyValueProvider expected = new ConfigPropertiesProviderImpl(Constants.GENERAL_OPTION_PATH);
  static String assertionErrorMessage;

  public GeneralOptions_OvertimeTabTest() {
    try {
      assertionErrorMessage = expected.getPropertyValueByKey("assertion_error_message");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * This test will test the error message that shows when selecting the None for overtime pay code setting, and the
   * compute overtime hours setting is set to yes.
   *
   * @throws Exception
   */
  @Test(priority = 1)
  public void testSelectingNoneForOvertimePayCode() throws Exception {
    LoginPage.loginAsSuperUser(driver);
    SeleniumWrapper.refreshBrowser(driver);
    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
    PayrollMenusAndLinksUtil.clickOvertimeTab();
    try {
      SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.OVERTIME_PAY_CODE, "NONE");

      GeneralOptionRadioButtons.clickGeneralOptionsRadioButton("Compute Overtime Hours", "Y");
      PayrollMenusAndLinksUtil.clickOnSaveButton();
      Assert.assertEquals(PayrollHelperUtil.getErrorNoticeMessageText(driver),
          "If compute overtime is selected, you must select an overtime code.");
      SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.OVERTIME_PAY_CODE, "O/T");

      PayrollMenusAndLinksUtil.clickOnSaveButton();
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will test selecting the OT payCode for overtime pay code setting.
   */
  @Test(priority = 2)
  public void testSelectingOvertimePayCode() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        OverTimeTabPageInterface.getWebElement(driver, PayrollConstants.OVERTIME_PAY_CODE));
    WebElement overtimePayCodeElement = OverTimeTabPageInterface.getWebElement(driver,
        PayrollConstants.OVERTIME_PAY_CODE);
    Select select = new Select(overtimePayCodeElement);
    if (!select.getOptions().isEmpty()) {
      select.selectByValue("O/T");
    }
    PayrollMenusAndLinksUtil.clickOnSaveButton();

    SeleniumWrapper.click(driver, PayrollConstants.OVERTIME_PAY_CODE);
    SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.OVERTIME_PAY_CODE, "O/T");

    PayrollMenusAndLinksUtil.clickOnSaveButton();
  }

  /**
   * This test will check that when the Compute Overtime Hours is set to no, none of the child fields are display.
   *
   * @throws Exception
   */
  @Test(priority = 3)
  public void testForComputeOvertimeHoursWhenSetToNo() throws Exception {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.OVERTIME_PAY_CODE, "O/T");
    GeneralOptionRadioButtons.clickGeneralOptionsRadioButton("Compute Overtime Hours", "N");
    try {
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(PayrollConstants.OVERTIME_METHOD_SELECTOR));
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_YES_BUTTON));
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.COMPUTE_DAILY_OVERTIME_YES_BUTTON));
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.COMPUTE_DOUBLE_OVERTIME_YES_BUTTON));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          OverTimeTabPageInterface.COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_YES_BUTTON));
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This Test will check that when the compute overtime hours is set to yes, all the child fields are display.
   *
   * @throws Exception
   */
  @Test(priority = 4)
  public void testForComputeOvertimeHoursWhenSetToYes() throws Exception {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.OVERTIME_PAY_CODE, "O/T");
    GeneralOptionRadioButtons.clickGeneralOptionsRadioButton("Compute Overtime Hours", "Y");
    try {
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(PayrollConstants.OVERTIME_METHOD_SELECTOR));
      Assert.assertTrue(
          SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_YES_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.COMPUTE_DAILY_OVERTIME_YES_BUTTON));
      Assert.assertTrue(
          SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.COMPUTE_DOUBLE_OVERTIME_YES_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          OverTimeTabPageInterface.COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_YES_BUTTON));
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This Test will select each overtime option and will also check the message that shows for each of the overtime
   * method selections
   * @throws Exception
   */
  @Test(priority = 5)
  public void testSelectingEachOptionForOvertimeMethod() throws Exception {
    String[] overtimeMethodOverrideOptionList = new String[]{"peel", "row", "day", "week"};
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    PayrollHelperUtil.setTimeEntryMethodToDetail();
    OverTimeTabPage.setComputeOvertimeHoursAndUpdateOvertimeMethod(driver);
    for (String option : overtimeMethodOverrideOptionList) {
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      WebElement overtimeMethodOptions = OverTimeTabPageInterface.getWebElement(driver,
          PayrollConstants.OVERTIME_METHOD_SELECTOR);
      JavascriptExecutorWrapper.scrollToElement(driver, overtimeMethodOptions);
      Select overtimeMethodOverrideOption = new Select(overtimeMethodOptions);
      overtimeMethodOverrideOption.selectByValue(option);
      try {
        switch (option) {
          case "peel":
            String peelOffActualMessage = OverTimeTabPageInterface.getWebElement(driver,
                OverTimeTabPageInterface.OVERTIME_METHOD_MESSAGE).getText();
            String peelOffMessage = "(Directly charges O/T to the Job Cost that it is incurred on.)";
            Assert.assertEquals(peelOffMessage, peelOffActualMessage);
            break;
          case "row":
            String peelOffByRowActualMessage = OverTimeTabPageInterface.getWebElement(driver,
                OverTimeTabPageInterface.OVERTIME_METHOD_MESSAGE).getText();
            String peelOfByRowMessage =
                "(Allocates O/T to the rows on the timesheet starting with the last day and" + " working back)";
            Assert.assertEquals(peelOfByRowMessage, peelOffByRowActualMessage);

            break;
          case "day":
            String prorateBackwardsByDayActualMessage = OverTimeTabPageInterface.getWebElement(driver,
                OverTimeTabPageInterface.OVERTIME_METHOD_MESSAGE).getText();
            String expectedProrateBackwardsByDayMessage = "(Allocates O/T on a week by week basis based on the DAY "
                + "the O/T is incurred and the PERCENTAGE of hours worked in each Job Cost on that DAY.)";
            Assert.assertEquals(expectedProrateBackwardsByDayMessage, prorateBackwardsByDayActualMessage);
            break;
          case "week":
            String prorateByWeekActualMessage = OverTimeTabPageInterface.getWebElement(driver,
                OverTimeTabPageInterface.OVERTIME_METHOD_MESSAGE).getText();
            String expectedProrateByWeekMessage =
                "(Allocates O/T on a week by week basis based on the PERCENTAGE of hours worked in each Job Cost.\n"
                    + "For Semi-Monthly, O/T is allocated based on the PERCENTAGE of hours worked in each Job Cost for "
                    + "the entire Pay Period.)";
            Assert.assertEquals(expectedProrateByWeekMessage, prorateByWeekActualMessage);
            PayrollHelperUtil.setOvertimeMethodOverrideByValueGeneralOptionsSettings(driver, "peel");
            break;
        }
      } catch (Throwable e) {
        SeleniumExceptions.handleException(e, driver);
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    }
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        OverTimeTabPageInterface.getWebElement(driver, PayrollConstants.OVERTIME_METHOD_SELECTOR));
    PayrollHelperUtil.setOvertimeMethodOverrideByValueGeneralOptionsSettings(driver, "row");
    PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will test the error message when selection the peel off method and the time entry is not detail.
   *
   * @throws Exception
   */
  @Test(priority = 6)
  public static void testPeelOffErrorWithTimeEntry() throws Exception {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    PayrollHelperUtil.setTimeEntryMethodToSummary();
    PayrollHelperUtil.setOvertimeMethodOverrideByValueGeneralOptionsSettings(driver, "peel");
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    String errorMessageDisplayed = PayrollHelperUtil.getErrorNoticeMessageText(driver);
    String errorActualMessage = "Peel off overtime calculation method is not allowed without a detailed time entry for "
        + "non exempt employees.";
    try {
      Assert.assertEquals(errorMessageDisplayed, errorActualMessage);
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      PayrollHelperUtil.setOvertimeMethodOverrideByValueGeneralOptionsSettings(driver, "row");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will check that when the compute weekly overtime hours is set to no that none of the child fields are
   * display.
   *
   * @throws Exception
   */
  @Test(priority = 7)
  public static void testSubFieldsNotDisplayedWhenComputeWeeklyOvertimeIsNo() throws Exception {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.CERTIFIED_PAYROLL_NO_BUTTON));
    OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.CERTIFIED_PAYROLL_NO_BUTTON).click();
    PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    OverTimeTabPage.setComputeOvertimeHoursAndUpdateOvertimeMethod(driver);
    OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_DAILY_OVERTIME_YES_BUTTON).click();
    OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_NO_BUTTON).click();
    PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        OverTimeTabPageInterface.getWebElement(driver, PayrollConstants.OVERTIME_METHOD_SELECTOR));
    try {
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_EMPLOYEE_LEVEL));
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.ALLOW_WEIGHTED_AVERAGE_OVERTIME_RATE_YES_BUTTON));
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will check the employee level default and also select a different employee group for the compute weekly
   * overtime hours field.
   * @throws Exception
   */
  @Test(priority = 8)
  public static void testEmployeeLevelForComputeWeeklyOvertime() throws Exception {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    OverTimeTabPage.clickYesToComputeOvertimeHours(driver);
    OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_YES_BUTTON).click();
    PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_EMPLOYEE_LEVEL));
    SeleniumWrapper.selectDropdownByVisibleText(OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_EMPLOYEE_LEVEL,
        "Default: Enabled");
    PayrollHelperUtil.checkForDefaultEmployeeGroup(OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_EMPLOYEE_LEVEL));
    SeleniumWrapper.setDropDownByIndex(
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_EMPLOYEE_LEVEL),
        2);
    try {
      Assert.assertTrue(OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.ALLOW_WEIGHTED_AVERAGE_OVERTIME_RATE_NO_BUTTON).isSelected());
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          OverTimeTabPageInterface.ALLOW_WEIGHTED_AVERAGE_OVERTIME_SEPARATE_PREMIUM_YES_BUTTON));
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will test the allow weighted average overtime rate subfield.
   */
  @Test(priority = 9)
  public static void testAllowWeightedAverageOvertimeRateSubField() {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    OverTimeTabPage.clickYesToComputeOvertimeHours(driver);
    OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_YES_BUTTON).click();
    JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_EMPLOYEE_LEVEL));
    OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.ALLOW_WEIGHTED_AVERAGE_OVERTIME_RATE_YES_BUTTON).click();
    SeleniumWrapper.selectDropdownByVisibleText(
        OverTimeTabPageInterface.ALLOW_WEIGHTED_AVERAGE_OVERTIME_RATE_EMPLOYEE_LEVEL, "Default: Enabled");
    PayrollHelperUtil.checkForDefaultEmployeeGroup(OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.ALLOW_WEIGHTED_AVERAGE_OVERTIME_RATE_EMPLOYEE_LEVEL));
    SeleniumWrapper.setDropDownByIndex(OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.ALLOW_WEIGHTED_AVERAGE_OVERTIME_RATE_EMPLOYEE_LEVEL), 2);
    PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will check that when the compute weekly overtime hours is set to no that none of the child fields are
   * displayed.
   *
   * @throws Exception
   */
  @Test(priority = 10)
  public static void testSeparatePremiumSubField() throws Exception {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    OverTimeTabPage.clickYesToComputeOvertimeHours(driver);
    JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_EMPLOYEE_LEVEL));
    OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_OVERTIME_YES_BUTTON).click();
    if (!OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_YES_BUTTON)
        .isSelected()) {
      OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_YES_BUTTON)
          .click();
    }
    if (!OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.ALLOW_WEIGHTED_AVERAGE_OVERTIME_RATE_YES_BUTTON).isSelected()) {
      OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.ALLOW_WEIGHTED_AVERAGE_OVERTIME_RATE_YES_BUTTON).click();
    }
    try {
      Assert.assertTrue(OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.ALLOW_WEIGHTED_AVERAGE_OVERTIME_SEPARATE_PREMIUM_NO_BUTTON).isSelected());
      OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.ALLOW_WEIGHTED_AVERAGE_OVERTIME_SEPARATE_PREMIUM_YES_BUTTON).click();
      WebElement payGroupSelectionElement = OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.ALLOW_WEIGHTED_AVERAGE_OVERTIME_SEPARATE_PREMIUM_PAY_CODE);
      Select payGroup = new Select(payGroupSelectionElement);
      if (!payGroup.getOptions().isEmpty()) {
        payGroup.selectByIndex(0);
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will check test setting the compute daily overtime hours to yes The test will also set the daily Overtime
   * hours and check the employee group.
   */
  @Test(priority = 11)
  public static void testSettingComputeDailyOvertimeHoursToYesAndSeparatePremiumSubField() {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    OverTimeTabPage.setComputeOvertimeHoursAndUpdateOvertimeMethod(driver);
    OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_DAILY_OVERTIME_YES_BUTTON).click();
    SeleniumWrapper.selectDropdownByVisibleText(OverTimeTabPageInterface.ALLOW_DAILY_OVERTIME_EMPLOYEE_LEVEL,
        "Default: Enabled");
    PayrollHelperUtil.checkForDefaultEmployeeGroup(
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.ALLOW_DAILY_OVERTIME_EMPLOYEE_LEVEL));
    SeleniumWrapper.setDropDownByIndex(
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.ALLOW_DAILY_OVERTIME_EMPLOYEE_LEVEL),
        2);
    SeleniumWrapper.sendKeyWithClear(driver,
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.DAILY_OVERTIME_HOURS), "8");
    PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will add rows to the job overtime override The test will also add classification and daily hours The Test
   * will also delete the rows.
   *
   * @throws Exception
   */
  @Test(priority = 12)
  public static void testAddingJobOvertimeOverrideToTheComputeDailyOvertimeHours() throws Exception {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    OverTimeTabPage.setComputeOvertimeHoursAndUpdateOvertimeMethod(driver);
    OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_DAILY_OVERTIME_YES_BUTTON).click();
    SeleniumWrapper.sendKeyWithClear(driver,
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.DAILY_OVERTIME_HOURS), "8");
    OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.JOB_OVERTIME_OVERRIDE_ADD_ROW).click();
    WebElement classificationSelectorElement = OverTimeTabPage.getClassificationJobOvertimeOverride(driver, 1);
    Select jobOverrideClassificationSelection = new Select(classificationSelectorElement);
    if (!jobOverrideClassificationSelection.getOptions().isEmpty()) {
      jobOverrideClassificationSelection.selectByIndex(1);
    }
    SeleniumWrapper.sendKeyWithClear(driver, OverTimeTabPage.getDailyHoursJobOvertimeOverride(driver, 1), "8");
    PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.DAILY_OVERTIME_HOURS));
    OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.JOB_OVERTIME_OVERRIDE_ADD_ROW).click();
    JavascriptExecutorWrapper.scrollToElement(driver,
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.DAILY_OVERTIME_HOURS));
    List<WebElement> DailyOvertimeJobHoursOverrideRows = OverTimeTabPageInterface.getWebElements(driver,
        OverTimeTabPageInterface.DAILY_OVERTIME_JOB_HOURS_OVERRIDE_ROWS);
    try {
      Assert.assertTrue(DailyOvertimeJobHoursOverrideRows.size() > 1);
      WebElement classificationSelectorElement2 = OverTimeTabPage.getClassificationJobOvertimeOverride(driver, 2);
      Select jobOverrideClassificationSelection2 = new Select(classificationSelectorElement2);
      if (jobOverrideClassificationSelection2.getOptions().size() > 2) {
        jobOverrideClassificationSelection2.selectByIndex(2);
      } else {
        jobOverrideClassificationSelection2.selectByIndex(1);
      }
      OverTimeTabPage.getDailyHoursJobOvertimeOverride(driver, 2).sendKeys("8");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.DAILY_OVERTIME_HOURS));
      OverTimeTabPage.getJobOvertimeOverrideRow(driver, 2).click();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      OverTimeTabPage.getJobOvertimeOverrideRow(driver, 1).click();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.DAILY_OVERTIME_HOURS));
      new WebDriverWait(driver, Duration.ofSeconds(10));
      PayrollHelperUtil.deleteTableRows(driver,
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.JOB_OVERTIME_OVERRIDE), 2);
      PayrollHelperUtil.deleteTableRows(driver,
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.JOB_OVERTIME_OVERRIDE), 1);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will check the compute double overtime hours field,the employee level field, and the double overtime pay
   * code dropdown. It will also test the minimum daily hours text field. Additionally, it will ensure that when the
   * text field is cleared, the compute double overtime hours field resets to "No."
   *
   * @throws Exception
   */
  @Test(priority = 13)
  public static void testComputeDoubleOvertimeHours() throws Exception {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    OverTimeTabPage.setComputeOvertimeHoursAndUpdateOvertimeMethod(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_DOUBLE_OVERTIME_YES_BUTTON));
    OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_DOUBLE_OVERTIME_YES_BUTTON).click();
    PayrollHelperUtil.checkForDefaultEmployeeGroup(
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.DOUBLE_OVERTIME_HOURS_EMPLOYEE_LEVEL));
    SeleniumWrapper.setDropDownByIndex(
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.DOUBLE_OVERTIME_HOURS_EMPLOYEE_LEVEL),
        2);
    WebElement doubleOvertimePayCodeElement = OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.DOUBLE_OVERTIME_PAY_CODE);
    Select doubleOvertimePayCodeSelection = new Select(doubleOvertimePayCodeElement);
    try {
      Assert.assertNotNull(doubleOvertimePayCodeSelection.getFirstSelectedOption());
      if (doubleOvertimePayCodeSelection.getOptions().size() >= 2) {
        doubleOvertimePayCodeSelection.selectByIndex(2);
      } else {
        doubleOvertimePayCodeSelection.selectByIndex(0);
      }

      SeleniumWrapper.sendKeyWithClear(driver,
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.DOUBLE_OVERTIME_MINIMUM_DAILY_HOURS),
          "8");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.COMPUTE_DOUBLE_OVERTIME_HOURS_NO_BUTTON));
      OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.DOUBLE_OVERTIME_MINIMUM_DAILY_HOURS)
          .clear();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.COMPUTE_DOUBLE_OVERTIME_HOURS_NO_BUTTON));

      Assert.assertTrue(OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.COMPUTE_DOUBLE_OVERTIME_HOURS_NO_BUTTON).isSelected());
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.DOUBLE_OVERTIME_HOURS_EMPLOYEE_LEVEL));
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.DOUBLE_OVERTIME_MINIMUM_DAILY_HOURS));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.DOUBLE_OVERTIME_PAY_CODE));
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will test the require consecutive hours for daily double overtime field. The test will also test the
   * employee level field.
   *
   * @throws Exception
   */
  @Test(priority = 14)
  public static void testRequireConsecutiveHoursForDailyDoubleOvertime() throws Exception {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.GROUP_CONSECUTIVE_HOURS_ON_OVERNIGHT_SHIFTS_NO_BUTTON).click();
    PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    try {

      JavascriptExecutorWrapper.scrollToElement(driver,
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_DOUBLE_OVERTIME_YES_BUTTON));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          OverTimeTabPageInterface.REQUIRE_CONSECUTIVE_HOURS_FOR_DAILY_DOUBLE_OVERTIME_YES_BUTTON));
      OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.GROUP_CONSECUTIVE_HOURS_ON_OVERNIGHT_SHIFTS_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_DOUBLE_OVERTIME_YES_BUTTON));
      OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.REQUIRE_CONSECUTIVE_HOURS_FOR_DAILY_DOUBLE_OVERTIME_YES_BUTTON).click();
      PayrollHelperUtil.checkForDefaultEmployeeGroup(OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.REQUIRE_CONSECUTIVE_HOURS_FOR_DAILY_DOUBLE_OVERTIME_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.REQUIRE_CONSECUTIVE_HOURS_FOR_DAILY_DOUBLE_OVERTIME_EMPLOYEE_LEVEL), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.REQUIRE_CONSECUTIVE_HOURS_FOR_DAILY_DOUBLE_OVERTIME_YES_BUTTON));

      Assert.assertTrue(OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.REQUIRE_CONSECUTIVE_HOURS_FOR_DAILY_DOUBLE_OVERTIME_YES_BUTTON).isSelected());
      OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.REQUIRE_CONSECUTIVE_HOURS_FOR_DAILY_DOUBLE_OVERTIME_NO_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will test the compute overtime hours after working consecutive days field The test will also test the
   * employee level field this will also test the number of consecutive days text subfield.
   *
   * @throws Exception
   */
  @Test(priority = 15)
  public static void testComputeOvertimeHoursAfterWorkingConsecutiveDays() throws Exception {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    OverTimeTabPage.setComputeOvertimeHoursAndUpdateOvertimeMethod(driver);
    JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_YES_BUTTON));
    OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_YES_BUTTON).click();
    PayrollHelperUtil.checkForDefaultEmployeeGroup(OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_EMPLOYEE_LEVEL));
    SeleniumWrapper.setDropDownByIndex(OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_EMPLOYEE_LEVEL), 2);
    SeleniumWrapper.sendKeyWithClear(driver,
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.NUMBER_OF_CONSECUTIVE_DAYS_TEXT_FIELD),
        "8");
    PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_YES_BUTTON));
    try {
      Assert.assertTrue(OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_YES_BUTTON).isSelected());
      OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.NUMBER_OF_CONSECUTIVE_DAYS_TEXT_FIELD)
          .clear();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      Assert.assertTrue(OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.GET_COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_NO_BUTTON).isSelected());
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          OverTimeTabPageInterface.COMPUTE_OVERTIME_AFTER_WORKING_CONSECUTIVE_DAYS_EMPLOYEE_LEVEL));
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.NUMBER_OF_CONSECUTIVE_DAYS_TEXT_FIELD));
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will test the show overtime warning on vertical timesheet after minimum rest hours field The test will
   * also test the employee level field This will also test the minimum rest hours text field.
   *
   * @throws Exception
   */
  @Test(priority = 16)
  public static void testShowOvertimeWarningOnVerticalTimesheetAfterMinimumRestHours() throws Exception {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    OverTimeTabPage.setComputeOvertimeHoursAndUpdateOvertimeMethod(driver);
    JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_YES_BUTTON));
    if (OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_YES_BUTTON).isSelected()) {
      OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_NO_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    }
    JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_YES_BUTTON));
    OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_YES_BUTTON).click();
    PayrollHelperUtil.checkForDefaultEmployeeGroup(OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_EMPLOYEE_LEVEL));
    SeleniumWrapper.setDropDownByIndex(OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_EMPLOYEE_LEVEL), 2);
    SeleniumWrapper.sendKeyWithClear(driver,
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.MINIMUM_REST_HOURS_TEXT_FIELD), "8");
    PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_YES_BUTTON));
    try {
      Assert.assertTrue(OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_YES_BUTTON).isSelected());
      OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.MINIMUM_REST_HOURS_TEXT_FIELD).clear();
      OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_NO_BUTTON).click();
      Assert.assertTrue(OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_NO_BUTTON).isSelected());
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_NO_BUTTON));
      Assert.assertTrue(OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_NO_BUTTON).isSelected());
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          OverTimeTabPageInterface.SHOW_OVERTIME_WARNING_ON_VERTICAL_TIMESHEET_EMPLOYEE_LEVEL));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.MINIMUM_REST_HOURS_TEXT_FIELD));
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will select and save each option for the show overtime prompt field options.
   */
  @Test(priority = 17)
  public static void testShowOvertimePromptField() {
    String[] overtimeMethodOverrideOptionList = new String[]{"N", "S", "P", "Y"};
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.SHOW_OVERTIME_PROMPT_SELECTOR));
    for (String option : overtimeMethodOverrideOptionList) {
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      WebElement overtimePromptOptionsElement = OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.SHOW_OVERTIME_PROMPT_SELECTOR);
      JavascriptExecutorWrapper.scrollToElement(driver, overtimePromptOptionsElement);
      Select overtimePromptOption = new Select(overtimePromptOptionsElement);
      overtimePromptOption.selectByValue(option);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will test the show overtime warning on vertical timesheet after minimum rest hours field. The test will
   * also test the employee level field this will also test the minimum rest hours text field.
   *
   * @throws Exception
   */
  @Test(priority = 18)
  public static void testRequiresApprovalField() throws Exception {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_YES_BUTTON));
    if (OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_YES_BUTTON)
        .isSelected()) {
      OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_NO_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    }
    JavascriptExecutorWrapper.scrollToElement(driver,
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_YES_BUTTON));
    OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_YES_BUTTON)
        .click();
    PayrollHelperUtil.checkForDefaultEmployeeGroup(OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_EMPLOYEE_LEVEL));
    SeleniumWrapper.setDropDownByIndex(OverTimeTabPageInterface.getWebElement(driver,
        OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_EMPLOYEE_LEVEL), 2);
    try {
      Assert.assertTrue(
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.LOCK_TIMESHEET_YES_BUTTON)
              .isSelected());
      PayrollHelperUtil.checkForDefaultEmployeeGroup(
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.LOCK_TIMESHEET_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.LOCK_TIMESHEET_EMPLOYEE_LEVEL), 2);
      SeleniumWrapper.sendKeyWithClear(driver,
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.OVERTIME_GRACE_MINUTES_TEXT_FIELD),
          "8");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.OVERTIME_GRACE_MINUTES_TEXT_FIELD)
          .clear();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_YES_BUTTON));
      Assert.assertTrue(
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_YES_BUTTON)
              .isSelected());
      OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_NO_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, OverTimeTabPageInterface.getWebElement(driver,
          OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_NO_BUTTON));
      Assert.assertTrue(
          OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_NO_BUTTON)
              .isSelected());
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.OVERTIME_REQUIRES_APPROVAL_EMPLOYEE_LEVEL));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.LOCK_TIMESHEET_YES_BUTTON));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.LOCK_TIMESHEET_EMPLOYEE_LEVEL));
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(OverTimeTabPageInterface.OVERTIME_GRACE_MINUTES_TEXT_FIELD));
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * Tear down method to quit the WebDriver after all tests in this module have run.
   */
  @AfterClass(alwaysRun = true)
  public void tearDownClass() {
    DriverManager.quitDriver();
  }
}