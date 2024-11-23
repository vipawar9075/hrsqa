package com.altres.projects.ats.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

public class AtsApplicationsListingPage extends Base {

  private static final By APPLICANTS = By.xpath("//a[normalize-space()='Applicants']");
  private static final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
  private static final By APPLICATIONS = By.xpath("//a[normalize-space()='Applications']");
  private static final By NAME_OR_EMAIL = By.xpath("//input[@name='search_string']");

  /**
   * Clicks on the Applications link within applicants tab.
   */
  public static void clickOnApplications() {
    wait.until(ExpectedConditions.elementToBeClickable(APPLICANTS));
    SeleniumWrapper.clickOnElement(APPLICANTS);
    SeleniumWrapper.clickOnElement(APPLICATIONS);
  }

  /**
   * Sets a Name, Email or Phone number.
   */
  public static void setNameOrEmail(String nameOrEmail) {
    SeleniumWrapper.enterText(NAME_OR_EMAIL, nameOrEmail);
  }

  /**
   * Clicks on ATS Applications Record by its row number.
   *
   * @param rowNumber
   */
  public static void clickAtsApplicationsRecord(int rowNumber) {
    By atsApplications = By.xpath("//table[@id='applications']//tbody//tr[" + rowNumber + "]//td[1]");
    SeleniumWrapper.clickOnElement(atsApplications);
  }


}

