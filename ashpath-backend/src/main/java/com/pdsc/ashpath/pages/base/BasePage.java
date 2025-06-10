package com.pdsc.ashpath.pages.base;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    public static WebDriver driver;
    public static JavascriptExecutor jsExecutor;
    public static WebDriverWait wait;

    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
        BasePage.jsExecutor = (JavascriptExecutor) driver;
        BasePage.wait = new WebDriverWait(driver, Duration.ofSeconds(3));
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
        scrollToElementJS(locator);
        var element = find(locator);
        element.click();
    }

    protected void clickJS(By locator) {
        scrollToElementJS(locator);
        var element = find(locator);
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    protected void scrollToElementJS(By locator) {
        var element = driver.findElement(locator);
        String jsScript = "arguments[0].scrollIntoView();";
        // var jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript(jsScript, element);
    }
}
