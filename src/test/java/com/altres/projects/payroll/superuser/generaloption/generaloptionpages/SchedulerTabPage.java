package com.altres.projects.payroll.superuser.generaloption.generaloptionpages;

import org.openqa.selenium.WebDriver;

import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollConstants;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollHelperUtil;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollMenusAndLinksUtil;
import com.altres.util.SeleniumWrapper;

/**
 * Class to contain all business logic methods for time entry tab.
 */
public class SchedulerTabPage implements SchedulerTabPageInterface {

  public static void setupSchedulerTabVisibilityConditions(WebDriver driver) {

    PayrollMenusAndLinksUtil.clickSettingsMenu();
    PayrollMenusAndLinksUtil.clickSchedulerConfigureMenu();
    PayrollHelperUtil.enableScheduler(driver);
    PayrollMenusAndLinksUtil.clickOnReturnButton();
    SeleniumWrapper.switchToDefaultContent(driver);
    PayrollMenusAndLinksUtil.clickGeneralOptionMenu();
    PayrollMenusAndLinksUtil.clickTimeEntryTab();
    if (!SchedulerTabPageInterface.getWebElement(driver,
        PayrollConstants.ALLOW_EMPLOYEE_LEVEL_TIME_ENTRY_METHOD_OVERRIDE_YES_BUTTON).isSelected()) {
      SchedulerTabPageInterface.getWebElement(driver,
          PayrollConstants.ALLOW_EMPLOYEE_LEVEL_TIME_ENTRY_METHOD_OVERRIDE_YES_BUTTON).click();
      PayrollMenusAndLinksUtil.clickOnSaveButton();
    }
  }
}