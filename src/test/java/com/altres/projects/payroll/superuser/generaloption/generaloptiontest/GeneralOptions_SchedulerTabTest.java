package com.altres.projects.payroll.superuser.generaloption.generaloptiontest;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.exception.SeleniumExceptions;
import com.altres.login.LoginPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.SchedulerTabPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.SchedulerTabPageInterface;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollHelperUtil;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollMenusAndLinksUtil;
import com.altres.util.FakeDataGenerator;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

/**
 * Selenium scripts to test general options scheduler tab.
 */
public class GeneralOptions_SchedulerTabTest extends Base {

  /**
   * This method will test the before start setting for the scheduled vs actual time warnings. This will set the field
   * to no and check that the buffer field is hidden. Then it will set the field to yes and check that the buffer field
   * is showing it will also test the buffer field.
   *
   * @throws Exception
   */
  @Test(priority = 1)
  public void testAllowSeparateChecksField() throws Exception {

    LoginPage.loginAsSuperUser(driver);
    SchedulerTabPage.setupSchedulerTabVisibilityConditions(driver);
    PayrollMenusAndLinksUtil.clickSchedulerTab();
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_BEFORE_START_YES_BUTTON));
      if (SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_BEFORE_START_YES_BUTTON)
          .isSelected()) {
        SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_BEFORE_START_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.SCHEDULE_BEFORE_START_BUFFER_MINUTES_TEXT_FIELD).isDisplayed());
      JavascriptExecutorWrapper.scrollToElement(driver,
          SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_BEFORE_START_NO_BUTTON));
      SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_BEFORE_START_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_BEFORE_START_YES_BUTTON));
      Assert.assertTrue(SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.SCHEDULE_BEFORE_START_BUFFER_MINUTES_TEXT_FIELD).isDisplayed());
      String minutes = String.valueOf(FakeDataGenerator.getRandomNumber(5, 55));
      SeleniumWrapper.sendKeyWithClear(driver, SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.SCHEDULE_BEFORE_START_BUFFER_MINUTES_TEXT_FIELD), minutes);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the after start setting for the scheduled vs actual time warnings this will set the field to
   * no and check that the buffer field is hidden. Then it will set the field to yes and check that the buffer field is
   * showing it will also test the buffer field.
   *
   * @throws Exception
   */
  @Test(priority = 2)
  public static void testScheduledAfterStartField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_AFTER_START_YES_BUTTON));
      if (SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_AFTER_START_YES_BUTTON)
          .isSelected()) {
        SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_AFTER_START_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.SCHEDULE_AFTER_START_BUFFER_MINUTES_TEXT_FIELD).isDisplayed());
      JavascriptExecutorWrapper.scrollToElement(driver,
          SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_AFTER_START_NO_BUTTON));
      SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_AFTER_START_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_AFTER_START_YES_BUTTON));
      Assert.assertTrue(SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.SCHEDULE_AFTER_START_BUFFER_MINUTES_TEXT_FIELD).isDisplayed());
      String minutes = String.valueOf(FakeDataGenerator.getRandomNumber(5, 55));
      SeleniumWrapper.sendKeyWithClear(driver, SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.SCHEDULE_AFTER_START_BUFFER_MINUTES_TEXT_FIELD), minutes);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the before end setting for the scheduled vs actual time warnings. This will set the field to
   * no and check that the buffer field is hidden. Then it will set the field to yes and check that the buffer field is
   * showing. It will also test the buffer field.
   *
   * @throws Exception
   */
  @Test(priority = 3)
  public static void testScheduledBeforeEndField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_BEFORE_END_YES_BUTTON));
      if (SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_BEFORE_END_YES_BUTTON)
          .isSelected()) {
        SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_BEFORE_END_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.SCHEDULE_BEFORE_END_BUFFER_MINUTES_TEXT_FIELD).isDisplayed());
      JavascriptExecutorWrapper.scrollToElement(driver,
          SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_BEFORE_END_NO_BUTTON));
      SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_BEFORE_END_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_BEFORE_END_YES_BUTTON));
      Assert.assertTrue(SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.SCHEDULE_BEFORE_END_BUFFER_MINUTES_TEXT_FIELD).isDisplayed());
      String minutes = String.valueOf(FakeDataGenerator.getRandomNumber(5, 55));
      SeleniumWrapper.sendKeyWithClear(driver, SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.SCHEDULE_BEFORE_END_BUFFER_MINUTES_TEXT_FIELD), minutes);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the after end setting for the scheduled vs actual time warnings. This will set the field to
   * no and check that the buffer field is hidden. Then it will set the field to yes and check that the buffer field is
   * showing. It will also test the buffer field.
   *
   * @throws Exception
   */
  @Test(priority = 4)
  public static void testScheduledAfterEndField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver,
          SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_AFTER_END_YES_BUTTON));
      if (SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_AFTER_END_YES_BUTTON)
          .isSelected()) {
        SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_AFTER_END_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertFalse(SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.SCHEDULE_AFTER_END_BUFFER_MINUTES_TEXT_FIELD).isDisplayed());
      JavascriptExecutorWrapper.scrollToElement(driver,
          SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_AFTER_END_NO_BUTTON));
      SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_AFTER_END_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver,
          SchedulerTabPageInterface.getWebElement(driver, SchedulerTabPageInterface.SCHEDULE_AFTER_END_YES_BUTTON));
      Assert.assertTrue(SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.SCHEDULE_AFTER_END_BUFFER_MINUTES_TEXT_FIELD).isDisplayed());
      String minutes = String.valueOf(FakeDataGenerator.getRandomNumber(5, 55));
      SeleniumWrapper.sendKeyWithClear(driver, SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.SCHEDULE_AFTER_END_BUFFER_MINUTES_TEXT_FIELD), minutes);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This method will test the scheduled tardy pay code. This will set the field to none and then it will select a pay
   * code. Then it will add the buffer minutes.
   *
   * @throws Exception
   */
  @Test(priority = 5)
  public static void testRestrictQuantityToHoursField() throws Exception {

    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.PAY_CODE_FOR_THE_SCHEDULE_TARDY_DROPDOWN_SELECTION));
      SeleniumWrapper.setDropDownByIndex(SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.PAY_CODE_FOR_THE_SCHEDULE_TARDY_DROPDOWN_SELECTION), 1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      JavascriptExecutorWrapper.scrollToElement(driver, SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.PAY_CODE_FOR_THE_SCHEDULE_TARDY_DROPDOWN_SELECTION));
      SeleniumWrapper.setDropDownByIndex(SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.PAY_CODE_FOR_THE_SCHEDULE_TARDY_DROPDOWN_SELECTION), 2);
      String minutes = String.valueOf(FakeDataGenerator.getRandomNumber(5, 55));
      SeleniumWrapper.sendKeyWithClear(driver, SchedulerTabPageInterface.getWebElement(driver,
          SchedulerTabPageInterface.BUFFER_FOR_THE_SCHEDULE_TARDY_TEXT_FIELD), minutes);
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