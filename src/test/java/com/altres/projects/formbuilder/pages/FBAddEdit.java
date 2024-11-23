package com.altres.projects.formbuilder.pages;

import static com.altres.base.Base.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.altres.util.SeleniumWrapper;

public interface FBAddEdit {

  By MODULE_DROPDOWN_LOCATOR = By.xpath("//select[@id='module']");
  By FORM_NAME = By.id("name");
  By DISPLAY_NAME_INPUT = By.id("displayName");
  By UPLOAD_BUTTON = By.xpath("//*[@id='pdf-file']");
  By ELECTRONIC_SIGNATURE = By.xpath("//select[@id='signature']");
  By ELECTRONIC_SIGNATURE_DATE_FIELD = By.xpath("//select[@id='signature-date']");
  By CHECKBOX_LOCATOR = By.id("anchor-form-anchor");
  By BEFORE_RADIO_BUTTON = By.xpath("//label[text()='Before']/preceding-sibling::input");
  By RETURN_BUTTON = By.xpath("(//input[@value='Return to Forms'])[2]");
  By FORM_PDF_DROPDOWN_LOCATOR = By.xpath("//select[@id='form-pdf']");
  By PREVIEW_FORM_BUTTON = By.xpath("(//input[@value='Preview Form'])[1]");
  By PDF_MAPPING_BUTTON = By.xpath("(//input[@value='PDF Mapping'])[1]");
  By SUPPLEMENT_UPLOAD_BUTTON = By.xpath("//input[@name='supplement_file']");
  By FORM_FIELDS_BUTTON = By.xpath("(//input[@value='Form Fields'])[1]");
  By DELETE_SUPPLEMENT_DOC = By.xpath("//div[@id='supplement-document-download']//a//i");
  By ACTIVE_CHECKBOX = By.name("active");
  By TEMPLATE_NAME = By.xpath("//input[@id='name']");
  By CUSTOMER_FORM_ANCHOR = By.xpath("//input[@id='anchor-form-anchor']");
  By CUSTOMER_RADIO_BUTTON = By.xpath("//input[@id='template-type1']");
  By CUSTOMER_INPUT_FIELD = By.xpath("//input[@id='customer']");
  By POSITION_DROPDOWN = By.xpath("//select[@id='position']");
  By VISIBILITY_CONFIGURATION_BUTTON = By.xpath("(//input[@value='Visibility Configuration'])[1]");
  By ADD_RULE_BUTTON = By.xpath("//input[@value='Add Rule']");
  By RULE_NAME_FIELD = By.xpath("//input[@id='rule_name']");
  By REFERENCE_PROPERTY_ADD_RULE = By.xpath("//select[@id='reference-field']");
  By COMPARISON_VALUE_ADD_RULE = By.xpath("//input[@id='comparison-value']");
  By UPLOAD_TO_IMIGIT_CHECKBOX = By.xpath("//input[@id='upload-to-imigit']");
  By IMIGIT_DOCUMENT_TYPE_DROPDOWN = By.xpath("//select[@id='imigit-document-type']");
  By CUSTOMER_FORM_POSITION_TEXT = By.xpath("//*[contains(text(),'Customer Form Position')]");

  /**
   * Method to get a web element by driver and locator element.
   *
   * @param locatorElement
   * @return
   */
  static WebElement getWebElement(By locatorElement) {
    return SeleniumWrapper.findElement(driver, locatorElement);
  }

}
