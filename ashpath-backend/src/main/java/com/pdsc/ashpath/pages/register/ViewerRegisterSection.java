package com.pdsc.ashpath.pages.register;

import org.openqa.selenium.By;

import com.pdsc.ashpath.pages.home.HomePage;

public class ViewerRegisterSection extends RegisterPage {
    private By fullNameEl = By.cssSelector(".viewer input[formcontrolname='fullname']");
    private By emailEl = By.cssSelector(".viewer input[formcontrolname='email']");
    private By passwordEl = By.cssSelector(".viewer input[formcontrolname='password']");
    private By repeatPasswordEl = By.cssSelector(".viewer input[formcontrolname='repeatPassword']");

    public HomePage register(
        String fullname, String email, String password, String repeatPassword
    ) {
        set(fullNameEl, fullname);
        set(emailEl, email);
        set(passwordEl, password);
        set(repeatPasswordEl, repeatPassword);
        submitForm(viewerBtnEl);
        return new HomePage();
    }
}
