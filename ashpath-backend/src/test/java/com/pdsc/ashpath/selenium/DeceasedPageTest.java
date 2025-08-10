package com.pdsc.ashpath.selenium;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pdsc.ashpath.pages.deceased.DeceasedPage;
import com.pdsc.ashpath.pages.home.HomePage;

public class DeceasedPageTest extends BaseTest {
        private HomePage homePage;
        private DeceasedPage deceasedPage;

        @BeforeMethod
        public void setUpDeceasedPageTest() {
                homePage = loginPage.logIntoApp("helmuth@yahoo.com", validPwd);
                Assert.assertTrue(homePage.isDisplayed(), "Home page should be displayed");

                deceasedPage = homePage.navigateToDeceasedPage();

                Assert.assertTrue(deceasedPage.isDisplayed(), "Deceased page should be displayed");
                deceasedPage.waitForDataToLoad();
        }

        @Test
        public void verifyPageLayout() {
                Assert.assertTrue(deceasedPage.isPageTitleDisplayed(),
                                "Page title should be displayed");
                Assert.assertEquals(deceasedPage.getPageTitle(), "Consulta de Falecidos",
                                "Page title should match expected text");

                Assert.assertTrue(deceasedPage.isFilterSectionDisplayed(),
                                "Filter section should be displayed");
                Assert.assertEquals(deceasedPage.getFilterTitle(), "Filtros",
                                "Filter section title should match expected text");

                Assert.assertTrue(deceasedPage.isResultsTableDisplayed(),
                                "Results table should be displayed");
        }

        @Test
        public void verifyFilterSection() {
                Assert.assertTrue(deceasedPage.isFilterButtonDisplayed(),
                                "Filter button should be displayed");
                Assert.assertTrue(deceasedPage.isClearButtonDisplayed(),
                                "Clear button should be displayed");

                deceasedPage.setStartDate("01/01/2020");
                deceasedPage.setEndDate("31/12/2024");
                deceasedPage.setGraveLocation("Cemetery A");

                Assert.assertEquals(deceasedPage.getStartDate(), "01/01/2020",
                                "Start date should be set correctly");
                Assert.assertEquals(deceasedPage.getEndDate(), "31/12/2024",
                                "End date should be set correctly");
                Assert.assertEquals(deceasedPage.getGraveLocation(), "Cemetery A",
                                "Grave location should be set correctly");
        }

        @Test
        public void verifyTableStructure() {
                Assert.assertTrue(deceasedPage.hasTableHeaders(),
                                "Table should have headers");
                Assert.assertTrue(deceasedPage.validateTableHeaders(),
                                "Table headers should match expected values");

                List<WebElement> headers = deceasedPage.getTableHeaders();
                Assert.assertEquals(headers.size(), 7,
                                "Table should have 7 columns");

                String[] expectedHeaders = { "Nome", "Data Nasc.", "Data Falec.", "Causa da Morte", "Status",
                                "Localização", "Ações" };
                for (int i = 0; i < expectedHeaders.length; i++) {
                        Assert.assertEquals(headers.get(i).getText(), expectedHeaders[i],
                                        String.format("Header %d should be '%s'", i + 1, expectedHeaders[i]));
                }
        }

        @Test
        public void verifyDataDisplay() {
                if (deceasedPage.hasDataInTable()) {

                        Assert.assertTrue(deceasedPage.getTableRowCount() > 0,
                                        "Table should have data rows");

                        List<WebElement> detailsButtons = deceasedPage.getDetailsButtons();
                        List<WebElement> certificateButtons = deceasedPage.getCertificateButtons();
                        int rowCount = deceasedPage.getTableRowCount();

                        Assert.assertEquals(detailsButtons.size(), rowCount,
                                        "Should have details button for each row");
                        Assert.assertEquals(certificateButtons.size(), rowCount,
                                        "Should have certificate button for each row");

                } else {
                        Assert.assertTrue(deceasedPage.isNoResultsMessageDisplayed(),
                                        "No results message should be displayed when no data");
                        Assert.assertEquals(deceasedPage.getNoResultsMessage(), "Nenhum falecido encontrado",
                                        "No results message should match expected text");
                }
        }

