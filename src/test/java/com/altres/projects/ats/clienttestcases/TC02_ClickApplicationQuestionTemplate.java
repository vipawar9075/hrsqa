package com.altres.projects.ats.clienttestcases;


import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.ats.pages.AtsSettingsPage;
import com.altres.util.SeleniumWrapper;

/**
 * Validate title of Application question templates page.
 */
public class TC02_ClickApplicationQuestionTemplate extends Base {

  @Test
  public void ATS2() {
    AtsSettingsPage.clickSettingsLink();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsSettingsPage.clickAppQuestionTemplate();
    SeleniumWrapper.switchToDefaultContent(driver);
    Assert.assertEquals(AtsSettingsPage.getAtsTemplatesMessagePageTitle(),
        "Applicant Tracking: Application Question Templates");
  }
}
