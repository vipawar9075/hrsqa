package com.altres.projects.formbuilder.admintestcases;

import static com.altres.projects.formbuilder.admintestcases.TC10_ValidateAnchorCustomFormPositionCanChange.customTemplateName;
import static com.altres.projects.formbuilder.admintestcases.TC10_ValidateAnchorCustomFormPositionCanChange.sharedTemplateName;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBFormFields;
import com.altres.projects.formbuilder.pages.FBListingPage;
import com.altres.projects.formbuilder.pages.FBVisibilityConfig;
import com.altres.projects.formbuilder.pages.FBVisibilityConfigPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.FakeDataGenerator;
import com.altres.util.SeleniumWrapper;

/**
 * Validates the Form Builder: Visibility Configuration screen functionality
 */
public class TC11_ValidateVisibilityCinfigurationFunctionality extends Base {

  static ConfigPropertiesProviderImpl propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.FORMBUILDER_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateVisibilityConfigurationScreen() throws IOException {
    FBAddEdit.getWebElement(FBFormFields.RETURN_TO_FORM_BUTTON).click();
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormsButton"));
    FBListingPage.clickOnFilteredForm(customTemplateName);
    FBAddEdit.getWebElement(FBAddEdit.VISIBILITY_CONFIGURATION_BUTTON).click();
    Assert.assertTrue(FBAddEdit.getWebElement(FBVisibilityConfig.NO_RULE_TEXT).isDisplayed(),
        "Error: No rule text is not displayed");
    Assert.assertEquals(FBAddEdit.getWebElement(FBVisibilityConfig.FORM_NAME).getText(), customTemplateName,
        "Form name does not match.");
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("addRuleButton"));
    AltresGeneralUtil.clickOnSaveButton();
    String actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    String expectedErrorMessage = propertyValueProvider.getPropertyValueByKey("error.message.blankRuleName");
    Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error: The error message is incorrect.");
    Assert.assertFalse(FBAddEdit.getWebElement(FBVisibilityConfig.REFERENCE_FIELD_DROPDOWN).isDisplayed(),
        "Error: Reference field is displayed.");
    Assert.assertFalse(FBAddEdit.getWebElement(FBVisibilityConfig.OPERATOR_DROPDOWN).isDisplayed(),
        "Error: Operator dropdown is " + "not displayed.");
    Assert.assertFalse(FBAddEdit.getWebElement(FBVisibilityConfig.COMPARISON_VALUE_INPUT_FIELD).isDisplayed(),
        "Error: " + "Comparison value input field");
    Assert.assertEquals(FBAddEdit.getWebElement(FBVisibilityConfig.PAGE_TITLE).getText(),
        propertyValueProvider.getPropertyValueByKey("expected.title.addRulePage"));
    Assert.assertEquals(FBAddEdit.getWebElement(FBVisibilityConfig.FORM_NAME).getText(), customTemplateName,
        "Form name does " + "not match.");
    Assert.assertTrue(FBVisibilityConfigPage.isSharedTemplateDisplayedInDataSourceDropdown(sharedTemplateName),
        "The shared template is not visible in the Data Source.");
    FBVisibilityConfigPage.setRuleName(FakeDataGenerator.getRandomNumber(4));
    AltresGeneralUtil.clickOnSaveButton();
    actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    String expectedErrMessage = propertyValueProvider.getPropertyValueByKey("error.message.blankDataSource");
    Assert.assertEquals(actualErrorMessage, expectedErrMessage, "Error: The error message is incorrect.");
    FBVisibilityConfigPage.selectDataSourceToAddRule("Dental Waiver");
    Assert.assertTrue(FBAddEdit.getWebElement(FBVisibilityConfig.REFERENCE_FIELD_DROPDOWN).isDisplayed(),
        "Error: Reference field " + "is displayed.");
    AltresGeneralUtil.clickOnSaveButton();
    actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    String expectedDataSourceErrorMessage = propertyValueProvider.getPropertyValueByKey(
        "error.message.blankReferenceField");
    Assert.assertEquals(actualErrorMessage, expectedDataSourceErrorMessage, "Error: The error message is incorrect.");
    FBVisibilityConfigPage.selectReferenceFieldToAddRule("customer");
    Assert.assertTrue(FBAddEdit.getWebElement(FBVisibilityConfig.OPERATOR_DROPDOWN).isDisplayed(),
        "Error: Operator dropdown is " + "not displayed.");
    Assert.assertTrue(FBAddEdit.getWebElement(FBVisibilityConfig.COMPARISON_VALUE_INPUT_FIELD).isDisplayed(),
        "Error: Comparison " + "value input field");
    AltresGeneralUtil.clickOnSaveButton();
    actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    String expectedcompErrorMessage = propertyValueProvider.getPropertyValueByKey("error.message.blankComparisonValue");
    Assert.assertEquals(actualErrorMessage, expectedcompErrorMessage, "Error: The error message is incorrect.");
    FBVisibilityConfigPage.setComparisonValue(FakeDataGenerator.getRandomNumber(100));
    FBVisibilityConfigPage.setRuleName(FakeDataGenerator.getRandomFullName());
    AltresGeneralUtil.clickOnSaveButton();
    String actualRuleErrMessage = AltresGeneralUtil.getErrorMessage();
    String expectedRuleNameErrorMessage = propertyValueProvider.getPropertyValueByKey(
        "error.message.characterDSpaceInRuleName");
    Assert.assertEquals(actualRuleErrMessage, expectedRuleNameErrorMessage, "Error: The error message is incorrect.");
    FBVisibilityConfigPage.setRuleName(propertyValueProvider.getPropertyValueByKey("ruleName"));
    AltresGeneralUtil.clickOnSaveButton();
    String actualSuccessMessage = AltresGeneralUtil.getSuccessNotice();
    String expectedSuccessMessage = propertyValueProvider.getPropertyValueByKey("error.message.ruleSave");
    Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "Error: The success message is incorrect.");
    AltresGeneralUtil.clickOnButton(
        propertyValueProvider.getPropertyValueByKey("returnToVisibilityConfigurationButton"));
    addRule();
    FBVisibilityConfigPage.setRuleName(FakeDataGenerator.getRandomNumber(6));
    AltresGeneralUtil.clickOnSaveButton();
    AltresGeneralUtil.clickOnButton(
        propertyValueProvider.getPropertyValueByKey("returnToVisibilityConfigurationButton"));
    Assert.assertTrue(FBAddEdit.getWebElement(FBVisibilityConfig.RULE_COMPLEXITY_DROPDOWN).isDisplayed(),
        "Error: Rule Complexity dropdown is not displayed");
    FBVisibilityConfigPage.selectRuleComplexity("COMPLEX");
    AltresGeneralUtil.clickOnSaveButton();
    String expectedBlankRuleLogicErrorMessage = propertyValueProvider.getPropertyValueByKey(
        "error.message.blankRuleLogic");
    String actualBlankRuleLogicErrorMessage = FBVisibilityConfigPage.getMessage(expectedBlankRuleLogicErrorMessage);
    Assert.assertEquals(actualBlankRuleLogicErrorMessage, expectedBlankRuleLogicErrorMessage,
        "Error: The error message is incorrect.");
    FBVisibilityConfigPage.setRuleLogic(FakeDataGenerator.getRandomWord());
    AltresGeneralUtil.clickOnSaveButton();
    String expectedInvalidRuleLogicErrorMessage = propertyValueProvider.getPropertyValueByKey(
        "error.message.invalidRuleLogic");
    String actualInvalidRuleLogicErrorMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualInvalidRuleLogicErrorMessage, expectedInvalidRuleLogicErrorMessage,
        "Error: The error message is incorrect.");
    FBVisibilityConfigPage.selectRuleComplexity("AND");
    AltresGeneralUtil.clickOnSaveButton();
    String expectedVisibilityConfigSaveMessage = propertyValueProvider.getPropertyValueByKey(
        "error.message.saveVisibilityConfig");
    String actualVisibilityConfigSaveMessage = AltresGeneralUtil.getSuccessNotice();
    Assert.assertEquals(actualVisibilityConfigSaveMessage, expectedVisibilityConfigSaveMessage,
        "Error: The error message is incorrect.");
    Assert.assertTrue(FBAddEdit.getWebElement(FBVisibilityConfig.RULE_NAME_TABLE_HEADER).isDisplayed());
    Assert.assertTrue(FBAddEdit.getWebElement(FBVisibilityConfig.RULE_TABLE_HEADER).isDisplayed());
    Assert.assertTrue(FBAddEdit.getWebElement(FBVisibilityConfig.COPY_RULE_ICON).isDisplayed());
    Assert.assertTrue(FBAddEdit.getWebElement(FBVisibilityConfig.ADDED_RULE_NAME).isDisplayed());
    FBAddEdit.getWebElement(FBVisibilityConfig.ADDED_RULE_NAME).click();
    AltresGeneralUtil.clickOnDeleteButton();
    SeleniumWrapper.acceptAlert();
    String actualSuccessNotice = AltresGeneralUtil.getSuccessNotice();
    String expectedSuccessNotice = propertyValueProvider.getPropertyValueByKey("error.message.deleteRule");
    Assert.assertEquals(actualSuccessNotice, expectedSuccessNotice, "Error: The success message is incorrect. ");

  }

  /**
   * Adds a rule with a duplicate rule name in order to verify the error message about the duplicate rule name.
   */
  public void addRule() throws IOException {
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("addRuleButton"));
    FBVisibilityConfigPage.setRuleName(propertyValueProvider.getPropertyValueByKey("ruleName"));
    FBVisibilityConfigPage.selectDataSourceToAddRule("Reference Property");
    FBVisibilityConfigPage.selectReferenceFieldToAddRule("Customer Tier");
    FBVisibilityConfigPage.setComparisonValue("A");
    AltresGeneralUtil.clickOnSaveButton();
    String expectedDuplicateRuleErrorMessage = propertyValueProvider.getPropertyValueByKey(
        "error.message.duplicateRuleName");
    String actualDuplicateRuleErrorMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualDuplicateRuleErrorMessage, expectedDuplicateRuleErrorMessage,
        "Error: The error message is incorrect.");

  }
}
