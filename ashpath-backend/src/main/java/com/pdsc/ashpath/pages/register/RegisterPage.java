package com.pdsc.ashpath.pages.register;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.pdsc.ashpath.pages.base.BasePage;
import com.pdsc.ashpath.pages.login.LoginPage;

public class RegisterPage extends BasePage {
    protected By selectEl = By.cssSelector("select.select");
    protected By adminBtnEl = By.cssSelector(".container.admin button[type='submit']");
    protected By necrotomistBtnEl = By.cssSelector(".container.necrotomist button[type='submit']");
    protected By viewerBtnEl = By.cssSelector(".container.viewer button[type='submit']");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.urlContains("register"));
    }

    public Select getSelectElement(By locator) {
        return new Select(find(locator));
    }

    public void selectByVisibleText(String text) {
        getSelectElement(selectEl).selectByVisibleText(text);
    }

    public AdminRegisterSection selectAdmin() {
        selectByVisibleText("ADMIN");
        return new AdminRegisterSection();
    }

    public LoginPage submitForm(By locator) {
        click(locator);
        return new LoginPage();
    }
}
