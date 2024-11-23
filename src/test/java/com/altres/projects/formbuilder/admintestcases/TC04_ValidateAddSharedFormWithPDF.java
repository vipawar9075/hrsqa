package com.altres.projects.formbuilder.admintestcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBAddEditPage;
import com.altres.projects.formbuilder.pages.FBHome;
import com.altres.projects.formbuilder.pages.FBListing;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.Constants;

/**
 * Validate the fields and success message are displayed on the forms detailed screen.
 */
public class TC04_ValidateAddSharedFormWithPDF extends Base {

  @Test
  public void templateWithPDF() {
    FBAddEdit.getWebElement(FBHome.USERNAME_FIELD).click();
    FBAddEdit.getWebElement(FBListing.ADD_FORM_BUTTON).click();
    FBAddEditPage.createNewTemplateWithPDF(Constants.DENTAL_WAIVER_PDF_PATH, "Quickhire");
    verifyTemplateCreation();
  }

  /**
   * Verifies the creation of a new template by checking the success message, electronic signature dropdown, and
   * signature date dropdown.
   */
  private void verifyTemplateCreation() {
    Assert.assertTrue(AltresGeneralUtil.isSuccessMessageDisplayed(), "Error: Success message not displayed");
    Assert.assertTrue(FBAddEdit.getWebElement(FBAddEdit.ELECTRONIC_SIGNATURE).isDisplayed(),
        "Error: Electronic signature dropdown not displayed");
    Assert.assertTrue(FBAddEdit.getWebElement(FBAddEdit.ELECTRONIC_SIGNATURE_DATE_FIELD).isDisplayed(),
        "Error: Signature date dropdown not displayed");
  }
}



