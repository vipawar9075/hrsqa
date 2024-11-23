package com.altres.projects.payroll.superuser.generaloption.generaloptiontest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.exception.SeleniumExceptions;
import com.altres.login.LoginPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.JobCostingLaborAllocationTab;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.JobCostingLaborAllocationTabInterface;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.OverTimeTabPage;
import com.altres.projects.payroll.superuser.generaloption.generaloptionpages.OverTimeTabPageInterface;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollConstants;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollHelperUtil;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollMenusAndLinksUtil;
import com.altres.util.FakeDataGenerator;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

/**
 * Selenium scripts to test general options job costing labor allocation tab.
 */
public class GeneralOption_JobCostingLaborAllocation extends Base {

  /**
   * This test will select each overtime option and will also check the message that shows for each of the overtime
   * method selections.
   *
   * @throws Exception
   */
  @Test(priority = 1)
  public void allowOverrideOvertimeAllocation() throws Exception {

    LoginPage.loginAsSuperUser(driver);
    try {
      PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
      SeleniumWrapper.switchToDefaultContent(driver);
      SeleniumWrapper.switchToInteriorContent(driver);
      PayrollMenusAndLinksUtil.clickJobCostingLaborAllocationTab();
      if (!SeleniumWrapper.isLocatorDisplayed(
          JobCostingLaborAllocationTabInterface.ALLOW_OVERRIDE_OVERTIME_ALLOCATION)) {
        PayrollMenusAndLinksUtil.clickOvertimeTab();
        OverTimeTabPage.clickYesToComputeOvertimeHours(driver);
        OverTimeTabPageInterface.getWebElement(driver, OverTimeTabPageInterface.COMPUTE_WEEKLY_OVERTIME_YES_BUTTON)
            .click();
        SeleniumWrapper.selectDropdownByVisibleText(PayrollConstants.OVERTIME_PAY_CODE, "O/T");
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
        PayrollMenusAndLinksUtil.clickJobCostingLaborAllocationTab();
      }
      ArrayList<String> allocationList = new ArrayList<>(Arrays.asList("N", "S", "P"));
      PayrollMenusAndLinksUtil.clickJobCostingLaborAllocationTab();
      WebElement allowOverrideOvertimeAllocationDropdown = SeleniumWrapper.findElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_OVERRIDE_OVERTIME_ALLOCATION);
      for (String options : allocationList) {
        WebElement dropdown = new WebDriverWait(driver, Duration.ofSeconds(10)).until(
            ExpectedConditions.visibilityOf(allowOverrideOvertimeAllocationDropdown));
        Select select = new Select(dropdown);
        select.selectByValue(options);
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      SeleniumWrapper.switchToDefaultContent(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
  }

  /**
   * This test will check and uncheck all the checkboxes for the free form labor cost center. This test will also test
   * the use description radio buttons.
   *
   * @throws Exception
   */
  @Test(priority = 2)
  public void checkingCheckBoxesForFreeFormLaborCostCentre() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      List<WebElement> allFreeFormCostings = SeleniumWrapper.findElements(driver,
          JobCostingLaborAllocationTabInterface.FREE_FROM_LABOR_COST_CENTRE_ALL_CHECK_BOXES);
      List<WebElement> notSelectedCheckboxes = allFreeFormCostings.stream()
          .filter(costing -> !costing.isSelected())
          .collect(Collectors.toList());
      notSelectedCheckboxes.forEach(WebElement::click);
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          JobCostingLaborAllocationTabInterface.CLICK_ON_YES_RADIO_BUTTON_FOR_USE_DESCRIPTION));
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.CLICK_ON_LOCN_CHECK_BOX);
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          JobCostingLaborAllocationTabInterface.CLICK_ON_YES_RADIO_BUTTON_FOR_USE_DESCRIPTION));
      SeleniumWrapper.switchToDefaultContent(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
  }

  /**
   * This test will test selecting a step, and each of the costing with the step This test will also test adding and
   * deleting a new row.
   *
   * @throws Exception
   */
  @Test(priority = 3)
  public static void testGetTimeSheetFlowRestrictionsTable() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      new WebDriverWait(driver, Duration.ofSeconds(2));
      if ((JobCostingLaborAllocationTabInterface.getWebElements(driver,
          JobCostingLaborAllocationTabInterface.TIMESHEET_FLOW_RESTRICTIONS_ROWS).isEmpty())) {
        JobCostingLaborAllocationTabInterface.getWebElement(driver,
            JobCostingLaborAllocationTabInterface.TIMESHEET_FLOW_RESTRICTIONS_ADD_ROW_BUTTON).click();
      }
      WebElement stepSelection =
          JobCostingLaborAllocationTab.isTimeSheetFlowRestrictionsStepSelectionDisplayed(driver, 0)
              ? JobCostingLaborAllocationTab.getTimeSheetFlowRestrictionsStepSelection(driver, 0)
              : JobCostingLaborAllocationTab.getTimeSheetFlowRestrictionsStepSelection(driver, 1);
      Map<String, String> stepSelectionOptionValues = SeleniumWrapper.getAvailableDropdownOptions(stepSelection);
      List<String> stepOptionValues = new ArrayList<>(stepSelectionOptionValues.keySet());
      for (String setupSelectionOption : stepOptionValues) {
        Select stepOptions = new Select(
            JobCostingLaborAllocationTab.isTimeSheetFlowRestrictionsStepSelectionDisplayed(driver, 0)
                ? JobCostingLaborAllocationTab.getTimeSheetFlowRestrictionsStepSelection(driver, 0)
                : JobCostingLaborAllocationTab.getTimeSheetFlowRestrictionsStepSelection(driver, 1));
        stepOptions.selectByValue(setupSelectionOption);
        WebElement laborCostCentreSelection =
            JobCostingLaborAllocationTab.isTimeSheetFlowRestrictionsLaborCostCenterSelectionDisplayed(driver, 0)
                ? JobCostingLaborAllocationTab.getTimeSheetFlowRestrictionsLaborCostCenterSelection(driver, 0)
                : JobCostingLaborAllocationTab.getTimeSheetFlowRestrictionsLaborCostCenterSelection(driver, 1);
        Map<String, String> laborCostCenterSelectionOptionValues = SeleniumWrapper.getAvailableDropdownOptions(
            laborCostCentreSelection);
        List<String> laborCostCenterOptionValues = new ArrayList<>(laborCostCenterSelectionOptionValues.keySet());

        for (String laborCostCanerOption : laborCostCenterOptionValues) {
          WebElement laborCostCenterSelectionOptions =
              JobCostingLaborAllocationTab.isTimeSheetFlowRestrictionsLaborCostCenterSelectionDisplayed(driver, 0)
                  ? JobCostingLaborAllocationTab.getTimeSheetFlowRestrictionsLaborCostCenterSelection(driver, 0)
                  : JobCostingLaborAllocationTab.getTimeSheetFlowRestrictionsLaborCostCenterSelection(driver, 1);
          Select laborCostCenterOptions = new Select(laborCostCenterSelectionOptions);
          laborCostCenterOptions.selectByValue(laborCostCanerOption);
          WebElement costCodeSelection =
              JobCostingLaborAllocationTab.isTimeSheetFlowRestrictionsCostFieldTextSelectionDisplayed(driver, 0)
                  ? JobCostingLaborAllocationTab.getTimeSheetFlowRestrictionsCostFieldTextSelection(driver, 0)
                  : JobCostingLaborAllocationTab.getTimeSheetFlowRestrictionsCostFieldTextSelection(driver, 1);
          SeleniumWrapper.sendKeyWithClear(driver, costCodeSelection, laborCostCanerOption);
          PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
        }
      }
      int currentRows = PayrollHelperUtil.getNumberOfRows(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.TIMESHEET_FLOW_RESTRICTIONS_TABLE));
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.TIMESHEET_FLOW_RESTRICTIONS_ADD_ROW_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      int rowNumber = PayrollHelperUtil.getNumberOfRows(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.TIMESHEET_FLOW_RESTRICTIONS_TABLE));
      Assert.assertTrue(rowNumber > currentRows);
      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.TIMESHEET_FLOW_RESTRICTIONS_TABLE));
      PayrollHelperUtil.deleteTableRows(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.TIMESHEET_FLOW_RESTRICTIONS_TABLE), 1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will select an employee group and add text to each of the costing text fields. This will also test the
   * error message when adding a new row without selecting an employee group. This will also test deleting a row.
   *
   * @throws Exception
   */
  @Test(priority = 4)
  public static void testLaborCostCenterDescriptionOverridesTable() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    new WebDriverWait(driver, Duration.ofSeconds(2));
    List<WebElement> rows = JobCostingLaborAllocationTabInterface.getWebElements(driver,
        JobCostingLaborAllocationTabInterface.LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_TABLE);
    if (rows.isEmpty()) {
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_ADD_ROW_BUTTON).click();
    }
    if (SeleniumWrapper.getDropdownOptionsSize(
        JobCostingLaborAllocationTabInterface.LABOR_COST_CENTER_DESCRIPTION_OVERRIDES_DROPDOWN) > 1) {
      SeleniumWrapper.setDropDownByIndex(
          JobCostingLaborAllocationTab.getLaborCostCenterDescriptionOverrideEmployeeGroupSelection(driver, 1), 2);
      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_TABLE));
      List<WebElement> allTextFields = JobCostingLaborAllocationTab.getLaborCostCenterDescriptionOverrideAllTextFields(
          driver, 1);
      for (WebElement textField : allTextFields) {
        JavascriptExecutorWrapper.scrollToElement(driver, textField);
        SeleniumWrapper.sendKeyWithClear(driver, textField, FakeDataGenerator.getRandomTitle());
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_ADD_ROW_BUTTON).click();
      PayrollMenusAndLinksUtil.clickOnSaveButton();
      try {
        String errorMessageDisplayed = PayrollHelperUtil.getErrorNoticeMessageText(driver);
        String errorActualMessage = "Please select employee groups for all Labor Cost Center Description Overrides.";
        Assert.assertEquals(errorMessageDisplayed, errorActualMessage);
        SeleniumWrapper.setDropDownByIndex(
            JobCostingLaborAllocationTab.getLaborCostCenterDescriptionOverrideEmployeeGroupSelection(driver, 2), 2);
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

        Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElements(driver,
            JobCostingLaborAllocationTabInterface.LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_TABLE).size() > rows.size());
        PayrollHelperUtil.deleteTableRows(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
            JobCostingLaborAllocationTabInterface.LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_TABLE_ROW), 2);

        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      } catch (Throwable e) {
        SeleniumExceptions.handleException(e, driver);
      }
    } else {
      PayrollHelperUtil.deleteTableRows(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_TABLE_ROW), 1);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will select an employee group and add text to each of the costing text field. This will also test the
   * error message when adding a new row without selecting an employee group. This will also test deleting a row This
   * will also test setting all costings to yes and then to no.
   *
   * @throws Exception
   */
  @Test(priority = 5)
  public static void testHiddenLaborCostCenterTable() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);

    List<WebElement> rows = JobCostingLaborAllocationTabInterface.getWebElements(driver,
        JobCostingLaborAllocationTabInterface.HIDDEN_LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_ALL_ROWS);
    int rowNumber = rows.size();
    if (rows.isEmpty()) {
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.HIDDEN_LABOR_COST_CENTERS_ADD_ROW).click();
    }
    if (SeleniumWrapper.getDropdownOptionsSize(
        JobCostingLaborAllocationTabInterface.HIDDEN_LABOR_COST_CENTER_DESCRIPTION_OVERRIDES_DROPDOWN) > 1) {
      List<WebElement> allDropdownFieldElements = JobCostingLaborAllocationTab.getHiddenLaborCostCenterDescriptionOverrideAllDropdownFields(
          driver, 1);
      for (WebElement dropdownFieldElement : allDropdownFieldElements) {
        Select allDropdownFields = new Select(dropdownFieldElement);
        String fieldName = dropdownFieldElement.getAttribute("name");
        if (fieldName.contains("name")) {
          allDropdownFields.selectByIndex(1);
        } else {
          JavascriptExecutorWrapper.scrollToElement(driver, dropdownFieldElement);
          allDropdownFields.selectByValue("Y");
        }
      }
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.HIDDEN_LABOR_COST_CENTERS_ADD_ROW).click();
      PayrollMenusAndLinksUtil.clickOnSaveButton();
      try {
        String errorMessageDisplayed = PayrollHelperUtil.getErrorNoticeMessageText(driver);
        String errorActualMessage = "Please select employee groups for all Hidden Labor Cost Centers.";
        Assert.assertEquals(errorMessageDisplayed, errorActualMessage);
        SeleniumWrapper.setDropDownByIndex(
            JobCostingLaborAllocationTab.getHiddenLaborCostCentersEmployeeGroupSelection(driver, 2), 2);
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

        Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElements(driver,
            JobCostingLaborAllocationTabInterface.HIDDEN_LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_ALL_ROWS).size()
            > rowNumber);
        PayrollHelperUtil.deleteTableRows(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
            JobCostingLaborAllocationTabInterface.HIDDEN_LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_TABLE), 2);
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

        List<WebElement> allDropdownFieldElements2 = JobCostingLaborAllocationTab.getHiddenLaborCostCenterDescriptionOverrideAllDropdownFields(
            driver, 1);
        for (WebElement dropdownFieldElement2 : allDropdownFieldElements2) {
          Select allDropdownFields2 = new Select(dropdownFieldElement2);
          String fieldName = dropdownFieldElement2.getAttribute("name");
          if (fieldName.contains("name")) {
            allDropdownFields2.selectByIndex(1);
          } else {
            JavascriptExecutorWrapper.scrollToElement(driver, dropdownFieldElement2);
            allDropdownFields2.selectByValue("N");
            String selectedValue = allDropdownFields2.getFirstSelectedOption().getAttribute("value");
            Assert.assertEquals(selectedValue, "N");
          }
        }
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

        WebElement hideLocation = JobCostingLaborAllocationTab.getHiddenLaborCostCentersLocationSelection(driver, 1);
        Select setHideLocation = new Select(hideLocation);
        setHideLocation.selectByValue("Y");
        String locationSelectedValue = setHideLocation.getFirstSelectedOption().getAttribute("value");
        Assert.assertEquals(locationSelectedValue, "Y");
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      } catch (Throwable e) {
        SeleniumExceptions.handleException(e, driver);
      }
    } else {
      PayrollHelperUtil.deleteTableRows(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.HIDDEN_LABOR_COST_CENTER_DESCRIPTION_OVERRIDE_TABLE_ROW), 1);
    }

    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will set the allow multi-pay rate field to Yes, This will also check the default employee group and then
   * select a different employee group.
   *
   * @throws Exception
   */
  @Test(priority = 6)
  public static void testAllowMultiPayRateChangeField() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      if (JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_MULTI_PAY_RATE_CHANGE_YES_BUTTON).isSelected()) {
        JobCostingLaborAllocationTabInterface.getWebElement(driver,
            JobCostingLaborAllocationTabInterface.ALLOW_MULTI_PAY_RATE_CHANGE_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_MULTI_PAY_RATE_CHANGE_NO_BUTTON).isSelected());

      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_MULTI_PAY_RATE_CHANGE_YES_BUTTON));
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_MULTI_PAY_RATE_CHANGE_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_MULTI_PAY_RATE_CHANGE_YES_BUTTON).isSelected());
      PayrollHelperUtil.checkForDefaultEmployeeGroup(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_MULTI_PAY_RATE_CHANGE_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_MULTI_PAY_RATE_CHANGE_EMPLOYEE_LEVEL), 1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will set the allow multi pay rate field to Yes, this will also check the default employee group and then
   * select a different employee group. This will test the set default allocation based on pay period hour percentage
   * field and the employee level. This will test the enable read-only employee view for timesheet allocation tab field
   * and the employee level. This will test the Use time-card tab rate if the rate is different from the employee's
   * default costing rate field and the employee level.
   *
   * @throws Exception
   */
  @Test(priority = 7)
  public static void testAllowPercentageAllocationField() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_PERCENTAGE_ALLOCATION_YES_BUTTON));
      if (JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_PERCENTAGE_ALLOCATION_YES_BUTTON).isSelected()) {
        JobCostingLaborAllocationTabInterface.getWebElement(driver,
            PayrollConstants.ALLOW_PERCENTAGE_ALLOCATION_NO_BUTTON).click();
        PayrollMenusAndLinksUtil.clickOnSaveButton();
      }
      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          PayrollConstants.ALLOW_PERCENTAGE_ALLOCATION_NO_BUTTON).isSelected());
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          JobCostingLaborAllocationTabInterface.SET_DEFAULT_ALLOCATION_BASED_ON_PAY_PERIOD_HOUR_PERCENTAGE_NO_BUTTON));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          JobCostingLaborAllocationTabInterface.ENABLE_READ_ONLY_EMPLOYEE_VIEW_FOR_TIMESHEET_ALLOCATION_TAB_NO_BUTTON));
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          JobCostingLaborAllocationTabInterface.USE_TIME_CARD_TAB_RATE_IF_RATE_IS_DIFFERENT_THAN_THE_EMPLOYEE_DEFAULT_COSTING_RATE_NO_BUTTON));

      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_PERCENTAGE_ALLOCATION_YES_BUTTON).click();
      PayrollMenusAndLinksUtil.clickOnSaveButton();

      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_PERCENTAGE_ALLOCATION_YES_BUTTON).isSelected());
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          JobCostingLaborAllocationTabInterface.SET_DEFAULT_ALLOCATION_BASED_ON_PAY_PERIOD_HOUR_PERCENTAGE_NO_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          JobCostingLaborAllocationTabInterface.ENABLE_READ_ONLY_EMPLOYEE_VIEW_FOR_TIMESHEET_ALLOCATION_TAB_NO_BUTTON));
      Assert.assertTrue(SeleniumWrapper.isLocatorDisplayed(
          JobCostingLaborAllocationTabInterface.USE_TIME_CARD_TAB_RATE_IF_RATE_IS_DIFFERENT_THAN_THE_EMPLOYEE_DEFAULT_COSTING_RATE_NO_BUTTON));

      PayrollHelperUtil.checkForDefaultEmployeeGroup(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_PERCENTAGE_ALLOCATION_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_PERCENTAGE_ALLOCATION_EMPLOYEE_LEVEL), 1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.SET_DEFAULT_ALLOCATION_BASED_ON_PAY_PERIOD_HOUR_PERCENTAGE_YES_BUTTON));
      if (JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.SET_DEFAULT_ALLOCATION_BASED_ON_PAY_PERIOD_HOUR_PERCENTAGE_YES_BUTTON)
          .isSelected()) {
        JobCostingLaborAllocationTabInterface.getWebElement(driver,
                JobCostingLaborAllocationTabInterface.SET_DEFAULT_ALLOCATION_BASED_ON_PAY_PERIOD_HOUR_PERCENTAGE_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.SET_DEFAULT_ALLOCATION_BASED_ON_PAY_PERIOD_HOUR_PERCENTAGE_YES_BUTTON));
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.SET_DEFAULT_ALLOCATION_BASED_ON_PAY_PERIOD_HOUR_PERCENTAGE_YES_BUTTON)
          .click();
      PayrollHelperUtil.checkForDefaultEmployeeGroup(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.SET_DEFAULT_ALLOCATION_BASED_ON_PAY_PERIOD_HOUR_PERCENTAGE_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.SET_DEFAULT_ALLOCATION_BASED_ON_PAY_PERIOD_HOUR_PERCENTAGE_EMPLOYEE_LEVEL),
          1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ENABLE_READ_ONLY_EMPLOYEE_VIEW_FOR_TIMESHEET_ALLOCATION_TAB_YES_BUTTON));
      if (JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.ENABLE_READ_ONLY_EMPLOYEE_VIEW_FOR_TIMESHEET_ALLOCATION_TAB_YES_BUTTON)
          .isSelected()) {
        JobCostingLaborAllocationTabInterface.getWebElement(driver,
                JobCostingLaborAllocationTabInterface.ENABLE_READ_ONLY_EMPLOYEE_VIEW_FOR_TIMESHEET_ALLOCATION_TAB_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ENABLE_READ_ONLY_EMPLOYEE_VIEW_FOR_TIMESHEET_ALLOCATION_TAB_YES_BUTTON));
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.ENABLE_READ_ONLY_EMPLOYEE_VIEW_FOR_TIMESHEET_ALLOCATION_TAB_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.USE_TIME_CARD_TAB_RATE_IF_RATE_IS_DIFFERENT_THAN_THE_EMPLOYEE_DEFAULT_COSTING_RATE_YES_BUTTON));
      if (JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.USE_TIME_CARD_TAB_RATE_IF_RATE_IS_DIFFERENT_THAN_THE_EMPLOYEE_DEFAULT_COSTING_RATE_YES_BUTTON)
          .isSelected()) {
        JobCostingLaborAllocationTabInterface.getWebElement(driver,
                JobCostingLaborAllocationTabInterface.USE_TIME_CARD_TAB_RATE_IF_RATE_IS_DIFFERENT_THAN_THE_EMPLOYEE_DEFAULT_COSTING_RATE_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.USE_TIME_CARD_TAB_RATE_IF_RATE_IS_DIFFERENT_THAN_THE_EMPLOYEE_DEFAULT_COSTING_RATE_YES_BUTTON));
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.USE_TIME_CARD_TAB_RATE_IF_RATE_IS_DIFFERENT_THAN_THE_EMPLOYEE_DEFAULT_COSTING_RATE_YES_BUTTON)
          .click();
      PayrollHelperUtil.checkForDefaultEmployeeGroup(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.USE_TIME_CARD_TAB_RATE_IF_RATE_IS_DIFFERENT_THAN_THE_EMPLOYEE_DEFAULT_COSTING_RATE_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.USE_TIME_CARD_TAB_RATE_IF_RATE_IS_DIFFERENT_THAN_THE_EMPLOYEE_DEFAULT_COSTING_RATE_EMPLOYEE_LEVEL),
          1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }

    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will set the allow work classification change field to yes, This will also check the default employee
   * group and then select a different employee group. This will test without PROJ association field and the employee
   * level this will test the uses employee job rates field and the employee level.
   *
   * @throws Exception
   */
  @Test(priority = 8)
  public static void testAllowWorkClassificationChangeField() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_WORK_CLASSIFICATION_CHANGE_YES_BUTTON));
      if (JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_WORK_CLASSIFICATION_CHANGE_YES_BUTTON).isSelected()) {
        JobCostingLaborAllocationTabInterface.getWebElement(driver,
            JobCostingLaborAllocationTabInterface.ALLOW_WORK_CLASSIFICATION_CHANGE_NO_BUTTON).click();
        PayrollMenusAndLinksUtil.clickOnSaveButton();
      }
      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_WORK_CLASSIFICATION_CHANGE_NO_BUTTON).isSelected());
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(JobCostingLaborAllocationTabInterface.WITHOUT_PROJ_ASSOCIATION_NO_BUTTON));
      Assert.assertFalse(
          SeleniumWrapper.isLocatorDisplayed(JobCostingLaborAllocationTabInterface.USES_EMPLOYEE_JOB_RATES_NO_BUTTON));

      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_WORK_CLASSIFICATION_CHANGE_YES_BUTTON).click();
      PayrollMenusAndLinksUtil.clickOnSaveButton();

      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_WORK_CLASSIFICATION_CHANGE_YES_BUTTON).isSelected());
      Assert.assertTrue(
          SeleniumWrapper.isLocatorDisplayed(JobCostingLaborAllocationTabInterface.WITHOUT_PROJ_ASSOCIATION_NO_BUTTON));
      Assert.assertTrue(
          SeleniumWrapper.isLocatorDisplayed(JobCostingLaborAllocationTabInterface.USES_EMPLOYEE_JOB_RATES_NO_BUTTON));

      PayrollHelperUtil.checkForDefaultEmployeeGroup(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_WORK_CLASSIFICATION_CHANGE_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_WORK_CLASSIFICATION_CHANGE_EMPLOYEE_LEVEL), 1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.WITHOUT_PROJ_ASSOCIATION_YES_BUTTON));
      if (JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.WITHOUT_PROJ_ASSOCIATION_YES_BUTTON).isSelected()) {
        JobCostingLaborAllocationTabInterface.getWebElement(driver,
            JobCostingLaborAllocationTabInterface.WITHOUT_PROJ_ASSOCIATION_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.WITHOUT_PROJ_ASSOCIATION_YES_BUTTON));
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.WITHOUT_PROJ_ASSOCIATION_YES_BUTTON).click();
      PayrollHelperUtil.checkForDefaultEmployeeGroup(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.WITHOUT_PROJ_ASSOCIATION_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.WITHOUT_PROJ_ASSOCIATION_EMPLOYEE_LEVEL), 1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.USES_EMPLOYEE_JOB_RATES_YES_BUTTON));
      if (JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.USES_EMPLOYEE_JOB_RATES_YES_BUTTON).isSelected()) {
        JobCostingLaborAllocationTabInterface.getWebElement(driver,
            JobCostingLaborAllocationTabInterface.USES_EMPLOYEE_JOB_RATES_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.USES_EMPLOYEE_JOB_RATES_YES_BUTTON));
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.USES_EMPLOYEE_JOB_RATES_YES_BUTTON).click();
      PayrollHelperUtil.checkForDefaultEmployeeGroup(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.USES_EMPLOYEE_JOB_RATES_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.USES_EMPLOYEE_JOB_RATES_EMPLOYEE_LEVEL), 1);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }

    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will set the enable employee read-only labor cost centers field to yes. This will also check the default
   * employee group and then select a different employee group.
   *
   * @throws Exception
   */
  @Test(priority = 9)
  public static void testEnableEmployeesReadOnlyLaborCostCenters() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      if (JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.ENABLE_EMPLOYEES_READ_ONLY_LABOR_COST_CENTERS_YES_BUTTON)
          .isSelected()) {
        JobCostingLaborAllocationTabInterface.getWebElement(driver,
            JobCostingLaborAllocationTabInterface.ENABLE_EMPLOYEES_READ_ONLY_LABOR_COST_CENTERS_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ENABLE_EMPLOYEES_READ_ONLY_LABOR_COST_CENTERS_NO_BUTTON).isSelected());
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ENABLE_EMPLOYEES_READ_ONLY_LABOR_COST_CENTERS_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ENABLE_EMPLOYEES_READ_ONLY_LABOR_COST_CENTERS_YES_BUTTON).isSelected());
      PayrollHelperUtil.checkForDefaultEmployeeGroup(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ENABLE_EMPLOYEES_READ_ONLY_LABOR_COST_CENTERS_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ENABLE_EMPLOYEES_READ_ONLY_LABOR_COST_CENTERS_EMPLOYEE_LEVEL), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }

    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will set the allow limiting labor cost center choices field to Yes. This will also check the default
   * employee group and then select a different employee group.
   *
   * @throws Exception
   */
  @Test(priority = 10)
  public static void testAllowLimitingLaborCostCenterChoices() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      if (JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_YES_BUTTON).isSelected()) {
        JobCostingLaborAllocationTabInterface.getWebElement(driver,
            JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_NO_BUTTON).click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_NO_BUTTON).isSelected());
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_USE_EMPLOYEE_GROUPS_NO_BUTTON));
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_YES_BUTTON).click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_YES_BUTTON).isSelected());
      PayrollHelperUtil.checkForDefaultEmployeeGroup(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_EMPLOYEE_LEVEL));
      SeleniumWrapper.setDropDownByIndex(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_EMPLOYEE_LEVEL), 2);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_NO_BUTTON).click();
      Assert.assertFalse(SeleniumWrapper.isLocatorDisplayed(
          JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_USE_EMPLOYEE_GROUPS_YES_BUTTON));
      SeleniumWrapper.setDropDownByIndex(JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_EMPLOYEE_LEVEL), 0);
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_YES_BUTTON).click();
      JavascriptExecutorWrapper.scrollToElement(driver, JobCostingLaborAllocationTabInterface.getWebElement(driver,
          JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_USE_EMPLOYEE_GROUPS_YES_BUTTON));
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_USE_EMPLOYEE_GROUPS_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.ALLOW_LIMITING_LABOR_COST_CENTER_CHOICES_USE_EMPLOYEE_GROUPS_YES_BUTTON)
          .isSelected());
    } catch (Throwable e) {
      SeleniumExceptions.handleException(e, driver);
    }
    SeleniumWrapper.switchToDefaultContent(driver);
  }

  /**
   * This test will set the allow labor cost center filter on timesheet approval field to yes.
   *
   * @throws Exception
   */
  @Test(priority = 11)
  public static void testAllowLaborCostCenterFiltersOnTimesheetApproval() throws Exception {
    SeleniumWrapper.switchToInteriorContent(driver);
    try {
      if (JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.ALLOW_LABOR_COST_CENTER_FILTERS_ON_TIMESHEET_APPROVAL_YES_BUTTON)
          .isSelected()) {
        JobCostingLaborAllocationTabInterface.getWebElement(driver,
                JobCostingLaborAllocationTabInterface.ALLOW_LABOR_COST_CENTER_FILTERS_ON_TIMESHEET_APPROVAL_NO_BUTTON)
            .click();
        PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
      }
      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.ALLOW_LABOR_COST_CENTER_FILTERS_ON_TIMESHEET_APPROVAL_NO_BUTTON)
          .isSelected());
      JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.ALLOW_LABOR_COST_CENTER_FILTERS_ON_TIMESHEET_APPROVAL_YES_BUTTON)
          .click();
      PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);

      Assert.assertTrue(JobCostingLaborAllocationTabInterface.getWebElement(driver,
              JobCostingLaborAllocationTabInterface.ALLOW_LABOR_COST_CENTER_FILTERS_ON_TIMESHEET_APPROVAL_YES_BUTTON)
          .isSelected());
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
