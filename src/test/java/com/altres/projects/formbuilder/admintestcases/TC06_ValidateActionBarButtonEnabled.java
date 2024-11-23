package com.altres.projects.formbuilder.admintestcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.formbuilder.pages.FBAddEdit;
import com.altres.projects.formbuilder.pages.FBAddEditPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.FakeDataGenerator;

/**
 * Validates the Preview Form, PDF Mapping button enable and PDF Document field visible for No PDF (Data Collection
 * Only),Generate PDF.
 */
public class TC06_ValidateActionBarButtonEnabled extends Base {

  @Test
  public void buttonEnable() {
    checkPreviewFormEnabledNoPdf();
    checkPreviewFormEnableGeneratePDF();
  }

  /**
   * Validates the Preview Form, PDF Mapping buttons enabled when Form PDF is selected as No PDF (Data Collection
   * Only).
   */
  public void checkPreviewFormEnabledNoPdf() {
    FBAddEditPage.selectFormPDFFromDropdown("No PDF (Data Collection Only)");
    Assert.assertFalse(FBAddEdit.getWebElement(FBAddEdit.UPLOAD_BUTTON).isDisplayed(),
        "PDF Document field is displayed for No PDF (Data Collection Only).");
    FBAddEditPage.setDisplayName(FakeDataGenerator.getRandomFullName());
    AltresGeneralUtil.clickOnSaveButton();
    AltresGeneralUtil.isSuccessMessageDisplayed();
    Assert.assertFalse(FBAddEdit.getWebElement(FBAddEdit.PREVIEW_FORM_BUTTON).isEnabled());
    Assert.assertFalse(FBAddEdit.getWebElement(FBAddEdit.PDF_MAPPING_BUTTON).isEnabled());
  }

  /**
   * Validates the Preview Form, PDF Mapping buttons enabled when Form PDF is selected as Generate PDF.
   */
  public void checkPreviewFormEnableGeneratePDF() {
    FBAddEditPage.selectFormPDFFromDropdown("Generate PDF");
    Assert.assertFalse(FBAddEdit.getWebElement(FBAddEdit.UPLOAD_BUTTON).isDisplayed(),
        "PDF Document field is displayed for Generate PDF.");
    AltresGeneralUtil.clickOnSaveButton();
    Assert.assertFalse(FBAddEdit.getWebElement(FBAddEdit.PREVIEW_FORM_BUTTON).isEnabled());
    Assert.assertFalse(FBAddEdit.getWebElement(FBAddEdit.PDF_MAPPING_BUTTON).isEnabled());
  }
}
