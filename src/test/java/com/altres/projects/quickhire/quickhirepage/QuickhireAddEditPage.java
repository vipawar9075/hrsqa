package com.altres.projects.quickhire.quickhirepage;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.altres.base.Base;
import com.altres.projects.quickhire.quickhireutil.InvalidSsnGenerator;
import com.altres.projects.quickhire.quickhireutil.SsnGenerator;
import com.altres.util.FakeDataGenerator;
import com.altres.util.SeleniumWrapper;

public class QuickhireAddEditPage extends Base {

  public static final By DROPDOWN_LOCATOR = By.className("ui-menu-item");
  private static final By SOCIAL_SECURITY_NUMBER = By.id("social-security-number");
  private static final By FIRST_NAME = By.id("first-name");
  private static final By LAST_NAME = By.id("last-name");
  private static final By BIRTH_DATE = By.id("birth-date");
  private static final By CLIENT_HIRE_DATE = By.id("hire-date");
  private static final By ALTRES_START_DATE = By.id("altres-new-hire-date");
  private static final By PRIMARY_PHONE = By.id("primary-phone");
  private static final By EMAIL = By.id("email");
  private static final By ADDRESS_FIELD = By.id("residential-address-line-1");
  private static final By JOB_TITLE_SELECT_BUTTON = By.xpath(
      "//select[@id='job-title']/option");
  private static final By PAY_RATE = By.id("pay-rate-amount");
  private static final By PAY_RATE_DROPDOWN = By.xpath("//select[@id='pay-frequency']/option");
  private static final By REASON_FOR_LOW_WAGE = By.xpath("//select[@id='reason-for-low-wage']/option");
  private static final By BENEFIT_GROUP_SELECT_BUTTON = By.xpath(
      "//select[@id='benefit-group']/option");
  private static final By REQUEST_BY_ADMIN = By.xpath("//select[@id='requested-by']/option");
  private static final By VALIDATION_MESSAGE = By.className("successNotice");
  private static final By RETURN_TO_NEW_HIRE = By.xpath(
      "//div[@id='bottomButtonBar']//input[@value='Return to New Hire Details']");
  private static final By VALIDATE_STATUS = By.xpath(
      "(//table[@class='grid formGrid noStripes']//tr[contains(.,'Status')]//td[2])[1]");
  private static final By ERROR_NOTICES = By.className("errorNotice");

  /**
   * Retrieves the error messages displayed on the page.
   *
   * @return
   */
  public static List<String> getDisplayedErrorMessages() {
    List<WebElement> errorElements = driver.findElements(ERROR_NOTICES);
    return errorElements.stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  /**
   * Sets a valid SSN.
   */
  public static void setSocialSecurityNumber() {
    String randomSSN = SsnGenerator.generateValidSSN();
    WebElement ssnField = new WebDriverWait(driver, Duration.ofSeconds(2))
        .until(ExpectedConditions.visibilityOfElementLocated(SOCIAL_SECURITY_NUMBER));
    ssnField.sendKeys(randomSSN);
  }

  /**
   * Sets a invalid SSN.
   */
  public static void setInvalidSocialSecurityNumber() {
    String randomSSN = InvalidSsnGenerator.generateInvalidSSN();
    WebElement ssnField = new WebDriverWait(driver, Duration.ofSeconds(2))
        .until(ExpectedConditions.visibilityOfElementLocated(SOCIAL_SECURITY_NUMBER));
    ssnField.sendKeys(randomSSN);
  }

  /**
   * Sets a random first name.
   */
  public static void setFirstName() {
    SeleniumWrapper.enterText(FIRST_NAME, FakeDataGenerator.getRandomFirstName());
  }

  /**
   * Sets a random 10-digit primary phone number.
   */
  public static void setPrimaryPhone() {
    SeleniumWrapper.enterText(PRIMARY_PHONE, FakeDataGenerator.getRandomNumber(10));
  }

  /**
   * Sets a random last name.
   */
  public static void setLastName() {
    SeleniumWrapper.enterText(LAST_NAME, FakeDataGenerator.getRandomLastName());
  }

  /**
   * Sets birth date.
   */
  public static void setBirthDate(String date) {
    SeleniumWrapper.enterText(BIRTH_DATE, date);
  }

  /**
   * Sets Client hire date.
   */
  public static void setClientHireDate(String date) {
    SeleniumWrapper.enterText(CLIENT_HIRE_DATE, date);
  }

  /**
   * Sets Altres start date.
   */
  public static void setAltresStartDate(String date) {
    SeleniumWrapper.enterText(ALTRES_START_DATE, date);
  }

  /**
   * Selects a Job Title by its index from the dropdown.
   *
   * @param index
   */
  public static void setJobTitle(int index) {
    SeleniumWrapper.selectByIndex(JOB_TITLE_SELECT_BUTTON, index);
  }

  /**
   * Sets a random email address.
   */
  public static void setEmail() {
    SeleniumWrapper.enterText(EMAIL, FakeDataGenerator.getEmail());
  }


  /**
   * Sets a random address number.
   */
  public static void setAddressNumber() {
    String addressNumber = String.valueOf(FakeDataGenerator.faker.number().randomDigitNotZero());
    SeleniumWrapper.enterText(ADDRESS_FIELD, addressNumber);
  }

  /**
   * Selects a benefit group by its index from the dropdown.
   *
   * @param index
   */
  public static void setBenefitGroup(int index) {
    SeleniumWrapper.selectByIndex(BENEFIT_GROUP_SELECT_BUTTON, index);
  }

  /**
   * Selects a pay rate and dropdown by its index.
   *
   * @param amount
   * @param index
   */
  public static void setPayRate(String amount, int index) {
    SeleniumWrapper.enterText(PAY_RATE, amount);
    SeleniumWrapper.selectByIndex(PAY_RATE_DROPDOWN, index);
  }

  /**
   * Selects a reason for low wage dropdown by its index.
   *
   * @param index
   */
  public static void setReasonForLowWage(int index) {
    SeleniumWrapper.selectByIndex(REASON_FOR_LOW_WAGE, index);
  }

  /**
   * Selects an option by its index from the dropdown.
   *
   * @param index
   */
  public static void setDropdown(int index) {
    SeleniumWrapper.selectByIndex(DROPDOWN_LOCATOR, index);
  }

  /**
   * Selects a request by its index from the dropdown.
   *
   * @param index
   */
  public static void setRequestedBy(int index) {
    SeleniumWrapper.selectByIndex(REQUEST_BY_ADMIN, index);
  }

  /**
   * Checks if a validation message is displayed on the page.
   *
   * @return
   */
  public static String getMessageDisplayed() {
    return SeleniumWrapper.getMessageDisplayed(VALIDATION_MESSAGE);
  }

  /**
   * Clicks on the "Return to New Hire" button.
   */
  public static void clickOnReturnToNewHireButton() {
    SeleniumWrapper.clickOnElement(RETURN_TO_NEW_HIRE);
  }

  /**
   * Validates and returns the status of a new hire.
   *
   * @return
   */
  public static String validateStatusOfNewHire() {
    WebElement statusElement = SeleniumWrapper.findElement(driver, VALIDATE_STATUS);
    return statusElement.getText().trim();
  }

}
