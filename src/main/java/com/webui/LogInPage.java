package com.webui;

/**
 * Created by achung on 5/7/2015.
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage {
    private WebDriver driver;

    @FindBy(id = "emailAddress")
    private WebElement loginEdit = null;

    @FindBy(id = "password")
    private WebElement passwordEdit = null;

    @FindBy(id = "submit")
    private WebElement submitButton = null;

    public LogInPage (WebDriver d) {
        this.driver = d;
        PageFactory.initElements(driver, this);
        if (!driver.getTitle().contains("Citrix Secure Sign In")) {
            throw (new IllegalStateException("This is not GoToWebinar Sign In Page!"));
        }
    }

    public LogInPage enterLoginId(String id) {
        loginEdit.sendKeys(id);
        return this;
    }

    public LogInPage enterPassword(String password) {
        passwordEdit.sendKeys(password);
        return this;
    }

    public MyWebinars submit() {
        submitButton.click();
        return (new MyWebinars(driver));
    }







}
