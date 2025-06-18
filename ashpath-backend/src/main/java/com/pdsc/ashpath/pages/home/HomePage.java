package com.pdsc.ashpath.pages.home;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pdsc.ashpath.pages.base.BasePage;
import com.pdsc.ashpath.pages.register.RegisterPage;

public class HomePage extends BasePage {
    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.urlContains("home"));
    }
}
