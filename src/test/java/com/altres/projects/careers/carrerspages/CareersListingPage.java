package com.altres.projects.careers.carrerspages;


import static org.testng.Reporter.log;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

public class CareersListingPage extends Base {

  private static final By CAREERS_ADD_NEW_COMPANY = By.xpath("//a[contains(text(),'+ Add New Company')]");
  private static final By CAREERS_MANAGE_ATS_COMPANY = By.xpath("//a[@title='Manage ATS companies']");
  private static final By CAREERS_PAGINATOR = By.xpath("//select[@name='elements_per_page']");
  private static final By CAREERS_LISTING_PAGE = By.xpath(
      "//div[@class='content']/table[@class='grid tight']/tbody/tr");
  private static final By SETTINGS = By.xpath("//a[contains(text(),'Settings')]");


  /**
   * Clicks on "Add New Company" link.
   */
  public static void clickAddNewCompanyLink() {
    SeleniumWrapper.click(driver, CAREERS_ADD_NEW_COMPANY);
  }

  /**
   * Clicks on "Manage ATS company" link.
   */
  public static void clickManageAtsCompanyLink() {
    SeleniumWrapper.click(driver, CAREERS_MANAGE_ATS_COMPANY);
  }

  /**
   * Clicks on settings button.
   */
  public static void clickSettingsButton() {
    SeleniumWrapper.click(driver, SETTINGS);
  }

  /**
   * Selects drop-down base on the value provided.
   *
   * @param value
   */
  public static void selectPaginator(String value) {
    SeleniumWrapper.selectDropdownByVisibleText(CAREERS_PAGINATOR, value);
  }

  /**
   * Searches for a company in the careers listing page and clicks on the corresponding row.
   *
   * @param companyName
   */
  public static void searchAndClickOnCompany(String companyName) {
    try {
      SeleniumWrapper.selectDropdownByVisibleText(CAREERS_LISTING_PAGE, companyName);
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
      wait.until(
          ExpectedConditions.visibilityOfElementLocated(By.xpath(
              "//tr[td[contains(text(),'" + companyName + "')]]")));
      WebElement matchingCompanyRow = driver.findElement(By.xpath(
          "//tr[td[contains(text(),'" + companyName + "')]]"));
      matchingCompanyRow.click();
    } catch (Exception e) {
      log("Exception occurred while searching and clicking on company: " + e.getMessage());
    }
  }
}

