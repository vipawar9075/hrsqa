package com.altres.projects.formbuilder.pages;

import org.openqa.selenium.By;

public interface FBVisibilityConfig {

  By PAGE_TITLE = By.xpath("//*[@id='admin-title']");
  By NO_RULE_TEXT = By.xpath("//*[contains(text(),'There are no rules.')]");
  By FORM_NAME = By.xpath("//*[@id='template_name']");
  By DATA_SOURCE_DROPDOWN = By.xpath("//select[@id='data-source']");
  By RULE_NAME_INPUT_FIELD = By.xpath("//input[@id='rule_name']");
  By REFERENCE_FIELD_DROPDOWN = By.xpath("//select[@id='reference-field']");
  By OPERATOR_DROPDOWN = By.xpath("//select[@id='operator']");
  By COMPARISON_VALUE_INPUT_FIELD = By.xpath("//input[@id='comparison-value']");
  By RULE_COMPLEXITY_DROPDOWN = By.xpath("//select[@id='rule_complexity']");
  By RULE_LOGIC_TEXT_AREA = By.xpath("//textarea[@name='rule_logic']");
  By RULE_NAME_TABLE_HEADER = By.xpath("//th[contains(text(),'Rule Name')]");
  By RULE_TABLE_HEADER = By.xpath("//th[text()='Rule']");
  By COPY_RULE_ICON = By.xpath("(//i[@class='fa fa-clone'])[1]");
  By ADDED_RULE_NAME = By.xpath("//td[contains(text(),'Customer Tier equal to A')]");

}
