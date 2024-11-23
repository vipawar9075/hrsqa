package com.altres.projects.payroll.superuser.generaloption.payrollutil;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

public class GeneralOptionRadioButtons extends Base {

  /**
   * Clicks on any radio button by its heading, Value passing into parameter.
   *
   * @param heading
   * @param value
   * @throws
   */
  public static void clickGeneralOptionsRadioButton(String heading, String value) {

    By generalRadioButton = By.xpath(
        "//div[contains(text(),'" + heading + "')]//input[@value='" + value + "']");

    SeleniumWrapper.clickOnElement(generalRadioButton);

  }

  /**
   * General radio button by its heading, Value passing into parameter.
   *
   * @param heading
   * @param value
   * @return
   */
  public static boolean isSelectedGeneralOptionRadioButton(String heading, String value) {
    By generalRadioButton = By.xpath(
        "//div[contains(text(),'" + heading + "')]//input[@value='" + value + "']"
    );

    try {
      boolean isSelected = driver.findElement(generalRadioButton).isSelected();
      return isSelected;
    } catch (StaleElementReferenceException e) {
      boolean isSelected = driver.findElement(generalRadioButton).isSelected();
      return isSelected;
    }
  }

  /**
   * Test employee level dropdown.
   *
   * @param heading
   * @param value
   * @return
   */
  public static boolean isEmployeeLeveDropdownDisplayed(String heading, String value) {

    By employeeLevelDropdown = By.xpath(
        "//div[contains(text(),'" + heading + "')]//select[@name='" + value + "']"
    );
    try {
      boolean isDisplayed = driver.findElement(employeeLevelDropdown).isDisplayed();
      return isDisplayed;

    } catch (StaleElementReferenceException e) {
      boolean isSelected = driver.findElement(employeeLevelDropdown).isDisplayed();
      return isSelected;
    }
  }

  /**
   * Test radio button displayed.
   *
   * @param heading
   * @param Value
   * @return
   */
  public static boolean isGeneralOptionsRadioButtonDisplayed(String heading, String Value) {

    By GENERAL_RADIOBUTTON = By.xpath(
        "//div[contains(text(),'" + heading + "')]//input[@value='" + Value + "']");
    boolean isDisplayed = driver.findElement(GENERAL_RADIOBUTTON).isDisplayed();
    return isDisplayed;
  }
}