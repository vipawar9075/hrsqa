package com.altres.projects.quickhire.admintestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.projects.quickhire.quickhirepage.QuickHireListingPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireAddEditPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.ConfigurationManager;
import com.altres.util.Constants;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.PropertyValueProvider;

/**
 * Validate the status of a new hire creation.
 */
public class TC08_ValidateNewHirePayroll extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.QUICKHIRE_ELEMENT_PROPERTIES_PATH);

  @Test(enabled = true)
  public void createNewUserHirePayroll() throws IOException, ConfigurationNotLoadedException {

    String returnToCustomerNH = propertyValueProvider.getPropertyValueByKey("returnToCustomerNH");
    String qhCustomer = propertyValueProvider.getPropertyValueByKey("qhCustomer");
    String payrollCompany = ConfigurationManager.getProperty("payroll_company");

    driver.navigate().refresh();
    AltresGeneralUtil.clickOnButton(returnToCustomerNH);
    AltresGeneralUtil.clickOnResetButton();
    QuickHireListingPage.setValueInTextboxesOnQHDetails(qhCustomer, payrollCompany);
    AltresGeneralUtil.clickOnFilterButton();
    QuickHireListingPage.clickOnCustomerName();
    QuickhireAddEditPage.setSocialSecurityNumber();
    QuickhireAddEditPage.setFirstName();
    QuickhireAddEditPage.setLastName();
    QuickhireAddEditPage.setPrimaryPhone();
    QuickhireAddEditPage.setEmail();
    QuickhireAddEditPage.setAddressNumber();
    QuickhireAddEditPage.setDropdown(1);
    JavascriptExecutorWrapper.scrollToBottom(driver);
    QuickhireAddEditPage.setRequestedBy(1);
    AltresGeneralUtil.clickOnSaveButton();
    Assert.assertEquals(QuickhireAddEditPage.getMessageDisplayed(),
        "The new hire has been successfully saved.");
  }
}

