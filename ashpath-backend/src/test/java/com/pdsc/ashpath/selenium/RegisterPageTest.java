package com.pdsc.ashpath.selenium;

import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {
    @Test
    public void registerAdmin_Succes() {
        var registerPage = loginPage
                .logInAsAdmin()
                .clickRegisterBtn();

        registerPage.isDisplayed();
        var admSect = registerPage.selectAdmin();
        admSect
                .register("franz bonaparta", "franz@gmail.com", "pass123", "pass123")
                .isDisplayed();
    }
}
