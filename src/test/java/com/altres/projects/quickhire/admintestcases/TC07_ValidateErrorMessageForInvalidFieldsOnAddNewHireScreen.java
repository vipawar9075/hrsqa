package com.altres.projects.quickhire.admintestcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.quickhire.quickhirepage.QuickhireAddEditPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.Date;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.PropertyValueProvider;

/**
 * Validate error messages on the Add New Hire screen.
 */
public class TC07_ValidateErrorMessageForInvalidFieldsOnAddNewHireScreen extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.QUICKHIRE_ELEMENT_PROPERTIES_PATH);

  @Test(enabled = true)
  public void validateErrorMessages() throws IOException {

    String expectedMorePayRateAmount = propertyValueProvider.getPropertyValueByKey("morePayRateAmount");
    String expectedLessPayRateAmount = propertyValueProvider.getPropertyValueByKey("lessPayRateAmount");
    String expectedMoreWageAmount = propertyValueProvider.getPropertyValueByKey("moreWageAmount");

    driver.navigate().refresh();
    addNewHireWithMandatoryFields();
    String date = Date.getPreviousDate(Date.getCurrentDate(), 20);
    String date1 = Date.getPreviousDate(Date.getCurrentDate(), 18);
    QuickhireAddEditPage.setBirthDate(date1);
    QuickhireAddEditPage.setClientHireDate(date1);
    QuickhireAddEditPage.setAltresStartDate(date);
    QuickhireAddEditPage.setJobTitle(2);
    QuickhireAddEditPage.setPayRate(expectedMoreWageAmount, 0);
    AltresGeneralUtil.clickOnSaveButton();
    List<String> keys = Arrays.asList(
        "expected.message.moreHourlyPayRate",
        "expected.message.invalidBirthAge",
        "expected.message.invalidAltresStartDate",
        "expected.message.blankPayDetails"
    );
    Map<String, String> expectedErrorMessagesMap = propertyValueProvider.getPropertyValuesByKeys(
        keys);
    List<String> expectedErrorMessages = new ArrayList<>(expectedErrorMessagesMap.values());
    List<String> actualErrorMessages = QuickhireAddEditPage.getDisplayedErrorMessages();
    List<String> filteredActualErrorMessages = actualErrorMessages.stream()
        .filter(expectedErrorMessages::contains)
        .collect(Collectors.toList());
    Assert.assertEquals(new HashSet<>(filteredActualErrorMessages),
        new HashSet<>(expectedErrorMessages), "Error messages does not match");

    driver.navigate().refresh();
    addNewHireWithMandatoryFields();
    QuickhireAddEditPage.setJobTitle(2);
    QuickhireAddEditPage.setPayRate(expectedMorePayRateAmount, 1);
    AltresGeneralUtil.clickOnSaveButton();

    String moreYearlyPayRate = propertyValueProvider.getPropertyValueByKey(
        "expected.message.moreYearlyPayRate");
    String actualMoreYearlyPayRateMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualMoreYearlyPayRateMessage, moreYearlyPayRate,
        "Error: The error message is incorrect.");

    driver.navigate().refresh();
    addNewHireWithMandatoryFields();
    QuickhireAddEditPage.setJobTitle(2);
    JavascriptExecutorWrapper.scrollToBottom(driver);
    QuickhireAddEditPage.setPayRate(expectedLessPayRateAmount, 1);
    AltresGeneralUtil.clickOnSaveButton();
    String blankLowWageReason = propertyValueProvider.getPropertyValueByKey(
        "expected.message.blankLowWageReason");
    String actualBlankLowWageReasonMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualBlankLowWageReasonMessage, blankLowWageReason,
        "Error: The error message is incorrect.");

    driver.navigate().refresh();

    addNewHireWithMandatoryFields();
    QuickhireAddEditPage.setJobTitle(2);
    QuickhireAddEditPage.setPayRate(expectedLessPayRateAmount, 1);
    QuickhireAddEditPage.setReasonForLowWage(3);
    AltresGeneralUtil.clickOnSaveButton();
    String lowWage = propertyValueProvider.getPropertyValueByKey(
        "expected.message.lowWage");
    String actualLowWageMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualLowWageMessage, lowWage,
        "Error: The error message is incorrect.");
  }


  public static void addNewHireWithMandatoryFields() {
    QuickhireAddEditPage.setSocialSecurityNumber();
    QuickhireAddEditPage.setFirstName();
    QuickhireAddEditPage.setLastName();
    QuickhireAddEditPage.setPrimaryPhone();
    QuickhireAddEditPage.setEmail();
    QuickhireAddEditPage.setAddressNumber();
    QuickhireAddEditPage.setDropdown(1);
    JavascriptExecutorWrapper.scrollToBottom(driver);
    QuickhireAddEditPage.setBenefitGroup(1);
    QuickhireAddEditPage.setRequestedBy(1);
  }


}


