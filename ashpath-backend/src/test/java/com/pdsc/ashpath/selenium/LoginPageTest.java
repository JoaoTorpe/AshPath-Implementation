package com.pdsc.ashpath.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {
    private String unauthorizedMsg = "Invalid email/password."; // 401

    @Test
    public void loginNecrotomist_Success() {
        var homePage = loginPage
                .logIntoApp("necrotomista1@ashpath.com", "senha123");

        Assert.assertTrue(homePage.isDisplayed());
    }

    @Test
    public void loginNecrotomist_Error() {
        loginPage.logIntoApp("necrotomista1@ashpath.com", "11111");

        Assert.assertTrue(loginPage.isErrorMsgPresent(unauthorizedMsg));
    }

    @Test
    public void loginAdmin_Success() {
        var homePage = loginPage
                .logIntoApp("admin@ashpath.com", "senha123");

        Assert.assertTrue(homePage.isDisplayed());
    }

    @Test
    public void loginAdmin_Error() {
        loginPage
                .logIntoApp("admin@ashpath.com", "sssssssss");

        Assert.assertTrue(loginPage.isErrorMsgPresent(unauthorizedMsg));
    }

    @Test
    public void loginViewer_Success() {
        var homePage = loginPage
                .logIntoApp("viewer1@ashpath.com", "senha123");

        Assert.assertTrue(homePage.isDisplayed());
    }

    @Test
    public void loginViewer_Error() {
        loginPage
                .logIntoApp("viewer1@ashpath.com", "ddd");

        Assert.assertTrue(loginPage.isErrorMsgPresent(unauthorizedMsg));
    }
}
