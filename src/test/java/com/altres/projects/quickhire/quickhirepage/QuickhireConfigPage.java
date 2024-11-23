package com.altres.projects.quickhire.quickhirepage;

import org.openqa.selenium.By;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;


public class QuickhireConfigPage extends Base {

  /**
   * Clicks on the Customer within Details Radio Buttons.
   */
  public static void clickOnRadioButtonWithinDetails(String name, int index) {
    By element = By.xpath("//*[text()='" + name + "']/../following-sibling::td/input[" + index + "]");
    SeleniumWrapper.clickOnElement(element);

  }

  /**
   * Clicks on the Hiring Manager Checkbox within admin config.
   */
  public static void clickOnHiringManagerCheckbox(int index) {
    By element = By.xpath("//input[@id='hr_" + index + "']");
    SeleniumWrapper.clickOnElement(element);
  }

}



