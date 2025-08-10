package com.pdsc.ashpath.pages.createdeceased;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import com.pdsc.ashpath.pages.base.BasePage;

public class CreateDeceasedPage extends BasePage {
    // Form elements
    private By fullnameEl = By.cssSelector("input[formcontrolname='fullname']");
    private By birthDateEl = By.cssSelector("input[formcontrolname='birthDate']");
    private By deathDateEl = By.cssSelector("input[formcontrolname='deathDate']");
    private By causeOfDeathEl = By.cssSelector("input[formcontrolname='causeOfDeath']");
    private By fatherNameEl = By.cssSelector("input[formcontrolname='fatherName']");
    private By motherNameEl = By.cssSelector("input[formcontrolname='motherName']");
    private By cremationEntryEl = By.cssSelector("select[formcontrolname='cremationEntryID']");
    private By graveEl = By.cssSelector("select[formcontrolname='graveID']");
    private By deathCertificateEl = By.cssSelector("input[type='file']");
    private By submitButtonEl = By.cssSelector("button[type='submit']");

    // Error message elements
    private By fullnameMsgEl = By.xpath("//input[@formcontrolname='fullname']/following-sibling::div[@class='error-message']");
    private By birthDateMsgEl = By.xpath("//input[@formcontrolname='birthDate']/following-sibling::div[@class='error-message']");
    private By deathDateMsgEl = By.xpath("//input[@formcontrolname='deathDate']/following-sibling::div[@class='error-message']");
    private By causeOfDeathMsgEl = By.xpath("//input[@formcontrolname='causeOfDeath']/following-sibling::div[@class='error-message']");
    private By fatherNameMsgEl = By.xpath("//input[@formcontrolname='fatherName']/following-sibling::div[@class='error-message']");
    private By motherNameMsgEl = By.xpath("//input[@formcontrolname='motherName']/following-sibling::div[@class='error-message']");
    private By deathCertificateMsgEl = By.xpath("//input[@type='file']/following-sibling::div[@class='error-message']");

    // Feedback message elements
    private By errorFeedbackEl = By.cssSelector(".error-feedback");
    private By successFeedbackEl = By.cssSelector(".success-feedback");

