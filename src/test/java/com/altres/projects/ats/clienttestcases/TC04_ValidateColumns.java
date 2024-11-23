package com.altres.projects.ats.clienttestcases;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.ats.pages.AtsListingPage;
import com.altres.projects.ats.pages.AtsSettingsPage;
import com.altres.util.SeleniumWrapper;

/**
 * Validate table column header of "Applicant Tracking:Application Question Templates"  listing screen.
 */
public class TC04_ValidateColumns extends Base {

  @Test
  public void ATS4() {
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsSettingsPage.clickSettingsLink();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsSettingsPage.clickAppQuestionTemplate();
    List<String> expectedHeaders = Arrays.asList("Name", "Created", "Created By", "Active");
    List<WebElement> actualTableHeaders = AtsListingPage.getTableHeaderElements();
    Assert.assertFalse(actualTableHeaders.isEmpty(), "Error: No table header elements found!");
    Assert.assertEquals(actualTableHeaders.size(), expectedHeaders.size(),
        "Error: Number of headers doesn't match expectation!");
    for (int i = 0; i < expectedHeaders.size(); i++) {
      String expectedHeader = expectedHeaders.get(i);
      String actualHeader = actualTableHeaders.get(i).getText().trim();
      Assert.assertEquals(actualHeader, expectedHeader,
          "Error: Header mismatch at index " + i + ". Expected: " + expectedHeader + ", Actual: "
              + actualHeader);
    }
  }
}

