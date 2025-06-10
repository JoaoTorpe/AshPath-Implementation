package com.pdsc.ashpath.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.pdsc.ashpath.pages.base.BasePage;
import com.pdsc.ashpath.pages.login.LoginPage;

public abstract class BaseTest {
    protected String baseUrl = "http://localhost:4200/";
    protected WebDriver driver;
    protected LoginPage loginPage;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    public void setUpMethod() {
        driver.get(baseUrl);
        BasePage.setDriver(driver);
        loginPage = new LoginPage();
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        // Thread.sleep(10000);
        driver.quit(); // closes the window and the webdriver
        // driver.close(); // only closes the window
    }
}
