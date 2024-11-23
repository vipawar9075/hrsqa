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
import com.altres.util.FakeDataGenerator;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Verifies change checker when multiselect and removed questions for Education, Experience, Additional Questions
 * fields. Also validates title of page before saving and after.
 */
public class TC06_ValidateAddRemoveQuestionsInTemplate extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateAddRemoveQuestionsTemplate() throws IOException {
    String checkboxActive = propertyValueProvider.getPropertyValueByKey("checkboxActive");
    String educationCheckbox = propertyValueProvider.getPropertyValueByKey("educationCheckbox");
    String experience = propertyValueProvider.getPropertyValueByKey("experience");
    String addAllSelectedEducationQuestionsButton = propertyValueProvider.getPropertyValueByKey(
        "addAllSelectedEducationQuestionsButton");
    String addAllSelectedExperienceQuestionsButton = propertyValueProvider.getPropertyValueByKey(
        "addAllSelectedExperienceQuestionsButton");
    String addAllSelectedAdditionalQuestionsButton = propertyValueProvider.getPropertyValueByKey(
        "addAllSelectedAdditionalQuestionsButton");
    String removeAllSelectedEducationQuestionsButton = propertyValueProvider.getPropertyValueByKey(
        "removeAllSelectedEducationQuestionsButton");
    String removeAllSelectedExperienceQuestionsButton = propertyValueProvider.getPropertyValueByKey(
        "removeAllSelectedExperienceQuestionsButton");
    String removeAllSelectedAdditionalQuestionsButton = propertyValueProvider.getPropertyValueByKey(
        "removeAllSelectedAdditionalQuestionsButton");
    String addQuestionTemplatePageTitle = propertyValueProvider.getPropertyValueByKey("addQuestionTemplatePageTitle");
    String editQuestionTemplatePageTitle = propertyValueProvider.getPropertyValueByKey("editQuestionTemplatePageTitle");
    SeleniumWrapper.click(driver, AtsListingPage.getResetButtonLocator());
    AtsListingPage.clickAddNewQuestionTemp();
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(checkboxActive);
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(educationCheckbox);
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(experience);
    AtsAddEditQuestionsTemplatePage.setTemplateName(FakeDataGenerator.getRandomFirstName());
    AtsAddEditQuestionsTemplatePage.clickOnMultiSelectableButton(addAllSelectedEducationQuestionsButton);
    SeleniumWrapper.acceptAlert();
    AtsAddEditQuestionsTemplatePage.clickOnMultiSelectableButton(addAllSelectedExperienceQuestionsButton);
    SeleniumWrapper.acceptAlert();
    AtsAddEditQuestionsTemplatePage.clickOnMultiSelectableButton(addAllSelectedAdditionalQuestionsButton);
    SeleniumWrapper.acceptAlert();
    AtsAddEditQuestionsTemplatePage.clickOnMultiSelectableButton(removeAllSelectedEducationQuestionsButton);
    SeleniumWrapper.acceptAlert();
    AtsAddEditQuestionsTemplatePage.clickOnMultiSelectableButton(removeAllSelectedExperienceQuestionsButton);
    SeleniumWrapper.acceptAlert();
    AtsAddEditQuestionsTemplatePage.clickOnMultiSelectableButton(removeAllSelectedAdditionalQuestionsButton);
    SeleniumWrapper.acceptAlert();
    SeleniumWrapper.switchToDefaultContent(driver);
    Assert.assertEquals(AtsAddEditQuestionsTemplatePage.getAddEditQuestionsTemplatePageTitle(),
        addQuestionTemplatePageTitle, "Error: The title does not match.");
    SeleniumWrapper.switchToInteriorContent(driver);
    AltresGeneralUtil.clickOnSaveButton();
    SeleniumWrapper.switchToDefaultContent(driver);
    Assert.assertEquals(AtsAddEditQuestionsTemplatePage.getAddEditQuestionsTemplatePageTitle(),
        editQuestionTemplatePageTitle, "Error: The title does not match.");
  }

}

