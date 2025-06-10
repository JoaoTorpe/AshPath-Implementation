package com.pdsc.ashpath.pages.login;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pdsc.ashpath.pages.base.BasePage;
import com.pdsc.ashpath.pages.home.HomePage;

public class LoginPage extends BasePage {
    private By emailEl = By.cssSelector("input[type='text']");
    private By passwordEl = By.cssSelector("input[type='password']");
    private By loginBtnEl = By.cssSelector("button[type='submit']");
    private By errorMessageEl = By.className("error");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.urlContains("login"));
    }

    public HomePage clickLoginBtn() {
        // clickJS(loginBtn);
        click(loginBtnEl);
        return new HomePage();
    }

    public HomePage logInAsNecrotomist() {
        set(this.emailEl, "necrotomista1@ashpath.com");
        set(this.passwordEl, "senha123");
        return clickLoginBtn();
    }

    public HomePage logInAsAdmin() {
        set(this.emailEl, "admin@ashpath.com");
        set(this.passwordEl, "senha123");
        return clickLoginBtn();
    }

    public HomePage logIntoApp(String email, String pwd) {
        set(this.emailEl, email);
        set(this.passwordEl, pwd);
        return clickLoginBtn();
    }

    public String getErrorMsg() {
        return find(errorMessageEl).getText();
    }

    public boolean isErrorMsgPresent(String errorMsg) {
        return wait.until(ExpectedConditions.textToBePresentInElement(find(errorMessageEl), errorMsg));
    }
}
