package com.altres.util;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.altres.base.Base;
import com.aventstack.extentreports.ExtentTest;
import com.github.javafaker.PhoneNumber;

/**
 * SeleniumWrapper provides wrapper methods for Selenium WebDriver interactions.
 */
public class SeleniumWrapper extends Base {

  public static final Wait<WebDriver> fluentWait = new FluentWait<>(driver)
      .withTimeout(Duration.ofSeconds(1))
      .pollingEvery(Duration.ofSeconds(1))
      .ignoring(Exception.class);
  private static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  private static ExtentTest extentTest;

  /**
   * Sets the ExtentTest instance for logging.
   *
   * @param extentTest
   */
  public static void setTest(ExtentTest extentTest) {
    SeleniumWrapper.extentTest = extentTest;
  }

  /**
   * Checks if a message is displayed and returns the text.
   *
   * @param messageLocator
   * @return
   */
  public static String getMessageDisplayed(By messageLocator) {
    try {
      WebElement messageElement = wait.until(
          ExpectedConditions.presenceOfElementLocated(messageLocator));
      String actualMessage = messageElement.getText();
      log("Checking actual message: " + actualMessage);
      return actualMessage;
    } catch (NoSuchElementException | TimeoutException e) {
      return null;
    }
  }

  /**
   * Sends keys to an element with optional refocus.
   *
   * @param driver
   * @param element
   * @param keys
   * @param isClear
   * @param refocus
   */
  public static void sendKeyWithRefocus(WebDriver driver, WebElement element, String keys,
      boolean isClear, WebElement refocus) {
    JavascriptExecutorWrapper.scrollToElement(driver, element);
    element.click();

    if (isClear) {
      element.clear();
    } else {
      element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
      element.sendKeys(Keys.BACK_SPACE);
    }
    element.sendKeys(keys);
    log("Sent keys to element: " + element + ", Keys: " + keys);
    if (refocus != null) {
      refocus.click();
    }
  }

  /**
   * Sends keys to an element.
   *
   * @param driver
   * @param element
   * @param keys
   */
  private static void sendKey(WebDriver driver, WebElement element, String keys) {
    sendKeyWithRefocus(driver, element, keys, true, null);
  }

  /**
   * Sends keys to an element with clearing the existing text.
   *
   * @param driver
   * @param element
   * @param keys
   */
  public static void sendKeyWithClear(WebDriver driver, WebElement element, String keys) {
    sendKey(driver, element, keys);
  }

