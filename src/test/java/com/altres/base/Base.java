package com.altres.base;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.altres.drivers.DriverManager;
import com.altres.exception.ConfigurationLoadException;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.reporting.ExtentReportsManager;
import com.altres.util.ConfigurationManager;
import com.altres.util.Constants;
import com.altres.util.Screenshot;
import com.altres.util.SeleniumWrapper;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityModelProvider;

/**
 * Base class for test setup and teardown methods.
 */
public class Base {

  public static final String ADMIN = "ADMIN";
  //This is in use.
  public static final String SUPER_USER = "SUPER_USER";
  public static WebDriver driver;
  private static ExtentReports extentReportsManager;
  private static ExtentTest currentTestCase;

  /**
   * Setup method to load configuration, initialize ExtentReports and driver instance.
   *
   * @param context
   * @throws ConfigurationLoadException
   */
  @BeforeSuite(alwaysRun = true)
  public void setUpSuite(ITestContext context) throws ConfigurationLoadException {
    ConfigurationManager.loadProperties();
    extentReportsManager = ExtentReportsManager.getInstance(Constants.REPORT_PATH);
    initiateDriverInstance(context);
  }

  /**
   * Setup method executed before each test method to initialize ExtentReports test instance.
   */
  @BeforeMethod(alwaysRun = true)
  public void setUpClass() {
    currentTestCase = extentReportsManager.createTest(this.getClass().getSimpleName(),
        "Test class description");
    SeleniumWrapper.setTest(currentTestCase);
  }

  /**
   * Initializes the WebDriver instance based on the test context.
   *
   * @param context
   */
  private void initiateDriverInstance(ITestContext context) {
    driver = DriverManager.getDriver(context);
    driver.manage().window().maximize();
  }

  /**
   * Navigates the WebDriver instance to a specific URL based on the provided URL type and retrieves
   * the appropriate URL from the configuration manager based on the input urlType currently
   * supports two URL types: "adminurl" and clienturl"
   *
   * @param urlType
   * @throws ConfigurationNotLoadedException
   */
  public static void navigateToURL(String urlType) throws ConfigurationNotLoadedException {
    if (driver == null) {
      throw new RuntimeException(
          "WebDriver not initialized. Call initiateDriverInstance() before using navigateToURL().");
    }

    String url;
    if (urlType.equals("adminurl")) {
      url = ConfigurationManager.getProperty(urlType);
    } else {
      url = ConfigurationManager.getProperty("clienturl");
    }
    driver.get(url);
  }

  /**
   * Teardown method executed after each test method to handle test result and cleanup.
   *
   * @param result
   */
  @AfterMethod(alwaysRun = true)
  public void tearDownMethod(ITestResult result) {
    if (currentTestCase == null) {
      return;
    }
    if (result.getStatus() == ITestResult.FAILURE) {
      try {
        MediaEntityModelProvider base64Screenshot = Screenshot.attachScreenshotToExtentReport(
            driver);
        if (base64Screenshot != null) {
          currentTestCase.fail("Refer this screenshot for more information: ", base64Screenshot);
        }
        currentTestCase.fail(result.getThrowable());
      } catch (Exception e) {
        e.printStackTrace();
      }
    } else if (result.getStatus() == ITestResult.SUCCESS) {
      currentTestCase.pass("Test passed");
    } else if (result.getStatus() == ITestResult.SKIP) {
      currentTestCase.skip("Refer this screenshot for more information: ",
          Screenshot.attachScreenshotToExtentReport(driver));
      currentTestCase.skip(result.getThrowable());
    }
  }

  /**
   * Cleans up the driver folder after the test suite execution and quit the driver and flush
   * ExtentReports
   */
  @AfterSuite(alwaysRun = true)
  public void cleanupAndEndSuite() {
    for (String driverPath : Constants.DRIVER_PATHS) {
      DriverManager.cleanUpDriverFolder(driverPath);
    }
    if (driver != null) {
      driver.quit();
    }
    extentReportsManager.flush();
  }
}

