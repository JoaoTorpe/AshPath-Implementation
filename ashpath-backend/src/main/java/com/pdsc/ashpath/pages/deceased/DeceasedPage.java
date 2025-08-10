package com.pdsc.ashpath.pages.deceased;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pdsc.ashpath.pages.base.BasePage;

public class DeceasedPage extends BasePage {
    private final By pageTitle = By.xpath("//h1[normalize-space(text())='Consulta de Falecidos']");

    private final By filterSection = By.className("filter-section");
    private final By filterTitle = By.cssSelector(".filter-section .card-title");

    private final By startDateInput = By.id("startDate");
    private final By endDateInput = By.id("endDate");
    private final By graveLocationInput = By.id("graveLocation");

    private final By filterButton = By.xpath("//button[normalize-space(text())='Filtrar']");
    private final By clearButton = By.xpath("//button[normalize-space(text())='Limpar']");

    private final By resultsTable = By.cssSelector(".table-responsive .table");
    private final By tableHeaders = By.xpath("//thead/tr/th");
    private final By tableRows = By.xpath("//tbody/tr");
    private final By noResultsMessage = By.xpath("//td[normalize-space(text())='Nenhum falecido encontrado']");

    private final By nameColumn = By.xpath("//tbody/tr/td[1]");
    private final By birthDateColumn = By.xpath("//tbody/tr/td[2]");
    private final By deathDateColumn = By.xpath("//tbody/tr/td[3]");
    private final By causeOfDeathColumn = By.xpath("//tbody/tr/td[4]");
    private final By statusColumn = By.xpath("//tbody/tr/td[5]");
    private final By locationColumn = By.xpath("//tbody/tr/td[6]");
    private final By actionsColumn = By.xpath("//tbody/tr/td[7]");

    private final By detailsButtons = By.xpath("//button[normalize-space(text())='Detalhes']");
    private final By certificateButtons = By.xpath("//button[normalize-space(text())='Certificado']");

    private final By crematedBadge = By.xpath("//tbody/tr/td[5]//span[contains(@class, 'bg-success')]");
    private final By gravedBadge = By.xpath("//tbody/tr/td[5]//span[contains(@class, 'bg-primary')]");
    private final By waitingCremationBadge = By.xpath("//tbody/tr/td[5]//span[contains(@class, 'bg-warning')]");

    private final By detailsModal = By.cssSelector(".modal[style*='display: block']");
    private final By detailsModalTitle = By.xpath("//h5[normalize-space(text())='Detalhes do Falecido']");
    private final By detailsModalCloseButton = By.className("btn-close");
    private final By detailsModalCloseFooterButton = By.xpath("//div[@class='modal-footer']//button[normalize-space(text())='Fechar']");

    private final By modalFullname = By.xpath("//p/strong[contains(text(), 'Nome Completo:')]");
    private final By modalBirthDate = By.xpath("//p/strong[contains(text(), 'Data de Nascimento:')]");
    private final By modalDeathDate = By.xpath("//p/strong[contains(text(), 'Data de Falecimento:')]");
    private final By modalCauseOfDeath = By.xpath("//p/strong[contains(text(), 'Causa da Morte:')]");
    private final By modalFatherName = By.xpath("//p/strong[contains(text(), 'Nome do Pai:')]");
    private final By modalMotherName = By.xpath("//p/strong[contains(text(), 'Nome da Mãe:')]");
    private final By modalStatus = By.xpath("//p/strong[contains(text(), 'Status:')]");
    private final By modalCremationEntry = By.xpath("//p/strong[contains(text(), 'Entrada de Crematório:')]");

    private final By pdfModal = By.cssSelector(".modal[style*='display: block'] .modal-xl");
    private final By pdfModalTitle = By.xpath("//h5[normalize-space(text())='Certificado de Óbito']");
    private final By pdfIframe = By.tagName("iframe");
    private final By pdfErrorMessage = By.xpath(
            "[contains(@class, 'alert-danger') and contains(text(), 'Não foi possível carregar o certificado de óbito')]");

