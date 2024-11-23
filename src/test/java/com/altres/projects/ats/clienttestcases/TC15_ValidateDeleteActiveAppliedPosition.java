package com.altres.projects.ats.clienttestcases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.ats.pages.AtsPositionListingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Validate Delete Active Applied Position in positions.
 */
public class TC15_ValidateDeleteActiveAppliedPosition extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateDeleteActiveAppliedPosition() throws IOException {
    String expectedErrorMessage = propertyValueProvider.getPropertyValueByKey(
        "expected.message.deletePosition");
    SeleniumWrapper.refreshBrowser(driver);
    String positionTitle = TC12_ValidateAppearByDefaultOnAllPosition.positionTitle;
    AtsPositionListingPage.clickOnPosition();
    SeleniumWrapper.switchToInteriorContent(driver);
    AltresGeneralUtil.clickOnResetButton();
    AtsPositionListingPage.setTitle(positionTitle);
    AltresGeneralUtil.clickOnFilterButton();
    AtsPositionListingPage.clickPositionRecord(1);
    AltresGeneralUtil.clickOnDeleteButton();
    SeleniumWrapper.acceptAlert();
    String actualErrorMessage = AltresGeneralUtil.getErrorMessage();
    Assert.assertEquals(actualErrorMessage, expectedErrorMessage, "Error: The error message is incorrect.");
  }
}

