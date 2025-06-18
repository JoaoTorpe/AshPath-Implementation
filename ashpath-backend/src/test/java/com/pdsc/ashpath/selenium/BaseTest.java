package com.pdsc.ashpath.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.pdsc.ashpath.pages.base.BasePage;
import com.pdsc.ashpath.pages.login.LoginPage;

public abstract class BaseTest {
    /*
     * WARNING: Don't remove '/login' from the baseUrl.
     * It is used to ensure that the LoginPage is loaded before each test.
     */
    protected String baseUrl = "http://localhost:4200/login";
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected String validPwd = "s3nh4@S";
    protected String invalidPwd = "s3nh4@SSS";

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
