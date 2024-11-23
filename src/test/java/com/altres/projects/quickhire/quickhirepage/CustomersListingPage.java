package com.altres.projects.quickhire.quickhirepage;

import org.openqa.selenium.By;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

public class CustomersListingPage extends Base {

  /**
   * Clicks on the Customer within Listing.
   */
  public static void clickOnCustomerRecord(String name) {
    By element = By.xpath("//td[normalize-space()='" + name + "']");
    SeleniumWrapper.clickOnElement(element);
  }

}



