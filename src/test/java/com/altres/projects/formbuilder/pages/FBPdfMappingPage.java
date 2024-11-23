package com.altres.projects.formbuilder.pages;

import org.openqa.selenium.By;

import com.altres.base.Base;
import com.altres.util.SeleniumWrapper;

/**
 * All the xpaths and methods required to perform specific actions to validate the test cases on the Form Builder: PDF
 * Mapping page are listed here.
 */
public class FBPdfMappingPage extends Base {

  /**
   * Selects a Form Field by its value from the dropdown.
   *
   * @param formField
   */
  public static void mapFormFields(String idValue, String formField) {
    By formFieldDropdown = By.xpath("//select[@id='" + idValue + "']");
    SeleniumWrapper.selectDropdownByVisibleText(formFieldDropdown, formField);
  }

}
