package com.pdsc.ashpath.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class BasePage {
    public static WebDriver driver;

    public void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected void set(By locator, String text) {
        var element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected void click(By locator) {
        find(locator).click();
    }
}
