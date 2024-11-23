package com.altres.projects.payroll.superuser.generaloption.generaloptiontest;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.login.LoginPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.GeneralTabPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.GeneralTabPageInterface;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollMenusAndLinksUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.FakeDataGenerator;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Selenium scripts to test general options general tab.
 */
public class GeneralOptions_GeneralTabTest extends Base {

  static PropertyValueProvider expected = new ConfigPropertiesProviderImpl(Constants.GENERAL_OPTION_PATH);
  static String displayPayRate;
  static String defaultEnabled;
  static String usesSuperVisors;
  static String payrollOverviewMode;
  static String notifyWebTeamOfAllNewHires;
  static String autoEnableNewHires;
  static String groupConsecutiveHours;
  static String allowSubmissionToAltresAtAnyStatus;
  static String allowTimesheetAttachments;
  static String allowClientToEnableDisableEtimePerEmployee;
  static String allowEmailToSupervisorsForOverageHours;
  static String useVerticalTimesheet;
  static String allowCrossPayPeriodShift;
  static String allowExemptPayWithoutHoursAdjustment;
  static String submitPayrollThroughTimesheetEntry;
  static String allowCovidPayCodeToEmployee;
  static String displayEmployeeMinorStatus;
  static String allowSCARules;
  static String yes;
  static String no;
  static String value;
  static String allowSubmissionOfTimeSheetThatDoNotContainPaidTime;

