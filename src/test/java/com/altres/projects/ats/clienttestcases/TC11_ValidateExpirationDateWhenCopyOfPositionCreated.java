package com.altres.projects.ats.clienttestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.ats.pages.AtsAddNewPositionPage;
import com.altres.projects.ats.pages.AtsPositionListingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Validate Expiration Date field shouldn't display when we copy a Position which is already created on Add new position
 * screen as that position is inactive.
 */
public class TC11_ValidateExpirationDateWhenCopyOfPositionCreated extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateExpirationDateWhenCopyOfPositionAlreadyCreated() throws IOException {
    String expirationDateField = propertyValueProvider.getPropertyValueByKey("expirationDateField");
    SeleniumWrapper.refreshBrowser(driver);
    AtsPositionListingPage.clickOnPosition();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsPositionListingPage.clickOnHideInactive();
    AltresGeneralUtil.clickOnFilterButton();
    AtsPositionListingPage.clickOnPositionRecord(1);
    AltresGeneralUtil.clickOnCopyButton();
    Assert.assertFalse(AtsAddNewPositionPage.isExpirationDateVisible(expirationDateField));
  }

}

