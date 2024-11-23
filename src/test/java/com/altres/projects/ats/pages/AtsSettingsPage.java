package com.altres.projects.ats.pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

public class AtsSettingsPage extends Base {

  public static final By SETTINGS_LINK = By.xpath("//a[contains(text(),'Settings')]");
  public static final By APP_QUESTION_TEMPLATES = By.xpath
      ("//a[@title='Maintain questions templates for position']");
  private static final By SETTING_RESULTS = By.xpath("//h1[@title='Settings']");
  private static final By APP_QUESTION_TEMPLATES_RESULTS = By.xpath(
      "//h1[@title='Applicant Tracking: Application Question Templates']");
  private static final By APP_QUESTIONS = By.xpath(
      "//a[@title='Maintain questions for job position applications']");
  private static final By SSO_DROPDOWN = By.xpath(
      "//select[@id='sso_index']");

  /**
   * Clicks the settings button.
   */
  public static void clickSettingsLink() {
    SeleniumWrapper.clickOnElement(SETTINGS_LINK);
  }

  /**
   * Clicks the application question template button.
   */
  public static void clickAppQuestionTemplate() {
    SeleniumWrapper.clickOnElement(APP_QUESTION_TEMPLATES);
  }

  /**
   * Gets the title of the settings page.
   *
   * @return
   */
  public static String getSettingsPageTitle() {
    return getPageTitle(SETTING_RESULTS);
  }

  /**
   * Gets the title of the templates message page.
   *
   * @return
   */
  public static String getAtsTemplatesMessagePageTitle() {
    return getPageTitle(APP_QUESTION_TEMPLATES_RESULTS);
  }

  /**
   * Gets the page title from the specified results locator.
   *
   * @param resultsLocator
   * @return
   */
  private static String getPageTitle(By resultsLocator) {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    try {
      WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(resultsLocator));
      return title.getText().trim();
    } catch (NoSuchElementException e) {
      return null;
    }
  }

  /**
   * Clicks the application questions link.
   */
  public static void clickOnAppQuestions() {
    SeleniumWrapper.clickOnElement(APP_QUESTIONS);
  }

  /**
   * Selects Single Sign On from dropdown based on value passes from parameter.
   *
   * @param user
   * @param company
   */
  public static void selectSingleSignOn(String user, String company) {
    List<WebElement> options = SeleniumWrapper.getDropdownOptions(driver.findElement(SSO_DROPDOWN));
    for (WebElement option : options) {
      if (option.getText().startsWith(user) && option.getText().endsWith(company)) {
        SeleniumWrapper.selectDropdownByVisibleText(SSO_DROPDOWN, option.getText());
        break;
      }
    }
  }

}

