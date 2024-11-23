package com.altres.projects.quickhire.admintestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.quickhire.quickhirepage.QuickHireListingPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireAddEditPage;
import com.altres.projects.quickhire.quickhirepage.QuickhireHomePage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;


/**
 * Validate scenario with invalid Social Security Numbers (SSN).
 */
public class TC06_InvalidSSN extends Base {

  private final PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(
      Constants.QUICKHIRE_ELEMENT_PROPERTIES_PATH);

  /**
   * Validate invalid ssn.
   *
   * @throws IOException
   */
  @Test
  public void validateNewHirePage() throws IOException {
    SeleniumWrapper.click(driver, QuickhireHomePage.getUsernameFieldLocator());
    QuickhireHomePage.clickNewHireButton();
    AltresGeneralUtil.clickOnFilterButton();
    QuickHireListingPage.clickOnCustomerName();
    QuickhireAddEditPage.setInvalidSocialSecurityNumber();
    AltresGeneralUtil.clickOnSaveButton();
    String expectedErrorMessage = propertyValueProvider.getPropertyValueByKey(
        "expected.message.invalidSSN");
    String actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualErrorMessage, expectedErrorMessage,
        "Error: The error message is incorrect.");
  }

}