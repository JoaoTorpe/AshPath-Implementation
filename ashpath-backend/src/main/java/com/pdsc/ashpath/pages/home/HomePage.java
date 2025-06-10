package com.pdsc.ashpath.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pdsc.ashpath.pages.base.BasePage;
import com.pdsc.ashpath.pages.register.RegisterPage;

public class HomePage extends BasePage {
    public By registerBtnEl = 
        By.xpath("//div[@class='right']//a[text()='Register']");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.urlContains("home"));
    }

    public RegisterPage clickRegisterBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(registerBtnEl)).click();
        return new RegisterPage();
    }
}
