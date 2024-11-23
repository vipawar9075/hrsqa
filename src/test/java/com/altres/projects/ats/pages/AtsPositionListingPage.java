package com.altres.projects.ats.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.altres.base.Base;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;


public class AtsPositionListingPage extends Base {

  private static final By ADD_NEW_POSITION = By.xpath("//a[normalize-space()='+ Add New Position']");
  private static final By APPLICANTS = By.xpath("//a[normalize-space()='Applicants']");
  private static final By POSITION = By.xpath("//a[normalize-space()='Positions']");
  private static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  private static final By HIDE_INACTIVE = By.xpath("//input[@id='hide_inactive']");
  private static final By TITLE = By.xpath("//input[@name='search_text']");

  /**
   * Clicks on add new position link.
   */
  public static void clickOnAddNewPosition() {
    SeleniumWrapper.switchToInteriorContent(driver);
    SeleniumWrapper.clickOnElement(ADD_NEW_POSITION);
  }

  /**
   * Clicks on the Position link within applicants tab.
   */
  public static void clickOnPosition() {
    wait.until(ExpectedConditions.elementToBeClickable(APPLICANTS));
    SeleniumWrapper.clickOnElement(APPLICANTS);
    SeleniumWrapper.clickOnElement(POSITION);
  }

  /**
   * Clicks on Position Record by its row number.
   *
   * @param rowNumber
   */
  public static void clickOnPositionRecord(int rowNumber) {
    By positionRecord = By.xpath("//table[@id=\"positions\"]//tr[" + rowNumber + "]//td[1]");
    SeleniumWrapper.clickOnElement(positionRecord);
  }

  /**
   * Clicks on Hide Inactive checkbox.
   */
  public static void clickOnHideInactive() {
    SeleniumWrapper.clickOnElement(HIDE_INACTIVE);
  }

  /**
   * Sets the Position title in the field.
   *
   * @param positionTitle
   */
  public static void setTitle(String positionTitle) {
    WebElement templateNameElement = SeleniumWrapper.findElement(driver, TITLE);
    templateNameElement.clear();
    templateNameElement.sendKeys(positionTitle);
  }

  /**
   * Clicks on Position Record by its row number.
   *
   * @param rowNumber
   */
  public static void clickPositionRecord(int rowNumber) {
    WebElement atsQuestions = SeleniumWrapper.findElement(driver,
        By.xpath("//table[@id='positions']//tbody//tr[" + rowNumber + "]//td[1]"));
    JavascriptExecutorWrapper.clickOnElement(driver, atsQuestions);
  }

}




