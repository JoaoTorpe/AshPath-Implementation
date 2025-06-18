package com.pdsc.ashpath.pages.register;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.pdsc.ashpath.pages.base.BasePage;
import com.pdsc.ashpath.pages.home.HomePage;
import com.pdsc.ashpath.pages.login.LoginPage;

public class RegisterPage extends BasePage {
    protected By selectEl = By.cssSelector("select.select");
    protected By submitEl = By.cssSelector(".container button[type='submit']");
    protected By errorMessageEl = By.className("error");

    private By fullNameEl = By.cssSelector("input[formcontrolname='fullname']");
    private By emailEl = By.cssSelector("input[formcontrolname='email']");
    private By passwordEl = By.cssSelector("input[formcontrolname='password']");
    private By repeatPasswordEl = By.cssSelector("input[formcontrolname='repeatPassword']");
    private By specializationEl = By.cssSelector("input[formcontrolname='specialization']");

    public LoginPage register(
        String fullname, String email, String password, String repeatPassword, String specialization
    ) {
        if (specialization != null) {
            set(specializationEl, specialization);
        }
        set(fullNameEl, fullname);
        set(emailEl, email);
        set(passwordEl, password);
        set(repeatPasswordEl, repeatPassword);
        submitForm(submitEl);
        return new LoginPage();
    }

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.urlContains("register"));
    }

    public boolean isErrorMsgPresent(String errorMsg) {
        return isMsgPresent(errorMessageEl, errorMsg);
    }

    public Select getSelectElement(By locator) {
        return new Select(find(locator));
    }

    public void selectByVisibleText(String text) {
        getSelectElement(selectEl).selectByVisibleText(text);
    }

    public void selectAdmin() {
        selectByVisibleText("ADMIN");
    }

    public void selectNecrotomist() {
        selectByVisibleText("NECROTOMIST");
    }

    public void selectViewer() {
        selectByVisibleText("VIEWER");
    }

    public LoginPage submitForm(By locator) {
        click(locator);
        return new LoginPage();
    }
}
