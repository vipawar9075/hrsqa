package com.altres.projects.quickhire.admintestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.exception.ConfigurationNotLoadedException;
import com.altres.projects.quickhire.quickhirepage.CustomersListingPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireAddEditPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireConfigPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireHomePage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.ConfigurationManager;
import com.altres.util.Constants;
import com.altres.util.PropertyValueProvider;

/**
 * Validate the status of a new hire creation.
 */
public class TC05_ValidateNewHireStatus extends Base {

  TC04_ValidateNewHireScreen validateNewHire = new TC04_ValidateNewHireScreen();
  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.QUICKHIRE_ELEMENT_PROPERTIES_PATH);

  @Test(enabled = true)
  public void createNewUserHire() throws IOException, ConfigurationNotLoadedException {

    String reqNewHireApproval = propertyValueProvider.getPropertyValueByKey("reqNewHireApproval");
    String quickhireConfig = propertyValueProvider.getPropertyValueByKey("quickhireConfig");
    String companyName = ConfigurationManager.getProperty("company_name");
    String customers = propertyValueProvider.getPropertyValueByKey("customers");
    String newStatus = propertyValueProvider.getPropertyValueByKey("newStatus");
    String readyForEmployee = propertyValueProvider.getPropertyValueByKey("readyForEmp");

    QuickhireHomePage.navigateToLeftPaneMenu(customers);
    CustomersListingPage.clickOnCustomerRecord(companyName);
    AltresGeneralUtil.clickOnButton(quickhireConfig);
    QuickhireConfigPage.clickOnRadioButtonWithinDetails(reqNewHireApproval, 1);
    QuickhireConfigPage.clickOnHiringManagerCheckbox(1);
    AltresGeneralUtil.clickOnSaveButton();

    validateNewHire.validateNewHirePage();
    QuickhireAddEditPage.clickOnReturnToNewHireButton();
    Assert.assertEquals(QuickhireAddEditPage.validateStatusOfNewHire(), newStatus);

    QuickhireHomePage.navigateToLeftPaneMenu(customers);
    CustomersListingPage.clickOnCustomerRecord(companyName);
    AltresGeneralUtil.clickOnButton(quickhireConfig);
    QuickhireConfigPage.clickOnRadioButtonWithinDetails(reqNewHireApproval, 2);
    AltresGeneralUtil.clickOnSaveButton();

    validateNewHire.validateNewHirePage();
    QuickhireAddEditPage.clickOnReturnToNewHireButton();
    Assert.assertEquals(QuickhireAddEditPage.validateStatusOfNewHire(), readyForEmployee);


  }
}
