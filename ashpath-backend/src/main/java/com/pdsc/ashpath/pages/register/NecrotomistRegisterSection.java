package com.pdsc.ashpath.pages.register;

import org.openqa.selenium.By;

import com.pdsc.ashpath.pages.home.HomePage;

public class NecrotomistRegisterSection extends RegisterPage {
    private By fullNameEl = By.cssSelector(".necrotomist input[formcontrolname='fullname']");
    private By specializationEl = By.cssSelector(".necrotomist input[formcontrolname='specialization']");
    private By emailEl = By.cssSelector(".necrotomist input[formcontrolname='email']");
    private By passwordEl = By.cssSelector(".necrotomist input[formcontrolname='password']");
    private By repeatPasswordEl = By.cssSelector(".necrotomist input[formcontrolname='repeatPassword']");

    public HomePage register(
        String fullname, String specialization, String email, String password, String repeatPassword
    ) {
        set(fullNameEl, fullname);
        set(specializationEl, specialization);
        set(emailEl, email);
        set(passwordEl, password);
        set(repeatPasswordEl, repeatPassword);
        submitForm(necrotomistBtnEl);
        return new HomePage();
    }
}
