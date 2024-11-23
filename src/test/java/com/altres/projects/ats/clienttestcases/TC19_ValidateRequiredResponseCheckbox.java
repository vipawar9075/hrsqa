package com.altres.projects.ats.clienttestcases;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.ats.atsutil.NewTemplateCreation;
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
 * Validates question is reflecting in position screen. Also verified Educational questions grid after checking the
 * Education checkbox. HAD-8442, HAD-8441 and HAD-8424 covered these JIRA issues.
 */
public class TC19_ValidateRequiredResponseCheckbox extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateRequiredResponseCheckbox() throws IOException {
    String applicationQuestions = propertyValueProvider.getPropertyValueByKey("applicationQuestions");
    SeleniumWrapper.refreshBrowser(driver);
    AtsSettingsPage.clickSettingsLink();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsSettingsPage.clickAppQuestionTemplate();
    String createdTemplateName = NewTemplateCreation.createNewTemplate();
    AtsListingPage.setTemplateName(createdTemplateName);
    SeleniumWrapper.click(driver, AtsListingPage.getFilterButtonLocator());
    AtsListingPage.clickATSTemplateRecord(1);
    List<String> selectedEducationQuestions = AtsAddEditQuestionsTemplatePage.getEducationSelectedQuestions();
    selectedEducationQuestions.remove(0);
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(propertyValueProvider.getPropertyValueByKey("educationCheckbox"));
    AltresGeneralUtil.clickOnSaveButton();
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(propertyValueProvider.getPropertyValueByKey("educationCheckbox"));
    List<String> availableEducationQuestions = AtsAddEditQuestionsTemplatePage.getEducationQuestionsAvailable();
    boolean isBothListQuestionsEqual = selectedEducationQuestions.equals(availableEducationQuestions);
    Assert.assertTrue(isBothListQuestionsEqual);
    AtsAddEditQuestionsTemplatePage.clickOnFirstRequireResponseCheckbox();
    String firstQuestion = AtsAddEditQuestionsTemplatePage.getAdditionalQuestionsOrder().get(0);
    AltresGeneralUtil.clickOnSaveButton();
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsPositionListingPage.clickOnPosition();
    AtsPositionListingPage.clickOnAddNewPosition();
    AtsAddNewPositionPage.setActiveInactive(propertyValueProvider.getPropertyValueByKey("activeCheckbox"));
    String positionTitle = FakeDataGenerator.getRandomTitle();
    AtsAddNewPositionPage.setTitle(positionTitle);
    AltresGeneralUtil.clickOnSaveButton();
    AtsAddNewPositionPage.clickOnTab(applicationQuestions);
    AtsAddNewPositionPage.selectATSQuestionTemplate(createdTemplateName);
    Assert.assertTrue(AtsAddNewPositionPage.getAdditionalQuestionsOrder().contains(firstQuestion));
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(propertyValueProvider.getPropertyValueByKey("experienceCheckbox"));
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(propertyValueProvider.getPropertyValueByKey("experienceCheckbox"));
    Assert.assertTrue(AtsAddNewPositionPage.isExperienceSelectedGridDisplayed());
  }
}

