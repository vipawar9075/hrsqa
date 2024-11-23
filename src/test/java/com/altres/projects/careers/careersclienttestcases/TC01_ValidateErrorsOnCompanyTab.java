package com.altres.projects.careers.careersclienttestcases;

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
import com.altres.login.LoginPage;
import com.altres.login.UserProperties;
import com.altres.projects.careers.carrerspages.CareersListingPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.ConfigPropertiesProviderImpl;
import com.altres.util.Constants;
import com.altres.util.JavascriptExecutorWrapper;
import com.altres.util.PropertyValueProvider;
import com.altres.util.SeleniumWrapper;

/**
 * Validates the error messages on company & email tab
 */
public class TC01_ValidateErrorsOnCompanyTab extends Base {

  LoginPage login;
  UserProperties userProps;
  private final PropertyValueProvider propertyValueProvider;

  public TC01_ValidateErrorsOnCompanyTab() {
    try {
      userProps = new UserProperties(ADMIN);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    propertyValueProvider = new ConfigPropertiesProviderImpl(Constants.CAREERS_ELEMENT_PROPERTIES_PATH);
  }

  /**
   * Validating the error messages on company and indeed tab.
   *
   * @throws IOException
   * @throws ConfigurationNotLoadedException
   */
  @Test
  public void validateErrorMessagesOnAddNewCompanyScreen() throws IOException, ConfigurationNotLoadedException {
    navigateToURL("clienturl");
    login = new LoginPage(driver);
    LoginPage.login(userProps, "ADMIN");
    driver.navigate().refresh();
    CareersListingPage.clickSettingsButton();
    SeleniumWrapper.switchToInteriorContent(driver);
    CareersListingPage.clickManageAtsCompanyLink();
    CareersListingPage.clickAddNewCompanyLink();
    JavascriptExecutorWrapper.scrollToBottom(driver);
    AltresGeneralUtil.clickOnSaveButton();
    List<String> keys = Arrays.asList(
        "expected.message.name",
        "expected.message.industry",
        "expected.message.phoneNumber",
        "expected.message.address",
        "expected.message.city",
        "expected.message.postalCode",
        "expected.message.description",
        "expected.message.companyEmail"
    );
    Map<String, String> expectedErrorMap = propertyValueProvider.getPropertyValuesByKeys(keys);
    List<String> expectedErrorMessages = new ArrayList<>(expectedErrorMap.values());
    List<String> actualErrorMessages = AltresGeneralUtil.getDisplayedErrorMessages();
    List<String> matchingErrors = actualErrorMessages.stream()
        .filter(expectedErrorMessages::contains)
        .collect(Collectors.toList());
    Assert.assertEquals(new HashSet<>(matchingErrors),
        new HashSet<>(expectedErrorMessages), "Error messages does not match");
  }
}