  /**
   * Clicks on an element.
   *
   * @param driver
   * @param by
   * @throws WebDriverException
   */
  public static void click(WebDriver driver, By by) throws WebDriverException {
    WebElement element = null;
    try {
      element = wait.until(ExpectedConditions.elementToBeClickable(by));
      element.click();
      log("Clicked on element:" + by);
    } catch (ElementNotInteractableException e) {
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].click();", element);
      assert element != null;
      log("Using JavaScript click for element with text: " + element.getText() + " (located by: "
          + by + ")");
    } catch (StaleElementReferenceException e) {
      log("Stale element reference encountered for element located by: " + by);
      throw e;
    }
  }

  /**
   * Finds an element.
   *
   * @param driver
   * @param by
   * @return
   */
  public static WebElement findElement(WebDriver driver, By by) {
    WebElement element = driver.findElement(by);
    JavascriptExecutorWrapper.scrollToElement(driver, element);
    log("Found element: " + by);
    return element;
  }

  /**
   * Finds List of elements.
   *
   * @param driver
   * @param by
   * @return
   */
  public static List<WebElement> findElements(WebDriver driver, By by) {
    List<WebElement> elements = driver.findElements(by);
    if (!elements.isEmpty()) {
      JavascriptExecutorWrapper.scrollToElement(driver, elements.get(0));
      log("Found elements: " + by);
    }
    return elements;
  }

  /**
   * Switches to the interior content frame.
   *
   * @param driver
   */
  public static void switchToInteriorContent(WebDriver driver) {
    driver.switchTo().frame(findElement(driver, By.name("interiorContentIFrame")));
    log("Switched to interior content frame");
  }

  /**
   * Switches to the default content.
   *
   * @param driver
   */
  public static void switchToDefaultContent(WebDriver driver) {
    driver.switchTo().defaultContent();
    log("Switched to default content");
  }

  /**
   * Logs a message if extentTest is not null.
   *
   * @param message
   */
  private static void log(String message) {
    if (extentTest != null) {
      extentTest.info(message);
    }
  }

  /**
   * Verifies if an element is present and logs its text.
   *
   * @param element
   */
  public static String getElementTextIfPresent(By element) {
    try {
      fluentWait.until(ExpectedConditions.presenceOfElementLocated(element));
      WebElement title = driver.findElement(element);
      return title.getText();
    } catch (NoSuchElementException e) {
      log("Element not found: " + element);
      return null;
    }
  }

  /**
   * Clicks on an element.
   *
   * @param element
   */
  public static void clickOnElement(By element) {
    try {
      getElementTextIfPresent(element);
      driver.findElement(element).click();
      log("Clicked on element: " + element);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Enters text into an element.
   *
   * @param element
   * @param value
   */
  public static void enterText(By element, Object value) {
    try {
      getElementTextIfPresent(element);
      if (value instanceof PhoneNumber) {
        driver.findElement(element).sendKeys(((PhoneNumber) value).phoneNumber());
      } else {
        driver.findElement(element).sendKeys(String.valueOf(value));
      }
      log("Entered text into element: " + element + ", Value: " + value);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Selects an option by index in a dropdown.
   *
   * @param locator
   * @param index
   */
  public static void selectByIndex(By locator, int index) {
    List<WebElement> options = wait.until(
        ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    if (index < 0 || index >= options.size()) {
      throw new IllegalArgumentException("Invalid index: " + index);
    }
    options.get(index).click();
    log("Selected by index: " + index);
  }

  /**
   * Selects a value in a dropdown by visible text.
   *
   * @param locator
   * @param value
   */
  public static void selectDropdownByVisibleText(By locator, String value) {
    try {
      WebElement dropdown = driver.findElement(locator);
      Select select = new Select(dropdown);
      select.selectByVisibleText(value);
      log("Selected dropdown value: " + value);
    } catch (Exception e) {
      log("Exception occurred while selecting dropdown value: " + e.getMessage());
    }
  }

  /**
   * Selects a value in a dropdown by option value.
   *
   * @param locator
   * @param value
   */
  public static void selectDropdownByOptionValue(By locator, String value) {
    try {
      WebElement dropdown = driver.findElement(locator);
      Select select = new Select(dropdown);
      String optionSelected = select.getFirstSelectedOption().getAttribute("value");
      if (!StringUtils.equals(optionSelected, value)) {
        select.selectByValue(value);
        log("Selected dropdown value: " + value);
      }
    } catch (Exception e) {
      log("Exception occurred while selecting dropdown value: " + e.getMessage());
      throw e;
    }
  }

  /**
   * Gets the text of an element.
   *
   * @param locator
   * @return
   */
  public static String getElementText(By locator) {
    String text = driver.findElement(locator).getText();
    log("Got text from element: " + locator + ", Text: " + text);
    return text;
  }

  /**
   * Gets the selected value of a dropdown.
   *
   * @param locator
   * @return
   */
  public static String getSelectedDropdownValue(By locator) {
    try {
      WebElement dropdown = driver.findElement(locator);
      Select select = new Select(dropdown);
      String selectedValue = select.getFirstSelectedOption().getText();
      log("Got selected dropdown value: " + selectedValue);
      return selectedValue;
    } catch (Exception e) {
      log("Exception occurred while getting selected dropdown value: " + e.getMessage());
      return null;
    }
  }

  /**
   * Switch to alert and accept it.
   */
  public static void acceptAlert() {
    driver.switchTo().alert().accept();
    log("Accepted the alert.");
  }

  /**
   * Checks whether the required element is enabled and logs its text.
   *
   * @param locator
   * @return
   */
  public static Boolean isElementEnabled(By locator) {
    boolean status = wait.until(ExpectedConditions.presenceOfElementLocated(locator)).isEnabled();
    log("Required element enabled: " + status + " i.e.," + locator);
    return status;
  }

  /**
   * Switch to parent frame.
   *
   * @param driver
   */
  public static void switchToParentFrame(WebDriver driver) {
    driver.switchTo().parentFrame();
  }

  /**
   * Switch to iframe base on the index.
   *
   * @param driver
   * @param index
   */
  public static void switchToIframeByIndex(WebDriver driver, int index) {
    int totalIframes = driver.findElements(By.tagName("iframe")).size();
    if (index < 0 || index >= totalIframes) {
      throw new IllegalArgumentException("Index out of bounds for the available iframes.");
    }
    driver.switchTo().frame(index);
    log("Switched to iframe with index: " + index);
  }

  /**
   * Refresh the browser
   *
   * @param driver
   */
  public static void refreshBrowser(WebDriver driver) {
    driver.navigate().refresh();
  }

  /**
   * Switches the frame using name or id.
   *
   * @param nameOrId
   */
  public static void switchToFrameByNameOrId(String nameOrId) {
    driver.switchTo().frame(nameOrId);
  }

  /**
   * Method to switch to a window based on its window index.
   *
   * @param index
   */
  public static void switchToWindowByIndex(int index) {
    Set<String> allWindows = driver.getWindowHandles();
    String[] windowHandles = allWindows.toArray(new String[0]);
    if (index < 0 || index >= windowHandles.length) {
      throw new IllegalArgumentException("Invalid window index: " + index);
    }
    // Switch to the window at the specified index
    driver.switchTo().window(windowHandles[index]);
  }

  /**
   * Switch to alert and dismiss it.
   */
  public static void dismissAlert() {
    driver.switchTo().alert().dismiss();
    log("Dismissed the alert.");
  }

  /**
   * Get text from alert.
   *
   * @param driver
   * @return
   */
  public static String getAlertText(WebDriver driver) {

    return driver.switchTo().alert().getText();
  }

  /**
   * Method to look for an alert.
   *
   * @param driver
   */
  public static void acceptAlertIfPresent(WebDriver driver) {
    try {
      driver.switchTo().alert().accept();
    } catch (Exception Ex) {
    }
  }

  /**
   * get first selected option text from dropdown.
   *
   * @param locator
   * @return
   */
  public static String getCurrentSelectedOptionText(By locator) {

    try {
      WebElement dropdown = driver.findElement(locator);
      Select select = new Select(dropdown);
      String text = select.getFirstSelectedOption().getText();
      log("Get first selected option.");
      return text;
    } catch (Exception e) {
      log("Exception occurred while get text of selected option: " + e.getMessage());
      return null;
    }
  }

  /**
   * Get size of dropdown.
   *
   * @param locator
   * @return
   */
  public static int getDropdownOptionsSize(By locator) {

    try {
      WebElement dropdown = driver.findElement(locator);
      Select select = new Select(dropdown);
      List<WebElement> allOptions = select.getOptions();
      int size = allOptions.size();
      log("Get size of options.");
      return size;
    } catch (Exception e) {
      log("Exception occurred while get text of selected option: " + e.getMessage());
      return 0;
    }
  }

  /**
   * Method to return child element.
   *
   * @param driver
   * @param parent
   * @param path
   * @return
   */
  public static WebElement findChildElement(WebDriver driver, WebElement parent, String path) {
    WebElement element = null;
    List<WebElement> elements = findRelativeElements(parent, path);

    try {
      element = elements.get(0);
      JavascriptExecutorWrapper.scrollToElement(driver, element);
    } catch (Exception e) {
      // element does not exist.
    }

    return element;
  }

  /**
   * Method to get all sub-elements matching for the provided "relativeXPath" parameter.
   *
   * @param webElement
   * @param relativeXPath
   * @return
   */
  public static List<WebElement> findRelativeElements(WebElement webElement, String relativeXPath) {

    new WebDriverWait(driver, Duration.ofSeconds(1));
    try {
      return webElement.findElements(By.xpath(relativeXPath));
    } finally {
      new WebDriverWait(driver, Duration.ofSeconds(10));
    }
  }

  /**
   * Method used to put web driver on wait untill locatorElement is visible else wait for 10 seconds.
   *
   * @param locatorElement
   */
  public static void webDriverWaitForVisibiltyOfElement(By locatorElement) {
    wait.until(ExpectedConditions.visibilityOfElementLocated(locatorElement));
  }

  /**
   * Method to update the dropdown based on index provided.
   *
   * @param element
   * @param selectIndex
   */
  public static void setDropDownByIndex(WebElement element, int selectIndex) {
    if (element != null && element.isDisplayed()) {
      Select selectEmployeeGroup = new Select(element);
      if (selectEmployeeGroup.getOptions().size() >= selectIndex && selectIndex > 0) {
        selectEmployeeGroup.selectByIndex(selectIndex - 1);
      } else {
        selectEmployeeGroup.selectByIndex(0);
        if (selectIndex != 1) {
          log("There were no selection for the index provided.");
        }
      }
    }
  }

  /**
   * This method will return all the available options from a dropdown selection field.
   *
   * @param element
   * @return
   */
  public static Map<String, String> getAvailableDropdownOptions(WebElement element) {
    Select dropDownSelection = new Select(element);
    Map<String, String> availableOptions = new HashMap<>();
    for (WebElement option : dropDownSelection.getOptions()) {
      availableOptions.put(option.getAttribute("value"), option.getText());
    }
    return availableOptions;
  }

  /**
   * Method to check whether the locator is visible.
   *
   * @param locator
   * @return
   */
  public static boolean isLocatorDisplayed(By locator) {
    boolean isElementVisible = findElement(driver, locator).isDisplayed();
    if (!isElementVisible) {
      log("Element not visible: " + locator);
    }
    return isElementVisible;
  }

  /**
   * Method to check whether the element is visible.
   *
   * @param element
   * @return
   */
  public static boolean isElementDisplayed(WebElement element) {
    boolean isElementVisible = element.isDisplayed();
    if (!isElementVisible) {
      log("Element not visible: " + element.getText());
    }
    return isElementVisible;
  }

  /**
   * Retrieves all options from the specified dropdown element.
   *
   * @param dropdown
   * @return a List of WebElement
   */
  public static List<WebElement> getDropdownOptions(WebElement dropdown) {
    Select select = new Select(dropdown);
    return select.getOptions();
  }

  /**
   * get first selected option text from dropdown.
   *
   * @param locator
   * @return
   */
  public static String getFirstSelectedOptionText(By locator) {

    try {
      WebElement dropdown = driver.findElement(locator);
      Select select = new Select(dropdown);
      String text = select.getFirstSelectedOption().getText();
      log("Get first selected option.");
      return text;
    } catch (Exception e) {
      log("Exception occurred while get text of selected option: " + e.getMessage());
      return null;
    }
  }

  /**
   * Simulates a drag-and-drop action by dragging an element from a source location and dropping it to a target
   * location.
   *
   * @param source
   * @param destination
   */
  public static void dragAndDrop(WebElement source, WebElement destination) {
    Actions actions = new Actions(driver);
    actions.clickAndHold(source)
        .moveToElement(destination)
        .release()
        .build()
        .perform();
  }
}