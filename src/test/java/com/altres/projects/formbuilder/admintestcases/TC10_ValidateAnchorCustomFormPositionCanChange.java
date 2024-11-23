package com.altres.projects.formbuilder.admintestcases;

import static com.altres.projects.formbuilder.admintestcases.TC09_ValidateSharedFormOrderCanChange.sharedTemplateOneName;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBAddEditPage;
import com.altres.projects.formbuilder.pages.FBFormFields;
import com.altres.projects.formbuilder.pages.FBFormFieldsPage;
import com.altres.projects.formbuilder.pages.FBHome;
import com.altres.projects.formbuilder.pages.FBListing;
import com.altres.projects.formbuilder.pages.FBListingPage;
import com.altres.projects.formbuilder.pages.FBPdfMappingPage;
import com.altres.projects.formbuilder.pages.FBVisibilityConfigPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.ConfigurationManager;
import com.altres.util.Constants;
import com.altres.util.FakeDataGenerator;
import com.altres.util.SeleniumWrapper;

/**
 * Validates the shared form in the position dropdown when its changed to a custom form, as well as the validation
 * message for the custom form's position, which is dependent on the shared form.
 */
public class TC10_ValidateAnchorCustomFormPositionCanChange extends Base {

  static String sharedTemplateName;
  static String customTemplateName;
  ConfigPropertiesProviderImpl propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.FORMBUILDER_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateCustomFormPositionChangeValidation()
      throws ConfigurationNotLoadedException, IOException, InterruptedException {
    navigateToURL("adminurl");
    driver.navigate().refresh();
    FBAddEdit.getWebElement(FBHome.USERNAME_FIELD).click();
    FBAddEdit.getWebElement(FBListing.ADD_FORM_BUTTON).click();
    sharedFormWithAfterEmpPosition();
    FBAddEdit.getWebElement(FBListing.ADD_FORM_BUTTON).click();
    customerForm();
    FBAddEdit.getWebElement(FBAddEdit.RETURN_BUTTON).click();
    FBListingPage.clickOnFilteredForm(sharedTemplateOneName);
    FBAddEditPage.selectCustomerFormPosition("before");
    FBAddEdit.getWebElement(FBAddEdit.CUSTOMER_FORM_ANCHOR).click();
    AltresGeneralUtil.clickOnSaveButton();
    String expectedCustomAnchorFormErrorMessage = propertyValueProvider.getPropertyValueByKey(
        "error.message.anchorCustomFormPositionChange");
    String actualCustomAnchorFormErrorMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(expectedCustomAnchorFormErrorMessage, actualCustomAnchorFormErrorMessage,
        "Error: The error message is incorrect.");
    FBAddEdit.getWebElement(FBAddEdit.RETURN_BUTTON).click();
    FBListingPage.clickOnFilteredForm(customTemplateName);
    Assert.assertTrue(FBAddEditPage.isSharedTemplateDisplayedInPositionDropdown(sharedTemplateOneName));
    FBAddEdit.getWebElement(FBAddEdit.RETURN_BUTTON).click();
    FBListingPage.clickOnFilteredForm(sharedTemplateOneName);
    FBAddEdit.getWebElement(FBAddEdit.CUSTOMER_RADIO_BUTTON).click();
    FBAddEditPage.setCustomerNumber(ConfigurationManager.getProperty("company_name"));
    AltresGeneralUtil.clickOnSaveButton();
    Thread.sleep(2000);
    Assert.assertTrue(FBAddEdit.getWebElement(FBAddEdit.POSITION_DROPDOWN).isDisplayed(),
        "Error: Position dropdown is not displayed.");
    Assert.assertFalse(FBAddEdit.getWebElement(FBAddEdit.CUSTOMER_FORM_ANCHOR).isDisplayed(),
        "Customer Form Anchor checkbox is displayed.");
    Thread.sleep(5000);
    Assert.assertFalse(FBAddEditPage.isSharedTemplateDisplayedInPositionDropdown(sharedTemplateOneName),
        "Error: Shared template is displayed in position dropdown.");
    FBAddEdit.getWebElement(FBAddEdit.RETURN_BUTTON).click();
    SeleniumWrapper.acceptAlert();
    FBListingPage.clickOnFilteredForm(sharedTemplateOneName);
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    String expectedMessage = propertyValueProvider.getPropertyValueByKey("error.message.questionReferred");
    String actualMessage = FBFormFieldsPage.getFBErrorMessage(expectedMessage);
    Assert.assertEquals(actualMessage, expectedMessage + " Download Reference Report",
        "Error: The error message is incorrect.");
  }

