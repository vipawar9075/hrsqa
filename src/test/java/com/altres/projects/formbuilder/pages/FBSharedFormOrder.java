package com.altres.projects.formbuilder.pages;

import org.openqa.selenium.By;

public interface FBSharedFormOrder {


  By MODULE_DROPDOWN = By.xpath("//select[@id='module']");
  By RETURN_TO_FORMS_BUTTON = By.xpath("(//input[@value='Return to Forms'])[1]");
  By TEMPLATE_ORDER_LIST = By.xpath("//ul[@id='sortable-list']//li");

}
