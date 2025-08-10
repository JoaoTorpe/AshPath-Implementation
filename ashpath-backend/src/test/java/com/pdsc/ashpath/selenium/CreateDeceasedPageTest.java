package com.pdsc.ashpath.selenium;

import java.io.File;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pdsc.ashpath.pages.createdeceased.CreateDeceasedPage;
import com.pdsc.ashpath.pages.home.HomePage;
import com.pdsc.ashpath.selenium.utils.FormErrors;

public class CreateDeceasedPageTest extends BaseTest {
    private HomePage homePage;
    private CreateDeceasedPage createDeceasedPage;
    private String testFilePath;
    
    @BeforeMethod
    public void setupCreateDeceasedTest() {
        homePage = loginPage.logInAsAdmin();
        Assert.assertTrue(homePage.isDisplayed());
        
        createDeceasedPage = homePage.navigateToCreateDeceasedPage();
        Assert.assertTrue(createDeceasedPage.isDisplayed());
        Assert.assertTrue(createDeceasedPage.isPageTitleCorrect());
        
        // Setup test file path for PDF upload
        String currentDir = System.getProperty("user.dir");
        testFilePath = Paths.get(currentDir, "src", "test", "resources", "test-death-certificate.pdf").toString();
        
        // Create a dummy PDF file for testing if it doesn't exist
        createTestPDFFile();
    }
    
    private void createTestPDFFile() {
        try {
            File testFile = new File(testFilePath);
            if (!testFile.exists()) {
                testFile.getParentFile().mkdirs();
                testFile.createNewFile();
                // Write minimal PDF header
                try (var writer = new java.io.FileWriter(testFile)) {
                    writer.write("%PDF-1.4\n1 0 obj<</Type/Catalog/Pages 2 0 R>>endobj\n2 0 obj<</Type/Pages/Kids[3 0 R]/Count 1>>endobj\n3 0 obj<</Type/Page/Parent 2 0 R/MediaBox[0 0 612 792]>>endobj\nxref\n0 4\n0000000000 65535 f \n0000000009 00000 n \n0000000058 00000 n \n0000000115 00000 n \ntrailer<</Size 4/Root 1 0 R>>\nstartxref\n186\n%%EOF");
                }
            }
        } catch (Exception e) {
            // Use resources folder file as fallback
            String resourcesDir = System.getProperty("user.dir");
            testFilePath = Paths.get(resourcesDir, "src", "main", "resources", "certidao-de-obito_modelo.pdf").toString();
        }
    }

    @Test
    public void createDeceased_FullnameField_Validation() {
        createDeceasedPage.setFullname("");
        createDeceasedPage.setDeathDate("01/01/2024"); // Trigger validation
        createDeceasedPage.isFullnameMsgPresent(FormErrors.DECEASED_FULLNAME_MIN_LENGTH_MSG);

        createDeceasedPage.setFullname("AB");
        createDeceasedPage.setBirthDate("01/01/1980");
        createDeceasedPage.isFullnameMsgPresent(FormErrors.DECEASED_FULLNAME_MIN_LENGTH_MSG);

        createDeceasedPage.setFullname("João da Silva Santos");
        createDeceasedPage.setBirthDate("01/01/1980");
        Assert.assertTrue(createDeceasedPage.isFullnameMsgHidden());
    }

    @Test
    public void createDeceased_BirthDateField_Validation() {
        createDeceasedPage.setBirthDate("");
        createDeceasedPage.setDeathDate("01/01/2024"); // Trigger validation
        createDeceasedPage.isBirthDateMsgPresent(FormErrors.DECEASED_BIRTH_DATE_REQUIRED_MSG);

        createDeceasedPage.setBirthDate("32/13/2024");
        createDeceasedPage.setDeathDate("01/01/2024");
        createDeceasedPage.isBirthDateMsgPresent(FormErrors.DECEASED_BIRTH_DATE_INVALID_MSG);

        createDeceasedPage.setBirthDate("01/01/2030");
        createDeceasedPage.setDeathDate("01/01/2024");
        createDeceasedPage.isBirthDateMsgPresent(FormErrors.DECEASED_BIRTH_DATE_NO_FUTURE_MSG);

        createDeceasedPage.setBirthDate("01/01/1980");
        createDeceasedPage.setDeathDate("01/01/2024");
        Assert.assertTrue(createDeceasedPage.isBirthDateMsgHidden());
    }

