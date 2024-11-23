package com.altres.projects.formbuilder.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.FakeDataGenerator;
import com.altres.util.SeleniumWrapper;

/**
 * All the xpaths and methods required to perform specific actions to validate the test cases on the Form Builder: Add
 * Form and Form Builder: Edit Form pages are listed here.
 */

public class FBAddEditPage extends Base {


  /**
   * Sets the first name.
   *
   * @param firstName
   */
  public static void setFirstName(String firstName) {
    SeleniumWrapper.findElement(driver, FBAddEdit.FORM_NAME).sendKeys(firstName);
  }

  /**
   * Sets the display name.
   *
   * @param displayName
   */
  public static void setDisplayName(String displayName) {
    SeleniumWrapper.findElement(driver, FBAddEdit.DISPLAY_NAME_INPUT).sendKeys(displayName);
  }

  /**
   * Selects a module from the dropdown by visible text.
   *
   * @param value
   */
  public static void selectModuleFromDropdown(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(FBAddEdit.MODULE_DROPDOWN_LOCATOR, value);
  }

  /**
   * @param value
   */
  public static void selectFormPDFFromDropdown(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(FBAddEdit.FORM_PDF_DROPDOWN_LOCATOR, value);
  }

  /**
   * Creates a new template with the specified PDF and module name.
   *
   * @param pdfPath
   * @param moduleName
   */
  public static void createNewTemplateWithPDF(String pdfPath, String moduleName) {
    selectModuleFromDropdown(moduleName);
    setFirstName(FakeDataGenerator.getRandomFirstName());
    setDisplayName(FakeDataGenerator.getRandomFullName());
    FBAddEditPage.uploadPdf(pdfPath);
    AltresGeneralUtil.clickOnSaveButton();
  }

  /**
   * Selects the radio buttons to set the position of the customer form.
   *
   * @param value
   */
  public static void selectCustomerFormPosition(String value) {
    By customerFormPosition = By.xpath("//input[@name='anchor'][@value='" + value + "']");
    SeleniumWrapper.clickOnElement(customerFormPosition);
  }

  /**
   * Sets the customer number for the custom form.
   *
   * @param value
   */
  public static void setCustomerNumber(Object value) {
    SeleniumWrapper.enterText(FBAddEdit.CUSTOMER_INPUT_FIELD, value);
    By customerName = By.xpath("//div[contains(text(),'" + value + "')]");
    SeleniumWrapper.click(driver, customerName);

  }

  /**
   * Selects the position from the position dropdown for the customer form.
   *
   * @param value
   */
  public static void selectPositionOfForm(String value) {
    SeleniumWrapper.click(driver, FBAddEdit.POSITION_DROPDOWN);
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(FBAddEdit.POSITION_DROPDOWN);
    By sharedFormName = By.xpath("//select[@id='position']//option[contains(text(),'" + value + "')]");
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(sharedFormName);
    SeleniumWrapper.clickOnElement(sharedFormName);
  }

  /**
   * Sets the Rule name in the visibility configuration.
   *
   * @param value
   */
  public static void setRuleName(String value) {
    WebElement ruleNameField = SeleniumWrapper.findElement(driver, FBAddEdit.RULE_NAME_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, ruleNameField, value);

  }

  /**
   * Selects the Reference property on the Add Rule screen.
   *
   * @param referenceProperty
   */
  public static void selectReferencePropertyToAddRule(String referenceProperty) {
    SeleniumWrapper.click(driver, FBAddEdit.REFERENCE_PROPERTY_ADD_RULE);
    By element = By.xpath("//select//option[contains(text(),'" + referenceProperty + "')]");
    SeleniumWrapper.clickOnElement(element);
  }

  /**
   * Checks whether the shared template is displayed in the custom form's position dropdown.
   *
   * @param sharedTemplateName
   */
  public static boolean isSharedTemplateDisplayedInPositionDropdown(String sharedTemplateName) {
    List<WebElement> dropdownOptions = SeleniumWrapper.getDropdownOptions(
        SeleniumWrapper.findElement(driver, FBAddEdit.POSITION_DROPDOWN));
    return dropdownOptions.stream()
        .map(WebElement::getText)
        .anyMatch(optionText -> optionText.contains(sharedTemplateName));

  }

  /**
   * Selects the IMIGIT Document Type from the dropdown.
   *
   * @param value
   */
  public static void selectImigitDocumentType(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(FBAddEdit.IMIGIT_DOCUMENT_TYPE_DROPDOWN, value);
  }

  /**
   * Uploads a PDF file by specifying the file path.
   *
   * @param filePath
   */
  public static void uploadPdf(String filePath) {
    SeleniumWrapper.findElement(driver, FBAddEdit.UPLOAD_BUTTON).sendKeys(filePath);
  }


  /**
   * Uploads the supplement Pdf.
   *
   * @param filePath
   */
  public static void uploadSupplementPdf(String filePath) {
    SeleniumWrapper.findElement(driver, FBAddEdit.SUPPLEMENT_UPLOAD_BUTTON).sendKeys(filePath);
  }


  /**
   * Retrieves the created Template name.
   *
   * @return
   */
  public static String getTemplateName() {
    return SeleniumWrapper.findElement(driver,FBAddEdit.TEMPLATE_NAME).getAttribute("value");
  }


  /**
   * Sets the electronic signature from the dropdown.
   *
   * @param value
   */
  public static void setElectronicSignature(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(FBAddEdit.ELECTRONIC_SIGNATURE, value);
  }

  /**
   * Sets the electronic signature date.
   *
   * @param value
   */
  public static void setElectronicSignatureDateField(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(FBAddEdit.ELECTRONIC_SIGNATURE_DATE_FIELD, value);
  }


  /**
   * Sets the comparison value on Add Rule screen.
   *
   * @param comparisonValue
   */
  public static void setComparisonValueToAddRule(Object comparisonValue) {
    SeleniumWrapper.enterText(FBAddEdit.COMPARISON_VALUE_ADD_RULE, comparisonValue);
  }

}










