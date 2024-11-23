package com.altres.projects.ats.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

public class AtsAppQuestionsListingPage extends Base {

  private static final By ADD_NEW_QUESTION = By.xpath("//a[normalize-space()='+ Add New Question']");
  private static final By TITLE = By.xpath("//input[@name='title']");

  /**
   * Clicks the add new question link.
   */
  public static void clickOnAddNewQuestion() {
    SeleniumWrapper.clickOnElement(ADD_NEW_QUESTION);
  }

  /**
   * Sets the title in the field.
   *
   * @param title
   */
  public static void setTitle(String title) {
    WebElement templateNameElement = SeleniumWrapper.findElement(driver, TITLE);
    templateNameElement.clear();
    templateNameElement.sendKeys(title);
  }

  /**
   * Clicks on ATS Question Record by its row number.
   *
   * @param rowNumber
   */
  public static void clickATSQuestionsRecord(int rowNumber) {
    By atsQuestions = By.xpath("//table[@id='questions']//tbody//tr[" + rowNumber + "]//td[1]");
    SeleniumWrapper.clickOnElement(atsQuestions);
  }


}


