package com.altres.projects.ats.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

public class AtsAddEditPage extends Base {

  private static final By NAME_FIELD = By.xpath("//input[@id='name']");
  private static final By ACTIVE_CHECKBOX = By.xpath("//input[@id='active1']");
  private static final By NAME_TEXT_FIELD = By.id("name");
  private static final By RETURN_BUTTON = By.xpath("//input[@value='Return to Question Templates']");

  /**
   * Enters the name in the name field.
   *
   * @return
   */
  public static WebElement enterName() {
    WebElement name = driver.findElement(NAME_FIELD);
    name.click();
    return name;
  }

  /**
   * Clicks the return button.
   */
  public static void clickReturnButton() {
    SeleniumWrapper.click(driver, RETURN_BUTTON);
  }

  /**
   * Clicks the active checkbox.
   */
  public static void clickActiveCheckBox() {
    SeleniumWrapper.clickOnElement(ACTIVE_CHECKBOX);
  }

  /**
   * Sets the full name in the name text field.
   *
   * @param fakeDataGenerator
   */
  public static void setFullName(String fakeDataGenerator) {
    SeleniumWrapper.enterText(NAME_TEXT_FIELD, fakeDataGenerator);
  }

}