        @Test
        public void verifyFilterFunctionality() {
                deceasedPage.setStartDate("01/01/2024");
                deceasedPage.setEndDate("31/12/2024");
                deceasedPage.clickFilterButton();
                deceasedPage.waitForDataToLoad();

                deceasedPage.clearAllFilters();
                deceasedPage.setGraveLocation("Ibura");
                deceasedPage.clickFilterButton();
                deceasedPage.waitForDataToLoad();

                deceasedPage.clearAllFilters();
                Assert.assertEquals(deceasedPage.getStartDate(), "",
                                "Start date should be cleared");
                Assert.assertEquals(deceasedPage.getEndDate(), "",
                                "End date should be cleared");
                Assert.assertEquals(deceasedPage.getGraveLocation(), "",
                                "Grave location should be cleared");
        }

        @Test
        public void verifyApplyFiltersMethod() {
                deceasedPage.applyFilters("01/01/2024", "31/12/2024", "Ibura");

                Assert.assertEquals(deceasedPage.getStartDate(), "01/01/2024",
                                "Start date should be applied");
                Assert.assertEquals(deceasedPage.getEndDate(), "31/12/2024",
                                "End date should be applied");
                Assert.assertEquals(deceasedPage.getGraveLocation(), "Ibura",
                                "Grave location should be applied");
        }

        @Test
        public void verifyDetailsModal() {
                if (!deceasedPage.hasDataInTable()) {
                        return;
                }

                deceasedPage.clickFirstDetailsButton();
                deceasedPage.waitForModalToAppear();

                Assert.assertTrue(deceasedPage.isDetailsModalDisplayed(),
                                "Details modal should be displayed");
                Assert.assertEquals(deceasedPage.getDetailsModalTitle(), "Detalhes do Falecido",
                                "Details modal title should match expected text");

                Assert.assertNotNull(deceasedPage.getModalFullname(),
                                "Modal should display fullname");
                Assert.assertNotNull(deceasedPage.getModalBirthDate(),
                                "Modal should display birth date");
                Assert.assertNotNull(deceasedPage.getModalDeathDate(),
                                "Modal should display death date");
                Assert.assertNotNull(deceasedPage.getModalCauseOfDeath(),
                                "Modal should display cause of death");
                Assert.assertNotNull(deceasedPage.getModalFatherName(),
                                "Modal should display father name");
                Assert.assertNotNull(deceasedPage.getModalMotherName(),
                                "Modal should display mother name");
                Assert.assertNotNull(deceasedPage.getModalStatus(),
                                "Modal should display status");

                deceasedPage.closeDetailsModalWithX();
                deceasedPage.waitForModalToDisappear();

                Assert.assertFalse(deceasedPage.isDetailsModalDisplayed(),
                                "Details modal should be closed");
        }

        @Test
        public void verifyDetailsModalCloseButton() {
                if (!deceasedPage.hasDataInTable()) {
                        return;
                }

                deceasedPage.clickFirstDetailsButton();
                deceasedPage.waitForModalToAppear();

                Assert.assertTrue(deceasedPage.isDetailsModalDisplayed(),
                                "Details modal should be displayed");

                deceasedPage.closeDetailsModalWithButton();
                deceasedPage.waitForModalToDisappear();

                Assert.assertFalse(deceasedPage.isDetailsModalDisplayed(),
                                "Details modal should be closed after clicking footer button");
        }

        @Test
        public void verifyCertificateModal() {
                if (!deceasedPage.hasDataInTable()) {
                        return;
                }

                deceasedPage.clickFirstCertificateButton();
                deceasedPage.waitForDataToLoad();

                if (deceasedPage.isPdfModalDisplayed()) {
                        Assert.assertEquals(deceasedPage.getPdfModalTitle(), "Certificado de Óbito",
                                        "PDF modal title should match expected text");

                        boolean pdfLoaded = deceasedPage.isPdfIframeDisplayed();
                        boolean errorShown = deceasedPage.isPdfErrorMessageDisplayed();

                        Assert.assertTrue(pdfLoaded || errorShown,
                                        "Either PDF should be loaded or error message should be shown");

                        if (errorShown) {
                                Assert.assertEquals(deceasedPage.getPdfErrorMessage(),
                                                "Não foi possível carregar o certificado de óbito.",
                                                "Error message should match expected text");
                        }

                        deceasedPage.closeDetailsModalWithX();
                        deceasedPage.waitForModalToDisappear();
                }
        }

