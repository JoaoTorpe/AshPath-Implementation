package com.pdsc.ashpath.selenium;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.pdsc.ashpath.selenium.utils.FormErrors;

public class RegisterPageTest extends BaseTest {
    @Test
    public void register_EmailField() {
        var registerPage = loginPage
                .clickRegisterBtn();

        registerPage.isDisplayed();
        registerPage.selectNecrotomist();

        registerPage.setEmail("1");
        registerPage.isEmailMsgPresent(FormErrors.EMAIL_INVALID_MSG);

        registerPage.setEmail("franz@gmail.com");
        Assert.assertTrue(registerPage.isEmailMsgHidden());
    }

    @Test
    public void register_FullNameField() {
        var registerPage = loginPage
                .clickRegisterBtn();

        registerPage.isDisplayed();
        registerPage.selectNecrotomist();

        registerPage.setFullName("1");
        registerPage.isFullNameMsgPresent(FormErrors.FULL_NAME_MIN_LENGTH_MSG);

        registerPage.setFullName("Franz Bonaparta");
        Assert.assertTrue(registerPage.isFullNameMsgHidden());
    }

    @Test
    public void register_PasswordField() {
        var registerPage = loginPage
                .clickRegisterBtn();

        registerPage.isDisplayed();
        registerPage.selectNecrotomist();

        registerPage.setPassword("1");
        registerPage.isPasswordMsgPresent(FormErrors.PASSWORD_MIN_LENGTH_MSG);

        registerPage.setPassword("invalidpwd");
        registerPage.isPasswordMsgPresent(FormErrors.PASSWORD_PATTERN_MSG);

        registerPage.setPassword(validPwd);
        Assert.assertTrue(registerPage.isPasswordMsgHidden());
    }

    @Test
    public void register_RepeatPasswordField() {
        var registerPage = loginPage
                .clickRegisterBtn();

        registerPage.isDisplayed();
        registerPage.selectNecrotomist();

        registerPage.setPassword(validPwd);

        registerPage.setRepeatPassword("1");
        registerPage.isRepeatPasswordMsgPresent(FormErrors.REPEAT_PASSWORD_MIN_LENGTH_MSG);

        registerPage.setRepeatPassword("invalidpwd");
        registerPage.isRepeatPasswordMsgPresent(FormErrors.REPEAT_PASSWORD_MISMATCH_MSG);

        registerPage.setRepeatPassword(validPwd);
        Assert.assertTrue(registerPage.isRepeatPasswordMsgHidden());
    }

    @Test
    public void register_SpecializationField() {
        var registerPage = loginPage
                .clickRegisterBtn();

        registerPage.isDisplayed();
        registerPage.selectNecrotomist();

        registerPage.setSpecialization("1");
        registerPage.isSpecializationMsgPresent(FormErrors.SPECIALIZATION_MIN_LENGTH_MSG);

        registerPage.setSpecialization("Autopsy Specialist");
        Assert.assertTrue(registerPage.isSpecializationMsgHidden());
    }

    @Test
    public void registerAdmin_AsAdmin_Succes() {
        var registerPage = loginPage
                .clickRegisterBtn();

        registerPage.isDisplayed();
        registerPage.selectAdmin();

        var loginPage = registerPage
                .register("Franz Bonaparta", "franz@gmail.com", validPwd, validPwd, null);

        Assert.assertTrue(loginPage.isDisplayed());
    }

    @Test
    public void registerAdmin_AsNecrotomist_Succes() {
        var registerPage = loginPage
                .clickRegisterBtn();

        registerPage.isDisplayed();
        registerPage.selectNecrotomist();

        var loginPage = registerPage
                .register("Franz Bonaparta", "franz@gmail.com", validPwd, validPwd, "Autopsy Specialist");

        Assert.assertTrue(loginPage.isDisplayed());
    }

    @Test
    public void registerAdmin_AsViewer_Succes() {
        var registerPage = loginPage
                .clickRegisterBtn();

        registerPage.isDisplayed();
        registerPage.selectViewer();

        var loginPage = registerPage
                .register("Franz Bonaparta", "franz@gmail.com", validPwd, validPwd, null);

        Assert.assertTrue(loginPage.isDisplayed());
    }
}