    @Test
    public void createDeceased_DeathDateField_Validation() {
        createDeceasedPage.setDeathDate("");
        createDeceasedPage.setCauseOfDeath("Causas naturais"); // Trigger validation
        createDeceasedPage.isDeathDateMsgPresent(FormErrors.DECEASED_DEATH_DATE_REQUIRED_MSG);

        createDeceasedPage.setDeathDate("32/13/2024");
        createDeceasedPage.setCauseOfDeath("Causas naturais");
        createDeceasedPage.isDeathDateMsgPresent(FormErrors.DECEASED_DEATH_DATE_INVALID_MSG);

        // Test future death date
        createDeceasedPage.setDeathDate("01/01/2030");
        createDeceasedPage.setCauseOfDeath("Causas naturais");
        createDeceasedPage.isDeathDateMsgPresent(FormErrors.DECEASED_DEATH_DATE_NO_FUTURE_MSG);

        // Test valid death date
        createDeceasedPage.setDeathDate("01/01/2024");
        createDeceasedPage.setCauseOfDeath("Causas naturais");
        Assert.assertTrue(createDeceasedPage.isDeathDateMsgHidden());
    }

    @Test
    public void createDeceased_CauseOfDeathField_Validation() {
        createDeceasedPage.setCauseOfDeath("");
        createDeceasedPage.setFatherName("José da Silva"); // Trigger validation
        createDeceasedPage.isCauseOfDeathMsgPresent(FormErrors.DECEASED_CAUSE_OF_DEATH_REQUIRED_MSG);

        createDeceasedPage.setCauseOfDeath("AB");
        createDeceasedPage.setFatherName("José da Silva");
        createDeceasedPage.isCauseOfDeathMsgPresent(FormErrors.DECEASED_CAUSE_OF_DEATH_MIN_LENGTH_MSG);

        createDeceasedPage.setCauseOfDeath("Causas naturais");
        createDeceasedPage.setFatherName("José da Silva");
        Assert.assertTrue(createDeceasedPage.isCauseOfDeathMsgHidden());
    }

    @Test
    public void createDeceased_FatherNameField_Validation() {
        createDeceasedPage.setFatherName("AB");
        createDeceasedPage.setMotherName("Maria da Silva"); // Trigger validation
        createDeceasedPage.isFatherNameMsgPresent(FormErrors.DECEASED_FATHER_NAME_MIN_LENGTH_MSG);

        createDeceasedPage.setFatherName("José da Silva Santos");
        createDeceasedPage.setMotherName("Maria da Silva");
        Assert.assertTrue(createDeceasedPage.isFatherNameMsgHidden());
    }

    @Test
    public void createDeceased_MotherNameField_Validation() {
        createDeceasedPage.setMotherName("AB");
        createDeceasedPage.setFatherName("José da Silva"); // Trigger validation
        createDeceasedPage.isMotherNameMsgPresent(FormErrors.DECEASED_MOTHER_NAME_MIN_LENGTH_MSG);

        createDeceasedPage.setMotherName("Maria da Silva Santos");
        createDeceasedPage.setFatherName("José da Silva");
        Assert.assertTrue(createDeceasedPage.isMotherNameMsgHidden());
    }

    @Test
    public void createDeceased_DeathCertificateField_Validation() {
        createDeceasedPage.setFullname("João da Silva Santos");
        createDeceasedPage.setBirthDate("01/01/1980");
        createDeceasedPage.setDeathDate("01/01/2024");
        createDeceasedPage.setCauseOfDeath("Causas naturais");
        createDeceasedPage.setFatherName("José da Silva Santos");
        createDeceasedPage.setMotherName("Maria da Silva Santos");

        Assert.assertTrue(createDeceasedPage.isSubmitButtonDisabled());
    }

    @Test
    public void createDeceased_SubmitButtonState() {
        Assert.assertTrue(createDeceasedPage.isSubmitButtonDisabled());

        createDeceasedPage.setFullname("João da Silva Santos");
        createDeceasedPage.setBirthDate("01/01/1980");
        createDeceasedPage.setDeathDate("01/01/2024");
        createDeceasedPage.setCauseOfDeath("Causas naturais");
        createDeceasedPage.setFatherName("José da Silva Santos");
        createDeceasedPage.setMotherName("Maria da Silva Santos");
        createDeceasedPage.uploadDeathCertificate(testFilePath);

        Assert.assertTrue(createDeceasedPage.isSubmitButtonEnabled());
    }

    @Test
    public void createDeceased_CompleteFormSubmission_Success() {
        createDeceasedPage.setFullname("João da Silva Santos");
        createDeceasedPage.setBirthDate("01/01/1980");
        createDeceasedPage.setDeathDate("01/01/2024");
        createDeceasedPage.setCauseOfDeath("Causas naturais");
        createDeceasedPage.setFatherName("José da Silva Santos");
        createDeceasedPage.setMotherName("Maria da Silva Santos");
        createDeceasedPage.uploadDeathCertificate(testFilePath);

        createDeceasedPage.submitForm();
        createDeceasedPage.isSuccessFeedbackPresent(FormErrors.DECEASED_SUCCESS_MSG);
    }

