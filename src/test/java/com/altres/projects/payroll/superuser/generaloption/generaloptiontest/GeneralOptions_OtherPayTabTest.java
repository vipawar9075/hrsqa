package com.altres.projects.payroll.superuser.generaloption.generaloptiontest;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.exception.SeleniumExceptions;
import com.altres.login.LoginPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.OtherPayTabPageInterface;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollHelperUtil;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollMenusAndLinksUtil;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

/**
 * Selenium scripts to test general options other pay tab.
 */
public class GeneralOptions_OtherPayTabTest extends Base {


  /**
   * This method will log in as super user and will test the allow separate checks. This will set the field to no and
   * check that the employee level is hidden. Then it will set the field to Yes and check that the employee level field
   * is showing. It will also test the employee level.
   *
   * @throws Exception
   */
  @Test(priority = 1)
  public void testAllowSeparateChecksField() throws Exception {
    LoginPage.loginAsSuperUser(driver);
    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
    PayrollMenusAndLinksUtil.clickOtherPayTab();

    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_SEPARATE_CHECKS_YES_BUTTON));
      if (OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_SEPARATE_CHECKS_YES_BUTTON)
          .isSelected()) {
        OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_SEPARATE_CHECKS_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(OtherPayTabPageInterface.ALLOW_SEPARATE_CHECKS_EMPLOYEE_LEVEL_SELECTION));
      JavascriptExecutorWrapper.scrollToElement(driver,
          OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_SEPARATE_CHECKS_YES_BUTTON));
      OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_SEPARATE_CHECKS_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver,
          OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_SEPARATE_CHECKS_YES_BUTTON));
      Assert.assertTrue(
          SeleniumWrapper.isLocatorDisplayed(OtherPayTabPageInterface.ALLOW_SEPARATE_CHECKS_EMPLOYEE_LEVEL_SELECTION));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_SEPARATE_CHECKS_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_SEPARATE_CHECKS_EMPLOYEE_LEVEL_SELECTION), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow other pay with date. This will set the field to no and check that the employee
   * level is hidden. Then it will set the field to yes and check that the employee level field is showing. It will also
   * test the employee level.
   *
   * @throws Exception
   */
  @Test(priority = 2)
  public void testAllowOtherPayWithDateField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_YES_BUTTON));
      if (OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_YES_BUTTON)
          .isSelected()) {
        OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_EMPLOYEE_LEVEL_SELECTION)
          && SeleniumWrapper.isLocatorDisplayed(
          OtherPayTabPageInterface.USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_YES_BUTTON));
      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_YES_BUTTON));
      OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_YES_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_EMPLOYEE_LEVEL_SELECTION)
          && SeleniumWrapper.isLocatorDisplayed(
          OtherPayTabPageInterface.USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_YES_BUTTON));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_EMPLOYEE_LEVEL_SELECTION), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the use timesheet date when submitting to davison field. This is a sub-field of allow other
   * pay with date. This will set the field to no and check that the employee level is hidden. Then it will set the
   * field to yes and check that the employee level field is showing. It will also test the employee level.
   *
   * @throws Exception
   */
  @Test(priority = 3)
  public void testUseTimesheetDateWhenSubmittingToDavisonField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_NO_BUTTON));
      if (OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_NO_BUTTON)
          .isSelected()) {
        OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_YES_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }

      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_YES_BUTTON));
      if (OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_YES_BUTTON).isSelected()) {
        OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_DATE_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          OtherPayTabPageInterface.USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_EMPLOYEE_LEVEL_SELECTION));
      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_YES_BUTTON));
      OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_YES_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          OtherPayTabPageInterface.USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_EMPLOYEE_LEVEL_SELECTION));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.USE_TIMESHEET_DATE_WHEN_SUBMITTING_TO_DAVISON_EMPLOYEE_LEVEL_SELECTION), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the allow other pay with hours field. This will set the field to no and check that the
   * employee level is hidden. Then it will set the field to yes and check that the employee level field is showing. It
   * will also test the employee level.
   *
   * @throws Exception
   */
  @Test(priority = 4)
  public void testAllowOtherPayWithHoursField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_YES_BUTTON));
      if (OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_YES_BUTTON)
          .isSelected()) {
        OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_EMPLOYEE_LEVEL_SELECTION)
          && SeleniumWrapper.isLocatorDisplayed(OtherPayTabPageInterface.RESTRICT_QUANTITY_TO_HOURS_YES_BUTTON));
      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_YES_BUTTON));
      OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_YES_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_EMPLOYEE_LEVEL_SELECTION)
          && SeleniumWrapper.isLocatorDisplayed(OtherPayTabPageInterface.RESTRICT_QUANTITY_TO_HOURS_YES_BUTTON));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_EMPLOYEE_LEVEL_SELECTION), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the restrict quantity to hours field. This is a sub-field of allow other pay with hours. This
   * will set the field to no and check that the employee level is hidden. Then it will set the field to yes and check
   * that the employee level field is showing. It will also test the employee level.
   *
   * @throws Exception
   */
  @Test(priority = 5)
  public void testRestrictQuantityToHoursField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_NO_BUTTON));
      if (OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_NO_BUTTON)
          .isSelected()) {
        OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.ALLOW_OTHER_PAY_WITH_HOURS_YES_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }

      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.RESTRICT_QUANTITY_TO_HOURS_YES_BUTTON));
      if (OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.RESTRICT_QUANTITY_TO_HOURS_YES_BUTTON)
          .isSelected()) {
        OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.RESTRICT_QUANTITY_TO_HOURS_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          OtherPayTabPageInterface.RESTRICT_QUANTITY_TO_HOURS_EMPLOYEE_LEVEL_SELECTION));
      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.RESTRICT_QUANTITY_TO_HOURS_YES_BUTTON));
      OtherPayTabPageInterface.getWebElement(driver, OtherPayTabPageInterface.RESTRICT_QUANTITY_TO_HOURS_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.RESTRICT_QUANTITY_TO_HOURS_YES_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          OtherPayTabPageInterface.RESTRICT_QUANTITY_TO_HOURS_EMPLOYEE_LEVEL_SELECTION));
      PayrollHelperUtil.checkForDefaultEmployeeGroup(OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.RESTRICT_QUANTITY_TO_HOURS_EMPLOYEE_LEVEL_SELECTION));
      SeleniumWrapper.setDropDownByIndex(OtherPayTabPageInterface.getWebElement(driver,
          OtherPayTabPageInterface.RESTRICT_QUANTITY_TO_HOURS_EMPLOYEE_LEVEL_SELECTION), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the other pay summary un grouping table. This will set the field to no and check that the
   * employee level is hidden. Then it will set the field to yes and check that the employee level field is showing. It
   * will also test the employee level.
   *
   * @throws Exception
   */
  @Test(priority = 6)
  public void testOtherPaySummaryUngroupingTable() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      List<WebElement> otherPaySummaryUngroupingTableCheckboxes = OtherPayTabPageInterface.getWebElements(driver,
          OtherPayTabPageInterface.ALL_CHECKBOXES_FROM_THE_OTHER_PAY_SUMMARY_UNGROUPING_TABLE);

      for (WebElement otherPaySummaryUngroupingCheckbox : otherPaySummaryUngroupingTableCheckboxes) {
        if (!otherPaySummaryUngroupingCheckbox.isSelected()) {
          otherPaySummaryUngroupingCheckbox.click();
        }
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      otherPaySummaryUngroupingTableCheckboxes = OtherPayTabPageInterface.getWebElements(driver,
          OtherPayTabPageInterface.ALL_CHECKBOXES_FROM_THE_OTHER_PAY_SUMMARY_UNGROUPING_TABLE);

      for (WebElement otherPaySummaryUngroupingCheckbox : otherPaySummaryUngroupingTableCheckboxes) {
        if (otherPaySummaryUngroupingCheckbox.isSelected()) {
          otherPaySummaryUngroupingCheckbox.click();
        }
      }
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