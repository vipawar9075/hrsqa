package com.altres.projects.formbuilder.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

public class FBVisibilityConfigPage extends Base {

  /**
   * Sets the Rule Logic.
   *
   * @param value
   */

  public static void setRuleLogic(String value) {
    SeleniumWrapper.findElement(driver, FBVisibilityConfig.RULE_LOGIC_TEXT_AREA).sendKeys(value);
  }

  /**
   *Retrieves the message displayed on the page.
   *
   * @param message
   * @return
   */
  public static String getMessage(String message) {
    By messageLocator = By.xpath("//div[contains(text(),'" + message + "')]");
    return SeleniumWrapper.getMessageDisplayed(messageLocator);
  }

  /**
   * Sets the Rule Logic.
   *
   * @param ruleName
   */
  public static void setRuleName(String ruleName) {
    WebElement ruleNameInputField = SeleniumWrapper.findElement(driver, FBVisibilityConfig.RULE_NAME_INPUT_FIELD);
    SeleniumWrapper.sendKeyWithClear(driver, ruleNameInputField, ruleName);
  }

  /**
   * Sets the comparison value.
   *
   * @param comparisonValue
   */
  public static void setComparisonValue(String comparisonValue) {
    WebElement comparisonValueInputField = SeleniumWrapper.findElement(driver,
        FBVisibilityConfig.COMPARISON_VALUE_INPUT_FIELD);
    comparisonValueInputField.sendKeys(comparisonValue);
  }

  /**
   * Selects the Data Sources on the Add Rule screen.
   *
   * @param dataSource
   */
  public static void selectDataSourceToAddRule(String dataSource) {
    SeleniumWrapper.click(driver, FBVisibilityConfig.DATA_SOURCE_DROPDOWN);
    By element = By.xpath("//select//option[contains(text(),'" + dataSource + "')]");
    SeleniumWrapper.findElement(driver, element).click();
  }

  /**
   * Checks whether the shared template is displayed in the custom form's position dropdown.
   *
   * @param sharedTemplateName
   * @return
   */
  public static boolean isSharedTemplateDisplayedInDataSourceDropdown(String sharedTemplateName) {
    List<WebElement> dropdownOptions = SeleniumWrapper.getDropdownOptions(
        SeleniumWrapper.findElement(driver, FBVisibilityConfig.DATA_SOURCE_DROPDOWN));
    return dropdownOptions.stream()
        .map(WebElement::getText)
        .anyMatch(optionText -> optionText.contains(sharedTemplateName));
  }

  /**
   * Selects the Reference Field from the reference field dropdown.
   *
   * @param dataSource
   */

  public static void selectReferenceFieldToAddRule(String dataSource) {
    SeleniumWrapper.click(driver, FBVisibilityConfig.REFERENCE_FIELD_DROPDOWN);
    By element = By.xpath("//select//option[contains(text(),'" + dataSource + "')]");
    SeleniumWrapper.clickOnElement(element);
  }

  /**
   * Selects the Rule complexity from rule complexity dropdown.
   *
   * @param value
   */

  public static void selectRuleComplexity(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(FBVisibilityConfig.RULE_COMPLEXITY_DROPDOWN, value);
  }
}
