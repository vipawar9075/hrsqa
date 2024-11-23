package com.altres.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;

/**
 * ScreenshotUtil provides utility methods for capturing screenshots during Selenium tests.
 */
public class Screenshot {

  public static WebDriver driver;

  /**
   * Captures a screenshot of the current browser window and saves it with a timestamp.
   *
   * @return
   */
  public static String takeScreenshot() {
    try {
      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
      String screenshotDir = System.getProperty("user.dir") + "/screenshots";
      String screenshotPath = screenshotDir + "/" + dateName + ".png";
      File dir = new File(screenshotDir);
      if (!dir.exists()) {
        dir.mkdirs();
      }
      FileUtils.copyFile(screenshot, new File(screenshotPath));
      return screenshotPath;
    } catch (Exception e) {
      System.err.println("Exception while taking screenshot: " + e.getMessage());
      return null;
    }
  }

  /**
   * Captures a screenshot and attaches it to the Extent Report.
   *
   * @param driver The WebDriver instance for capturing the screenshot.
   * @return A MediaEntityModelProvider with the screenshot, or null if an error occurs.
   */
  public static MediaEntityModelProvider attachScreenshotToExtentReport(WebDriver driver) {
    MediaEntityModelProvider screenshot = null;
    try {
      screenshot = MediaEntityBuilder.createScreenCaptureFromBase64String(
          ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64)).build();
    } catch (Exception e) {
      System.err.println("An error occurred while attaching the screenshot.");
    }
    return screenshot;
  }
}



