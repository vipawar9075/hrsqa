package com.altres.projects.formbuilder.admintestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBAddEditPage;
import com.altres.projects.formbuilder.pages.FBFormFields;
import com.altres.projects.formbuilder.pages.FBFormFieldsPage;
import com.altres.projects.formbuilder.pages.FBHome;
import com.altres.projects.formbuilder.pages.FBListing;
import com.altres.projects.formbuilder.pages.FBListingPage;
import com.altres.projects.formbuilder.pages.FBPdfMapping;
import com.altres.projects.formbuilder.pages.FBPdfMappingPage;
import com.altres.projects.formbuilder.pages.FBSharedFormOrder;
import com.altres.projects.formbuilder.pages.FBSharedFormOrderPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.SeleniumWrapper;

/**
 * Validates shared template order changes error messages having field dependencies, and the issue noted in ticket
 * HAD-23729.
 */
public class TC09_ValidateSharedFormOrderCanChange extends Base {

  public static String sharedTemplateOneName;
  public static String sharedTemplateTwoName;
  public static String defaultValueDataSourceValue;
  static ConfigPropertiesProviderImpl propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.FORMBUILDER_ELEMENT_PROPERTIES_PATH);

  /**
   * Validates the error message for the shared anchor form while deactivating it.
   */
  public static void validateErrorForSharedAnchorForm() throws IOException {
    FBAddEdit.getWebElement(FBSharedFormOrder.RETURN_TO_FORMS_BUTTON).click();
    FBListingPage.clickOnFilteredForm(sharedTemplateOneName);
    FBAddEdit.getWebElement(FBAddEdit.ACTIVE_CHECKBOX).click();
    AltresGeneralUtil.clickOnSaveButton();
    String expectedAnchorFormErrorMessage = propertyValueProvider.getPropertyValueByKey(
        "error.message.inactiveAnchorSharedForm");
    String actualAnchorFormErrorMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(expectedAnchorFormErrorMessage, actualAnchorFormErrorMessage,
        "Error: The error message is incorrect.");
  }

  @Test
  public void validateSharedFormOrderChange() throws IOException {
    SeleniumWrapper.webDriverWaitForVisibiltyOfElement(FBAddEdit.RETURN_BUTTON);
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormsButton"));
    FBAddEdit.getWebElement(FBListing.ADD_FORM_BUTTON).click();
    FBAddEditPage.uploadPdf(Constants.AUTHORIZATION_FOR_CONSUMER_REPORT_PDF);
    FBAddEditPage.createNewTemplateWithPDF(Constants.AUTHORIZATION_FOR_CONSUMER_REPORT_PDF,
        propertyValueProvider.getPropertyValueByKey("quickhireModule"));
    createSharedTemplateOne();
    createSharedTemplateTwo();
    FBAddEdit.getWebElement(FBAddEdit.RETURN_BUTTON).click();
    FBAddEdit.getWebElement(FBListing.SHARED_FORM_ORDER_BUTTON).click();
    FBSharedFormOrderPage.selectModuleFromDropdown(propertyValueProvider.getPropertyValueByKey("quickhireModule"));
    FBSharedFormOrderPage.dragAndDropSharedTemplate(sharedTemplateOneName, sharedTemplateTwoName);
    AltresGeneralUtil.clickOnSaveButton();
    String actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualErrorMessage,
        "Cannot place form " + sharedTemplateTwoName + " before form " + sharedTemplateOneName
            + " due to dependent fields.");
    FBSharedFormOrderPage.selectModuleFromDropdown(propertyValueProvider.getPropertyValueByKey("verifyI9Module"));
    Assert.assertEquals(SeleniumWrapper.findElements(driver, FBSharedFormOrder.TEMPLATE_ORDER_LIST).size(), 1);
    Assert.assertEquals(propertyValueProvider.getPropertyValueByKey("verifyI9Form"),
        SeleniumWrapper.findElements(driver, FBSharedFormOrder.TEMPLATE_ORDER_LIST).get(0).getText());
    validateErrorForSharedAnchorForm();
  }

  /**
   * Create a shared template having single input field.
   */
  public void createSharedTemplateOne() {
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    FBFormFieldsPage.createSingleInputField();
    FBAddEdit.getWebElement(FBFormFields.RETURN_TO_FORM_BUTTON).click();
    FBAddEdit.getWebElement(FBAddEdit.PDF_MAPPING_BUTTON).click();
    FBPdfMappingPage.mapFormFields("pdf-field-first_name", "question1");
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBPdfMapping.RETURN_TO_FORM_BUTTON).click();
    Assert.assertFalse(FBAddEdit.getWebElement(FBAddEdit.CUSTOMER_FORM_POSITION_TEXT).isDisplayed(),
        "Error: Customer Form Position is displayed.");
    FBAddEdit.getWebElement(FBAddEdit.ACTIVE_CHECKBOX).click();
    FBAddEdit.getWebElement(FBAddEdit.CUSTOMER_FORM_ANCHOR).click();
    Assert.assertTrue(FBAddEdit.getWebElement(FBAddEdit.CUSTOMER_FORM_POSITION_TEXT).isDisplayed(),
        "Error: Customer Form Position is not displayed.");
    FBAddEditPage.setElectronicSignature("signature");
    FBAddEditPage.setElectronicSignatureDateField("signature_date");
    AltresGeneralUtil.clickOnSaveButton();
    sharedTemplateOneName = FBAddEditPage.getTemplateName();
    defaultValueDataSourceValue = "Form:" + " " + sharedTemplateOneName;
    FBAddEdit.getWebElement(FBAddEdit.RETURN_BUTTON).click();
  }

  /**
   * Create a shared template with a single input field and the default data source as shared template one.
   *
   * @throws InterruptedException
   */
  public void createSharedTemplateTwo() throws IOException {
    FBAddEdit.getWebElement(FBHome.USERNAME_FIELD).click();
    FBAddEdit.getWebElement(FBListing.ADD_FORM_BUTTON).click();
    FBAddEditPage.uploadSupplementPdf(Constants.DENTAL_WAIVER_PDF_PATH);
    FBAddEdit.getWebElement(FBAddEdit.CUSTOMER_FORM_ANCHOR).click();
    FBAddEditPage.createNewTemplateWithPDF(Constants.AUTHORIZATION_FOR_CONSUMER_REPORT_PDF,
        propertyValueProvider.getPropertyValueByKey("quickhireModule"));
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    FBFormFieldsPage.createSingleInputField();
    FBAddEdit.getWebElement(FBFormFields.SINGLE_INPUT_TEXT).click();
    FBAddEdit.getWebElement(FBFormFields.SINGLE_INPUT_EDIT_LINK).click();
    FBFormFieldsPage.selectDefaultValueDataSource(defaultValueDataSourceValue);
    FBAddEdit.getWebElement(FBFormFields.OK_BUTTON).click();
    String actualReferenceFieldValidation = propertyValueProvider.getPropertyValueByKey(
        "error.message.blankReferenceField");
    String expectedReferenceFieldValidation = FBFormFieldsPage.getFBErrorMessage(actualReferenceFieldValidation);
    Assert.assertEquals(actualReferenceFieldValidation, expectedReferenceFieldValidation,
        "Error: The error message is incorrect.");
    FBFormFieldsPage.selectReferenceField("question1");
    FBAddEdit.getWebElement(FBFormFields.OK_BUTTON).click();
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBFormFields.RETURN_TO_FORM_BUTTON).click();
    FBAddEdit.getWebElement(FBAddEdit.PDF_MAPPING_BUTTON).click();
    FBPdfMappingPage.mapFormFields("pdf-field-first_name", "question1");
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBFormFields.RETURN_TO_FORM_BUTTON).click();
    FBAddEdit.getWebElement(FBAddEdit.ACTIVE_CHECKBOX).click();
    FBAddEditPage.setElectronicSignature("signature");
    FBAddEditPage.setElectronicSignatureDateField("signature_date");
    AltresGeneralUtil.clickOnSaveButton();
    sharedTemplateTwoName = FBAddEditPage.getTemplateName();
  }

}
