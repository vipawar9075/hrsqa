package com.altres.projects.ats.clienttestcases;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.ats.pages.AtsAddEditPage;
import com.altres.projects.ats.pages.AtsListingPage;
import com.altres.projects.ats.pages.AtsSettingsPage;
import com.altres.util.FakeDataGenerator;
import com.altres.util.SeleniumWrapper;

/**
 * Verifies the filtering functionality on the ATS Settings page. Verifies that no entries are found after filtering and
 * resets the filter and verifies that the initial number of entries is restored.
 */
public class TC05_ValidateResetButtonFunctionality extends Base {

  @Test
  public void ATS5() {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    String firstName = FakeDataGenerator.getRandomFirstName();
    SeleniumWrapper.switchToDefaultContent(driver);
    AtsSettingsPage.clickSettingsLink();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsSettingsPage.clickAppQuestionTemplate();
    int initialCount = getEntriesCount(wait);
    SeleniumWrapper.sendKeyWithClear(driver, AtsAddEditPage.enterName(), firstName);
    SeleniumWrapper.click(driver, AtsListingPage.getFilterButtonLocator());
    int newCount = getEntriesCount(wait);
    Assert.assertEquals(newCount, 0, "Initial count is not equals to new count");
    SeleniumWrapper.click(driver, AtsListingPage.getEntriesFoundXPath());
    SeleniumWrapper.click(driver, AtsListingPage.getResetButtonLocator());
    int resetCount = getEntriesCount(wait);
    Assert.assertEquals(resetCount, initialCount,
        "Error: The number of entries after reset does not match the initial count.");
  }

  /**
   * Retrieves the number of entries found in the ATS Listing Page.
   *
   * @param wait
   * @return
   */
  private int getEntriesCount(WebDriverWait wait) {
    String messageText;
    try {
      WebElement messageElement =
          wait.until(
              ExpectedConditions.presenceOfElementLocated(AtsListingPage.getEntriesFoundXPath()));
      messageText = messageElement.getText().trim();
      return getNumberOfEntries(messageText);
    } catch (NoSuchElementException e) {
      return 0;
    }
  }

  /**
   * Parses the message text to extract the number of entries.
   *
   * @param messageText
   * @return
   */
  private int getNumberOfEntries(String messageText) {

    String[] parts = messageText.split(" ");
    return Integer.parseInt(parts[0]);
  }


}

