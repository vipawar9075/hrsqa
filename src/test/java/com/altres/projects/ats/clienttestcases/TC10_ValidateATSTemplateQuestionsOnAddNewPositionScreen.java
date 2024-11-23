package com.altres.projects.ats.clienttestcases;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.ats.atsutil.NewTemplateCreation;
import com.altres.projects.ats.pages.AtsAddEditPage;
import com.altres.projects.ats.pages.AtsAddEditQuestionsTemplatePage;
import com.altres.projects.ats.pages.AtsAddNewPositionPage;
import com.altres.projects.ats.pages.AtsListingPage;
import com.altres.projects.ats.pages.AtsPositionListingPage;
import com.altres.projects.ats.pages.AtsSettingsPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.FakeDataGenerator;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Validate Edit Icon Functionality in Application Questions tab, validate change checker on Add New Position Screen.
 * Also Validate ATS Questions Template Questions matches with on Add new position screen.
 */
public class TC10_ValidateATSTemplateQuestionsOnAddNewPositionScreen extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateEditIconFunctionalityAndChangeChecker() throws IOException {
    String returnToPositionListButton = propertyValueProvider.getPropertyValueByKey("returnToPositionListButton");
    String applicationQuestions = propertyValueProvider.getPropertyValueByKey("applicationQuestions");
    String returnToPositionButton = propertyValueProvider.getPropertyValueByKey("returnToPositionButton");
    String atsQuestionTemplatePageTitle = propertyValueProvider.getPropertyValueByKey("atsQuestionTemplatePageTitle");
    SeleniumWrapper.refreshBrowser(driver);
    AtsSettingsPage.clickSettingsLink();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsSettingsPage.clickAppQuestionTemplate();
    String createdTemplateName = NewTemplateCreation.createNewTemplate();
    SeleniumWrapper.sendKeyWithClear(driver, AtsAddEditPage.enterName(), createdTemplateName);
    SeleniumWrapper.click(driver, AtsListingPage.getFilterButtonLocator());
    AtsListingPage.clickATSTemplateRecord(1);
    List<String> expectedEducationSelectedQuestions = AtsAddEditQuestionsTemplatePage.getEducationSelectedQuestions();
    List<String> expectedExperienceSelectedQuestions = AtsAddEditQuestionsTemplatePage.getExperienceSelectedQuestions();
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsPositionListingPage.clickOnPosition();
    AtsPositionListingPage.clickOnAddNewPosition();
    String positionTitle = FakeDataGenerator.getRandomTitle();
    AtsAddNewPositionPage.setTitle(positionTitle);
    AltresGeneralUtil.clickOnButton(returnToPositionListButton);
    SeleniumWrapper.dismissAlert();
    AltresGeneralUtil.clickOnButton(returnToPositionListButton);
    SeleniumWrapper.acceptAlert();
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsPositionListingPage.clickOnPosition();
    AtsPositionListingPage.clickOnAddNewPosition();
    AtsAddNewPositionPage.clickOnTab(applicationQuestions);
    AtsAddNewPositionPage.clickOnEditIcon();
    SeleniumWrapper.switchToDefaultContent(driver);
    Assert.assertEquals(AtsAddEditQuestionsTemplatePage.getAddEditQuestionsTemplatePageTitle(),
        atsQuestionTemplatePageTitle);
    SeleniumWrapper.switchToInteriorContent(driver);
    AltresGeneralUtil.clickOnButton(returnToPositionButton);
    AtsAddNewPositionPage.selectATSQuestionTemplate(createdTemplateName);
    Assert.assertTrue(AtsAddNewPositionPage.validateEducationSelectedQuestions(expectedEducationSelectedQuestions));
    Assert.assertTrue(AtsAddNewPositionPage.validateExperienceSelectedQuestions(expectedExperienceSelectedQuestions));
  }
}

