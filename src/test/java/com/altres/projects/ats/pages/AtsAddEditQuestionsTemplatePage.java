package com.altres.projects.ats.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

public class AtsAddEditQuestionsTemplatePage extends Base {

  private static final By NAME_FIELD = By.id("name");
  private static final By PAGE_TITLE = By.xpath("//h1[@id='interiorContentTitle']");
  private static final By RETURN_BUTTON = By.xpath("//input[@value='Return to Question Templates']");
  private static final By REQUIRE_RESPONSE_CHECKBOX = By.xpath("((//li[@class='questionOrder '])[1]//input)[1]");

  /**
   * Clicks on any multi-selectable button by  its name passing into parameter.
   *
   * @param name
   */
  public static void clickOnMultiSelectableButton(String name) {
    By multiSelectableButton = By.xpath("//input[@name='" + name + "']");
    SeleniumWrapper.clickOnElement(multiSelectableButton);
  }

  /**
   * Clicks on any single-selectable button by its name passing into parameter.
   *
   * @param name
   */
  public static void clickOnSingleSelectableButton(String name) {
    By singleSelectableButton = By.xpath("//input[@name='" + name + "']");
    SeleniumWrapper.clickOnElement(singleSelectableButton);
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
   * Clicks on the Return To Question Template Button.
   */
  public static void returnToQuestionTemplate() {
    SeleniumWrapper.clickOnElement(RETURN_BUTTON);
  }


  /**
   * Gets the Page Title Question Template Page.
   *
   * @return
   */
  public static String getAddEditQuestionsTemplatePageTitle() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    try {
      WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(PAGE_TITLE));
      return title.getText().trim();
    } catch (NoSuchElementException e) {
      return null;
    }
  }

  /**
   * Sets the template name in the name field.
   *
   * @param templateName
   */
  public static void setTemplateName(String templateName) {
    WebElement templateNameElement = SeleniumWrapper.findElement(driver, NAME_FIELD);
    templateNameElement.clear();
    templateNameElement.sendKeys(templateName);
  }

  /**
   * Select a Question by its index from listing.
   *
   * @param name
   */
  public static void setQuestionByIndex(String name, int index) {
    By element = By.xpath("//select[@name='" + name + "']//option");
    SeleniumWrapper.selectByIndex(element, index);
  }

  /**
   * Captures the values from the education fields.
   *
   * @return List of education values.
   */
  public static List<String> getEducationSelectedQuestions() {
    List<WebElement> educationSelectedQuestions = SeleniumWrapper
        .findElements(driver, By.xpath("//select[@name='selectedEducationQuestions']//optgroup[1]//option"));
    return captureFieldValues(educationSelectedQuestions);
  }

  /**
   * Captures the values from the experience fields.
   *
   * @return List of experience values.
   */
  public static List<String> getExperienceSelectedQuestions() {
    List<WebElement> experienceSelectedQuestions = SeleniumWrapper
        .findElements(driver, By.xpath("//select[@name='selectedExperienceQuestions']//optgroup[1]//option"));
    return captureFieldValues(experienceSelectedQuestions);
  }

  /**
   * Captures the text values from a list of WebElements.
   *
   * @param elements List of WebElements to capture text from.
   * @return List of text values.
   */
  public static List<String> captureFieldValues(List<WebElement> elements) {
    return elements.stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  /**
   * Captures the values from the additional question fields.
   *
   * @return List of addition question values.
   */
  public static List<String> getAdditionalSelectedQuestions() {
    List<WebElement> additionalSelectedQuestions = SeleniumWrapper
        .findElements(driver, By.xpath("//select[@name='selectedAdditionalQuestions']//optgroup[1]//option"));
    return captureFieldValues(additionalSelectedQuestions);
  }

  /**
   * Captures the values from the additional question order.
   *
   * @return List of addition question order values.
   */
  public static List<String> getAdditionalQuestionsOrder() {
    List<WebElement> additionalQuestionsOrder = SeleniumWrapper
        .findElements(driver, By.xpath("//li[@class='questionOrder ']//div[@class='question-words']/span[2]"));
    return captureFieldValues(additionalQuestionsOrder);
  }

  /**
   * Select a Question by its name from listing.
   *
   * @param gridName
   * @param questionNames
   */
  public static void selectQuestionByName(String gridName, List<String> questionNames) {
    List<WebElement> element = SeleniumWrapper
        .findElements(driver, By.xpath("//select[@name='" + gridName + "']//option"));
    element.stream()
        .filter(option -> questionNames.contains(option.getText()))
        .forEach(WebElement::click);
  }

  /**
   * Clicks the "Require Response" checkbox using Selenium.
   */
  public static void clickOnFirstRequireResponseCheckbox() {
    SeleniumWrapper.clickOnElement(REQUIRE_RESPONSE_CHECKBOX);
  }

  /**
   * Captures the values from the education available fields.
   *
   * @return List of education values.
   */
  public static List<String> getEducationQuestionsAvailable() {
    List<WebElement> educationSelectedQuestions = SeleniumWrapper
        .findElements(driver, By.xpath("//select[@name='selectedEducationQuestions_available']//option"));
    return captureFieldValues(educationSelectedQuestions);
  }
}

