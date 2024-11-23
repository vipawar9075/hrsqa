package com.altres.projects.ats.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

public class AtsAddEditQuestionPage extends Base {

  private static final By TITLE = By.xpath("//input[@name='title']");
  private static final By QUESTION = By.xpath("//textarea[@name='question']");
  private static final By INPUT_TYPE = By.xpath("//select[@id='question-type']");
  private static final By RESPONSE_REQUIREMENT = By.xpath("//select[@id='requirement-type']");
  private static final By ANSWER = By.xpath("//input[@name='answer_text_0']");

  /**
   * Set Title.
   */
  public static void setTitle(String name) {
    SeleniumWrapper.enterText(TITLE, name);
  }

  /**
   * Set Question.
   */
  public static void setQuestion(String question) {
    SeleniumWrapper.enterText(QUESTION, question);
  }

  /**
   * Clicks on any checkbox by its name passing into parameter.
   *
   * @param name
   */
  public static void clickOnCheckbox(String name) {
    By checkbox = By.xpath("//input[@name='" + name + "']");
    SeleniumWrapper.clickOnElement(checkbox);
  }

  /**
   * Select Input Type from dropdown values.
   *
   * @param value
   */
  public static void selectInputType(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(INPUT_TYPE, value);
  }

  /**
   * Select Response Requirement from dropdown values.
   *
   * @param value
   */
  public static void selectResponseRequirement(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(RESPONSE_REQUIREMENT, value);
  }

  /**
   * Captures the values from the additional question fields.
   *
   * @return List of addition question values.
   */
  public static List<String> getAdditionalSelectedQuestions() {
    List<WebElement> additionalSelectedQuestions = SeleniumWrapper.findElements(driver,
        By.xpath("//select[@name='ats_question_multiselect[]']//optgroup[1]//option"));
    return AtsAddEditQuestionsTemplatePage.captureFieldValues(additionalSelectedQuestions);
  }

  /**
   * Captures the values from the additional question order.
   *
   * @return List of addition question order values.
   */
  public static List<String> getAdditionalQuestionsOrder() {
    List<WebElement> additionalQuestionsOrder = SeleniumWrapper.findElements(driver,
        By.xpath("//div[@class='questionWords']/span[2]"));
    return AtsAddEditQuestionsTemplatePage.captureFieldValues(additionalQuestionsOrder);
  }

  /**
   * Select dropdown values by its names.
   *
   * @param name
   * @param value
   */
  public static void selectDropdown(String name, String value) {
    By dropdownName = By.xpath("//select[@name='" + name + "']");
    SeleniumWrapper.selectDropdownByVisibleText(dropdownName, value);
  }

  /**
   * Set Qualifying Answer.
   *
   * @param answer
   */
  public static void setQualifyAnswer(String answer) {
    SeleniumWrapper.enterText(ANSWER, answer);
  }


}

