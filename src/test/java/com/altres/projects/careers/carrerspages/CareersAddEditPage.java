package com.altres.projects.careers.carrerspages;

import static org.testng.Reporter.log;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.altres.base.Base;
import com.altres.util.FakeDataGenerator;
import com.altres.util.SeleniumWrapper;

/**
 * Class provides a set of static methods to interact with the careers page module.
 */
public class CareersAddEditPage extends Base {

  private static final By ERROR_NOTICES = By.className("errorNotice");
  private static final By CAREERS_BRANDING_TAB = By.xpath("//a[normalize-space()='Branding']");
  private static final By CAREERS_BACKGROUND_CUSTOM_RADIOBUTTON = By.xpath(
      "//input[@id='custom-background']");
  private static final By CAREERS_BACKGROUND_UPLOAD = By.id("custom-background-image");
  private static final By CAREERS_HEADER_CUSTOM_RADIOBUTTON = By.xpath(
      "//span[label[text()='Custom']]/input[@type='radio' and @name='logo']");
  private static final By CAREERS_HEADER_UPLOAD = By.id("input_image");
  private static final By CAREERS_FOOTER_TEXTBOX_LOCATORS = By.xpath(
      "//*[@id='contentWrapper']/table[3]/tbody/tr[position() >= 3 and position() <= 9]/td[2]");
  private static final By CAREERS_FOOTER_CUSTOM_RADIOBUTTON = By.xpath(
      "//span[label[text()='Custom']]//input[@name='callToActionButton']");
  private static final By CAREERS_COMPANY_TAB = By.xpath("//*[@id='company']/a");
  private static final By CAREERS_NAME_FIELD = By.id("name");
  private static final By CAREERS_SELECT_INDUSTRY = By.xpath("//select[@id='industry']/option");
  private static final By CAREERS_PHONE_NUMBER = By.id("phone-number");
  private static final By CAREERS_ADDRESS1 = By.id("address-1");
  private static final By CAREERS_CITY = By.id("city");
  private static final By CAREERS_POSTAL_CODE = By.id("postal-code");
  private static final By CAREERS_DESCRIPTION_FIELD = By.id("tinymce");
  private static final By CAREERS_INDEED_TAB = By.xpath("//*[@id='indeed']/a");
  private static final By CAREERS_EMAIL = By.id("email");
  private static final By CAREERS_ACTIVE_CHECKBOX = By.id("is-active");
  private static final By CAREERS_RETURN_TO_COMPANIES_BUTTON = By.xpath("//input[@value='Return to Companies']");
  private static final By CAREERS_PAGE_Button = By.xpath("//input[@value='Add Careers Page']");
  private static final By CAREERS_URL_FIELD = By.id("urlName");
  private static final By CAREERS_PUBLISHED_RADIOBUTTON = By.xpath("//input[@id='published_0']");
  private static final By CAREERS_DISPLAY_HRS_RADIOBUTTON = By.xpath("//input[@id='displayInHrs_0']");
  private static final By CAREERS_RETURN_TO_COMPANY_BUTTON = By.xpath("//input[@value='Return to Company']");
  private static final By CAREERS_PAGE_DETAILS = By.xpath("//input[@value='Careers Page Details']");

  /**
   * Common method to click elements
   *
   * @param locator
   */
  private static void clickElement(By locator) {
    SeleniumWrapper.click(driver, locator);
  }

  /**
   * Common method to upload files
   *
   * @param locator
   * @param filePath
   */
  private static void uploadFile(By locator, String filePath) {
    SeleniumWrapper.findElement(driver, locator).sendKeys(filePath);
  }

  /**
   * Clicks on the branding tab in the careers section.
   */
  public static void clickBrandingTab() {
    clickElement(CAREERS_BRANDING_TAB);
  }

  /**
   * Clicks on the custom radio button for careers background.
   */
  public static void clickCareersBackgroundCustomRadiobutton() {
    clickElement(CAREERS_BACKGROUND_CUSTOM_RADIOBUTTON);
  }

  /**
   * Clicks on the custom radio button for careers header.
   */
  public static void clickCareersHeaderCustomRadiobutton() {
    clickElement(CAREERS_HEADER_CUSTOM_RADIOBUTTON);
  }

  /**
   * Uploads a PDF file for the careers header.
   *
   * @param filePath
   */
  public static void uploadHeaderPdf(String filePath) {
    uploadFile(CAREERS_HEADER_UPLOAD, filePath);
  }

  /**
   * Uploads a PDF file for the careers background.
   *
   * @param filePath
   */
  public static void uploadPdf(String filePath) {
    uploadFile(CAREERS_BACKGROUND_UPLOAD, filePath);
  }

  /**
   * Retrieves specific error messages from the page that match the expected messages.
   *
   * @param expectedErrorMessages
   * @return
   */
  public static List<String> getSpecificErrorMessages(List<String> expectedErrorMessages) {
    return driver.findElements(ERROR_NOTICES).stream()
        .map(WebElement::getText)
        .filter(expectedErrorMessages::contains)
        .collect(Collectors.toList());
  }

