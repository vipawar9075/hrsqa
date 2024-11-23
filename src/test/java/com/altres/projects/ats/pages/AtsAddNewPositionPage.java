package com.altres.projects.ats.pages;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.altres.base.Base;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.SeleniumWrapper;

public class AtsAddNewPositionPage extends Base {

  private static final WebElement EXPIRATION_DATE = driver.findElement(By.xpath("//input[@id='expiration_date']"));
  private static final By TITLE = By.xpath("//input[@id='title']");
  private static final By APPLY_URL = By.xpath("//div[@id='applyUrlBox']//a//img");
  private static final By EMAIL = By.xpath("//input[@id='email']");
  private static final By FIRST_NAME = By.xpath("//input[@id='first_name']");
  private static final By LAST_NAME = By.xpath("//input[@id='last_name']");
  private static final By PHONE_NUMBER = By.xpath("//input[@id='phone']");
  private static final By AGREE = By.xpath("//input[@id='agree']");
  private static final By YES = By.xpath("//span[text()='Yes']/..");
  private static final By EDIT_ICON = By.xpath(
      "//select[@id='select-template']/following::a[@title='edit']");
  private static final By TEMPLATE = By.xpath("//select[@id='select-template']");
  private static final By EXPIRATION_DATE_TEXT = By.xpath("//label[normalize-space()='Expiration Date:']");
  private static final By COMPLETED_NOTIFICATIONS_CHECKBOXES = By.xpath("//tbody//tr//td[5]/input");
  private static final By ALL_ATS_ADMINS_COMPLETED_NOTIFICATIONS = By.xpath("//input[@id='notification_0']");
  private static final By EXPERIENCE_QUESTIONS_SELECTED_GRID = By.xpath(
      "//select[@name='ats_experience_question_multiselect[]']");
  private static final By POSITION_TITLE_FROM_APPLY_PAGE = By.xpath("//p[@id='msg_id']");

  /**
   * Set active field yes or no.
   */
  public static void setActiveInactive(String id) {
    By element = By.xpath("//input[@id='" + id + "']");
    SeleniumWrapper.clickOnElement(element);
  }

