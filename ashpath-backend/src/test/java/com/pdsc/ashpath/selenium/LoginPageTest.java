package com.pdsc.ashpath.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pdsc.ashpath.selenium.utils.FormErrors;

public class LoginPageTest extends BaseTest {
    @Test
    public void loginNecrotomist_EmailField() {
        loginPage.setEmail("1");
        loginPage.isEmailMsgPresent(FormErrors.EMAIL_INVALID);

        loginPage.setEmail("a".repeat(65));
        loginPage.isEmailMsgPresent(FormErrors.EMAIL_MAX_LENGTH);
    }

    @Test  void loginNecrotomist_PasswordField() {
        loginPage.setPassword("1");
        loginPage.isPasswordMsgPresent(FormErrors.PASSWORD_MIN_LENGTH);

        loginPage.setPassword("a".repeat(65));
        loginPage.isPasswordMsgPresent(FormErrors.PASSWORD_MAX_LENGTH);

        loginPage.setPassword("invalidpwd");
        loginPage.isPasswordMsgPresent(FormErrors.PASSWORD_PATTERN);
    }

    @Test
    public void loginNecrotomist_Success() {
        var homePage = loginPage
                .logIntoApp("necrotomista1@ashpath.com", validPwd);

        Assert.assertTrue(homePage.isDisplayed());
    }

    @Test
    public void loginNecrotomist_Error() {
        loginPage.logIntoApp("necrotomista1@ashpath.com", invalidPwd);

        Assert.assertTrue(loginPage.isErrorMsgPresent(FormErrors.UNAUTHORIZED_MSG));
    }

    @Test
    public void loginAdmin_Success() {
        var homePage = loginPage
                .logIntoApp("admin@ashpath.com", validPwd);

        Assert.assertTrue(homePage.isDisplayed());
    }

    @Test
    public void loginAdmin_Error() {
        loginPage
                .logIntoApp("admin@ashpath.com", invalidPwd);

        Assert.assertTrue(loginPage.isErrorMsgPresent(FormErrors.UNAUTHORIZED_MSG));
    }

    @Test
    public void loginViewer_Success() {
        var homePage = loginPage
                .logIntoApp("viewer1@ashpath.com", validPwd);

        Assert.assertTrue(homePage.isDisplayed());
    }

    @Test
    public void loginViewer_Error() {
        loginPage
                .logIntoApp("viewer1@ashpath.com", invalidPwd);

        Assert.assertTrue(loginPage.isErrorMsgPresent(FormErrors.UNAUTHORIZED_MSG));
    }
}
