package com.pdsc.ashpath.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {
    @Test
    public void registerAdmin_AsAdmin_Succes() {
        var registerPage = loginPage
                .clickRegisterBtn();

        registerPage.isDisplayed();
        registerPage.selectAdmin();

        var loginPage = registerPage
                .register("franz bonaparta", "franz@gmail.com", validPwd, validPwd, null);

        Assert.assertTrue(loginPage.isDisplayed());
    }
}
