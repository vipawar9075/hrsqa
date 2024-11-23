package com.altres.projects.formbuilder.admintestcases;

import static com.altres.projects.formbuilder.admintestcases.TC09_ValidateSharedFormOrderCanChange.sharedTemplateOneName;
import static com.altres.projects.formbuilder.admintestcases.TC10_ValidateAnchorCustomFormPositionCanChange.customTemplateName;
import static com.altres.projects.formbuilder.admintestcases.TC11_ValidateVisibilityCinfigurationFunctionality.propertyValueProvider;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBAddEditPage;
import com.altres.projects.formbuilder.pages.FBFormFields;
import com.altres.projects.formbuilder.pages.FBFormFieldsPage;
import com.altres.projects.formbuilder.pages.FBListingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.FakeDataGenerator;
import com.altres.util.SeleniumWrapper;

/**
 * Validates the inactive shared template's error message.
 */
public class TC12_ValidateInactiveSharedTemplate extends Base {

  @Test
  public void validateInactiveSharedTemplate() throws IOException {
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormButton"));
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormsButton"));
    FBListingPage.clickOnFilteredForm(sharedTemplateOneName);
    FBAddEditPage.selectFormPDFFromDropdown("No PDF (Data Collection Only)");
    AltresGeneralUtil.clickOnButton("after");
    AltresGeneralUtil.clickOnSaveButton();
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormsButton"));
    FBListingPage.clickOnFilteredForm(customTemplateName);
    FBAddEditPage.selectPositionOfForm(sharedTemplateOneName);
    FBAddEditPage.selectFormPDFFromDropdown("No PDF (Data Collection Only)");
    AltresGeneralUtil.clickOnSaveButton();
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    FBAddEdit.getWebElement(FBFormFields.SINGLE_INPUT_TEXT).click();
    FBAddEdit.getWebElement(FBFormFields.SINGLE_INPUT_EDIT_LINK).click();
    FBFormFieldsPage.selectDefaultValueDataSource(sharedTemplateOneName);
    FBFormFieldsPage.selectReferenceField("question1");
    FBAddEdit.getWebElement(FBFormFields.OK_BUTTON).click();
    AltresGeneralUtil.clickOnSaveButton();
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormButton"));
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormsButton"));
    FBListingPage.clickOnFilteredForm(sharedTemplateOneName);
    FBAddEdit.getWebElement(FBAddEdit.ACTIVE_CHECKBOX).click();
    AltresGeneralUtil.clickOnSaveButton();
    String actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    String expectedErrorMessage = propertyValueProvider.getPropertyValueByKey(
        "error.message.inactiveAnchorForCustomForms");
    Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error: The error message is incorrect.");
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormsButton"));
    FBListingPage.clickOnFilteredForm(sharedTemplateOneName);
    AltresGeneralUtil.clickOnButton("before");
    AltresGeneralUtil.clickOnSaveButton();
    actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    String expErrMessage = propertyValueProvider.getPropertyValueByKey("error.message.anchorShareFormPositionChange");
    Assert.assertEquals(actualErrorMessage, expErrMessage, "Error: The error message is incorrect.");
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormsButton"));
    FBListingPage.clickOnFilteredForm(customTemplateName);
    SeleniumWrapper.sendKeyWithClear(driver, FBAddEdit.getWebElement(FBAddEdit.CUSTOMER_INPUT_FIELD),
        FakeDataGenerator.getRandomNumber(6));
    AltresGeneralUtil.clickOnSaveButton();
    actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    String invalidCustomerErrorMessage = propertyValueProvider.getPropertyValueByKey("error.message.invalidCustomer");
    Assert.assertEquals(actualErrorMessage, invalidCustomerErrorMessage, "Error: The error message is incorrect.");
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormsButton"));
    SeleniumWrapper.acceptAlert();
    FBListingPage.clickOnFilteredForm(customTemplateName);
    FBAddEditPage.selectPositionOfForm("");
    AltresGeneralUtil.clickOnSaveButton();
    actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    String expectedBlankPositionErrorMessage = propertyValueProvider.getPropertyValueByKey(
        "error.message.blankPosition");
    Assert.assertEquals(actualErrorMessage, expectedBlankPositionErrorMessage,
        "Error: The error message is incorrect.");


  }
}
