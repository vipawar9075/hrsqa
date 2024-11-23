package com.altres.projects.ats.atsutil;

import java.io.IOException;

import com.altres.projects.ats.pages.AtsAddEditPage;
import com.altres.projects.ats.pages.AtsAddEditQuestionsTemplatePage;
import com.altres.projects.ats.pages.AtsListingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.FakeDataGenerator;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Utility class for creating new templates.
 */
public class NewTemplateCreation {

  static PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.ATS_ELEMENT_PROPERTIES_PATH);

  /**
   * Creates a new template and returns the generated template name.
   *
   * @return
   */
  public static String createNewTemplate() throws IOException {
    String educationCheckbox = propertyValueProvider.getPropertyValueByKey("educationCheckbox");
    String experience = propertyValueProvider.getPropertyValueByKey("experience");
    String addAllSelectedEducationQuestionsButton = propertyValueProvider.getPropertyValueByKey(
        "addAllSelectedEducationQuestionsButton");
    String addAllSelectedExperienceQuestionsButton = propertyValueProvider.getPropertyValueByKey(
        "addAllSelectedExperienceQuestionsButton");
    String addAllSelectedAdditionalQuestionsButton = propertyValueProvider.getPropertyValueByKey(
        "addAllSelectedAdditionalQuestionsButton");

    AtsListingPage.clickAddNewQuestionTemp();
    AtsAddEditPage.clickActiveCheckBox();
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(educationCheckbox);
    AtsAddEditQuestionsTemplatePage.clickOnCheckbox(experience);
    String generatedTemplateName = FakeDataGenerator.getQuestionTemplateName();
    AtsAddEditPage.setFullName(generatedTemplateName);
    AtsAddEditQuestionsTemplatePage.clickOnMultiSelectableButton(addAllSelectedEducationQuestionsButton);
    SeleniumWrapper.acceptAlert();
    AtsAddEditQuestionsTemplatePage.clickOnMultiSelectableButton(addAllSelectedExperienceQuestionsButton);
    SeleniumWrapper.acceptAlert();
    AtsAddEditQuestionsTemplatePage.clickOnMultiSelectableButton(addAllSelectedAdditionalQuestionsButton);
    SeleniumWrapper.acceptAlert();
    AltresGeneralUtil.clickOnSaveButton();
    AtsAddEditPage.clickReturnButton();
    return generatedTemplateName;
  }
}











