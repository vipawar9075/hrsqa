package com.altres.projects.payroll.superuser.generaloption.generaloptiontest;

import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.exception.SeleniumExceptions;
import com.altres.login.LoginPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.TimeEntryTabPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.TimeEntryTabPageInterface;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollConstants;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollHelperUtil;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollMenusAndLinksUtil;
import com.altres.util.SeleniumWrapper;

/**
 * Selenium scripts to test impact of configuration on general options time entry tab.
 */
public class GeneralOptions_TimeEntryConfigurationsImpact extends Base {

  /**
   * This will test the time entry methods settings impact.
   *
   * @throws Exception
   */
  @Test(priority = 1)
  public void VerifyTimeEntryMethodsImpact() throws Exception {
    LoginPage.loginAsSuperUser(driver);

    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
    PayrollHelperUtil.disableVerticalTimesheetCompanyLevel(driver);
    PayrollMenusAndLinksUtil.clickTimeEntryTab();

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);

    PayrollHelperUtil.setTimeEntryMethodToDetail();
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    PayrollMenusAndLinksUtil.clickPayrollMenu();
    PayrollMenusAndLinksUtil.clickTimesheetApprovalMenu();

    String employeeName = PayrollHelperUtil.getFirstEmployeeNameFromTimesheetApproval();
    boolean isEmployeeHasVerticalTimesheet;
    List<String> timesheetHeaders;
    try {
      SeleniumWrapper.switchToDefaultContent(driver);
      PayrollHelperUtil.openEmployeeSetting(driver, employeeName);
      isEmployeeHasVerticalTimesheet = PayrollHelperUtil.isEmployeeHasVerticalTimesheet(driver);
      PayrollMenusAndLinksUtil.clickPayrollMenu();
      PayrollMenusAndLinksUtil.clickTimesheetApprovalMenu();
      PayrollMenusAndLinksUtil.clickOnFirstEmployeeOfTimesheetApproval();

      if (isEmployeeHasVerticalTimesheet) {
        SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.VERTICAL_TIMESHEET_HEADERS);

        timesheetHeaders = TimeEntryTabPageInterface.getWebElements(driver, PayrollConstants.VERTICAL_TIMESHEET_HEADERS)
            .stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

        Assert.assertTrue(timesheetHeaders.contains("In"));
        Assert.assertTrue(timesheetHeaders.contains("Out"));
      } else {
        SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.TIMESHEET_SAVE_BUTTON);

        TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.TIMESHEET_DATE_ROW_FIELD).click();

        timesheetHeaders = TimeEntryTabPageInterface.getWebElements(driver,
                PayrollConstants.HORIZONTAL_TIMESHEET_HEADERS).stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

        Assert.assertTrue(timesheetHeaders.contains("In"));
        Assert.assertTrue(timesheetHeaders.contains("Out"));
      }
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    PayrollMenusAndLinksUtil.clickOnHomeMenu();

    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
    PayrollMenusAndLinksUtil.clickTimeEntryTab();
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);

    try {
      PayrollHelperUtil.setTimeEntryMethodToSummary();
      PayrollMenusAndLinksUtil.clickOnSaveButton();

      SeleniumWrapper.switchToDefaultContent(driver);
      PayrollHelperUtil.openEmployeeSetting(driver, employeeName);
      isEmployeeHasVerticalTimesheet = PayrollHelperUtil.isEmployeeHasVerticalTimesheet(driver);
      PayrollMenusAndLinksUtil.clickPayrollMenu();
      PayrollMenusAndLinksUtil.clickTimesheetApprovalMenu();
      PayrollMenusAndLinksUtil.clickOnFirstEmployeeOfTimesheetApproval();

      if (isEmployeeHasVerticalTimesheet) {
        SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.VERTICAL_TIMESHEET_HEADERS);

        timesheetHeaders = TimeEntryTabPageInterface.getWebElements(driver, PayrollConstants.VERTICAL_TIMESHEET_HEADERS)
            .stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

        Assert.assertTrue(timesheetHeaders.contains("In"));
        Assert.assertTrue(timesheetHeaders.contains("Out"));
      } else {
        SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.TIMESHEET_SAVE_BUTTON);
        TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.TIMESHEET_DATE_ROW_FIELD).click();

        boolean isDateFieldEnable = SeleniumWrapper.isElementEnabled(PayrollConstants.TIMESHEET_DATE_ROW_FIELD);

        Assert.assertTrue(isDateFieldEnable);
      }
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    PayrollMenusAndLinksUtil.clickOnHomeMenu();

    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
    PayrollMenusAndLinksUtil.clickTimeEntryTab();
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);

    try {
      TimeEntryTabPage.setTimeEntryMethodToDetailAndSummary();
      PayrollMenusAndLinksUtil.clickOnSaveButton();

      SeleniumWrapper.switchToDefaultContent(driver);
      PayrollHelperUtil.openEmployeeSetting(driver, employeeName);
      isEmployeeHasVerticalTimesheet = PayrollHelperUtil.isEmployeeHasVerticalTimesheet(driver);
      PayrollMenusAndLinksUtil.clickPayrollMenu();
      PayrollMenusAndLinksUtil.clickTimesheetApprovalMenu();
      PayrollMenusAndLinksUtil.clickOnFirstEmployeeOfTimesheetApproval();

      if (isEmployeeHasVerticalTimesheet) {
        SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.VERTICAL_TIMESHEET_HEADERS);

        timesheetHeaders = TimeEntryTabPageInterface.getWebElements(driver, PayrollConstants.VERTICAL_TIMESHEET_HEADERS)
            .stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

        Assert.assertTrue(timesheetHeaders.contains("In"));
        Assert.assertTrue(timesheetHeaders.contains("Out"));
        Assert.assertTrue(timesheetHeaders.contains("Hours"));
      } else {
        SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.TIMESHEET_SAVE_BUTTON);

        TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.TIMESHEET_DATE_ROW_FIELD).click();
        boolean isDateFieldEnable = SeleniumWrapper.isElementEnabled(PayrollConstants.TIMESHEET_DATE_ROW_FIELD);

        timesheetHeaders = TimeEntryTabPageInterface.getWebElements(driver,
                PayrollConstants.HORIZONTAL_TIMESHEET_HEADERS).stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

        Assert.assertTrue(timesheetHeaders.contains("In"));
        Assert.assertTrue(timesheetHeaders.contains("Out"));
        Assert.assertTrue(isDateFieldEnable);
      }
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    PayrollMenusAndLinksUtil.clickOnHomeMenu();
  }

  /**
   * This will set the time entry method to detail. This will test the allow employee-level time entry method override
   * is set to yes setting. Override the time entry method from employee setting.
   *
   * @throws Exception
   */
  @Test(priority = 2)
  public void verifyEmployeeLevelTimeEntryMethodOverrideImpact() throws Exception {

    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();

    PayrollMenusAndLinksUtil.clickTimeEntryTab();

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);

    PayrollHelperUtil.setTimeEntryMethodToDetail();
    SeleniumWrapper.findElement(driver,
        TimeEntryTabPageInterface.ALLOW_EMPLOYEE_LEVEL_TIME_ENTRY_METHOD_OVERRIDE_YES_BUTTON).click();
    PayrollMenusAndLinksUtil.clickOnSaveButton();
    PayrollMenusAndLinksUtil.clickPayrollMenu();
    PayrollMenusAndLinksUtil.clickTimesheetApprovalMenu();

    String employeeName = PayrollHelperUtil.getFirstEmployeeNameFromTimesheetApproval();
    boolean isEmployeeHasVerticalTimesheet;
    try {

      SeleniumWrapper.switchToDefaultContent(driver);
      PayrollHelperUtil.openEmployeeSetting(driver, employeeName);

      TimeEntryTabPage.setTimeEntryMethodToSummaryInEmployeeSetting();

      PayrollMenusAndLinksUtil.clickOnSaveButton();
      isEmployeeHasVerticalTimesheet = PayrollHelperUtil.isEmployeeHasVerticalTimesheet(driver);
      PayrollMenusAndLinksUtil.clickPayrollMenu();
      PayrollMenusAndLinksUtil.clickTimesheetApprovalMenu();
      PayrollMenusAndLinksUtil.clickOnFirstEmployeeOfTimesheetApproval();

      if (isEmployeeHasVerticalTimesheet) {
        List<String> timesheetHeaders = TimeEntryTabPageInterface.getWebElements(driver,
                PayrollConstants.VERTICAL_TIMESHEET_HEADERS).stream()
            .map(WebElement::getText)
            .collect(Collectors.toList());

        Assert.assertTrue(timesheetHeaders.contains("In"));
        Assert.assertTrue(timesheetHeaders.contains("Out"));
      } else {
        SeleniumWrapper.webDriverWaitForVisibiltyOfElement(PayrollConstants.TIMESHEET_SAVE_BUTTON);

        TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.TIMESHEET_DATE_ROW_FIELD).click();
        boolean isDateFieldEnable = SeleniumWrapper.isElementEnabled(PayrollConstants.TIMESHEET_DATE_ROW_FIELD);

        Assert.assertTrue(isDateFieldEnable);
      }
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    PayrollMenusAndLinksUtil.clickOnHomeMenu();
  }

  /**
   * This will test the allow employee-level time entry method override is set to no setting. Verify the grid with
   * headers are displayed.
   *
   * @throws Exception
   */

  @Test(priority = 3)
  public void verifyEmployeeLevelTimeEntryMethodOverrideByEmployeeGroupImpact() throws Exception {

    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
    PayrollMenusAndLinksUtil.clickTimeEntryTab();

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      PayrollHelperUtil.setTimeEntryMethodToDetail();
      if (TimeEntryTabPageInterface.getWebElement(driver,
          TimeEntryTabPageInterface.ALLOW_EMPLOYEE_LEVEL_TIME_ENTRY_METHOD_OVERRIDE_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            TimeEntryTabPageInterface.ALLOW_EMPLOYEE_LEVEL_TIME_ENTRY_METHOD_OVERRIDE_NO_BUTTON).click();
        PayrollMenusAndLinksUtil.clickOnSaveButton();
      }

      String gridHeading = SeleniumWrapper.getElementText(
          TimeEntryTabPageInterface.TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP);
      System.out.println(gridHeading);

      List<String> TimeEntryMethodByEmployeeGroupHeaders = TimeEntryTabPageInterface.getWebElements(driver,
              TimeEntryTabPageInterface.TIME_ENTRY_METHOD_BY_EMPLOYEE_GROUP_HEADERS).stream()
          .map(WebElement::getText)
          .collect(Collectors.toList());

      Assert.assertTrue(TimeEntryMethodByEmployeeGroupHeaders.contains("Employee Group"));
      Assert.assertTrue(TimeEntryMethodByEmployeeGroupHeaders.contains("Method"));
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    PayrollMenusAndLinksUtil.clickOnHomeMenu();
  }

  /**
   * This will test the allow mobile time clock entry method is set to no setting. Verify the grid with headers and
   * fields are displayed in employee setting.
   *
   * @throws Exception
   */
  @Test(priority = 4)
  public void verifyAllowMobileTimeClockImpact() throws Exception {

    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
    PayrollMenusAndLinksUtil.clickTimeEntryTab();

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      PayrollHelperUtil.setTimeEntryMethodToDetail();
      if (!TimeEntryTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON).isSelected()) {
        TimeEntryTabPageInterface.getWebElement(driver,
            PayrollConstants.ALLOW_MOBILE_TIME_CLOCK_ENTRY_FIELD_YES_BUTTON).click();
        PayrollMenusAndLinksUtil.clickOnSaveButton();
      }

      SeleniumWrapper.switchToDefaultContent(driver);
      PayrollMenusAndLinksUtil.clickSettingsMenu();
      PayrollMenusAndLinksUtil.clickEmployeesMenu();
      TimeEntryTabPageInterface.getWebElement(driver, PayrollConstants.EMPLOYEE_FIRST_ROW).click();

      SeleniumWrapper.webDriverWaitForVisibiltyOfElement(TimeEntryTabPageInterface.MOBILE_TIME_CLOCK_HEADING);

      List<String> TimeEntryMethodByEmployeeGroupHeaders = TimeEntryTabPageInterface.getWebElements(driver,
              TimeEntryTabPageInterface.MOBILE_TIME_CLOCK_HEADING_FIELDS).stream()
          .map(WebElement::getText)
          .collect(Collectors.toList());

      Assert.assertTrue(TimeEntryMethodByEmployeeGroupHeaders.contains("Phone Number"));
      Assert.assertTrue(TimeEntryMethodByEmployeeGroupHeaders.contains("New Pin"));
      Assert.assertTrue(TimeEntryMethodByEmployeeGroupHeaders.contains("Re-Type New Pin"));
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
  }

  /**
   * Tear down method to quit the WebDriver after all tests in this module have run.
   */
  @AfterClass(alwaysRun = true)
  public void tearDownClass() {
    DriverManager.quitDriver();
  }
}
