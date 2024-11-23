package com.altres.projects.ats.clienttestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.ats.pages.AtsAddEditQuestionsTemplatePage;
import com.altres.projects.ats.pages.AtsListingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Verifies change checker when single select and removed question for Education, Experience, Additional Questions
 * fields. Also verifies validation message when saved template with blank name.
 */
public class TC07_ValidateRemovingSingleQuestion extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateRemovingSingleQuestionTemplate() throws IOException {
    String checkboxActive = propertyValueProvider.getPropertyValueByKey("checkboxActive");
    String educationCheckbox = propertyValueProvider.getPropertyValueByKey("educationCheckbox");
    String experience = propertyValueProvider.getPropertyValueByKey("experience");
    String availableSelectedEducationQuestions = propertyValueProvider.getPropertyValueByKey(
        "availableSelectedEducationQuestions");
    String addSelectedEducationQuestions = propertyValueProvider.getPropertyValueByKey("addSelectedEducationQuestions");
    String selectedEducationQuestions = propertyValueProvider.getPropertyValueByKey("selectedEducationQuestions");
    String removeSelectedEducationQuestions = propertyValueProvider.getPropertyValueByKey(
        "removeSelectedEducationQuestions");
    String availableSelectedExperienceQuestions = propertyValueProvider.getPropertyValueByKey(
        "availableSelectedExperienceQuestions");
    String addSelectedExperienceQuestions = propertyValueProvider.getPropertyValueByKey(
        "addSelectedExperienceQuestions");
    String selectedExperienceQuestions = propertyValueProvider.getPropertyValueByKey("selectedExperienceQuestions");
    String removeSelectedExperienceQuestions = propertyValueProvider.getPropertyValueByKey(
        "removeSelectedExperienceQuestions");
    String availableSelectedAdditionalQuestions = propertyValueProvider.getPropertyValueByKey(
        "availableSelectedAdditionalQuestions");
    String addSelectedAdditionalQuestionsButton = propertyValueProvider.getPropertyValueByKey(
        "addSelectedAdditionalQuestionsButton");
    String selectedAdditionalQuestions = propertyValueProvider.getPropertyValueByKey("selectedAdditionalQuestions");
    String removeSelectedAdditionalQuestions = propertyValueProvider.getPropertyValueByKey(
        "removeSelectedAdditionalQuestions");
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsAddEditQuestionsTemplatePage.returnToQuestionTemplate();
    AtsListingPage.clickAddNewQuestionTemp();
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(checkboxActive);
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(educationCheckbox);
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(experience);
    AtsAddEditQuestionsTemplatePage.setQuestionByIndex(availableSelectedEducationQuestions, 0);
    AtsAddEditQuestionsTemplatePage.clickOnSingleSelectableButton(addSelectedEducationQuestions);
    AtsAddEditQuestionsTemplatePage.setQuestionByIndex(selectedEducationQuestions, 1);
    AtsAddEditQuestionsTemplatePage.clickOnSingleSelectableButton(removeSelectedEducationQuestions);
    AtsAddEditQuestionsTemplatePage.setQuestionByIndex(availableSelectedExperienceQuestions, 0);
    AtsAddEditQuestionsTemplatePage.clickOnSingleSelectableButton(addSelectedExperienceQuestions);
    AtsAddEditQuestionsTemplatePage.setQuestionByIndex(selectedExperienceQuestions, 1);
    AtsAddEditQuestionsTemplatePage.clickOnSingleSelectableButton(removeSelectedExperienceQuestions);
    AtsAddEditQuestionsTemplatePage.setQuestionByIndex(availableSelectedAdditionalQuestions, 0);
    AtsAddEditQuestionsTemplatePage.clickOnSingleSelectableButton(addSelectedAdditionalQuestionsButton);
    AtsAddEditQuestionsTemplatePage.setQuestionByIndex(selectedAdditionalQuestions, 0);
    AtsAddEditQuestionsTemplatePage.clickOnSingleSelectableButton(removeSelectedAdditionalQuestions);
    AltresGeneralUtil.clickOnSaveButton();
    String actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualErrorMessage, "Please enter a name for the template.",
        "Error: The error message is incorrect.");
    AtsAddEditQuestionsTemplatePage.returnToQuestionTemplate();
    SeleniumWrapper.acceptAlert();
  }

}