    // Page title
    private By pageTitleEl = By.cssSelector("h1");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.urlContains("necrotomist"));
    }

    public boolean isPageTitleCorrect() {
        return wait.until(ExpectedConditions.textToBePresentInElement(find(pageTitleEl), "Cadastrar Falecido"));
    }

    // Set methods for form fields
    public CreateDeceasedPage setFullname(String fullname) {
        set(fullnameEl, fullname);
        return this;
    }

    public CreateDeceasedPage setBirthDate(String birthDate) {
        set(birthDateEl, birthDate);
        return this;
    }

    public CreateDeceasedPage setDeathDate(String deathDate) {
        set(deathDateEl, deathDate);
        return this;
    }

    public CreateDeceasedPage setCauseOfDeath(String causeOfDeath) {
        set(causeOfDeathEl, causeOfDeath);
        return this;
    }

    public CreateDeceasedPage setFatherName(String fatherName) {
        set(fatherNameEl, fatherName);
        return this;
    }

    public CreateDeceasedPage setMotherName(String motherName) {
        set(motherNameEl, motherName);
        return this;
    }

    public CreateDeceasedPage selectCremationEntry(String optionText) {
        Select cremationSelect = new Select(find(cremationEntryEl));
        cremationSelect.selectByVisibleText(optionText);
        return this;
    }

    public CreateDeceasedPage selectGrave(String optionText) {
        Select graveSelect = new Select(find(graveEl));
        graveSelect.selectByVisibleText(optionText);
        return this;
    }

    public CreateDeceasedPage uploadDeathCertificate(String filePath) {
        find(deathCertificateEl).sendKeys(filePath);
        return this;
    }

    // Validation message methods
    public boolean isFullnameMsgPresent(String msg) {
        return isMsgPresent(fullnameMsgEl, msg);
    }

    public boolean isBirthDateMsgPresent(String msg) {
        return isMsgPresent(birthDateMsgEl, msg);
    }

    public boolean isDeathDateMsgPresent(String msg) {
        return isMsgPresent(deathDateMsgEl, msg);
    }

    public boolean isCauseOfDeathMsgPresent(String msg) {
        return isMsgPresent(causeOfDeathMsgEl, msg);
    }

    public boolean isFatherNameMsgPresent(String msg) {
        return isMsgPresent(fatherNameMsgEl, msg);
    }

    public boolean isMotherNameMsgPresent(String msg) {
        return isMsgPresent(motherNameMsgEl, msg);
    }

    public boolean isDeathCertificateMsgPresent(String msg) {
        return isMsgPresent(deathCertificateMsgEl, msg);
    }

    // Hidden message methods
    public boolean isFullnameMsgHidden() {
        return isMsgHidden(fullnameMsgEl);
    }

    public boolean isBirthDateMsgHidden() {
        return isMsgHidden(birthDateMsgEl);
    }

    public boolean isDeathDateMsgHidden() {
        return isMsgHidden(deathDateMsgEl);
    }

    public boolean isCauseOfDeathMsgHidden() {
        return isMsgHidden(causeOfDeathMsgEl);
    }

    public boolean isFatherNameMsgHidden() {
        return isMsgHidden(fatherNameMsgEl);
    }

    public boolean isMotherNameMsgHidden() {
        return isMsgHidden(motherNameMsgEl);
    }

    public boolean isDeathCertificateMsgHidden() {
        return isMsgHidden(deathCertificateMsgEl);
    }

    // Feedback message methods
    public boolean isErrorFeedbackPresent(String msg) {
        return isMsgPresent(errorFeedbackEl, msg);
    }

    public boolean isSuccessFeedbackPresent(String msg) {
        return isMsgPresent(successFeedbackEl, msg);
    }

    public boolean isErrorFeedbackHidden() {
        return isMsgHidden(errorFeedbackEl);
    }

    public boolean isSuccessFeedbackHidden() {
        return isMsgHidden(successFeedbackEl);
    }

    // Form submission
    public CreateDeceasedPage submitForm() {
        click(submitButtonEl);
        return this;
    }

    public boolean isSubmitButtonEnabled() {
        return find(submitButtonEl).isEnabled();
    }

    public boolean isSubmitButtonDisabled() {
        return !find(submitButtonEl).isEnabled();
    }

    // Complete form submission method
    public CreateDeceasedPage createDeceased(String fullname, String birthDate, String deathDate, 
                                           String causeOfDeath, String fatherName, String motherName, 
                                           String filePath) {
        setFullname(fullname);
        setBirthDate(birthDate);
        setDeathDate(deathDate);
        setCauseOfDeath(causeOfDeath);
        setFatherName(fatherName);
        setMotherName(motherName);
        if (filePath != null && !filePath.isEmpty()) {
            uploadDeathCertificate(filePath);
        }
        submitForm();
        return this;
    }

    // Overloaded method for optional fields
    public CreateDeceasedPage createDeceased(String fullname, String birthDate, String deathDate, 
                                           String causeOfDeath, String fatherName, String motherName, 
                                           String cremationEntry, String grave, String filePath) {
        setFullname(fullname);
        setBirthDate(birthDate);
        setDeathDate(deathDate);
        setCauseOfDeath(causeOfDeath);
        setFatherName(fatherName);
        setMotherName(motherName);
        
        if (cremationEntry != null && !cremationEntry.isEmpty()) {
            selectCremationEntry(cremationEntry);
        }
        
        if (grave != null && !grave.isEmpty()) {
            selectGrave(grave);
        }
        
        if (filePath != null && !filePath.isEmpty()) {
            uploadDeathCertificate(filePath);
        }
        
        submitForm();
        return this;
    }
}