        // DEPRECATED
        // @Test
        // public void verifyStatusBadges() {
        //         if (!deceasedPage.hasDataInTable()) {
        //                 return;
        //         }

        //         List<WebElement> crematedBadges = deceasedPage.getCrematedBadges();
        //         List<WebElement> gravedBadges = deceasedPage.getGravedBadges();
        //         List<WebElement> waitingBadges = deceasedPage.getWaitingCremationBadges();

        //         int totalBadges = crematedBadges.size() + gravedBadges.size() + waitingBadges.size();
        //         int totalRows = deceasedPage.getTableRowCount();

        //         Assert.assertEquals(totalBadges, totalRows,
        //                         "Each row should have exactly one status badge");
        // }

        @Test
        public void verifyLocationColumnLogic() {
                if (!deceasedPage.hasDataInTable()) {
                        return;
                }

                List<WebElement> statusColumns = deceasedPage.getStatusColumns();
                List<WebElement> locationColumns = deceasedPage.getLocationColumns();

                for (int i = 0; i < statusColumns.size() && i < locationColumns.size(); i++) {
                        String status = statusColumns.get(i).getText();
                        String location = locationColumns.get(i).getText();

                        if (status.contains("GRAVED")) {
                                Assert.assertTrue(!location.isEmpty(),
                                                "GRAVED status should have location or dash");
                        } else {
                                Assert.assertEquals(location, "-",
                                                "Non-graved status should show dash in location column");
                        }
                }
        }

        @Test
        public void verifyRowActions() {
                if (!deceasedPage.hasDataInTable()) {
                        return;
                }

                int rowCount = deceasedPage.getTableRowCount();

                int testRows = Math.min(3, rowCount);

                for (int i = 0; i < testRows; i++) {
                        deceasedPage.clickDetailsButtonForRow(i);
                        deceasedPage.waitForModalToAppear();

                        Assert.assertTrue(deceasedPage.isDetailsModalDisplayed(),
                                        String.format("Details modal should open for row %d", i + 1));

                        deceasedPage.closeDetailsModalWithX();
                        deceasedPage.waitForModalToDisappear();

                        Assert.assertFalse(deceasedPage.isDetailsModalDisplayed(),
                                        String.format("Details modal should close for row %d", i + 1));
                }
        }

        @Test
        public void verifyDateMaskFormatting() {
                deceasedPage.setStartDate("01012020");
                String formattedStartDate = deceasedPage.getStartDate();

                Assert.assertTrue(formattedStartDate.contains("/"),
                                "Date should be formatted with slashes");

                deceasedPage.setEndDate("31122024");
                String formattedEndDate = deceasedPage.getEndDate();

                Assert.assertTrue(formattedEndDate.contains("/"),
                                "End date should be formatted with slashes");
        }

        @Test
        public void verifyEmptyStateHandling() {
                deceasedPage.applyFilters("01/01/1900", "02/01/1900", "NonExistentLocation");

                Assert.assertTrue(deceasedPage.isNoResultsMessageDisplayed(),
                                "No results message should be displayed for empty results");
                Assert.assertEquals(deceasedPage.getNoResultsMessage(), "Nenhum falecido encontrado",
                                "No results message should match expected text");

                deceasedPage.clearAllFilters();
        }

        @Test
        public void verifyModalBackdrop() {
                if (!deceasedPage.hasDataInTable()) {
                        return;
                }

                deceasedPage.clickFirstDetailsButton();
                deceasedPage.waitForModalToAppear();

                Assert.assertTrue(deceasedPage.isModalBackdropDisplayed(),
                                "Modal backdrop should be displayed when modal is open");

                deceasedPage.closeDetailsModalWithX();
                deceasedPage.waitForModalToDisappear();
        }
}
