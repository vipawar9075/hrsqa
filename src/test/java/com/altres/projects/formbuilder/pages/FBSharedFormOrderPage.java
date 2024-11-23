package com.altres.projects.formbuilder.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

/**
 * All the xpaths and methods required to perform specific actions to validate the test cases on the Form Builder:
 * Shared Form Order and Form pages are listed here.
 */
public class FBSharedFormOrderPage extends Base {


  /**
   * Drags the shared template
   *
   * @param fromElementText
   * @param toElementText
   */
  public static void dragAndDropSharedTemplate(String fromElementText, String toElementText) {
    By toElementLocator = By.xpath("//span[contains(text(),'" + fromElementText + "')]");
    By fromElementLocator = By.xpath("//span[contains(text(),'" + toElementText + "')]");
    WebElement toElement = SeleniumWrapper.findElement(driver, toElementLocator);
    WebElement fromElement = SeleniumWrapper.findElement(driver, fromElementLocator);
    JavascriptExecutorWrapper.scrollToElement(driver, fromElement);
    JavascriptExecutorWrapper.scrollToElement(driver, toElement);
    SeleniumWrapper.dragAndDrop(fromElement, toElement);
  }

  /**
   * Selects a module from the dropdown by visible text.
   *
   * @param value
   */
  public static void selectModuleFromDropdown(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(FBSharedFormOrder.MODULE_DROPDOWN, value);
  }

}
