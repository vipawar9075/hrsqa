package com.altres.projects.formbuilder.pages;

import org.openqa.selenium.By;

public interface FBListing {

  By CUSTOMER_NAME_FIELD = By.xpath("//div[@class='filter']/span[starts-with(text(),'Customer " + "Name')]");
  By CUSTOMER_NUMBER_FIELD = By.xpath("//div[@class='filter']/span[starts-with(text(),'Cust #')]");
  By MODULE = By.xpath("//div[@class='filter']/span[starts-with(text(),'Module')]");
  By ACTIVE_DROPDOWN = By.xpath("//select[@name='FormBuilderTemplateListControlleris_active']");
  By ADD_FORM_BUTTON = By.xpath("//div[@class='actions-bar noPrint']//input[@value='Add Form']");
  By FILTER_BUTTON = By.xpath("//input[@value='Filter']");
  By RESET_BUTTON = By.xpath("//input[@value='Reset']");
  By SHARED_FORM_ORDER_BUTTON = By.xpath("//input[@value='Shared Form Order']");
  By NAME_FIELD = By.xpath("//input[@name='FormBuilderTemplateListControllerName']");
  By FIRST_FORM = By.xpath("//td[contains(@class,'first')]");

}