  /**
   * Creates the shared template with the single input field mapped and the rule in the visibility configuration.
   */
  public void sharedFormWithAfterEmpPosition() throws IOException {
    FBAddEditPage.selectModuleFromDropdown(propertyValueProvider.getPropertyValueByKey("quickhireModule"));
    sharedTemplateName = FakeDataGenerator.getRandomTitle();
    FBAddEditPage.setFirstName(sharedTemplateName);
    FBAddEditPage.setDisplayName(FakeDataGenerator.getRandomFirstName());
    FBAddEdit.getWebElement(FBAddEdit.CUSTOMER_FORM_ANCHOR).click();
    FBAddEditPage.selectCustomerFormPosition("after");
    FBAddEditPage.uploadPdf(Constants.AUTHORIZATION_FOR_CONSUMER_REPORT_PDF);
    FBAddEditPage.uploadSupplementPdf(Constants.DENTAL_WAIVER_PDF_PATH);
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    FBFormFieldsPage.createSingleInputField();
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormButton"));
    FBAddEdit.getWebElement(FBAddEdit.PDF_MAPPING_BUTTON).click();
    FBPdfMappingPage.mapFormFields("pdf-field-first_name", "question1");
    AltresGeneralUtil.clickOnSaveButton();
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormButton"));
    FBAddEdit.getWebElement(FBAddEdit.ACTIVE_CHECKBOX).click();
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBAddEdit.VISIBILITY_CONFIGURATION_BUTTON).click();
    FBAddEdit.getWebElement(FBAddEdit.ADD_RULE_BUTTON).click();
    FBAddEditPage.setRuleName(propertyValueProvider.getPropertyValueByKey("ruleOne"));
    FBVisibilityConfigPage.selectDataSourceToAddRule("Reference Property");
    FBAddEditPage.selectReferencePropertyToAddRule("Customer Number");
    FBAddEditPage.setComparisonValueToAddRule(
        propertyValueProvider.getPropertyValueByKey("customerNumberAdvantageCompanyOne"));
    AltresGeneralUtil.clickOnSaveButton();
    AltresGeneralUtil.clickOnButton(
        propertyValueProvider.getPropertyValueByKey("returnToVisibilityConfigurationButton"));
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormButton"));
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormsButton"));
  }

  /**
   * Creates a customer form with a single input field, using the default data source as a shared template.
   */
  public void customerForm() throws IOException, ConfigurationNotLoadedException {
    FBAddEditPage.selectModuleFromDropdown(propertyValueProvider.getPropertyValueByKey("quickhireModule"));
    FBAddEdit.getWebElement(FBAddEdit.CUSTOMER_RADIO_BUTTON).click();
    FBAddEditPage.setCustomerNumber(ConfigurationManager.getProperty("company_name"));
    customTemplateName = FakeDataGenerator.getRandomTitle();
    FBAddEditPage.setFirstName(customTemplateName);
    FBAddEditPage.setDisplayName(FakeDataGenerator.getRandomFirstName());
    FBAddEditPage.selectPositionOfForm(sharedTemplateOneName);
    FBAddEditPage.uploadPdf(Constants.AUTHORIZATION_FOR_CONSUMER_REPORT_PDF);
    FBAddEditPage.uploadSupplementPdf(Constants.DENTAL_WAIVER_PDF_PATH);
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    FBFormFieldsPage.createSingleInputField();
    FBAddEdit.getWebElement(FBFormFields.SINGLE_INPUT_TEXT).click();
    FBAddEdit.getWebElement(FBFormFields.SINGLE_INPUT_EDIT_LINK).click();
    FBFormFieldsPage.selectDefaultValueDataSource(sharedTemplateName);
    FBFormFieldsPage.selectReferenceField("question1");
    FBAddEdit.getWebElement(FBFormFields.OK_BUTTON).click();
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBFormFields.RETURN_TO_FORM_BUTTON).click();
  }

}