    private final By modalBackdrop = By.xpath("//div[@data-testid='modal-backdrop']");

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.urlContains("deceased"));
    }

    public boolean isPageTitleDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPageTitle() {
        return find(pageTitle).getText();
    }

    public boolean isFilterSectionDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(filterSection)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getFilterTitle() {
        return find(filterTitle).getText();
    }

    public void setStartDate(String date) {
        set(startDateInput, date);
    }

    public void setEndDate(String date) {
        set(endDateInput, date);
    }

    public void setGraveLocation(String location) {
        set(graveLocationInput, location);
    }

    public String getStartDate() {
        return find(startDateInput).getAttribute("value");
    }

    public String getEndDate() {
        return find(endDateInput).getAttribute("value");
    }

    public String getGraveLocation() {
        return find(graveLocationInput).getAttribute("value");
    }

    public void clickFilterButton() {
        click(filterButton);
    }

    public void clickClearButton() {
        click(clearButton);
    }

    public boolean isFilterButtonDisplayed() {
        return find(filterButton).isDisplayed();
    }

    public boolean isClearButtonDisplayed() {
        return find(clearButton).isDisplayed();
    }

    public boolean isResultsTableDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(resultsTable)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getTableHeaders() {
        return driver.findElements(tableHeaders);
    }

    public List<WebElement> getTableRows() {
        return driver.findElements(tableRows);
    }

    public int getTableRowCount() {
        return getTableRows().size();
    }

    public boolean isNoResultsMessageDisplayed() {
        try {
            return find(noResultsMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getNoResultsMessage() {
        return find(noResultsMessage).getText();
    }

    public List<WebElement> getNameColumns() {
        return driver.findElements(nameColumn);
    }

    public List<WebElement> getBirthDateColumns() {
        return driver.findElements(birthDateColumn);
    }

    public List<WebElement> getDeathDateColumns() {
        return driver.findElements(deathDateColumn);
    }

    public List<WebElement> getCauseOfDeathColumns() {
        return driver.findElements(causeOfDeathColumn);
    }

    public List<WebElement> getStatusColumns() {
        return driver.findElements(statusColumn);
    }

    public List<WebElement> getLocationColumns() {
        return driver.findElements(locationColumn);
    }

    public List<WebElement> getActionsColumns() {
        return driver.findElements(actionsColumn);
    }

    public List<WebElement> getDetailsButtons() {
        return driver.findElements(detailsButtons);
    }

    public List<WebElement> getCertificateButtons() {
        return driver.findElements(certificateButtons);
    }

    public void clickFirstDetailsButton() {
        List<WebElement> buttons = getDetailsButtons();
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public void clickFirstCertificateButton() {
        List<WebElement> buttons = getCertificateButtons();
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public void clickDetailsButtonForRow(int rowIndex) {
        List<WebElement> buttons = getDetailsButtons();
        if (rowIndex < buttons.size()) {
            buttons.get(rowIndex).click();
        }
    }

    public void clickCertificateButtonForRow(int rowIndex) {
        List<WebElement> buttons = getCertificateButtons();
        if (rowIndex < buttons.size()) {
            buttons.get(rowIndex).click();
        }
    }

    public List<WebElement> getCrematedBadges() {
        return driver.findElements(crematedBadge);
    }

    public List<WebElement> getGravedBadges() {
        return driver.findElements(gravedBadge);
    }

    public List<WebElement> getWaitingCremationBadges() {
        return driver.findElements(waitingCremationBadge);
    }

    public boolean isDetailsModalDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(detailsModal)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getDetailsModalTitle() {
        return find(detailsModalTitle).getText();
    }

    public void closeDetailsModalWithX() {
        click(detailsModalCloseButton);
    }

    public void closeDetailsModalWithButton() {
        click(detailsModalCloseFooterButton);
    }

    public String getModalFullname() {
        return find(modalFullname).getText();
    }

    public String getModalBirthDate() {
        return find(modalBirthDate).getText();
    }

    public String getModalDeathDate() {
        return find(modalDeathDate).getText();
    }

    public String getModalCauseOfDeath() {
        return find(modalCauseOfDeath).getText();
    }

    public String getModalFatherName() {
        return find(modalFatherName).getText();
    }

    public String getModalMotherName() {
        return find(modalMotherName).getText();
    }

    public String getModalStatus() {
        return find(modalStatus).getText();
    }

    public boolean isModalCremationEntryDisplayed() {
        try {
            return find(modalCremationEntry).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalCremationEntry() {
        return find(modalCremationEntry).getText();
    }

    public boolean isPdfModalDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(pdfModal)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPdfModalTitle() {
        return find(pdfModalTitle).getText();
    }

    public boolean isPdfIframeDisplayed() {
        try {
            return find(pdfIframe).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isPdfErrorMessageDisplayed() {
        try {
            return find(pdfErrorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getPdfErrorMessage() {
        return find(pdfErrorMessage).getText();
    }

    public void waitForDataToLoad() {
        wait.until(ExpectedConditions.presenceOfElementLocated(resultsTable));
    }

    public void waitForModalToAppear() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(detailsModal));
    }

    public void waitForModalToDisappear() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(detailsModal));
    }

    public void waitForPdfModalToAppear() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pdfModal));
    }

    public boolean isModalBackdropDisplayed() {
        try {
            return find(modalBackdrop).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void applyFilters(String startDate, String endDate, String location) {
        if (startDate != null && !startDate.isEmpty()) {
            setStartDate(startDate);
        }
        if (endDate != null && !endDate.isEmpty()) {
            setEndDate(endDate);
        }
        if (location != null && !location.isEmpty()) {
            setGraveLocation(location);
        }
        clickFilterButton();
        waitForDataToLoad();
    }

    public void clearAllFilters() {
        clickClearButton();
        waitForDataToLoad();
    }

    public boolean hasTableHeaders() {
        List<WebElement> headers = getTableHeaders();
        return headers.size() == 7;
    }

    public boolean validateTableHeaders() {
        List<WebElement> headers = getTableHeaders();
        if (headers.size() != 7)
            return false;

        String[] expectedHeaders = { "Nome", "Data Nasc.", "Data Falec.", "Causa da Morte", "Status", "Localização",
                "Ações" };

        for (int i = 0; i < expectedHeaders.length; i++) {
            if (!headers.get(i).getText().equals(expectedHeaders[i])) {
                return false;
            }
        }
        return true;
    }

    public boolean hasDataInTable() {
        return getTableRowCount() > 0 && !isNoResultsMessageDisplayed();
    }
}
