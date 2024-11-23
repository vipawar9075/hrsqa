package com.altres.projects.formbuilder.admintestcases;

import static com.altres.projects.formbuilder.admintestcases.TC09_ValidateSharedFormOrderCanChange.sharedTemplateOneName;
import static com.altres.projects.formbuilder.admintestcases.TC11_ValidateVisibilityCinfigurationFunctionality.propertyValueProvider;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBFormFields;
import com.altres.projects.formbuilder.pages.FBFormFieldsPage;
import com.altres.projects.formbuilder.pages.FBListingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.SeleniumWrapper;

/**
 * Validates the Dental Waiver form's warning message when its fields are referred in another form. Ticket HAD-23729.
 */
public class TC13_ValidateIssuingAuthorityFieldInVerifyI9 extends Base {

  @Test
  public static void validateIssuingAuthorityField() throws IOException {
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormsButton"));
    SeleniumWrapper.acceptAlert();
    FBListingPage.clickOnFilteredForm(sharedTemplateOneName);
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    FBAddEdit.getWebElement(FBFormFields.SINGLE_INPUT_TEXT).click();
    FBAddEdit.getWebElement(FBFormFields.SINGLE_INPUT_EDIT_LINK).click();
    FBFormFieldsPage.selectDefaultValueDataSource(
        propertyValueProvider.getPropertyValueByKey("dentalWaiverDataSource"));
    FBFormFieldsPage.selectReferenceField("companyName");
    FBAddEdit.getWebElement(FBFormFields.OK_BUTTON).click();
    AltresGeneralUtil.clickOnSaveButton();
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormButton"));
    AltresGeneralUtil.clickOnButton(propertyValueProvider.getPropertyValueByKey("returnToFormsButton"));
    FBListingPage.clickOnFilteredForm(propertyValueProvider.getPropertyValueByKey("dentalWaiverDataSource"));
    FBAddEdit.getWebElement(FBAddEdit.FORM_FIELDS_BUTTON).click();
    String expDentalMessage =
        propertyValueProvider.getPropertyValueByKey("error.message.dentalWaiverQuestionReferred") + " Reference Report";
    String actualDentalMessage = FBFormFieldsPage.getFBErrorMessage(
        propertyValueProvider.getPropertyValueByKey("error.message.dentalWaiverQuestionReferred"));
    Assert.assertEquals(actualDentalMessage, expDentalMessage, "Error: The error message is incorrect.");
  }

  /**
   * Tear down method to quit the WebDriver after all tests in this module have completed their execution.
   */
  @AfterClass(alwaysRun = true)
  public void tearDownClass() {
    DriverManager.quitDriver();
  }
}
