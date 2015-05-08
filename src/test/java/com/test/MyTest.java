package com.test;

/**
 * Created by achung on 5/8/2015.
 */

import com.webui.LogInPage;
import com.webui.MyWebinars;

import com.webui.SchedulePage;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.annotations.Listeners;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

public class MyTest {
    private final String siteURL = "https://global.gotowebinar.com/webinars.tmpl";

    @Test(enabled = true)
    @Parameters({"id","password"})
    public void scheduleTest(String id, String password) throws Exception {
        String webinarTitle = "My meeting " + getCurrentDate("MMddyyyyhhmmss");
        System.setProperty("webdriver.chrome.driver", "C:\\chromeDriver\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(siteURL);

        (new LogInPage(driver)).enterLoginId(id).enterPassword(password).submit();
        driver.findElement(By.linkText("Schedule a Webinar")).click();
        Thread.sleep(2000);
        SchedulePage sPage = new SchedulePage(driver);
        sPage.setScheduleTitle(webinarTitle);
        List<String> selectedValues = sPage.setScheduleDate();
        sPage.submitSchedule();

        driver.get("https://global.gotowebinar.com/webinars.tmpl");
        driver.findElement(By.linkText(webinarTitle)).click();
        Thread.sleep(2000);
        String actualDateTime = driver.findElement(By.className("time")).getText();
        String expectedDateTime = selectedValues.get(0) + " " + selectedValues.get(1);
        assertTrue(actualDateTime.contains(expectedDateTime),"Webinar Date is not correct.");

        driver.quit();
    }

    public String getCurrentDate(String format) {
        DateFormat dFormat = new SimpleDateFormat(format);
        Date date = new Date();
        return dFormat.format(date);
    }
}
