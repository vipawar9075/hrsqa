package com.altres.projects.formbuilder.pages;

import org.openqa.selenium.By;

public interface FBFormFields {

  By SUPPLEMENT_LINK = By.xpath("//*[contains(@id,'supplement')]");
  By FORM_FIELDS_HTML = By.xpath("//div[@title='Html']");
  By HTML_EDIT_LINK = By.xpath("//span[@data-bind=\"text: $data.hasTitle ? text: ''\"][normalize-space()='Edit']");
  By HTML_TEXT_AREA = By.xpath("//textarea[@class='form-control']");
  By OK_BUTTON = By.xpath("//input[@value='OK']");
  By PREVIEW_FORM_TAB = By.xpath("//*[text()='Preview']");
  By RETURN_TO_FORM_BUTTON = By.xpath("(//input[@value='Return to Form'])[1]");
  By SINGLE_INPUT_TOOL = By.xpath("//span[contains(text(),'Single Input')]");
  By SINGLE_INPUT_EDIT_LINK = By.xpath(
      "//span[@class='svda_question_action svd-main-color']//span[contains(text(),'Edit')]");
  By DEFAULT_VALE_DATA_SOURCE = By.xpath("//select[@aria-label='Default Value Data Source']");
  By SINGLE_INPUT_TEXT = By.xpath("//span[contains(text(),'question')]");
  By REFERENCE_FIELD = By.xpath("//select[@aria-label='Reference Field']");
  By LIST_C_DROPDOWN = By.xpath("//select[@aria-label='List C']");
  By ISSUE_AUTHORITY_DROPDOWN = By.xpath("//select[@aria-label='Issuing Authority']");

}
