package com.pdsc.ashpath.pages.register;

import org.openqa.selenium.By;

import com.pdsc.ashpath.pages.home.HomePage;
import com.pdsc.ashpath.pages.login.LoginPage;

public class AdminRegisterSection extends RegisterPage {
    private By fullNameEl = By.cssSelector(".admin input[formcontrolname='fullname']");
    private By emailEl = By.cssSelector(".admin input[formcontrolname='email']");
    private By passwordEl = By.cssSelector(".admin input[formcontrolname='password']");
    private By repeatPasswordEl = By.cssSelector(".admin input[formcontrolname='repeatPassword']");

    public HomePage register(
        String fullname, String email, String password, String repeatPassword
    ) {
        set(fullNameEl, fullname);
        set(emailEl, email);
        set(passwordEl, password);
        set(repeatPasswordEl, repeatPassword);
        submitForm(adminBtnEl);
        return new HomePage();
    }
}