  /**
   * Enters random data into all input fields in the footer section of the careers page
   */
  public static void enterDataInRows() {
    List<WebElement> cells = driver.findElements(CAREERS_FOOTER_TEXTBOX_LOCATORS);
    for (WebElement cell : cells) {
      WebElement inputField = cell.findElement(By.tagName("input"));
      String randomData = FakeDataGenerator.getRandomWord();
      inputField.sendKeys(randomData);
    }
  }

  /**
   * Clicks on the custom radio button for careers footer.
   */
  public static void clickCareersFooterCustomRadiobutton() {
    clickElement(CAREERS_FOOTER_CUSTOM_RADIOBUTTON);
  }

  /**
   * Clicks on the company tab in the careers section.
   */
  public static void clickCompanyTab() {
    clickElement(CAREERS_COMPANY_TAB);
  }

  /**
   * Sets the company name field and returns the corresponding WebElement.
   *
   * @return
   */
  public static WebElement setCompanyName() {
    WebElement name = driver.findElement(CAREERS_NAME_FIELD);
    name.click();
    return name;
  }

  /**
   * Selects an industry option from a dropdown by its index.
   *
   * @param index
   */
  public static void setIndustry(int index) {
    SeleniumWrapper.selectByIndex(CAREERS_SELECT_INDUSTRY, index);
  }

  /**
   * Sets a random phone number in the phone number field.
   */
  public static void setPhoneNumber() {
    SeleniumWrapper.enterText(CAREERS_PHONE_NUMBER, FakeDataGenerator.getRandomNumber(10));
  }

  /**
   * Enters a random address into the address field.
   */
  public static void enterAddress() {
    String address = FakeDataGenerator.getAddress();
    SeleniumWrapper.findElement(driver, CAREERS_ADDRESS1).sendKeys(address);
  }

  /**
   * Enters a random city into the city field.
   */
  public static void enterCity() {
    String city = FakeDataGenerator.getCity();
    SeleniumWrapper.findElement(driver, CAREERS_CITY).sendKeys(city);
  }

  /**
   * Sets a random postal code in the postal code field.
   */
  public static void setPostalCode() {
    SeleniumWrapper.enterText(CAREERS_POSTAL_CODE, FakeDataGenerator.getRandomNumber(5));
  }

  /**
   * Sets a random description in the description field.
   */
  public static void setDescription() {
    SeleniumWrapper.enterText(CAREERS_DESCRIPTION_FIELD, FakeDataGenerator.getDescription());
  }

  /**
   * Clicks on the Indeed tab in the careers section.
   */
  public static void clickIndeedTab() {
    clickElement(CAREERS_INDEED_TAB);
  }

  /**
   * Sets a random email in the email field.
   */
  public static void setEmail() {
    SeleniumWrapper.enterText(CAREERS_EMAIL, FakeDataGenerator.getEmail());
  }

  /**
   * Clicks on the active checkbox in the careers section.
   */
  public static void clickActiveCheckbox() {
    clickElement(CAREERS_ACTIVE_CHECKBOX);
  }

  /**
   * Clicks on the button to return to the companies list.
   */
  public static void clickReturnToCompaniesButton() {
    clickElement(CAREERS_RETURN_TO_COMPANIES_BUTTON);
  }

  /**
   * Checks if the "Add Career" button is displayed on the page.
   *
   * @return
   */
  public static boolean isAddCareerButtonDisplayed() {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    try {
      WebElement button = wait.until(ExpectedConditions.visibilityOfElementLocated(CAREERS_PAGE_Button));
      return button.isDisplayed();
    } catch (TimeoutException e) {
      log("Add Career button was not found within the time limit.");
    }
    return false;
  }

  /**
   * Clicks on the "Add Career" button.
   */
  public static void clickAddCareerButton() {
    clickElement(CAREERS_PAGE_Button);
  }

  /**
   * Sets a random URL name in the URL field.
   */
  public static void setUrlName() {
    SeleniumWrapper.enterText(CAREERS_URL_FIELD, FakeDataGenerator.getRandomFirstName());
  }

  /**
   * Selects the published radio button.
   */
  public static void selectPublishedRadioButton() {
    clickElement(CAREERS_PUBLISHED_RADIOBUTTON);
  }

  /**
   * Selects the display hours radio button.
   */
  public static void selectDisplayHrsRadiButton() {
    clickElement(CAREERS_DISPLAY_HRS_RADIOBUTTON);
  }

  /**
   * Clicks on the button to return to the company page.
   */
  public static void clickReturnToCompanyButton() {
    clickElement(CAREERS_RETURN_TO_COMPANY_BUTTON);
  }

  /**
   * Checks if the career details button is displayed on the page.
   *
   * @return
   */
  public static boolean isCareerDetailsButtonDisplayed() {
    return driver.findElement(CAREERS_PAGE_DETAILS).isDisplayed();
  }

  /**
   * Clicks on the career details button.
   */
  public static void clickCareersPageDetailsButton() {
    clickElement(CAREERS_PAGE_DETAILS);
  }
}






