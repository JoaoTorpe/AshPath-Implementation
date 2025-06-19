package com.pdsc.ashpath.pages.register;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.pdsc.ashpath.pages.base.BasePage;
import com.pdsc.ashpath.pages.login.LoginPage;

public class RegisterPage extends BasePage {
    protected By selectEl = By.cssSelector("select.select");
    protected By submitEl = By.cssSelector(".container button[type='submit']");
    protected By errorMessageEl = By.className("error");

    private By fullNameEl = By.cssSelector("input[formcontrolname='fullname']");
    private By fullNameMsgEl = By.cssSelector("input[formcontrolname='fullname'] + .error-message");
    private By emailEl = By.cssSelector("input[formcontrolname='email']");
    private By emailMsgEl = By.cssSelector("input[formcontrolname='email'] + .error-message");
    private By passwordEl = By.cssSelector("input[formcontrolname='password']");
    private By passwordMsgEl = By.cssSelector("input[formcontrolname='password'] + .error-message");
    private By repeatPasswordEl = By.cssSelector("input[formcontrolname='repeatPassword']");
    private By repeatPasswordMsgEl = By.cssSelector("input[formcontrolname='repeatPassword'] + .error-message");
    private By specializationEl = By.cssSelector("input[formcontrolname='specialization']");
    private By specializationMsgEl = By.cssSelector("input[formcontrolname='specialization'] + .error-message");

    public LoginPage register(
            String fullname, String email, String password, String repeatPassword, String specialization) {
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

    public boolean isFullNameMsgPresent(String msg) {
        return isMsgPresent(fullNameMsgEl, msg);
    }

    public boolean isEmailMsgPresent(String msg) {
        return isMsgPresent(emailMsgEl, msg);
    }

    public boolean isPasswordMsgPresent(String msg) {
        return isMsgPresent(passwordMsgEl, msg);
    }

    public boolean isRepeatPasswordMsgPresent(String msg) {
        return isMsgPresent(repeatPasswordMsgEl, msg);
    }

    public boolean isSpecializationMsgPresent(String msg) {
        return isMsgPresent(specializationMsgEl, msg);
    }

    public boolean isEmailMsgHidden() {
        return isMsgHidden(emailMsgEl);
    }

    public boolean isFullNameMsgHidden() {
        return isMsgHidden(fullNameMsgEl);
    }

    public boolean isPasswordMsgHidden() {
        return isMsgHidden(passwordMsgEl);
    }

    public boolean isRepeatPasswordMsgHidden() {
        return isMsgHidden(repeatPasswordMsgEl);
    }

    public boolean isSpecializationMsgHidden() {
        return isMsgHidden(specializationMsgEl);
    }

    public RegisterPage setFullName(String fullName) {
        set(fullNameEl, fullName);
        return this;
    }

    public RegisterPage setEmail(String email) {
        set(emailEl, email);
        return this;
    }

    public RegisterPage setPassword(String password) {
        set(passwordEl, password);
        return this;
    }

    public RegisterPage setRepeatPassword(String repeatPassword) {
        set(repeatPasswordEl, repeatPassword);
        return this;
    }

    public RegisterPage setSpecialization(String specialization) {
        set(specializationEl, specialization);
        return this;
    }

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.urlContains("register"));
    }

    public boolean isErrorMsgPresent(String msg) {
        return isMsgPresent(errorMessageEl, msg);
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
