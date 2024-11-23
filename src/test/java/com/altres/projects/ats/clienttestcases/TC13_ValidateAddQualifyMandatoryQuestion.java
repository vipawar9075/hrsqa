package com.altres.projects.ats.clienttestcases;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.projects.ats.pages.AtsAddEditPage;
import com.altres.projects.ats.pages.AtsAddEditQuestionPage;
import com.altres.projects.ats.pages.AtsAddEditQuestionsTemplatePage;
import com.altres.projects.ats.pages.AtsAddNewPositionPage;
import com.altres.projects.ats.pages.AtsAppQuestionsListingPage;
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
 * Create ATS Question with qualifying question. Create Question template by adding qualifying question. Create a
 * position with adding created question template. Then apply to created position and validate if additional question
 * screen throws any validation when clicks on next without filling answer of qualifying mandatory question.
 */

public class TC13_ValidateAddQualifyMandatoryQuestion extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateAddQualifyQuestion() throws IOException {
    String checkboxActive = propertyValueProvider.getPropertyValueByKey("checkboxActive");
    String applicationQuestions = propertyValueProvider.getPropertyValueByKey("applicationQuestions");
    String nextButtonId = propertyValueProvider.getPropertyValueByKey("nextButtonId");
    String nextButton = propertyValueProvider.getPropertyValueByKey("nextButton");
    String position = propertyValueProvider.getPropertyValueByKey("position");
    String activeCheckbox = propertyValueProvider.getPropertyValueByKey("activeCheckbox");
    String educationCheckbox = propertyValueProvider.getPropertyValueByKey("educationCheckbox");
    String experience = propertyValueProvider.getPropertyValueByKey("experience");
    String availableSelectedExperienceQuestions = propertyValueProvider.getPropertyValueByKey(
        "availableSelectedExperienceQuestions");
    String addSelectedExperienceQuestions = propertyValueProvider.getPropertyValueByKey(
        "addSelectedExperienceQuestions");
    String availableSelectedAdditionalQuestions = propertyValueProvider.getPropertyValueByKey(
        "availableSelectedAdditionalQuestions");
    String addSelectedAdditionalQuestionsButton = propertyValueProvider.getPropertyValueByKey(
        "addSelectedAdditionalQuestionsButton");
    String qualifyingQuestionCheckbox = propertyValueProvider.getPropertyValueByKey("qualifyingQuestionCheckbox");
    String selectQualifyingQuestionAction = propertyValueProvider.getPropertyValueByKey(
        "selectQualifyingQuestionAction");
    String selectQualifyingQuestionType = propertyValueProvider.getPropertyValueByKey("selectQualifyingQuestionType");
    String selectMandatoryForSubmission = propertyValueProvider.getPropertyValueByKey("selectMandatoryForSubmission");
    String radioButtonsHorizontal = propertyValueProvider.getPropertyValueByKey("RadioButtonsHorizontal");
    String qualifyingAnswerCheckbox = propertyValueProvider.getPropertyValueByKey("qualifyingAnswerCheckbox");
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
    AtsAddEditQuestionPage.clickOnCheckbox(qualifyingQuestionCheckbox);
    AtsAddEditQuestionPage.selectDropdown(selectQualifyingQuestionAction, selectMandatoryForSubmission);
    AtsAddEditQuestionPage.selectDropdown(selectQualifyingQuestionType, radioButtonsHorizontal);
    String answer = FakeDataGenerator.getRandomTitle();
    AtsAddEditQuestionPage.setQualifyAnswer(answer);
    AtsAddEditQuestionPage.clickOnCheckbox(qualifyingAnswerCheckbox);
    AltresGeneralUtil.clickOnSaveButton();
    Assert.assertTrue(AltresGeneralUtil.isSuccessMessageDisplayed(), "Error: Success message not displayed");
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsSettingsPage.clickSettingsLink();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsSettingsPage.clickAppQuestionTemplate();
    AtsListingPage.clickAddNewQuestionTemp();
    String generatedTemplateName = FakeDataGenerator.getQuestionTemplateName();
    AtsAddEditPage.setFullName(generatedTemplateName);
    AtsAddEditPage.clickActiveCheckBox();
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(educationCheckbox);
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(experience);
    List<String> questions = Arrays.asList("From/To", "Contact Employer and Reason", "Job Skills & Qualifications");
    AtsAddEditQuestionsTemplatePage.selectQuestionByName(availableSelectedExperienceQuestions, questions);
    AtsAddEditQuestionsTemplatePage.clickOnSingleSelectableButton(addSelectedExperienceQuestions);
    AtsAddEditQuestionsTemplatePage.selectQuestionByName(availableSelectedAdditionalQuestions,
        Collections.singletonList(title));
    AtsAddEditQuestionsTemplatePage.clickOnSingleSelectableButton(addSelectedAdditionalQuestionsButton);
    AltresGeneralUtil.clickOnSaveButton();
    Assert.assertTrue(AltresGeneralUtil.isSuccessMessageDisplayed(), "Error: Success message not displayed");
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsPositionListingPage.clickOnPosition();
    AtsPositionListingPage.clickOnAddNewPosition();
    AtsAddNewPositionPage.clickOnTab(applicationQuestions);
    AtsAddNewPositionPage.selectATSQuestionTemplate(generatedTemplateName);
    AtsAddNewPositionPage.clickOnTab(position);
    AtsAddNewPositionPage.setActiveInactive(activeCheckbox);
    String positionTitle = FakeDataGenerator.getRandomTitle();
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
    AtsAddNewPositionPage.clickOnNext(nextButton);
    AtsAddNewPositionPage.clickOnNext(nextButton);
    AtsAddNewPositionPage.clickOnNext(nextButton);
    AtsAddNewPositionPage.clickOnNext(nextButton);
    String expectedErrorMsg = "An answer is required for '" + question + "'.";
    String actualErrorMsg = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualErrorMsg, expectedErrorMsg, "Error: The error message is incorrect.");
    DriverManager.closeDriver();
    SeleniumWrapper.switchToWindowByIndex(0);
  }

}

