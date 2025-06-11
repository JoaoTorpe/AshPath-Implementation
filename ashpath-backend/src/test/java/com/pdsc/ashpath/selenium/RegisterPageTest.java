package com.pdsc.ashpath.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

public class RegisterPageTest extends BaseTest {
    // 403
    private String forbbidenMsg = "You don't have permission to create this type of user.";

    @Test
    public void registerAdmin_AsAdmin_Succes() {
        var registerPage = loginPage
                .logInAsAdmin()
                .clickRegisterBtn();

        registerPage.isDisplayed();
        var section = registerPage.selectAdmin();
        section
                .register("franz bonaparta", "franz@gmail.com", "pass123", "pass123")
                .isDisplayed();
    }

    @Test
    public void registerNecrotomist_AsAdmin_Succes() {
        var registerPage = loginPage
                .logInAsAdmin()
                .clickRegisterBtn();

        registerPage.isDisplayed();
        var section = registerPage.selectNecrotomist();
        section
                .register("franz bonaparta", "Forensic Pathology Support", "franz@gmail.com", "pass123", "pass123")
                .isDisplayed();
    }

    @Test
    public void registerViewer_AsAdmin_Succes() {
        var registerPage = loginPage
                .logInAsAdmin()
                .clickRegisterBtn();

        registerPage.isDisplayed();
        var section = registerPage.selectViewer();
        section
                .register("franz bonaparta", "franz@gmail.com", "pass123", "pass123")
                .isDisplayed();
    }

    @Test
    public void registerAdmin_AsNecrotomist_Error() {
        var registerPage = loginPage
                .logInAsNecrotomist()
                .clickRegisterBtn();

        registerPage.isDisplayed();
        var section = registerPage.selectAdmin();
        section.register("franz bonaparta", "franz@gmail.com", "pass123", "pass123");

        Assert.assertTrue(registerPage.isErrorMsgPresent(forbbidenMsg));
    }

    @Test
    public void registerNecrotomist_AsNecrotomist_Error() {
        var registerPage = loginPage
                .logInAsNecrotomist()
                .clickRegisterBtn();

        registerPage.isDisplayed();
        var section = registerPage.selectNecrotomist();
        section.register("franz bonaparta", "Forensic Pathology Support", "franz@gmail.com", "pass123", "pass123");

        Assert.assertTrue(registerPage.isErrorMsgPresent(forbbidenMsg));
    }

    @Test
    public void registerViewer_AsNecrotomist_Succes() {
        var registerPage = loginPage
                .logInAsNecrotomist()
                .clickRegisterBtn();

        registerPage.isDisplayed();
        var section = registerPage.selectViewer();
        section.register("franz bonaparta", "franz@gmail.com", "pass123", "pass123");

        Assert.assertTrue(registerPage.isErrorMsgPresent(forbbidenMsg));
    }

    @Test
    public void registerAdmin_AsViewer_Error() {
        var registerPage = loginPage
                .logInAsViewer()
                .clickRegisterBtn();

        registerPage.isDisplayed();
        var section = registerPage.selectAdmin();
        section.register("franz bonaparta", "franz@gmail.com", "pass123", "pass123");

        Assert.assertTrue(registerPage.isErrorMsgPresent(forbbidenMsg));
    }

    @Test
    public void registerNecrotomist_AsViewer_Error() {
        var registerPage = loginPage
                .logInAsViewer()
                .clickRegisterBtn();

        registerPage.isDisplayed();
        var section = registerPage.selectNecrotomist();
        section.register("franz bonaparta", "Forensic Pathology Support", "franz@gmail.com", "pass123", "pass123");

        Assert.assertTrue(registerPage.isErrorMsgPresent(forbbidenMsg));
    }

    @Test
    public void registerViewer_AsViewer_Error() {
        var registerPage = loginPage
                .logInAsViewer()
                .clickRegisterBtn();

        registerPage.isDisplayed();
        var section = registerPage.selectViewer();
        section.register("franz bonaparta", "franz@gmail.com", "pass123", "pass123");

        Assert.assertTrue(registerPage.isErrorMsgPresent(forbbidenMsg));
    }
}
