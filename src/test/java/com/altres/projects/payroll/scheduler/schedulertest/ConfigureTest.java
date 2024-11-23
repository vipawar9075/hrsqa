package com.altres.projects.payroll.scheduler.schedulertest;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.login.LoginPage;
import com.altres.projects.payroll.scheduler.schedulerpages.ConfigureTestPageInterface;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollHelperUtil;
import com.altres.projects.payroll.superuser.generaloption.payrollutil.PayrollMenusAndLinksUtil;
import com.altres.util.FakeDataGenerator;
import com.altres.util.SeleniumWrapper;

/**
 * Selenium scripts to test scheduler configure screen.
 */
public class ConfigureTest extends Base {

  /**
   * The purpose of this method is to test the dropdown indexes on the configure screen. This will change the indexes of
   * the dropdown in the table and click the save button.
   *
   * @throws ConfigurationNotLoadedException
   */
  @Test(priority = 1)
  public void changeDropdownIndexes() throws ConfigurationNotLoadedException {

    LoginPage.loginAsSuperUser(driver);
    PayrollMenusAndLinksUtil.clickSettingsMenu();
    PayrollMenusAndLinksUtil.clickSchedulerConfigureMenu();
    PayrollHelperUtil.enableScheduler(driver);

    WebElement table = ConfigureTestPageInterface.getWebElement(driver, ConfigureTestPageInterface.SCHEDULER_TABLE);
    if (table != null) {
      List<WebElement> dropdowns = ConfigureTestPageInterface.getWebElements(driver,
          ConfigureTestPageInterface.SCHEDULER_DROPDOWNS);
      for (WebElement dropdown : dropdowns) {
        if (dropdown.isDisplayed()) {
          Select select = new Select(dropdown);
          int newIndex = FakeDataGenerator.getRandomNumber(0, select.getOptions().size());
          select.selectByIndex(newIndex);
        }
      }
    }
    PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
  }

  /**
   * The purpose of this method is to test the checkbox values on the configure screen. This will change the checkbox
   * values in the table and click the save button.
   */
  @Test(priority = 2)
  public void changeCheckboxValues() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollHelperUtil.enableScheduler(driver);
    WebElement table = ConfigureTestPageInterface.getWebElement(driver, ConfigureTestPageInterface.SCHEDULER_TABLE);
    if (table != null) {
      List<WebElement> checkboxes = ConfigureTestPageInterface.getWebElements(driver,
          ConfigureTestPageInterface.SCHEDULER_CHECKBOXES);
      for (WebElement checkbox : checkboxes) {
        if (checkbox != null && !checkbox.isSelected()) {
          checkbox.click();
        }
      }
      PayrollMenusAndLinksUtil.clickOnSaveButton();

      checkboxes = ConfigureTestPageInterface.getWebElements(driver, ConfigureTestPageInterface.SCHEDULER_CHECKBOXES);
      for (WebElement checkbox : checkboxes) {
        if (checkbox.isSelected()) {
          checkbox.click();
        }
      }
      PayrollMenusAndLinksUtil.clickOnSaveButton();

      checkboxes = ConfigureTestPageInterface.getWebElements(driver, ConfigureTestPageInterface.SCHEDULER_CHECKBOXES);
      for (WebElement checkbox : checkboxes) {
        if (!checkbox.isSelected()) {
          checkbox.click();
        }
      }
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
    }
  }

  /**
   * The purpose of this method is to test the radio values on the configure screen. This will change the radio values
   * in the table and click the save button.
   */
  @Test(priority = 3)
  public void changeRadioYesValues() {

    SeleniumWrapper.switchToDefaultContent(driver);
    SeleniumWrapper.switchToInteriorContent(driver);
    PayrollHelperUtil.enableScheduler(driver);

    WebElement table = ConfigureTestPageInterface.getWebElement(driver, ConfigureTestPageInterface.SCHEDULER_TABLE);
    if (table != null) {
      String radioValue;
      List<WebElement> radioButtons = SeleniumWrapper.findRelativeElements(table, ".//input[@type='radio']");
      for (WebElement radioButton : radioButtons) {
        radioValue = radioButton.getAttribute("id");
        if (!radioValue.equals("enabled2")) {
          radioButton.click();
        }
      }
      PayrollMenusAndLinksUtil.clickOnSaveButton();

      table = ConfigureTestPageInterface.getWebElement(driver, ConfigureTestPageInterface.SCHEDULER_TABLE);
      radioButtons = SeleniumWrapper.findRelativeElements(table, ".//input[@type='radio']");
      for (WebElement radioButton : radioButtons) {
        radioValue = radioButton.getAttribute("id");
        if (!radioValue.equals("enabled2")) {
          radioButton.click();
        }
      }
      PayrollHelperUtil.clickSaveAndCheckForSuccessMessageIsDisplayed(driver);
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