package com.altres.drivers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;

import com.altres.base.Base;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * DriverManager class that provides WebDriver instances based on the specified browser type. It extends the Base class
 * to inherit common functionalities.
 */

public class DriverManager extends Base {

  public static final String CHROME = "chrome";
  public static final String EDGE = "edge";
  private static final Logger logger = LoggerFactory.getLogger(DriverManager.class);

  /**
   * Retrieves a WebDriver instance based on the specified browser name.
   *
   * @param context
   * @return
   */
  public static WebDriver getDriver(ITestContext context) {
    String browserName = context.getCurrentXmlTest().getParameter("browser");
    WebDriver driver;
    if (browserName == null || browserName.isEmpty()) {
      browserName = CHROME;
    }
    switch (browserName.toLowerCase()) {
      case CHROME:
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        break;
      case EDGE:
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        break;
      default:
        throw new IllegalArgumentException("Invalid browser name: + browserName");
    }
    driver.manage().window().maximize();
    return driver;
  }

  /**
   * Quits the WebDriver instance if it has been initialized.
   */
  public static void quitDriver() {
    if (driver != null) {
      driver.quit();
    }
  }

  /**
   * Closes the WebDriver instance if it has been initialized.
   */
  public static void closeDriver() {
    if (driver != null) {
      driver.close();
    }
  }

  /**
   * Clean up driver directories.
   *
   * @param driverPath
   */
  public static void cleanUpDriverFolder(String driverPath) {
    try {
      Path path = Paths.get(driverPath);
      if (Files.exists(path)) {
        Files.walk(path)
            .sorted((p1, p2) -> p2.compareTo(p1))
            .map(Path::toFile)
            .forEach(file -> {
              if (!file.delete()) {
                logger.error("Failed to delete file or directory: " + file.getAbsolutePath());
              }
            });
        logger.info("Deleted driver directory: " + driverPath);
      }
    } catch (IOException e) {
      logger.error("Error during driver cleanup at " + driverPath + ": " + e.getMessage(), e);
    }
  }
}

