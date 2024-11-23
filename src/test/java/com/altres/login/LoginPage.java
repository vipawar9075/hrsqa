package com.altres.login;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.altres.base.Base;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.util.ConfigurationManager;
import com.altres.util.SeleniumWrapper;

/**
 * LoginPage handles the login functionality of the HRS. It extends the Base class to utilize common methods and
 * properties.
 */
public class LoginPage {

  private static WebDriver driver;

  public LoginPage(WebDriver driver) {
    super();
    LoginPage.driver = driver;
  }

  /**
   * Logs in a user to the HRS.
   */
  public static void login(UserProperties userProps, String userType) throws ConfigurationNotLoadedException {
    if (isLoggedIn()) {
      return;
    }
    String username = userProps.getUsername();
    String password = userProps.getPassword();
    int userIndex = userProps.getUserIndex();
    if ("SUPER_USER".equals(userType)) {
      loginAsSuperUSer(username, password);
      selectCompany();
    } else if (userIndex != -1) {
      loginAsStandardUser(username, password, userIndex);
    }
  }

  /**
   * Logs in as a superuser by navigating to the internal login page and submitting the credentials.
   *
   * @param username
   * @param password
   */
  private static void loginAsSuperUSer(String username, String password) {
    SeleniumWrapper.findElement(driver, By.xpath("//a[normalize-space()='Internal Login']")).click();
    SeleniumWrapper.findElement(driver, By.name("user_id")).sendKeys(username);
    SeleniumWrapper.findElement(driver, By.name("password")).sendKeys(password);
    SeleniumWrapper.findElement(driver, By.name("enter_button")).click();

  }

  /**
   * Selects a company from the dropdown based on the company name and number properties configured.
   *
   * @throws ConfigurationNotLoadedException
   */
  private static void selectCompany() throws ConfigurationNotLoadedException {
    WebElement comboBox = SeleniumWrapper.findElement(driver, By.id("company"));
    comboBox.sendKeys(ConfigurationManager.getProperty("company_name").trim());

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    WebElement selectedOption = wait.until(
        ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[@id='ui-id-1']/li/div[contains(text(), '"
            + ConfigurationManager.getProperty("company_name").trim()
            + " - "
            + ConfigurationManager.getProperty("company_number").trim() + "')]")));
    selectedOption.click();
    SeleniumWrapper.findElement(driver, By.name("enter_button")).click();
    SeleniumWrapper.refreshBrowser(driver);
  }

  /**
   * Logs in as a standard user by switching to the specified frame, entering the credentials, and selecting the company
   * based on the user index.
   *
   * @param username
   * @param password
   * @param userIndex
   */
  private static void loginAsStandardUser(String username, String password, int userIndex) {
    SeleniumWrapper.switchToFrameByNameOrId("quicksignIFrame");
    SeleniumWrapper.findElement(driver, By.name("username")).sendKeys(username);
    SeleniumWrapper.findElement(driver, By.name("password")).sendKeys(password);
    SeleniumWrapper.findElement(driver, By.name("submit")).click();
    SeleniumWrapper.switchToDefaultContent(driver);
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("MNT-COMPANYSELECTOR-employer-index")));
    Select employeeSelector = new Select(SeleniumWrapper.findElement(driver, By.id("MNT-COMPANYSELECTOR-employer"
        + "-index")));
    employeeSelector.selectByIndex(userIndex);
    SeleniumWrapper.findElement(driver, By.name("enter_button")).click();
    SeleniumWrapper.refreshBrowser(driver);
  }

  /**
   * Checks if a user is already logged in.
   *
   * @return
   */
  public static boolean isLoggedIn() {
    try {
      return SeleniumWrapper.findElement(driver, By.id("logout")).isDisplayed();

    } catch (NoSuchElementException e) {
      return false;
    }
  }

  //This is in use.

  /**
   * log out a user.
   */
  public static void logout() {
    SeleniumWrapper.switchToDefaultContent(driver);
    try {
      WebElement logOutLink =
          new WebDriverWait(driver, Duration.ofSeconds(5)).until(
              ExpectedConditions.elementToBeClickable(By.linkText("Logout")));
      ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", logOutLink);
      logOutLink.click();
      By confirmButtonBy = By.xpath("//span[@class='buttonWrapper']/input[@value='Yes']");
      WebElement confirmButton = new WebDriverWait(driver, Duration.ofSeconds(10))
          .until(ExpectedConditions.elementToBeClickable(confirmButtonBy));
      confirmButton.click();
    } catch (NoSuchElementException e) {
      SeleniumWrapper.switchToDefaultContent(driver);
    }
  }

  /**
   * Method to login as superuser.
   *
   * @param driver
   * @throws ConfigurationNotLoadedException
   */
  public static void loginAsSuperUser(WebDriver driver) throws ConfigurationNotLoadedException {
    final UserProperties userProps;
    try {
      userProps = new UserProperties(Base.SUPER_USER);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    Base.navigateToURL("clienturl");
    new LoginPage(driver);
    login(userProps, Base.SUPER_USER);
    SeleniumWrapper.refreshBrowser(driver);
  }
}
