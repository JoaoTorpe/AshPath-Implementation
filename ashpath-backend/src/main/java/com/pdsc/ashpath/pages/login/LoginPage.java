package com.pdsc.ashpath.pages.login;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pdsc.ashpath.pages.base.BasePage;
import com.pdsc.ashpath.pages.home.HomePage;
import com.pdsc.ashpath.pages.register.RegisterPage;

public class LoginPage extends BasePage {
    private By emailEl = By.cssSelector("input[type='text']");
    private By emailMsgEl = By.cssSelector("input[type='text'] + .error-message");
    private By passwordEl = By.cssSelector("input[type='password']");
    private By passwordMsgEl = By.cssSelector("input[type='password'] + .error-message");
    private By loginBtnEl = By.cssSelector("button[type='submit']");
    private By errorMessageEl = By.className("error");
    public By registerBtnEl = By.xpath("//button[@type='button'][text()='Register']");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.urlContains("login"));
    }

    public RegisterPage clickRegisterBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(registerBtnEl)).click();
        return new RegisterPage();
    }

    public HomePage clickLoginBtn() {
        // clickJS(loginBtn);
        click(loginBtnEl);
        return new HomePage();
    }

    public HomePage logInAsNecrotomist() {
        set(this.emailEl, "necrotomista1@ashpath.com");
        set(this.passwordEl, "s3nh4@S");
        return clickLoginBtn();
    }

    public HomePage logInAsAdmin() {
        set(this.emailEl, "admin@ashpath.com");
        set(this.passwordEl, "s3nh4@S");
        return clickLoginBtn();
    }

    public HomePage logInAsViewer() {
        set(this.emailEl, "viewer1@ashpath.com");
        set(this.passwordEl, "s3nh4@S");
        return clickLoginBtn();
    }

    public void setEmail(String email) {
        set(this.emailEl, email);
    }

    public void setPassword(String password) {
        set(this.passwordEl, password);
    }

    public HomePage logIntoApp(String email, String pwd) {
        set(this.emailEl, email);
        set(this.passwordEl, pwd);
        return clickLoginBtn();
    }

    public String getErrorMsg() {
        return find(errorMessageEl).getText();
    }

    public boolean isEmailMsgPresent(String msg) {
        return isMsgPresent(emailMsgEl, msg);
    }

    public boolean isEmailMsgHidden() {
        return isMsgHidden(emailMsgEl);
    }

    public boolean isPasswordMsgPresent(String msg) {
        return isMsgPresent(passwordMsgEl, msg);
    }

    public boolean isPasswordMsgHidden() {
        return isMsgHidden(passwordMsgEl);
    }

    public boolean isErrorMsgPresent(String msg) {
        return isMsgPresent(errorMessageEl, msg);
    }
}
