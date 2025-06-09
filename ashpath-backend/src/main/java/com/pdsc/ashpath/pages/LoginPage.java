package com.pdsc.ashpath.pages;

import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    By emailEl = By.cssSelector("input[type='text']");
    By passwordEl = By.cssSelector("input[type='password']");
    By loginBtn = By.cssSelector("button[type='submit']");
    By errorMessageEl = By.className("error");

    public HomePage clickLoginBtn() {
        click(loginBtn);
        return new HomePage();
    }

    public HomePage logIntoApp(String email, String pwd) {
        set(this.emailEl, email);
        set(this.passwordEl, pwd);
        return clickLoginBtn();
    }

    public String getErrorMsg() {
        return find(errorMessageEl).getText();
    }
}
