package com.altres.projects.careers.careersclienttestcases;


import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.careers.careersutil.CompanyContext;
import com.altres.projects.careers.carrerspages.CareersAddEditPage;
import com.altres.projects.careers.carrerspages.CareersListingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.FakeDataGenerator;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * validates if new company is created.
 */
public class TC03_04_CareersCreateNewCompany extends Base {

  private final PropertyValueProvider propertyValueProvider;
  String companyName;

  public TC03_04_CareersCreateNewCompany() {
    propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.CAREERS_ELEMENT_PROPERTIES_PATH);
  }

  /**
   * Creates a new company.
   *
   * @throws IOException
   */

  @Test(priority = 1)
  public void createNewCompany() throws IOException {
    companyName = FakeDataGenerator.getCompany();
    CareersListingPage.clickAddNewCompanyLink();
    CareersAddEditPage.clickCompanyTab();
    CareersAddEditPage.clickActiveCheckbox();
    SeleniumWrapper.sendKeyWithClear(driver, CareersAddEditPage.setCompanyName(), companyName);
    CompanyContext.setCompanyName(companyName);
    CareersAddEditPage.setIndustry(1);
    CareersAddEditPage.setPhoneNumber();
    CareersAddEditPage.enterAddress();
    CareersAddEditPage.enterCity();
    CareersAddEditPage.setPostalCode();
    int iframeIndex = 0;
    SeleniumWrapper.switchToIframeByIndex(driver, iframeIndex);
    CareersAddEditPage.setDescription();
    SeleniumWrapper.switchToParentFrame(driver);
    CareersAddEditPage.clickIndeedTab();
    CareersAddEditPage.setEmail();
    AltresGeneralUtil.clickOnSaveButton();
    SeleniumWrapper.acceptAlert();
    String expectedSuccessNotice = propertyValueProvider.getPropertyValueByKey("expected.message.successNotice");
    String actualSuccessNotice = AltresGeneralUtil.getSuccessNotice();
    Assert.assertEquals(actualSuccessNotice, expectedSuccessNotice, "Error: The message is incorrect.");
    CareersAddEditPage.clickReturnToCompaniesButton();
  }

  /**
   * Validates Careers page details screen.
   *
   * @throws IOException
   */
  @Test(priority = 2, dependsOnMethods = "createNewCompany")
  public void validateCareersPageDetailsScreen() throws IOException {
    CareersListingPage.selectPaginator("All");
    CareersListingPage.searchAndClickOnCompany(companyName);
    JavascriptExecutorWrapper.scrollToBottom(driver);
    boolean isAddCareerButtonDisplayed = CareersAddEditPage.isAddCareerButtonDisplayed();
    Assert.assertTrue(CareersAddEditPage.isAddCareerButtonDisplayed(), "Add Career button is not displayed");
    if (isAddCareerButtonDisplayed) {
      CareersAddEditPage.clickAddCareerButton();
    }
    CareersAddEditPage.setUrlName();
    CareersAddEditPage.selectPublishedRadioButton();
    CareersAddEditPage.selectDisplayHrsRadiButton();
    AltresGeneralUtil.clickOnSaveButton();
    String expectedSuccessMessage = propertyValueProvider.getPropertyValueByKey(
        "expected.message.careersPageSuccessNotice");
    String actualSuccessMessage = AltresGeneralUtil.getSuccessNotice();
    Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage, "Error: The message is incorrect.");
    CareersAddEditPage.clickReturnToCompanyButton();
    Assert.assertTrue(CareersAddEditPage.isCareerDetailsButtonDisplayed(),
        "'Career Details' button should be displayed.");
  }

}

