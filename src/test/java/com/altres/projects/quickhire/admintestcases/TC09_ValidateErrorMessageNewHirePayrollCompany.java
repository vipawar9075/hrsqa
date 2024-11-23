package com.altres.projects.quickhire.admintestcases;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.drivers.DriverManager;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.projects.quickhire.quickhirepage.QuickHireListingPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireAddEditPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireHomePage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.ConfigurationManager;
import com.altres.util.Constants;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;


/**
 * Validate the status of a new hire creation for Payroll Company.
 */
public class TC09_ValidateErrorMessageNewHirePayrollCompany extends Base {


  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.QUICKHIRE_ELEMENT_PROPERTIES_PATH);

  @Test(enabled = true)
  public void validateErrorMessagesNewUserHirePayroll() throws IOException, ConfigurationNotLoadedException {

    String qhCustomer = propertyValueProvider.getPropertyValueByKey("qhCustomer");
    String payrollCompany = ConfigurationManager.getProperty("payroll_company");

    driver.navigate().refresh();
    SeleniumWrapper.click(driver, QuickhireHomePage.getUsernameFieldLocator());
    QuickhireHomePage.clickNewHireButton();
    AltresGeneralUtil.clickOnFilterButton();
    AltresGeneralUtil.clickOnResetButton();
    QuickHireListingPage.setValueInTextboxesOnQHDetails(qhCustomer, payrollCompany);
    AltresGeneralUtil.clickOnFilterButton();
    QuickHireListingPage.clickOnCustomerName();
    AltresGeneralUtil.clickOnSaveButton();

    List<String> keys = Arrays.asList(
        "expected.message.number",
        "expected.message.firstName",
        "expected.message.lastName",
        "expected.message.primaryPhone",
        "expected.message.email",
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

  /**
   * Tear down method to quit the WebDriver after all tests in this module have run.
   */
  @AfterClass(alwaysRun = true)
  public void tearDownClass() {
    DriverManager.quitDriver();
  }

}
