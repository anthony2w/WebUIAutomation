package com.webui;

/**
 * Created by achung on 5/8/2015.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyWebinars {
    private WebDriver driver;

    public MyWebinars(WebDriver d) {
        this.driver = d;
        PageFactory.initElements(driver,this);
        if (!driver.getTitle().contains("My Webinars")) {
            throw (new IllegalStateException("This is not My Webinars Page!"));
        }
    }
}