  public GeneralOptions_GeneralTabTest() {
    try {
      displayPayRate = expected.getPropertyValueByKey("display_pay_rate");
      defaultEnabled = expected.getPropertyValueByKey("default_enabled");
      usesSuperVisors = expected.getPropertyValueByKey("uses_supervisors");
      payrollOverviewMode = expected.getPropertyValueByKey("payroll_overview_mode");
      notifyWebTeamOfAllNewHires = expected.getPropertyValueByKey("notify_web_team_of_all_new_hires");
      autoEnableNewHires = expected.getPropertyValueByKey("auto_enable_new_hires");
      groupConsecutiveHours = expected.getPropertyValueByKey("group_consecutive_hours_on_overnight_shift");
      allowSubmissionToAltresAtAnyStatus = expected.getPropertyValueByKey("allow_submission_to_altres_at_any_status");
      allowTimesheetAttachments = expected.getPropertyValueByKey("allow_timesheet_attachments");
      allowClientToEnableDisableEtimePerEmployee = expected.getPropertyValueByKey(
          "allow_client_to_enable_disable_etime_per-employee");
      allowEmailToSupervisorsForOverageHours = expected.getPropertyValueByKey(
          "allow_email_to_supervisors_for_overage_hours");
      useVerticalTimesheet = expected.getPropertyValueByKey("use_vertical_timesheet");
      allowCrossPayPeriodShift = expected.getPropertyValueByKey("allow_cross_pay_period_shift");
      allowExemptPayWithoutHoursAdjustment = expected.getPropertyValueByKey(
          "allow_exempt_pay_without_hours_adjustment");
      submitPayrollThroughTimesheetEntry = expected.getPropertyValueByKey("submit_payroll_through_timesheet_entry");
      allowCovidPayCodeToEmployee = expected.getPropertyValueByKey("allow_covid_pay_code_to_employee");
      displayEmployeeMinorStatus = expected.getPropertyValueByKey("display_employee_minor_status");
      allowSCARules = expected.getPropertyValueByKey("allow_sca_rules");
      allowSubmissionOfTimeSheetThatDoNotContainPaidTime = expected.getPropertyValueByKey(
          "allow_submission_of_timesheet_that_do_not_contain_paid_time");
      yes = expected.getPropertyValueByKey("y");
      no = expected.getPropertyValueByKey("n");
      value = expected.getPropertyValueByKey("value");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * The test will set the payroll overview setting to yes/no and will verify the different pop-up messages.
   */
  @Test(priority = 1)
  public void payrollOverviewSetting() throws ConfigurationNotLoadedException {

    LoginPage.loginAsSuperUser(driver);
    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(payrollOverviewMode, yes)) {
      GeneralTabPage.clickGeneralOptionsRadioButton(payrollOverviewMode, no);
      GeneralTabPage.clickGeneralOptionsSaveButton();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(payrollOverviewMode, no));
    }
    GeneralTabPage.clickGeneralOptionsRadioButton(payrollOverviewMode, yes);
    String actualTextPayrollOverviewAlertOnChangeSettingToYes = SeleniumWrapper.getAlertText(driver);
    String expectedTextPayrollOverviewAlertOnChangeSetting = "Payroll Overview Mode uses default setting configuration."
        + " To confirm, save the general options. To cancel, change the Payroll Overview Mode back.";
    Assert.assertEquals(actualTextPayrollOverviewAlertOnChangeSettingToYes,
        expectedTextPayrollOverviewAlertOnChangeSetting);
    SeleniumWrapper.acceptAlertIfPresent(driver);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    String actualTextPayrollOverviewAlertOnSave = SeleniumWrapper.getAlertText(driver);
    String expectedTextPayrollOverviewAlertOnSave =
        "You are changing the Payroll Overview Mode from 'No' to 'Yes'.\n" + "\n" + "Are you sure?";
    Assert.assertEquals(actualTextPayrollOverviewAlertOnSave, expectedTextPayrollOverviewAlertOnSave);
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(payrollOverviewMode, yes));
    GeneralTabPage.clickGeneralOptionsRadioButton(payrollOverviewMode, no);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    String actualTextPayrollOverviewAlertOnSaveForNo = SeleniumWrapper.getAlertText(driver);
    String expectedTextPayrollOverviewAlertOnSaveForNo =
        "You are changing the Payroll Overview Mode from 'Yes' to 'No'.\n" + "\n" + "Are you sure?";
    Assert.assertEquals(actualTextPayrollOverviewAlertOnSaveForNo, expectedTextPayrollOverviewAlertOnSaveForNo);
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(payrollOverviewMode, no));
  }

  /**
   * The test will set the uses supervisors setting to yes/no and will verify the different pop-up messages.
   */
  @Test(priority = 2)
  public static void usesSupervisorsSetting() throws IOException {

    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(usesSuperVisors, yes)) {
      GeneralTabPage.clickGeneralOptionsRadioButton(usesSuperVisors, no);
      SeleniumWrapper.acceptAlertIfPresent(driver);
      GeneralTabPage.clickGeneralOptionsSaveButton();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(usesSuperVisors, no));
    }
    GeneralTabPage.clickGeneralOptionsRadioButton(usesSuperVisors, yes);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(usesSuperVisors, yes));
    GeneralTabPage.clickGeneralOptionsRadioButton(usesSuperVisors, no);
    String actualTextUsesSupervisorAlertOnChangeSettingToNo = SeleniumWrapper.getAlertText(driver);
    String expectedTextUsesSupervisorAlertOnChangeSettingToNo = expected.getPropertyValueByKey(
        "expected_text_uses_supervisor_alert_on_change_setting_to_no");
    Assert.assertEquals(actualTextUsesSupervisorAlertOnChangeSettingToNo,
        expectedTextUsesSupervisorAlertOnChangeSettingToNo);
    SeleniumWrapper.acceptAlertIfPresent(driver);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(usesSuperVisors, no));
    GeneralTabPage.clickGeneralOptionsRadioButton(usesSuperVisors, yes);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
  }

  /**
   * The test will set the display rate setting to yes/no and will select an employee group.
   */
  @Test(priority = 3)
  public static void displayPayRate() {

    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(displayPayRate, yes)) {
      GeneralTabPage.clickGeneralOptionsRadioButton(displayPayRate, no);
      GeneralTabPage.clickGeneralOptionsSaveButton();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(displayPayRate, no));
    }

    GeneralTabPage.clickGeneralOptionsRadioButton(displayPayRate, yes);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(displayPayRate, yes));
    WebElement displayPayRateEmployeeLevel = GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.DISPLAY_PAY_RATE_EMPLOYEE_LEVEL);
    Assert.assertTrue(displayPayRateEmployeeLevel != null && displayPayRateEmployeeLevel.isDisplayed());
    String actualTextOfDefaultEmployeeGroupSelected = GeneralTabPage.getFirstSelectedOptionText();
    String expectedTextOfDefaultEmployeeGroupSelected = defaultEnabled;
    Assert.assertEquals(actualTextOfDefaultEmployeeGroupSelected, expectedTextOfDefaultEmployeeGroupSelected);
    if (GeneralTabPage.getEmployeeLevelOptionsSize() > 1) {
      GeneralTabPage.selectEmployeeLevelOption(1);
      Assert.assertNotEquals(expectedTextOfDefaultEmployeeGroupSelected, GeneralTabPage.getFirstSelectedOptionText());
    }
    GeneralTabPage.clickGeneralOptionsSaveButton();
    GeneralTabPage.getFirstSelectedOptionText();
    GeneralTabPage.clickGeneralOptionsRadioButton(displayPayRate, no);
    SeleniumWrapper.acceptAlertIfPresent(driver);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    Assert.assertFalse(
        GeneralTabPageInterface.getWebElement(driver, GeneralTabPageInterface.DISPLAY_PAY_RATE_EMPLOYEE_LEVEL)
            .isDisplayed());
    SeleniumWrapper.acceptAlertIfPresent(driver);
  }

  /**
   * This test will test notify web team of all new hires setting.
   */
  @Test(priority = 4)
  public static void notifyWebTeamOfAllNewHires() {
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(notifyWebTeamOfAllNewHires, yes)) {
      setSettingToNo(notifyWebTeamOfAllNewHires, no);
    }
    setSettingToYes(notifyWebTeamOfAllNewHires, yes);
    setSettingToNo(notifyWebTeamOfAllNewHires, no);
  }

  /**
   * This Test will test the auto enable new hires setting.
   */
  @Test(priority = 5)
  public static void autoEnableNewHires() {
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(autoEnableNewHires, yes)) {
      GeneralTabPage.clickGeneralOptionsRadioButton(autoEnableNewHires, no);
      GeneralTabPage.clickGeneralOptionsSaveButton();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(autoEnableNewHires, no));
    }
    GeneralTabPage.clickGeneralOptionsRadioButton(autoEnableNewHires, yes);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(autoEnableNewHires, yes));
    String description = FakeDataGenerator.getDescription();
    SeleniumWrapper.sendKeyWithClear(driver, GeneralTabPage.enterText(), description);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    GeneralTabPage.clickGeneralOptionsRadioButton(autoEnableNewHires, no);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(autoEnableNewHires, no));
  }

  /**
   * This test will test selecting each batch status option from the Davison return batch status setting.
   */
  @Test(priority = 6)
  public static void davisonReturnBatchStatus() {
    GeneralTabPage.selectDavisonReturnBatchStatusOption("In Process");
    String selectInProcess = GeneralTabPage.getFirstSelectedOptionTextOfDavisonReturnBatch();
    String expectedInProcessStatus = "In Process";
    Assert.assertEquals(selectInProcess, expectedInProcessStatus);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    GeneralTabPage.selectDavisonReturnBatchStatusOption("Pending-Supervisor");
    String selectSubmitted = GeneralTabPage.getFirstSelectedOptionTextOfDavisonReturnBatch();
    String expectedSubmittedStatus = "Pending-Supervisor";
    Assert.assertEquals(selectSubmitted, expectedSubmittedStatus);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    GeneralTabPage.selectDavisonReturnBatchStatusOption("Pending-Payroll");
    String selectApproved = GeneralTabPage.getFirstSelectedOptionTextOfDavisonReturnBatch();
    String expectedApprovedStatus = "Pending-Payroll";
    Assert.assertEquals(selectApproved, expectedApprovedStatus);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    GeneralTabPage.selectDavisonReturnBatchStatusOption("Authorized");
    String selectAuthorized = GeneralTabPage.getFirstSelectedOptionTextOfDavisonReturnBatch();
    String expectedAuthorizedStatus = "Authorized";
    Assert.assertEquals(selectAuthorized, expectedAuthorizedStatus);
    GeneralTabPage.clickGeneralOptionsSaveButton();
  }

  /**
   * This test will test the group consecutive hours on overnight shifts setting.
   */
  @Test(priority = 7)
  public static void groupConsecutiveHoursOnOvernightShifts() {
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(groupConsecutiveHours, yes)) {
      setSettingToNo(groupConsecutiveHours, no);
    }
    setSettingToYes(groupConsecutiveHours, yes);
    setSettingToNo(groupConsecutiveHours, no);
  }

  /**
   * This test will test adding quantities to the total timesheet text field setting.
   */
  @Test(priority = 8)
  public static void totalTimesheet() {
    SeleniumWrapper.sendKeyWithClear(driver, GeneralTabPage.enterTimesheet(), "2");
    GeneralTabPage.clickGeneralOptionsSaveButton();
    WebElement timesheet = GeneralTabPage.enterTimesheet();
    String totalTimesheetValue = timesheet.getAttribute(value);
    Assert.assertEquals(totalTimesheetValue, "2");
    timesheet.clear();
    GeneralTabPage.clickGeneralOptionsSaveButton();
    WebElement timesheetNew = GeneralTabPage.enterTimesheet();
    String totalTimesheetDefaultValue = timesheetNew.getAttribute(value);
    Assert.assertEquals(totalTimesheetDefaultValue, "1");
  }

  /**
   * This test will test adding quantities to the timesheet-uneditable pay period text field setting.
   */
  @Test(priority = 9)
  public static void timesheetUneditablePayPeriod() {
    SeleniumWrapper.sendKeyWithClear(driver, GeneralTabPage.getTimesheetUnEditablePayPeriods(), "3");
    GeneralTabPage.clickGeneralOptionsSaveButton();
    WebElement timesheetPayPeriod = GeneralTabPage.getTimesheetUnEditablePayPeriods();
    String totalTimesheetValue = timesheetPayPeriod.getAttribute(value);
    Assert.assertEquals(totalTimesheetValue, "3");
    timesheetPayPeriod.clear();
    GeneralTabPage.clickGeneralOptionsSaveButton();
    String timesheetPayPeriodDefaultValue = GeneralTabPage.getTimesheetUnEditablePayPeriods().getAttribute(value);
    Assert.assertEquals(timesheetPayPeriodDefaultValue, "2");
  }

  /**
   * This test will test the admins and the different selections as well as the payroll notification.
   */
  @Test(priority = 10)
  public static void admins() {
    if (GeneralTabPageInterface.getWebElement(driver, GeneralTabPageInterface.ADMIN_TYPE_SELECTION_DROPDOWN) != null) {
      GeneralTabPage.getAdminTypeSelection("Approver");
      GeneralTabPage.clickGeneralOptionsSaveButton();
      Assert.assertEquals(GeneralTabPage.getFirstSelectedOptionTextOfAdminType(), "Approver");
      GeneralTabPage.isAdminPayrollNotificationDisplayed(driver);
      Assert.assertFalse(GeneralTabPage.isAdminPayrollNotificationDisplayed(driver));
      GeneralTabPage.getAdminTypeSelection("Approver /W Rate");
      GeneralTabPage.clickGeneralOptionsSaveButton();
      Assert.assertEquals(GeneralTabPage.getFirstSelectedOptionTextOfAdminType(), "Approver /W Rate");
      Assert.assertFalse(GeneralTabPage.isAdminPayrollNotificationDisplayed(driver));
      GeneralTabPage.getAdminTypeSelection("Read-Only");
      GeneralTabPage.clickGeneralOptionsSaveButton();
      Assert.assertEquals(GeneralTabPage.getFirstSelectedOptionTextOfAdminType(), "Read-Only");
      Assert.assertFalse(GeneralTabPage.isAdminPayrollNotificationDisplayed(driver));
      GeneralTabPage.getAdminTypeSelection("Coordinator");
      GeneralTabPage.clickGeneralOptionsSaveButton();
      Assert.assertEquals(GeneralTabPage.getFirstSelectedOptionTextOfAdminType(), "Coordinator");
      Assert.assertFalse(GeneralTabPage.isAdminPayrollNotificationDisplayed(driver));
      GeneralTabPage.getAdminTypeSelection("Payroll");
      GeneralTabPage.clickGeneralOptionsSaveButton();
      Assert.assertEquals(GeneralTabPage.getFirstSelectedOptionTextOfAdminType(), "Payroll");
      Assert.assertTrue(GeneralTabPage.isAdminPayrollNotificationDisplayed(driver));
    }
  }

  /**
   * This test will test the allow submission of timesheets that do not contain paid time, deductions, or time off
   * requests setting.
   */
  @Test(priority = 11)
  public static void allowToSubmitEmptyTimesheet() {
    JavascriptExecutorWrapper.scrollToBottom(driver);
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(allowSubmissionOfTimeSheetThatDoNotContainPaidTime, yes)) {
      setSettingToNo(allowSubmissionOfTimeSheetThatDoNotContainPaidTime, no);
    }
    JavascriptExecutorWrapper.scrollToBottom(driver);
    setSettingToYes(allowSubmissionOfTimeSheetThatDoNotContainPaidTime, yes);
    setSettingToNo(allowSubmissionOfTimeSheetThatDoNotContainPaidTime, no);
  }

  /**
   * This test will test the allow submission to ALTRES with any status setting.
   */
  @Test(priority = 12)
  public static void allowSubmissionToALTRESWithAnyStatus() {

    JavascriptExecutorWrapper.scrollToBottom(driver);
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(allowSubmissionToAltresAtAnyStatus, yes)) {
      setSettingToNo(allowSubmissionToAltresAtAnyStatus, no);
    }
    JavascriptExecutorWrapper.scrollToBottom(driver);
    setSettingToYes(allowSubmissionToAltresAtAnyStatus, yes);
    setSettingToNo(allowSubmissionToAltresAtAnyStatus, no);
  }

  /**
   * This test will test the allow timesheet attachments setting.
   */
  @Test(priority = 13)
  public static void allowTimesheetAttachments() {

    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(allowTimesheetAttachments, yes)) {
      GeneralTabPage.clickGeneralOptionsRadioButton(allowTimesheetAttachments, no);
      GeneralTabPage.clickGeneralOptionsSaveButton();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(allowTimesheetAttachments, no));
    }

    GeneralTabPage.clickGeneralOptionsRadioButton(allowTimesheetAttachments, yes);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(allowTimesheetAttachments, yes));
    WebElement allowTimesheetAttachmentsEmployeeLevel = GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALLOW_TIMESHEET_ATTACHMENTS);
    Assert.assertTrue(
        allowTimesheetAttachmentsEmployeeLevel != null && allowTimesheetAttachmentsEmployeeLevel.isDisplayed());
    String actualDefaultSelectedText = GeneralTabPage.getFirstSelectedOptionTextOfAllowTimesheetAttachments();
    String expectedDefaultEmployeeGroupText = defaultEnabled;
    Assert.assertEquals(actualDefaultSelectedText, expectedDefaultEmployeeGroupText);
    if (GeneralTabPage.getAllowTimesheetAttachmentsEmployeeLevelOptionsSize() > 1) {
      GeneralTabPage.selectEmployeeLevelOptionOfAllowTimesheetAttachments(1);
      String newSelectedEmployeeGroup = GeneralTabPage.getFirstSelectedOptionTextOfAllowTimesheetAttachments();
      Assert.assertNotEquals(actualDefaultSelectedText, newSelectedEmployeeGroup);
    }
    GeneralTabPage.clickGeneralOptionsSaveButton();
    GeneralTabPage.clickGeneralOptionsRadioButton(allowTimesheetAttachments, no);
    SeleniumWrapper.acceptAlertIfPresent(driver);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    WebElement allowTimesheetAttachmentEmployeeLevelNew = GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALLOW_TIMESHEET_ATTACHMENTS);
    Assert.assertFalse(allowTimesheetAttachmentEmployeeLevelNew.isDisplayed());
    SeleniumWrapper.acceptAlertIfPresent(driver);
  }

  /**
   * This test will test the allow client to enable/disable eTime per employee setting.
   */
  @Test(priority = 14)
  public static void allowClientToEnableDisableETimePerEmployee() {
    JavascriptExecutorWrapper.scrollToBottom(driver);
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(allowClientToEnableDisableEtimePerEmployee, yes)) {
      setSettingToNo(allowClientToEnableDisableEtimePerEmployee, no);
    }
    JavascriptExecutorWrapper.scrollToBottom(driver);
    setSettingToYes(allowClientToEnableDisableEtimePerEmployee, yes);
    setSettingToNo(allowClientToEnableDisableEtimePerEmployee, no);
  }

  /**
   * This test will test the allow email to supervisors for overage hours setting. This Test will also test the also
   * send email to employees for overage hours child setting. This test will also test the email On overrage hours
   * field.
   */

  @Test(priority = 15)
  public static void allowEmailToSupervisorsEmployeesForOverageHours() {
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(allowEmailToSupervisorsForOverageHours, yes)) {
      GeneralTabPage.clickGeneralOptionsRadioButton(allowEmailToSupervisorsForOverageHours, no);
      GeneralTabPage.clickGeneralOptionsSaveButton();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(allowEmailToSupervisorsForOverageHours, no));
    }
    GeneralTabPage.clickGeneralOptionsRadioButton(allowEmailToSupervisorsForOverageHours, yes);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(allowEmailToSupervisorsForOverageHours, yes));
    WebElement employeeLevel = GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALLOW_EMAIL_TO_SUPERVISORS_FOR_OVERAGE_HOURS_EMPLOYEE_LEVEL);
    Assert.assertTrue(employeeLevel != null && employeeLevel.isDisplayed());
    String currentEmployeeGroupValue = SeleniumWrapper.getSelectedDropdownValue(
        GeneralTabPageInterface.ALLOW_EMAIL_TO_SUPERVISORS_FOR_OVERAGE_HOURS_EMPLOYEE_LEVEL);
    String defaultEmployeeGroup = defaultEnabled;
    Assert.assertEquals(currentEmployeeGroupValue, defaultEmployeeGroup);
    Assert.assertTrue(GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALSO_SEND_EMAIL_TO_EMPLOYEE_FOR_OVERAGE_HOURS_YES_BUTTON).isEnabled());
    int employeeGroups = SeleniumWrapper.getDropdownOptionsSize(
        GeneralTabPageInterface.ALLOW_EMAIL_TO_SUPERVISORS_FOR_OVERAGE_HOURS_EMPLOYEE_LEVEL);
    if (employeeGroups > 0) {
      SeleniumWrapper.setDropDownByIndex(GeneralTabPageInterface.getWebElement(driver,
          GeneralTabPageInterface.ALLOW_EMAIL_TO_SUPERVISORS_FOR_OVERAGE_HOURS_EMPLOYEE_LEVEL), 1);
    }
    GeneralTabPage.clickGeneralOptionsSaveButton();
    String newEmployeeGroup = SeleniumWrapper.getSelectedDropdownValue(
        GeneralTabPageInterface.ALLOW_EMAIL_TO_SUPERVISORS_FOR_OVERAGE_HOURS_EMPLOYEE_LEVEL);
    Assert.assertEquals(currentEmployeeGroupValue, newEmployeeGroup);
    Assert.assertTrue(GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALSO_SEND_EMAIL_TO_EMPLOYEE_FOR_OVERAGE_HOURS_NO_BUTTON).isSelected());
    GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALSO_SEND_EMAIL_TO_EMPLOYEE_FOR_OVERAGE_HOURS_YES_BUTTON).click();
    GeneralTabPageInterface.getWebElement(driver, GeneralTabPageInterface.EMAIL_ON_OVERAGE_HOURS).sendKeys("20");
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALSO_SEND_EMAIL_TO_EMPLOYEE_FOR_OVERAGE_HOURS_YES_BUTTON).isSelected());
    String overageHoursValue = GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.EMAIL_ON_OVERAGE_HOURS).getAttribute(value);
    Assert.assertTrue(overageHoursValue.contains("20"));
    GeneralTabPage.clickGeneralOptionsRadioButton(allowEmailToSupervisorsForOverageHours, no);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertFalse(GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALLOW_EMAIL_TO_SUPERVISORS_FOR_OVERAGE_HOURS_EMPLOYEE_LEVEL).isDisplayed());
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(allowEmailToSupervisorsForOverageHours, no));
    Assert.assertFalse(GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALSO_SEND_EMAIL_TO_EMPLOYEE_FOR_OVERAGE_HOURS_YES_BUTTON).isDisplayed());
    Assert.assertFalse(
        GeneralTabPageInterface.getWebElement(driver, GeneralTabPageInterface.EMAIL_ON_OVERAGE_HOURS).isDisplayed());
  }

  /**
   * This test will test the use vertical timesheet setting.
   */
  @Test(priority = 17)
  public static void useVerticalTimesheet() {
    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    JavascriptExecutorWrapper.scrollToElement(driver, GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_YES_BUTTON));
    if (GeneralTabPageInterface.getWebElement(driver, GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_YES_BUTTON)
        .isSelected()) {
      GeneralTabPageInterface.getWebElement(driver, GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_NO_BUTTON).click();
      GeneralTabPage.clickGeneralOptionsSaveButton();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      Assert.assertTrue(GeneralTabPageInterface.getWebElement(driver,
          GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_NO_BUTTON).isSelected());
    }
    GeneralTabPageInterface.getWebElement(driver, GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_YES_BUTTON).click();
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_YES_BUTTON).isSelected());
    WebElement employeeLevel = GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_EMPLOYEE_LEVEL);
    Assert.assertTrue(employeeLevel != null && employeeLevel.isDisplayed());
    String currentEmployeeGroup = SeleniumWrapper.getSelectedDropdownValue(
        GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_EMPLOYEE_LEVEL);
    String defaultEmployeeGroup = defaultEnabled;
    Assert.assertEquals(currentEmployeeGroup, defaultEmployeeGroup);
    Assert.assertTrue(GeneralTabPageInterface.getWebElement(driver,
            GeneralTabPageInterface.ALLOW_IN_AND_OUT_TIMES_FOR_LEAVE_CODES_THAT_DO_NOT_REQUIRE_APPROVAL_YES_BUTTON)
        .isEnabled());
    Assert.assertTrue(GeneralTabPageInterface.getWebElement(driver,
            GeneralTabPageInterface.ALLOW_IN_AND_OUT_TIMES_FOR_LEAVE_CODES_THAT_DO_NOT_REQUIRE_APPROVAL_YES_BUTTON)
        .isEnabled());
    int employeeGroups = SeleniumWrapper.getDropdownOptionsSize(
        GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_EMPLOYEE_LEVEL);
    if (employeeGroups > 0) {
      SeleniumWrapper.setDropDownByIndex(GeneralTabPageInterface.getWebElement(driver,
              GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_EMPLOYEE_LEVEL),
          1);
    }
    GeneralTabPage.clickGeneralOptionsSaveButton();
    String newEmployeeGroup = SeleniumWrapper.getSelectedDropdownValue(
        GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_EMPLOYEE_LEVEL);
    Assert.assertEquals(currentEmployeeGroup, newEmployeeGroup);
    Assert.assertTrue(GeneralTabPageInterface.getWebElement(driver,
            GeneralTabPageInterface.ALLOW_IN_AND_OUT_TIMES_FOR_LEAVE_CODES_THAT_DO_NOT_REQUIRE_APPROVAL_NO_BUTTON)
        .isSelected());
    GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALLOW_IN_AND_OUT_TIMES_FOR_LEAVE_CODES_THAT_DO_NOT_REQUIRE_APPROVAL_YES_BUTTON).click();
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPageInterface.getWebElement(driver,
            GeneralTabPageInterface.ALLOW_IN_AND_OUT_TIMES_FOR_LEAVE_CODES_THAT_DO_NOT_REQUIRE_APPROVAL_YES_BUTTON)
        .isSelected());
    GeneralTabPageInterface.getWebElement(driver, GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_NO_BUTTON).click();
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertFalse(
        GeneralTabPageInterface.getWebElement(driver, GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_EMPLOYEE_LEVEL)
            .isDisplayed());
    Assert.assertTrue(GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.USE_VERTICAL_TIMESHEET_NO_BUTTON).isSelected());
    Assert.assertFalse(GeneralTabPageInterface.getWebElement(driver,
            GeneralTabPageInterface.ALLOW_IN_AND_OUT_TIMES_FOR_LEAVE_CODES_THAT_DO_NOT_REQUIRE_APPROVAL_YES_BUTTON)
        .isDisplayed());
  }

  /**
   * This test will test the allow cross pay period shift setting.
   */
  @Test(priority = 18)
  public static void allowCrossPayPeriodShift() {
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(allowCrossPayPeriodShift, yes)) {
      GeneralTabPage.clickGeneralOptionsRadioButton(allowCrossPayPeriodShift, no);
      GeneralTabPage.clickGeneralOptionsSaveButton();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(allowCrossPayPeriodShift, no));
    }
    GeneralTabPage.clickGeneralOptionsRadioButton(allowCrossPayPeriodShift, yes);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(allowCrossPayPeriodShift, yes));
    WebElement employeeLevel = GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALLOW_CROSS_PAY_PERIOD_SHIFT_EMPLOYEE_LEVEL);
    Assert.assertTrue(employeeLevel != null && employeeLevel.isDisplayed());
    String currentEmployeeGroup = SeleniumWrapper.getSelectedDropdownValue(
        GeneralTabPageInterface.ALLOW_CROSS_PAY_PERIOD_SHIFT_EMPLOYEE_LEVEL);
    String defaultEmployeeGroup = defaultEnabled;
    Assert.assertEquals(currentEmployeeGroup, defaultEmployeeGroup);
    int employeeGroups = SeleniumWrapper.getDropdownOptionsSize(
        GeneralTabPageInterface.ALLOW_CROSS_PAY_PERIOD_SHIFT_EMPLOYEE_LEVEL);
    if (employeeGroups > 0) {
      SeleniumWrapper.setDropDownByIndex(GeneralTabPageInterface.getWebElement(driver,
              GeneralTabPageInterface.ALLOW_CROSS_PAY_PERIOD_SHIFT_EMPLOYEE_LEVEL),
          1);
    }
    GeneralTabPage.clickGeneralOptionsSaveButton();
    String newEmployeeGroup = SeleniumWrapper.getSelectedDropdownValue(
        GeneralTabPageInterface.ALLOW_CROSS_PAY_PERIOD_SHIFT_EMPLOYEE_LEVEL);
    Assert.assertEquals(currentEmployeeGroup, newEmployeeGroup);
    GeneralTabPage.clickGeneralOptionsRadioButton(allowCrossPayPeriodShift, no);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertFalse(GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALLOW_CROSS_PAY_PERIOD_SHIFT_EMPLOYEE_LEVEL).isDisplayed());
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(allowCrossPayPeriodShift, no));
    SeleniumWrapper.acceptAlertIfPresent(driver);
  }

  /**
   * This test will test the allow exempt pay without hours adjustment setting.
   */
  @Test(priority = 19)
  public static void allowExemptPayWithoutHoursAdjustment() {
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(allowExemptPayWithoutHoursAdjustment, yes)) {
      GeneralTabPage.clickGeneralOptionsRadioButton(allowExemptPayWithoutHoursAdjustment, no);
      GeneralTabPage.clickGeneralOptionsSaveButton();
      SeleniumWrapper.acceptAlertIfPresent(driver);
      Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(allowExemptPayWithoutHoursAdjustment, no));
    }
    GeneralTabPage.clickGeneralOptionsRadioButton(allowExemptPayWithoutHoursAdjustment, yes);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(allowExemptPayWithoutHoursAdjustment, yes));

    WebElement employeeLevel = GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALLOW_EXEMPT_PAY_WITHOUT_HOURS_ADJUSTMENT_EMPLOYEE_LEVEL);
    Assert.assertTrue(employeeLevel != null && employeeLevel.isDisplayed());
    String currentEmployeeGroup = SeleniumWrapper.getSelectedDropdownValue(
        GeneralTabPageInterface.ALLOW_EXEMPT_PAY_WITHOUT_HOURS_ADJUSTMENT_EMPLOYEE_LEVEL);
    String defaultEmployeeGroup = defaultEnabled;
    Assert.assertEquals(currentEmployeeGroup, defaultEmployeeGroup);
    int employeeGroups = SeleniumWrapper.getDropdownOptionsSize(
        GeneralTabPageInterface.ALLOW_EXEMPT_PAY_WITHOUT_HOURS_ADJUSTMENT_EMPLOYEE_LEVEL);
    if (employeeGroups > 0) {
      SeleniumWrapper.setDropDownByIndex(GeneralTabPageInterface.getWebElement(driver,
              GeneralTabPageInterface.ALLOW_EXEMPT_PAY_WITHOUT_HOURS_ADJUSTMENT_EMPLOYEE_LEVEL),
          1);
    }
    GeneralTabPage.clickGeneralOptionsSaveButton();
    String newEmployeeGroup = SeleniumWrapper.getSelectedDropdownValue(
        GeneralTabPageInterface.ALLOW_EXEMPT_PAY_WITHOUT_HOURS_ADJUSTMENT_EMPLOYEE_LEVEL);
    Assert.assertEquals(currentEmployeeGroup, newEmployeeGroup);
    GeneralTabPage.clickGeneralOptionsRadioButton(allowExemptPayWithoutHoursAdjustment, no);
    SeleniumWrapper.acceptAlertIfPresent(driver);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    Assert.assertFalse(GeneralTabPageInterface.getWebElement(driver,
        GeneralTabPageInterface.ALLOW_EXEMPT_PAY_WITHOUT_HOURS_ADJUSTMENT_EMPLOYEE_LEVEL).isDisplayed());
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(allowExemptPayWithoutHoursAdjustment, no));
  }

  /**
   * This test will test the submit payroll through timesheet entry setting.
   */
  @Test(priority = 20)
  public static void submitPayrollThroughTimesheetEntry() {
    JavascriptExecutorWrapper.scrollToBottom(driver);
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(submitPayrollThroughTimesheetEntry, yes)) {
      setSettingToNo(submitPayrollThroughTimesheetEntry, no);
    }
    JavascriptExecutorWrapper.scrollToBottom(driver);
    setSettingToYes(submitPayrollThroughTimesheetEntry, yes);
    setSettingToNo(submitPayrollThroughTimesheetEntry, no);
  }

  /**
   * This test will test the submit payroll through timesheet entry setting.
   */
  @Test(priority = 21)
  public static void allowCovidPayCodeToEmployees() {
    JavascriptExecutorWrapper.scrollToBottom(driver);
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(allowCovidPayCodeToEmployee, yes)) {
      setSettingToNo(allowCovidPayCodeToEmployee, no);
    }
    JavascriptExecutorWrapper.scrollToBottom(driver);
    setSettingToYes(allowCovidPayCodeToEmployee, yes);
    setSettingToNo(allowCovidPayCodeToEmployee, no);
  }

  /**
   * This Test will test the display employee minor status setting.
   */
  @Test(priority = 22)
  public static void displayEmployeeMinorStatus() {
    JavascriptExecutorWrapper.scrollToBottom(driver);
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(displayEmployeeMinorStatus, yes)) {
      setSettingToNo(displayEmployeeMinorStatus, no);
    }
    JavascriptExecutorWrapper.scrollToBottom(driver);
    setSettingToYes(displayEmployeeMinorStatus, yes);
    setSettingToNo(displayEmployeeMinorStatus, no);
  }

  /**
   * This test will test the allow SCA rules setting.
   */
  @Test(priority = 23)
  public static void allowSCARules() {
    JavascriptExecutorWrapper.scrollToBottom(driver);
    if (GeneralTabPage.isSelectedGeneralOptionRadioButton(allowSCARules, yes)) {
      setSettingToNo(allowSCARules, no);
    }
    JavascriptExecutorWrapper.scrollToBottom(driver);
    setSettingToYes(allowSCARules, yes);
    setSettingToNo(allowSCARules, no);
  }

  /**
   * Method set setting to Yes.
   *
   * @param heading
   * @param value
   */
  public static void setSettingToYes(String heading, String value) {
    GeneralTabPage.clickGeneralOptionsRadioButton(heading, value);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(heading, value));
  }

  /**
   * Method set setting to No.
   *
   * @param heading
   * @param value
   */
  public static void setSettingToNo(String heading, String value) {
    GeneralTabPage.clickGeneralOptionsRadioButton(heading, value);
    GeneralTabPage.clickGeneralOptionsSaveButton();
    SeleniumWrapper.acceptAlertIfPresent(driver);
    Assert.assertTrue(GeneralTabPage.isSelectedGeneralOptionRadioButton(heading, value));
  }
}