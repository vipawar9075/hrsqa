package com.altres.projects.careers.carrerspages;

import static org.testng.Reporter.log;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.altres.base.Base;
import com.altres.util.FakeDataGenerator;
import com.altres.util.SeleniumWrapper;

public class CareersDetailsPage extends Base {

  private static final By CAREERS_ADD_NEW_SECTION = By.xpath("//a[normalize-space()='+ Add New Section']");
  private static final String BASE_RADIO_BUTTON_ID = "active_";
  private static final By CAREERS_TITLE_TEXT_FIELD = By.id("title");
  private static final By CAREERS_DELETE_BUTTON = By.xpath(
      "//div[@id='section-details-page']/input[@value='Delete']");
  private static final By CAREERS_PENCIL_EDIT_ICON = By.cssSelector(".fas.fa-pencil-alt");

  /**
   * Common method to click elements.
   *
   * @param locator
   */
  private static void clickElement(By locator) {
    SeleniumWrapper.click(driver, locator);
  }

  /**
   * Clicks on the "Add New Section" link.
   */
  public static void clickAddNewSection() {
    clickElement(CAREERS_ADD_NEW_SECTION);
  }

  /**
   * Clicks on active radio button with the specified index.
   *
   * @param index
   */
  public static void clickActiveRadioButton(int index) {
    By activeRadioButton = By.id(BASE_RADIO_BUTTON_ID + index);
    clickElement(activeRadioButton);
  }

  /**
   * Enters random text into the title text field.
   */
  public static void enterTitleText() {
    SeleniumWrapper.enterText(CAREERS_TITLE_TEXT_FIELD, FakeDataGenerator.getRandomWord());
  }

  /**
   * Checks if the "Delete" button is displayed on the page within a timeout.
   *
   * @return
   */
  public static boolean isDeleteButtonDisplayed() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    try {
      WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(CAREERS_DELETE_BUTTON));
      return button.isDisplayed();
    } catch (TimeoutException e) {
      log("Delete button was not found within the time limit.");
    }
    return false;
  }

  /**
   * Clicks on the "Delete" button.
   */
  public static void clickDeleteButton() {
    clickElement(CAREERS_DELETE_BUTTON);
  }

  /**
   * Clicks on the pencil edit icon.
   */
  public static void clickPencilEditIcon() {
    clickElement(CAREERS_PENCIL_EDIT_ICON);
  }

}



