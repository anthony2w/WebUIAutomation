package com.webui;

/**
 * Created by achung on 5/8/2015.
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class SchedulePage {
    private WebDriver driver;

    @FindBy(id = "name")
    private WebElement titleEdit = null;

    @FindBy(className = "ui-datepicker-trigger")
    private WebElement calendarObj = null;

    @FindBy(id = "webinarTimesForm.dateTimes_0.baseDate")
    private WebElement selectedDateObj = null;

    @FindBy(id = "schedule.submit.button")
    private WebElement submitButton = null;

    @FindBy(id = "webinarTimesForm.dateTimes_0.startTime")
    private WebElement startTimeObj = null;

    @FindBy(id = "webinarTimesForm_dateTimes_0_startAmPm_trig")
    private WebElement startAmPm = null;

    @FindBy(id = "webinarTimesForm.dateTimes_0.endTime")
    private WebElement endTimeObj = null;

    @FindBy(id = "webinarTimesForm_dateTimes_0_endAmPm_trig")
    private WebElement endAmPm = null;

    @FindBy(id = "webinarTimesForm_timeZone_trig")
    private WebElement timeZone = null;

    public SchedulePage(WebDriver d) {
        this.driver = d;
        PageFactory.initElements(driver,this);
        String title = driver.getTitle();
        if (!driver.getTitle().contains("Schedule a Webinar")) {
            throw (new IllegalStateException("This is not Schedule a Webinar Page!"));
        }
    }

    public SchedulePage setScheduleTitle(String title) {
        titleEdit.sendKeys(title);
        return this;
    }

    public List<String> setScheduleDate() throws Exception{
        List<String> selectedValues = new ArrayList<String>();

        calendarObj.click();
        List<WebElement> sDay = driver.findElements(By.tagName("a"));
        List<String> dString = new ArrayList<String>();
        for (WebElement a: sDay) {
            dString.add(a.getText());
        }
        driver.findElement(By.linkText(dString.get(dString.indexOf("Next") + 4))).click();

        Thread.sleep(2000);
        selectedValues.add(selectedDateObj.getAttribute("value"));
        selectedValues.add(startTimeObj.getAttribute("value") + " " + startAmPm.getText() +
        " - " + endTimeObj.getAttribute("value") + " " + endAmPm.getText());
        return selectedValues;
    }

    public void submitSchedule() {
        submitButton.click();
    }
}
