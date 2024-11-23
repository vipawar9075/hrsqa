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
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.projects.quickhire.quickhirepage.QuickHireListingPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireAddEditPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireHomePage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Validate error messages on the Add New Hire screen.
 */
public class TC03_ValidateErrorMessageOnAddNewHireScreen extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.QUICKHIRE_ELEMENT_PROPERTIES_PATH);

  @Test(enabled = true)
  public void validateErrorMessages() throws IOException, ConfigurationNotLoadedException {

    SeleniumWrapper.click(driver, QuickhireHomePage.getUsernameFieldLocator());
    QuickhireHomePage.clickNewHireButton();
    QuickHireListingPage.setCompanyName();
    AltresGeneralUtil.clickOnFilterButton();
    QuickHireListingPage.clickOnCustomerName();
    AltresGeneralUtil.clickOnSaveButton();
    List<String> keys = Arrays.asList(
        "expected.message.number",
        "expected.message.firstName",
        "expected.message.lastName",
        "expected.message.primaryPhone",
        "expected.message.email",
        "expected.message.benefitGroup",
        "expected.message.requestedBy"
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
  }
}


