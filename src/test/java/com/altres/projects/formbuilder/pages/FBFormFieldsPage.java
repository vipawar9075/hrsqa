package com.altres.projects.formbuilder.pages;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import com.altres.base.Base;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.SeleniumWrapper;


/**
 * All the xpaths and methods required to perform specific actions to validate the test cases on the Form Builder: Form
 * Fields page are listed here.
 */
public class FBFormFieldsPage extends Base {


  /**
   * Creates a HTML field having supplement tag.
   *
   * @param supplementName
   */
  public static void addHtmlFieldWithsupplementTag(String supplementName) {
    SeleniumWrapper.click(driver, FBFormFields.FORM_FIELDS_HTML);
    SeleniumWrapper.click(driver, FBFormFields.HTML_EDIT_LINK);
    SeleniumWrapper.click(driver, FBFormFields.HTML_TEXT_AREA);
    SeleniumWrapper.findElement(driver, FBFormFields.HTML_TEXT_AREA)
        .sendKeys("<fb-supplement>" + supplementName + "</fb-supplement>");
    SeleniumWrapper.click(driver, FBFormFields.OK_BUTTON);
    AltresGeneralUtil.clickOnSaveButton();
  }

  /**
   * Creates the single input field.
   */
  public static void createSingleInputField() {
    SeleniumWrapper.click(driver, FBFormFields.SINGLE_INPUT_TOOL);
    AltresGeneralUtil.clickOnSaveButton();
  }

  /**
   * Selects the Default value data source from the dropdown.
   *
   * @param value
   */
  public static void selectDefaultValueDataSource(String value) {
    SeleniumWrapper.click(driver, FBFormFields.DEFAULT_VALE_DATA_SOURCE);
    By element = By.xpath("//select//option[contains(text(),'" + value + "')]");
    SeleniumWrapper.findElement(driver, element).click();
  }

  /**
   * Selects the reference field from the dropdown.
   *
   * @param value
   */
  public static void selectReferenceField(String value) {
    SeleniumWrapper.click(driver, FBFormFields.REFERENCE_FIELD);
    By element = By.xpath("//select//option[contains(text(),'" + value + "')]");
    SeleniumWrapper.findElement(driver, element).click();
  }

  /**
   * Retrieves the message.
   *
   * @param message
   * @return
   */
  public static String getFBErrorMessage(String message) {
    By element = By.xpath("//*[contains(text(),'" + message + "')]");
    return SeleniumWrapper.getMessageDisplayed(element);
  }

  /**
   * Validates whether the file is downloaded or not.
   *
   * @return
   */
  public static boolean isFileDownloaded() throws IOException {
    String downloadPath = Constants.DOWNLOADED_FILE_PATH;
    String expectedFileName = new ConfigPropertiesProviderImpl(
        Constants.FORMBUILDER_ELEMENT_PROPERTIES_PATH).getPropertyValueByKey("formbuilderUploadedSupplementName");
    File file = new File(downloadPath, expectedFileName);
    Wait<WebDriver> fluentWait = new FluentWait<>(driver)
        .withTimeout(Duration.ofSeconds(5))
        .pollingEvery(Duration.ofSeconds(5))
        .ignoring(Exception.class).withMessage("File is not downloaded.");
    Boolean fileDisplayed = fluentWait.until(f -> file.exists() && file.canRead());
    file.delete();
    return fileDisplayed;
  }

  /**
   * Selects a value from List C for the Verify I9 form.
   *
   * @param value
   */
  public static void selectValueFromDropdown(By element,String value){
    SeleniumWrapper.selectDropdownByVisibleText(element,value);
  }
}
