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
 * Validate Checkboxes are getting enabled even when no access granted for the admin in permission/notification tab.
 */
public class TC14_ValidateCheckboxEnabledWhenAccessGrantedForAdmin extends Base {

  PropertyValueProvider propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.ATS_ELEMENT_PROPERTIES_PATH);

  @Test
  public void validateExpirationDateWhenCopyOfPositionAlreadyCreated() throws IOException {
    String headerPermissionNotifications = propertyValueProvider.getPropertyValueByKey("headerPermissionNotifications");
    String positionTitle = propertyValueProvider.getPropertyValueByKey("randomTitle");
    SeleniumWrapper.refreshBrowser(driver);
    AtsPositionListingPage.clickOnPosition();
    AtsPositionListingPage.clickOnAddNewPosition();
    AtsAddNewPositionPage.setTitle(positionTitle);
    AtsAddNewPositionPage.clickOnTab(headerPermissionNotifications);
    AtsAddNewPositionPage.clickOnAllATSAdminsCompletedNotificationsCheckbox();
    int beforeSave = AtsAddNewPositionPage.getSizeOfDisabledCheckboxes();
    AtsAddNewPositionPage.clickSecondEnabledCheckbox();
    AltresGeneralUtil.clickOnSaveButton();
    int afterSave = AtsAddNewPositionPage.getSizeOfDisabledCheckboxes();
    Assert.assertEquals(beforeSave, afterSave);
  }

}

