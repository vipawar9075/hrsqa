package com.altres.projects.ats.clienttestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.projects.ats.atsutil.NewTemplateCreation;
import com.altres.projects.ats.pages.AtsAddEditQuestionPage;
import com.altres.projects.ats.pages.AtsAddEditQuestionsTemplatePage;
import com.altres.projects.ats.pages.AtsAddNewPositionPage;
import com.altres.projects.ats.pages.AtsAppQuestionsListingPage;
import com.altres.projects.ats.pages.AtsApplicationsListingPage;
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
 * Create ATS Question.Validating Question is in Question template multiselect and application question tab is inside
 * Position. Also validate same when appear by default checkbox is checked while creating question then apply position
 * through url and validate the application from applicant on applications and click on save & hire. Logged admin must
 * be hiring manager.
 */
public class TC12_ValidateAppearByDefaultOnAllPosition extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);
  protected static String positionTitle = FakeDataGenerator.getRandomTitle();

  @Test
  public void validateAppearByDefaultOnAllPosition() throws IOException {
    String checkboxActive = propertyValueProvider.getPropertyValueByKey("checkboxActive");
    String singleLineTextField = propertyValueProvider.getPropertyValueByKey("singleLineTextField");
    String selectSetResponseReq = propertyValueProvider.getPropertyValueByKey("selectSetResponseReq");
    String applicationQuestions = propertyValueProvider.getPropertyValueByKey("applicationQuestions");
    String appearByDefaultCheckbox = propertyValueProvider.getPropertyValueByKey("appearByDefaultCheckbox");
    String nextButtonId = propertyValueProvider.getPropertyValueByKey("nextButtonId");
    String nextButton = propertyValueProvider.getPropertyValueByKey("nextButton");
    String addAllSelectedAdditionalQuestionsButton = propertyValueProvider.getPropertyValueByKey(
        "addAllSelectedAdditionalQuestionsButton");
    String atsQuestionMultiselectRemoveAllButton = propertyValueProvider.getPropertyValueByKey(
        "atsQuestionMultiselectRemoveAllButton");
    String position = propertyValueProvider.getPropertyValueByKey("position");
    String activeCheckbox = propertyValueProvider.getPropertyValueByKey("activeCheckbox");
    String saveAndHireButton = propertyValueProvider.getPropertyValueByKey("saveAndHireButton");
    String addNewHireTitle = propertyValueProvider.getPropertyValueByKey("addNewHireTitle");
    SeleniumWrapper.refreshBrowser(driver);
    AtsSettingsPage.clickSettingsLink();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsSettingsPage.clickOnAppQuestions();
    AtsAppQuestionsListingPage.clickOnAddNewQuestion();
    String title = FakeDataGenerator.getRandomTitle();
    String question = FakeDataGenerator.getDescription();
    AtsAddEditQuestionPage.setTitle(title);
    AtsAddEditQuestionPage.setQuestion(question);
    AtsAddEditQuestionPage.clickOnCheckbox(checkboxActive);
    AtsAddEditQuestionPage.selectInputType(singleLineTextField);
    AtsAddEditQuestionPage.selectResponseRequirement(selectSetResponseReq);
    AltresGeneralUtil.clickOnSaveButton();
    Assert.assertTrue(AltresGeneralUtil.isSuccessMessageDisplayed(), "Error: Success message not displayed");
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsSettingsPage.clickSettingsLink();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsSettingsPage.clickAppQuestionTemplate();
    String createdTemplateName = NewTemplateCreation.createNewTemplate();
    AtsListingPage.setTemplateName(createdTemplateName);
    SeleniumWrapper.click(driver, AtsListingPage.getFilterButtonLocator());
    AtsListingPage.clickATSTemplateRecord(1);
    AtsAddEditQuestionsTemplatePage.clickOnMultiSelectableButton(addAllSelectedAdditionalQuestionsButton);
    SeleniumWrapper.acceptAlert();
    AltresGeneralUtil.clickOnSaveButton();
    Assert.assertTrue(AtsAddEditQuestionsTemplatePage.getAdditionalSelectedQuestions().contains(title));
    Assert.assertTrue(AtsAddEditQuestionsTemplatePage.getAdditionalQuestionsOrder().contains(question));
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsPositionListingPage.clickOnPosition();
    AtsPositionListingPage.clickOnAddNewPosition();
    AtsAddNewPositionPage.clickOnTab(applicationQuestions);
    AtsAddNewPositionPage.selectATSQuestionTemplate(createdTemplateName);
    Assert.assertTrue(AtsAddEditQuestionPage.getAdditionalSelectedQuestions().contains(title));
    Assert.assertTrue(AtsAddEditQuestionPage.getAdditionalQuestionsOrder().contains(question));
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsSettingsPage.clickSettingsLink();
    SeleniumWrapper.acceptAlert();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsSettingsPage.clickOnAppQuestions();
    AtsAppQuestionsListingPage.setTitle(title);
    AltresGeneralUtil.clickOnFilterButton();
    AtsAppQuestionsListingPage.clickATSQuestionsRecord(1);
    AtsAddEditQuestionPage.clickOnCheckbox(appearByDefaultCheckbox);
    AltresGeneralUtil.clickOnSaveButton();
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsSettingsPage.clickSettingsLink();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsSettingsPage.clickAppQuestionTemplate();
    AtsListingPage.setTemplateName(createdTemplateName);
    SeleniumWrapper.click(driver, AtsListingPage.getFilterButtonLocator());
    AtsListingPage.clickATSTemplateRecord(1);
    Assert.assertFalse(AtsAddEditQuestionsTemplatePage.getAdditionalSelectedQuestions().contains(title));
    Assert.assertTrue(AtsAddEditQuestionsTemplatePage.getAdditionalQuestionsOrder().contains(question));
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsPositionListingPage.clickOnPosition();
    AtsPositionListingPage.clickOnAddNewPosition();
    AtsAddNewPositionPage.clickOnTab(applicationQuestions);
    AtsAddNewPositionPage.selectATSQuestionTemplate(createdTemplateName);
    Assert.assertFalse(AtsAddEditQuestionPage.getAdditionalSelectedQuestions().contains(title));
    Assert.assertTrue(AtsAddEditQuestionPage.getAdditionalQuestionsOrder().contains(question));
    AtsAddEditQuestionsTemplatePage.clickOnMultiSelectableButton(atsQuestionMultiselectRemoveAllButton);
    SeleniumWrapper.acceptAlert();
    AtsAddNewPositionPage.clickOnTab(position);
    AtsAddNewPositionPage.setActiveInactive(activeCheckbox);
    AtsAddNewPositionPage.setTitle(positionTitle);
    AltresGeneralUtil.clickOnSaveButton();
    AtsAddNewPositionPage.clickOnApplyUrl();
    SeleniumWrapper.switchToWindowByIndex(1);
    String email = FakeDataGenerator.getEmail();
    AtsAddNewPositionPage.setEmail(email);
    AtsAddNewPositionPage.clickOnNext(nextButtonId);
    String firstName = FakeDataGenerator.getRandomFirstName();
    String lastName = FakeDataGenerator.getRandomLastName();
    String phoneNumber = FakeDataGenerator.getRandomNumber(10);
    AtsAddNewPositionPage.setContactInfo(firstName, lastName, phoneNumber);
    for (int i = 0; i < 5; i++) {
      AtsAddNewPositionPage.clickOnNext(nextButton);
    }
    AtsAddNewPositionPage.clickOnAgree();
    DriverManager.closeDriver();
    SeleniumWrapper.switchToWindowByIndex(0);
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsApplicationsListingPage.clickOnApplications();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsApplicationsListingPage.setNameOrEmail(email);
    AltresGeneralUtil.clickOnFilterButton();
    AtsApplicationsListingPage.clickAtsApplicationsRecord(1);
    AltresGeneralUtil.clickOnButton(saveAndHireButton);
    SeleniumWrapper.switchToDefaultContent(driver);
    Assert.assertEquals(AltresGeneralUtil.getPageTitle(), addNewHireTitle);
  }

}