  /**
   * Set expiration date.
   */
  public static void setExpirationDate(String date) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.presenceOfElementLocated(By.id("expiration_date")));
    SeleniumWrapper.sendKeyWithClear(driver, EXPIRATION_DATE, date);
  }

  /**
   * Set Title.
   */
  public static void setTitle(String name) {
    SeleniumWrapper.sendKeyWithClear(driver, driver.findElement(TITLE), name);
  }

  /**
   * Checks tab names, buttons are displayed on Add new position page .
   *
   * @param elementNames
   * @return
   */
  public static boolean areElementsDisplayedByName(List<String> elementNames) {
    return elementNames.stream()
        .map(name -> driver.findElement(By.xpath("//a[text()='" + name + "'] | //input[@value='" + name + "']")))
        .allMatch(WebElement::isDisplayed);
  }

  /**
   * Clicks on apply url link.
   */
  public static void clickOnApplyUrl() {
    SeleniumWrapper.clickOnElement(APPLY_URL);
  }

  /**
   * Set email.
   *
   * @param email
   */
  public static void setEmail(String email) {
    SeleniumWrapper.enterText(EMAIL, email);
  }

  /**
   * Clicks on next button.
   */
  public static void clickOnNext(String id) {
    WebElement next = driver.findElement(By.xpath("//input[@id='" + id + "']"));
    JavascriptExecutorWrapper.clickOnElement(driver, next);
  }

  /**
   * Set Contact information like first name,last name,phone number.
   *
   * @param firstName
   * @param lastName
   * @param phoneNumber
   */
  public static void setContactInfo(String firstName, String lastName, String phoneNumber) {
    SeleniumWrapper.enterText(FIRST_NAME, firstName);
    SeleniumWrapper.enterText(LAST_NAME, lastName);
    SeleniumWrapper.enterText(PHONE_NUMBER, phoneNumber);
  }

  /**
   * Clicks on agree button.
   */
  public static void clickOnAgree() {
    WebElement element = driver.findElement(AGREE);
    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
  }

  /**
   * Retrieves the value of the first name field from the web page.
   *
   * @return
   */
  public static String getFirstName() {
    return SeleniumWrapper.findElement(driver, FIRST_NAME).getAttribute("value");
  }

  /**
   * Retrieves the value of the Last name field from the web page.
   *
   * @return
   */
  public static String getLastName() {
    return SeleniumWrapper.findElement(driver, LAST_NAME).getAttribute("value");
  }

  /**
   * Clicks on Yes button.
   */
  public static void clickOnYes() {
    SeleniumWrapper.clickOnElement(YES);
  }

  /**
   * Clicks on the tabs within Add New Position page.
   */
  public static void clickOnTab(String tabName) {
    By element = By.xpath("//a[text()='" + tabName + "']");
    SeleniumWrapper.clickOnElement(element);
  }

  /**
   * Clicks on edit icon.
   */
  public static void clickOnEditIcon() {
    SeleniumWrapper.clickOnElement(EDIT_ICON);
  }

  /**
   * Validates the education values on the validation screen.
   *
   * @param expectedValues List of expected education values.
   * @return true if the values match, false otherwise.
   */
  public static boolean validateEducationSelectedQuestions(List<String> expectedValues) {
    List<WebElement> educationQuestions = SeleniumWrapper.findElements(driver,
        By.xpath("//select[@name='ats_education_question_multiselect[]']//option"));
    return validateFieldValues(expectedValues, educationQuestions);
  }

  /**
   * Validates the experience values on the validation screen.
   *
   * @param expectedValues List of expected experience values.
   * @return true if the values match, false otherwise.
   */
  public static boolean validateExperienceSelectedQuestions(List<String> expectedValues) {
    List<WebElement> experienceQuestions = SeleniumWrapper.findElements(driver,
        By.xpath("//select[@name='ats_experience_question_multiselect[]']//option"));
    return validateFieldValues(expectedValues, experienceQuestions);
  }

  /**
   * Retrieves the order of additional questions from the web page and captures their field values.
   *
   * @return
   */
  public static List<String> getAdditionalQuestionsOrder() {
    List<WebElement> additionalQuestionsOrder = SeleniumWrapper.findElements(driver,
        By.xpath("//li[@class='questionOrder']//div[@class='questionWords']/span[2]"));
    return AtsAddEditQuestionsTemplatePage.captureFieldValues(additionalQuestionsOrder);
  }

  /**
   * Validates that the text values of a list of WebElements match the expected values.
   *
   * @param expectedValues List of expected text values.
   * @param elements       List of WebElements to validate.
   * @return true if the values match, false otherwise.
   */
  public static boolean validateFieldValues(List<String> expectedValues, List<WebElement> elements) {
    List<String> actualValues = elements.stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
    return expectedValues.equals(actualValues);
  }

  /**
   * Selects ATS Question template from dropdown values.
   *
   * @param templateName
   */
  public static void selectATSQuestionTemplate(String templateName) {
    SeleniumWrapper.selectDropdownByVisibleText(TEMPLATE, templateName);
  }

  /**
   * Validate whether Expiration Date is visible on screen.
   *
   * @param text
   * @return
   */
  public static boolean isExpirationDateVisible(String text) {
    return SeleniumWrapper.getElementTextIfPresent(EXPIRATION_DATE_TEXT).contains(text);
  }

  /**
   * Clicks on All ATS Admins checkbox within completed notification column.
   */
  public static void clickOnAllATSAdminsCompletedNotificationsCheckbox() {
    SeleniumWrapper.clickOnElement(ALL_ATS_ADMINS_COMPLETED_NOTIFICATIONS);
  }

  /**
   * Gets the size of disabled checkboxes.
   *
   * @return the number of disabled checkboxes
   */
  public static int getSizeOfDisabledCheckboxes() {
    int size = 0;
    List<WebElement> elements = driver.findElements(COMPLETED_NOTIFICATIONS_CHECKBOXES);
    for (WebElement element : elements) {
      if (!element.isEnabled()) {
        size++;
      }
    }
    return size;
  }

  /**
   * Clicks on the second enabled checkbox found in the list of completed notifications checkboxes.
   */
  public static void clickSecondEnabledCheckbox() {
    driver.findElements(COMPLETED_NOTIFICATIONS_CHECKBOXES).stream()
        .filter(WebElement::isEnabled)
        .skip(1)
        .findFirst()
        .ifPresent(WebElement::click);
  }

  /**
   * Clear and Set Title.
   */
  public static void clearAndSetTitle(String name) {
    SeleniumWrapper.sendKeyWithClear(driver, driver.findElement(TITLE), name);
  }

  /**
   * Get the position title from the apply page by removing specific phrases from the text.
   *
   * @return the cleaned position title as a String.
   */
  public static String getPositionTitleFromApplyPage() {
    String positionTitle = SeleniumWrapper.getElementText(POSITION_TITLE_FROM_APPLY_PAGE);
    return positionTitle.replace("Apply for the ", "").replace(" position.", "").trim();
  }

  /**
   * Checks if the experience grid is displayed.
   *
   * @return
   */
  public static boolean isExperienceSelectedGridDisplayed() {
    return driver.findElement(EXPERIENCE_QUESTIONS_SELECTED_GRID).isDisplayed();
  }

}