    @Test
    public void createDeceased_WithOptionalFields_Success() {
        createDeceasedPage.setFullname("Maria das Dores Silva");
        createDeceasedPage.setBirthDate("15/03/1975");
        createDeceasedPage.setDeathDate("20/07/2024");
        createDeceasedPage.setCauseOfDeath("Acidente vascular cerebral");
        createDeceasedPage.setFatherName("Antônio Silva Santos");
        createDeceasedPage.setMotherName("Rosa das Dores Silva");
        
        try {
            createDeceasedPage.selectCremationEntry("Nenhuma / Não se aplica");
            createDeceasedPage.selectGrave("Nenhuma / Não se aplica");
        } catch (Exception e) {
        }
        
        createDeceasedPage.uploadDeathCertificate(testFilePath);
        createDeceasedPage.submitForm();
        createDeceasedPage.isSuccessFeedbackPresent(FormErrors.DECEASED_SUCCESS_MSG);
    }

    @Test
    public void createDeceased_UsingHelperMethod_Success() {
        createDeceasedPage.createDeceased(
            "Carlos Eduardo Silva", 
            "10/05/1965", 
            "15/12/2023", 
            "Complicações cardíacas", 
            "Eduardo Silva Santos", 
            "Ana Maria Silva",
            testFilePath
        );

        createDeceasedPage.isSuccessFeedbackPresent(FormErrors.DECEASED_SUCCESS_MSG);
    }

    @Test
    public void createDeceased_InvalidDateOrder_Validation() {
        createDeceasedPage.setFullname("João da Silva Santos");
        createDeceasedPage.setBirthDate("01/01/2020");
        createDeceasedPage.setDeathDate("01/01/2019"); // Death before birth
        createDeceasedPage.setCauseOfDeath("Causas naturais");
        createDeceasedPage.setFatherName("José da Silva Santos");
        createDeceasedPage.setMotherName("Maria da Silva Santos");

        createDeceasedPage.isDeathDateMsgPresent(FormErrors.DECEASED_DEATH_DATE_AFTER_BIRTH_MSG);
        
        Assert.assertTrue(createDeceasedPage.isSubmitButtonDisabled());
        
        createDeceasedPage.setDeathDate("01/01/2021"); // Death after birth
        
        Assert.assertTrue(createDeceasedPage.isDeathDateMsgHidden());
        createDeceasedPage.uploadDeathCertificate(testFilePath);
        Assert.assertTrue(createDeceasedPage.isSubmitButtonEnabled());
    }

    @Test
    public void createDeceased_SameBirthAndDeathDate_Validation() {
        // Test same birth and death date (edge case)
        createDeceasedPage.setFullname("João da Silva Santos");
        createDeceasedPage.setBirthDate("01/01/2020");
        createDeceasedPage.setDeathDate("01/01/2020"); // Same date
        createDeceasedPage.setCauseOfDeath("Causas naturais");
        createDeceasedPage.setFatherName("José da Silva Santos");
        createDeceasedPage.setMotherName("Maria da Silva Santos");

        // Should show error message since death must be AFTER birth
        createDeceasedPage.isDeathDateMsgPresent(FormErrors.DECEASED_DEATH_DATE_AFTER_BIRTH_MSG);
        
        // Form should be invalid
        Assert.assertTrue(createDeceasedPage.isSubmitButtonDisabled());
    }

    @Test
    public void createDeceased_MaxLengthFields_Validation() {
        // Test maximum length validation for text fields
        String longName = "A".repeat(129); // Exceeds maxlength of 128
        String validName = "A".repeat(128); // Exactly at maxlength
        
        createDeceasedPage.setFullname(longName);
        createDeceasedPage.setBirthDate("01/01/1980");
        
        // The input should truncate or limit to maxlength
        // Verify that the form still functions correctly
        createDeceasedPage.setFullname(validName);
        createDeceasedPage.setBirthDate("01/01/1980");
        createDeceasedPage.setDeathDate("01/01/2024");
        createDeceasedPage.setCauseOfDeath("Causas naturais");
        createDeceasedPage.setFatherName("José da Silva Santos");
        createDeceasedPage.setMotherName("Maria da Silva Santos");
        createDeceasedPage.uploadDeathCertificate(testFilePath);

        Assert.assertTrue(createDeceasedPage.isSubmitButtonEnabled());
    }
}
