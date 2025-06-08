package com.pdsc.ashpath.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.pdsc.ashpath.AshPathUtils;

public class LoginScreen {
    String url = AshPathUtils.url + "login";
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @AfterClass
    public void tearDown() {
        // driver.quit(); // closes the window and the webdriver
        // driver.close(); // only closes the window
    }

    @Test
    public void TestLogin() {
        WebElement btn = driver.findElement(By.cssSelector("button[type='submit']"));
        var email = driver.findElement(By.cssSelector("input[type='text']"));
        var pwd = driver.findElement(By.cssSelector("input[type='password']"));

        email.sendKeys("necrotomista1@ashpath.com");
        pwd.sendKeys("senha123");
        btn.click();
    }
}
