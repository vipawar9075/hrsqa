package com.altres.projects.ats.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

public class AtsApplicationPage extends Base {

  private static final By UPLOAD_BUTTON = By.xpath("//input[@id='attachment-file']");

  /**
   * Sets the first name in the text box. Clears the text box if it already contains text.
   *
   * @param firstName the first name to be entered
   */
  public static void setFirstName(String firstName) {
    WebElement element = driver.findElement(By.xpath("//input[@name='first_name']"));
    SeleniumWrapper.sendKeyWithClear(driver, element, firstName);
  }

  /**
   * Uploads a PDF file by specifying the file path.
   *
   * @param filePath
   */
  public static void uploadPdf(String filePath) {
    driver.findElement(UPLOAD_BUTTON).sendKeys(filePath);
  }

  /**
   * Checks if an uploaded file is present in the attachments list.
   *
   * @param fileName
   * @return true if the file is present and displayed, false otherwise
   */
  public static boolean isUploadedFilePresent(String fileName) {
    try {
      return SeleniumWrapper.findElement(driver,
          By.xpath("//tbody[@id='attachments-list-table-body" + "']//a[text()='" + fileName + "']")).isDisplayed();
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  /**
   * Removes an uploaded file from the attachments list. This method locates the file by its name in the attachments
   * list table and clicks the corresponding remove link.
   *
   * @param name the name of the file to be removed
   */
  public static void removeUploadedFile(String name) {
    SeleniumWrapper.findElement(driver, By.xpath(
            "//tbody[@id='attachments-list-table-body']//td//a[text()='" + name + "']/." + "./preceding-sibling::td/a"))
        .click();
  }

  /**
   * Select dropdown values by its names.
   *
   * @param name
   * @param value
   */
  public static void selectDropdown(String name, String value) {
    AtsAddEditQuestionPage.selectDropdown(name, value);
  }
}

