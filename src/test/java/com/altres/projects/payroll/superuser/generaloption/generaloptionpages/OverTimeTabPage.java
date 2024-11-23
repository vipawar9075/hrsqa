package com.altres.projects.payroll.superuser.generaloption.generaloptionpages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollHelperUtil;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollMenusAndLinksUtil;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

public class OverTimeTabPage extends Base implements OverTimeTabPageInterface {

  /**
   * This method will set the compute overtime and the ComputeOvertimeHoursTo yes and it will update the overtime method
   * to compute by row.
   *
   * @param driver
   */
  public static void setComputeOvertimeHoursAndUpdateOvertimeMethod(WebDriver driver) {
    clickYesToComputeOvertimeHours(driver);
    PayrollHelperUtil.setOvertimeMethodOverrideByValueGeneralOptionsSettings(driver, "row");
    if (!OverTimeTabPageInterface.getWebElement(driver, COMPUTE_WEEKLY_OVERTIME_YES_BUTTON).isSelected()) {
      OverTimeTabPageInterface.getWebElement(driver, COMPUTE_WEEKLY_OVERTIME_YES_BUTTON).click();
    }
    PayrollHelperUtil.clickSaveAndVerifySuccessNotice(driver);
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
  }

  /**
   * This method will set the ComputeOvertimeHoursToYes.
   *
   * @param driver
   */
  public static void clickYesToComputeOvertimeHours(WebDriver driver) {
    PayrollMenusAndLinksUtil.clickOnExpandTabsButton(driver);
    JavascriptExecutorWrapper.scrollToElement(driver,
        OverTimeTabPageInterface.getWebElement(driver, COMPUTE_OVERTIME_YES_BUTTON));
    if (!OverTimeTabPageInterface.getWebElement(driver, COMPUTE_OVERTIME_YES_BUTTON).isSelected()) {
      OverTimeTabPageInterface.getWebElement(driver, COMPUTE_OVERTIME_YES_BUTTON).click();
      SeleniumWrapper.acceptAlertIfPresent(driver);
    }
  }

  /**
   * This method returns the classification for the job overtime override element.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getClassificationJobOvertimeOverride(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElement(driver,
        By.xpath("//select[@name='daily_overtime_override_job_" + rowNumber + "']"));
  }

  /**
   * This method returns the daily hours for the job overtime override element.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getDailyHoursJobOvertimeOverride(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElement(driver,
        By.xpath("//input[@name='daily_overtime_override_hours_" + rowNumber + "']"));
  }

  /**
   * This method returns the add row link for the job overtime override element.
   *
   * @param driver
   * @param rowNumber
   * @return
   */
  public static WebElement getJobOvertimeOverrideRow(WebDriver driver, int rowNumber) {
    return SeleniumWrapper.findElement(driver,
        By.xpath("//*[@id='dailyOvertimeJobHoursOverride']/tr[" + rowNumber + "]"));
  }
}
