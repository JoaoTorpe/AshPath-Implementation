package com.pdsc.ashpath.pages.home;

import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pdsc.ashpath.pages.base.BasePage;

public class HomePage extends BasePage {
    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.urlContains("home"));
    }
}
