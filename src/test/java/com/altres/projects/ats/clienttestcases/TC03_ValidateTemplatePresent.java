package com.altres.projects.ats.clienttestcases;


import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.altres.base.Base;
import com.altres.projects.ats.atsutil.NewTemplateCreation;
import com.altres.projects.ats.pages.AtsListingPage;
import com.altres.projects.ats.pages.AtsSettingsPage;
import com.altres.util.AltresGeneralUtil;
import com.altres.util.SeleniumWrapper;

/**
 * Validate if the newly created template is displayed on the listing page of Applicant Tracking listing screen.
 */
public class TC03_ValidateTemplatePresent extends Base {


  @Test
  public void ATS3() throws IOException {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    AtsSettingsPage.clickSettingsLink();
    SeleniumWrapper.switchToInteriorContent(driver);
    AtsSettingsPage.clickAppQuestionTemplate();
    String createdTemplateName = NewTemplateCreation.createNewTemplate();
    AtsListingPage.setTemplateName(createdTemplateName);
    AltresGeneralUtil.clickOnFilterButton();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("table")));
    List<WebElement> tableRows = driver.findElements(By.tagName("tr"));
    List<String> tableTexts = tableRows.stream()
        .flatMap(row -> row.findElements(By.tagName("td")).stream())
        .map(WebElement::getText)
        .collect(Collectors.toList());
    Assert.assertEquals(tableTexts.contains(createdTemplateName), true,
        "Error: Search text '" + createdTemplateName + "' not found in the table!");
    AltresGeneralUtil.clickOnResetButton();
  }
}