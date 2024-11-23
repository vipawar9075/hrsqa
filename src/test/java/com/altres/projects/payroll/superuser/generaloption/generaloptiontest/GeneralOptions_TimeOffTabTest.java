package com.altres.projects.payroll.superuser.generaloption.generaloptiontest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.exception.SeleniumExceptions;
import com.altres.login.LoginPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.TimeOffTabPageInterface;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollHelperUtil;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollMenusAndLinksUtil;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

/**
 * Selenium scripts to test general options time off tab.
 */
public class GeneralOptions_TimeOffTabTest extends Base {

  /**
   * Test the works weekends field, this will check and uncheck both checkboxes for saturday and sunday field.
   *
   * @throws Exception
   */
  @Test(priority = 1)
  public void testWorksWeekendsField() throws Exception {

    navigateToURL("clienturl");
    LoginPage.loginAsSuperUser(driver);
    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
    PayrollMenusAndLinksUtil.clickTimeOffTab();
    try {
      if (!TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.WORKS_SATURDAY_FIELD).isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.WORKS_SATURDAY_FIELD).click();
      }
      if (!TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.WORKS_SUNDAY_FIELD).isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.WORKS_SUNDAY_FIELD).click();
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.WORKS_SATURDAY_FIELD).click();
      TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.WORKS_SUNDAY_FIELD).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow time off as percentage of day. This will set the field to no and check that the
   * employee level is hidden. Then it will set the field to yes and check that the employee level field is showing. It
   * will also test the employee Level.
   *
   * @throws Exception
   */
  @Test(priority = 2)
  public void testAllowTimeOffAsPercentageOfDayField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_YES_BUTTON));
      if (TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_YES_BUTTON).isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          TimeOffTabPageInterface.ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_EMPLOYEE_LEVEL));
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_YES_BUTTON));
      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_YES_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          TimeOffTabPageInterface.ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_EMPLOYEE_LEVEL));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_EMPLOYEE_LEVEL), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow time off on holiday. This will set the field to no and check that the employee
   * level is hidden. Then it will set the field to yes and check that the employee level field is showing. It will also
   * test the employee Level.
   *
   * @throws Exception
   */
  @Test(priority = 3)
  public void testAllowTimeOffOnHolidayField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.ALLOW_TIME_OFF_ON_HOLIDAY_YES_BUTTON));
      if (TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.ALLOW_TIME_OFF_ON_HOLIDAY_YES_BUTTON)
          .isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.ALLOW_TIME_OFF_ON_HOLIDAY_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(TimeOffTabPageInterface.ALLOW_TIME_OFF_ON_HOLIDAY_EMPLOYEE_LEVEL));
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.ALLOW_TIME_OFF_ON_HOLIDAY_YES_BUTTON));
      TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.ALLOW_TIME_OFF_ON_HOLIDAY_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_TIME_OFF_AS_PERCENTAGE_OF_DAY_YES_BUTTON));
      Assert.assertTrue(
          SeleniumWrapper.isLocatorDisplayed(TimeOffTabPageInterface.ALLOW_TIME_OFF_ON_HOLIDAY_EMPLOYEE_LEVEL));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_TIME_OFF_ON_HOLIDAY_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_TIME_OFF_ON_HOLIDAY_EMPLOYEE_LEVEL), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the non-reg pay code on holiday worked hours on. This will set the field to no and check that
   * the employee level is hidden. Then it will set the field to yes and check that the employee level field is showing.
   * It will also test the employee Level, pay code selection and the include exempt employees sub-field.
   *
   * @throws Exception
   */
  @Test(priority = 4)
  public void testNonRegPayCodeOnHolidayWorkedHoursOnField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_YES_BUTTON));
      if (TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_YES_BUTTON).isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          TimeOffTabPageInterface.NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_EMPLOYEE_LEVEL)
          && SeleniumWrapper.isLocatorDisplayed(TimeOffTabPageInterface.NON_REG_PAY_CODE_SELECTION)
          && SeleniumWrapper.isLocatorDisplayed(TimeOffTabPageInterface.INCLUDE_EXEMPT_EMPLOYEES_NO_BUTTON));
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_YES_BUTTON));
      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_YES_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          TimeOffTabPageInterface.NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_EMPLOYEE_LEVEL)
          && SeleniumWrapper.isLocatorDisplayed(TimeOffTabPageInterface.NON_REG_PAY_CODE_SELECTION)
          && SeleniumWrapper.isLocatorDisplayed(TimeOffTabPageInterface.INCLUDE_EXEMPT_EMPLOYEES_NO_BUTTON));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.NON_REG_PAY_CODE_ON_HOLIDAY_WORKED_HOURS_EMPLOYEE_LEVEL), 2);
      SeleniumWrapper.setDropDownByIndex(
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.NON_REG_PAY_CODE_SELECTION), 1);

      if (TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.INCLUDE_EXEMPT_EMPLOYEES_YES_BUTTON)
          .isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.INCLUDE_EXEMPT_EMPLOYEES_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.INCLUDE_EXEMPT_EMPLOYEES_YES_BUTTON));
      TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.INCLUDE_EXEMPT_EMPLOYEES_YES_BUTTON)
          .click();

      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the holiday specific percentage limits.
   *
   * @throws Exception
   */
  @Test(priority = 5)
  public void testHolidaySpecificPercentageLimitsField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.HOLIDAY_SPECIFIC_PERCENTAGE_LIMITS_YES_BUTTON));
      if (TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.HOLIDAY_SPECIFIC_PERCENTAGE_LIMITS_YES_BUTTON).isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.HOLIDAY_SPECIFIC_PERCENTAGE_LIMITS_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.HOLIDAY_SPECIFIC_PERCENTAGE_LIMITS_YES_BUTTON));
      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.HOLIDAY_SPECIFIC_PERCENTAGE_LIMITS_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the maximum holiday pay hours limits.
   *
   * @throws Exception
   */
  @Test(priority = 6)
  public void testMaximumHolidayPayHours() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.MAXIMUM_HOLIDAYS_PAY_HOURS_TEXT_FIELD));

      TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.MAXIMUM_HOLIDAYS_PAY_HOURS_TEXT_FIELD)
          .clear();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      SeleniumWrapper.sendKeyWithClear(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.MAXIMUM_HOLIDAYS_PAY_HOURS_TEXT_FIELD),
          "8");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.MAXIMUM_HOLIDAYS_PAY_HOURS_TEXT_FIELD));
      String getMaximumHolidayPayHoursValue = TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.MAXIMUM_HOLIDAYS_PAY_HOURS_TEXT_FIELD).getAttribute("value");
      Assert.assertEquals(getMaximumHolidayPayHoursValue, "8");
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the exclude company holidays field.
   *
   * @throws Exception
   */
  @Test(priority = 7)
  public void testExcludeCompanyHolidaysField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_YES_BUTTON));
      if (TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_YES_BUTTON)
          .isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_YES_BUTTON));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_TABLE));

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_YES_BUTTON));
      TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the exclude company holidays table.
   *
   * @throws Exception
   */
  @Test(priority = 8)
  public void testExcludeCompanyHolidaysTable() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_YES_BUTTON));
      if (TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_YES_BUTTON)
          .isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_YES_BUTTON));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_TABLE));

      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_YES_BUTTON));
      TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_YES_BUTTON)
          .click();
      if (TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_NO_BUTTON)
          .isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_YES_BUTTON)
            .click();
      }

      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ADD_ROW_FOR_THE_EXCLUDE_COMPANY_HOLIDAYS_TABLE).click();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_TABLE));

      Map<String, String> excludeFromHolidaySelectionOptionValues = SeleniumWrapper.getAvailableDropdownOptions(
          TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
              TimeOffTabPageInterface.EXCLUDE_FROM_HOLIDAY_SELECTION_XPATH, 1));
      List<String> excludeFromHolidayOptionValues = new ArrayList<>(excludeFromHolidaySelectionOptionValues.keySet());
      for (String excludeFromHolidaySelectionOption : excludeFromHolidayOptionValues) {
        WebElement excludeFromHolidaySelectionOptions = TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
            TimeOffTabPageInterface.EXCLUDE_FROM_HOLIDAY_SELECTION_XPATH, 1);
        Select excludeFromHolidayAvailableOptions = new Select(excludeFromHolidaySelectionOptions);
        excludeFromHolidayAvailableOptions.selectByValue(excludeFromHolidaySelectionOption);

        SeleniumWrapper.setDropDownByIndex(TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
            TimeOffTabPageInterface.EXCLUDE_FROM_HOLIDAY_EMPLOYEE_GROUP_SELECTION_XPATH, 1), 0);
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }

      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ADD_ROW_FOR_THE_EXCLUDE_COMPANY_HOLIDAYS_TABLE).click();
      JavascriptExecutorWrapper.scrollToElement(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_TABLE));
      PayrollHelperUtil.deleteTableRows(driver,
          TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.EXCLUDE_COMPANY_HOLIDAYS_TABLE), 1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      int tableRows = TimeOffTabPageInterface.getWebElements(driver,
          TimeOffTabPageInterface.ROWS_EXCLUDE_COMPANY_HOLIDAYS_TABLE).size();
      Assert.assertEquals(tableRows, 1);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow employee settings override field.
   *
   * @throws Exception
   */
  @Test(priority = 9)
  public void testAllowEmployeeSettingsOverrideField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_EMPLOYEE_SETTINGS_OVERRIDE_YES_BUTTON));
      if (TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_EMPLOYEE_SETTINGS_OVERRIDE_YES_BUTTON).isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.ALLOW_EMPLOYEE_SETTINGS_OVERRIDE_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_EMPLOYEE_SETTINGS_OVERRIDE_YES_BUTTON));
      TimeOffTabPageInterface.getWebElement(driver, TimeOffTabPageInterface.ALLOW_EMPLOYEE_SETTINGS_OVERRIDE_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the company observed holidays show auto pay field.
   *
   * @throws Exception
   */
  @Test(priority = 10)
  public void testCompanyObservedHolidaysShowAutoPayField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.COMPANY_OBSERVE_HOLIDAY_SHOW_AUTO_PAY_YES_BUTTON));
      if (TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.COMPANY_OBSERVE_HOLIDAY_SHOW_AUTO_PAY_YES_BUTTON).isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.COMPANY_OBSERVE_HOLIDAY_SHOW_AUTO_PAY_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.COMPANY_OBSERVE_HOLIDAY_SHOW_AUTO_PAY_YES_BUTTON));
      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.COMPANY_OBSERVE_HOLIDAY_SHOW_AUTO_PAY_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow editable alternative daily hours for holidays field. This will set the field to no
   * and check that the employee level is hidden. Then it will set the field to yes and check that the employee level
   * field is showing. It will also test the employee level.
   *
   * @throws Exception
   */
  @Test(priority = 11)
  public void testAllowEditableAlternativeDailyHoursForHolidaysField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_YES_BUTTON));
      if (TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_YES_BUTTON).isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          TimeOffTabPageInterface.ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_EMPLOYEE_LEVEL));
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_YES_BUTTON));
      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_YES_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          TimeOffTabPageInterface.ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_EMPLOYEE_LEVEL));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALLOW_EDITABLE_ALTERNATIVE_DAILY_HOURS_FOR_HOLIDAYS_EMPLOYEE_LEVEL), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the override default daily hours for holidays table. This will set the field to no and check
   * that the employee level is hidden. Then it will set the field to yes and check that the employee level field is
   * showing. It will also test the employee Level.
   *
   * @throws Exception
   */
  @Test(priority = 12)
  public void testOverrideDefaultDailyHoursForHolidaysTable() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      List<WebElement> timeOffRequestTableRows = TimeOffTabPageInterface.getWebElements(driver,
          TimeOffTabPageInterface.ROWS_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE);
      timeOffRequestTableRows.forEach(element -> {
        JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE));
        PayrollHelperUtil.deleteTableRows(driver, TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE), 1);
      });
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE));
      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ADD_ROW_FOR_THE_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE).click();
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE));
      SeleniumWrapper.setDropDownByIndex(TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_EMPLOYEE_GROUP_SELECTION_XPATH, 1), 0);
      SeleniumWrapper.sendKeyWithClear(driver, TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_DEFAULT_HOURS_TEXT_FIELD_XPATH, 1), "6");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE));

      if (SeleniumWrapper.getDropdownOptionsSize(
          TimeOffTabPageInterface.ROWS_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE_EMPLOYEE_GROUPS) > 1) {
        TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.ADD_ROW_FOR_THE_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE).click();
        SeleniumWrapper.sendKeyWithClear(driver, TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
            TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_DEFAULT_HOURS_TEXT_FIELD_XPATH, 2), "8");
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

        JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE));
        TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.ADD_ROW_FOR_THE_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE).click();
        SeleniumWrapper.setDropDownByIndex(TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
            TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_EMPLOYEE_GROUP_SELECTION_XPATH, 2), 2);
        SeleniumWrapper.sendKeyWithClear(driver, TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
            TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_DEFAULT_HOURS_TEXT_FIELD_XPATH, 2), "8");
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

        JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE));
        PayrollHelperUtil.deleteTableRows(driver, TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE), 1);
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      int tableRows = TimeOffTabPageInterface.getWebElements(driver,
          TimeOffTabPageInterface.ROWS_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_HOLIDAYS_TABLE).size();
      Assert.assertEquals(tableRows, 1);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow editable alternative daily hours for time off request field. This will set the
   * field to no and check that the employee level is hidden. Then it will set the field to yes and check that the
   * employee level field is showing. It will also test the employee Level.
   *
   * @throws Exception
   */
  @Test(priority = 13)
  public void testAllowEditableAlternativeDailyHoursForTimeOffRequestField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_YES_BUTTON));
      if (TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_YES_BUTTON).isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          TimeOffTabPageInterface.ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_EMPLOYEE_LEVEL));
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_YES_BUTTON));
      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_YES_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          TimeOffTabPageInterface.ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_EMPLOYEE_LEVEL));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ALTERNATIVE_DAILY_HOURS_FOR_TIME_OFF_REQUEST_EMPLOYEE_LEVEL), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the override default daily hours for time off request. This will set the field to no and
   * check that the employee level is hidden. Then it will set the field to yes and check that the employee level field
   * is showing. It will also test the employee level.
   *
   * @throws Exception
   */
  @Test(priority = 14)
  public void testOverrideDefaultDailyHoursForTimeOffRequestTable() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      List<WebElement> timeOffRequestTableRows = TimeOffTabPageInterface.getWebElements(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE_ROWS);

      timeOffRequestTableRows.forEach(element -> {
        JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE));
        PayrollHelperUtil.deleteTableRows(driver, TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE), 1);
      });
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE));
      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ADD_ROW_FOR_THE_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE).click();
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE));
      SeleniumWrapper.setDropDownByIndex(TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
              TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_EMPLOYEE_GROUP_SELECTION_XPATH, 1),
          0);
      SeleniumWrapper.sendKeyWithClear(driver, TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
              TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_DEFAULT_HOURS_TEXT_FIELD_XPATH, 1),
          "6");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE));
      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ADD_ROW_FOR_THE_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE).click();
      SeleniumWrapper.sendKeyWithClear(driver, TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
              TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_DEFAULT_HOURS_TEXT_FIELD_XPATH, 2),
          "8");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE));
      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.ADD_ROW_FOR_THE_OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE).click();
      SeleniumWrapper.setDropDownByIndex(TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
              TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_EMPLOYEE_GROUP_SELECTION_XPATH, 2),
          2);
      SeleniumWrapper.sendKeyWithClear(driver, TimeOffTabPageInterface.getWebElementByRelativeXPath(driver,
              TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_DEFAULT_HOURS_TEXT_FIELD_XPATH, 2),
          "8");
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE));
      PayrollHelperUtil.deleteTableRows(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE), 1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      int tableRows = TimeOffTabPageInterface.getWebElements(driver,
          TimeOffTabPageInterface.OVERRIDE_DEFAULT_DAILY_HOURS_FOR_TIME_OFF_REQUEST_TABLE_ROWS).size();
      Assert.assertEquals(tableRows, 1);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the hide remaining hours from accrued balance field.
   *
   * @throws Exception
   */
  @Test(priority = 15)
  public void testHideRemainingHoursFromAccruedBalanceField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.HIDE_REMAINING_HOURS_FROM_ACCURED_BALANCE_YES_BUTTON));
      if (TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.HIDE_REMAINING_HOURS_FROM_ACCURED_BALANCE_YES_BUTTON).isSelected()) {
        TimeOffTabPageInterface.getWebElement(driver,
            TimeOffTabPageInterface.HIDE_REMAINING_HOURS_FROM_ACCURED_BALANCE_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }

      JavascriptExecutorWrapper.scrollToElement(driver, TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.HIDE_REMAINING_HOURS_FROM_ACCURED_BALANCE_YES_BUTTON));
      TimeOffTabPageInterface.getWebElement(driver,
          TimeOffTabPageInterface.HIDE_REMAINING_HOURS_FROM_ACCURED_BALANCE_YES_BUTTON).click();
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