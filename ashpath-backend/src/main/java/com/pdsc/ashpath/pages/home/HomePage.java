package com.pdsc.ashpath.pages.home;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.pdsc.ashpath.pages.base.BasePage;
import com.pdsc.ashpath.pages.deceased.DeceasedPage;

public class HomePage extends BasePage {

    // Locators for home page elements
    private final By welcomeSection = By.className("welcome-section");
    private final By welcomeTitle = By.cssSelector(".welcome-section h1");
    private final By welcomeDescription = By.cssSelector(".welcome-section p");

    private final By latestSection = By.className("latest-section");
    private final By latestSectionTitle = By.cssSelector(".latest-section h2");
    private final By latestCardsGrid = By.className("latest-cards-grid");
    private final By latestCards = By.className("latest-card");
    private final By latestCardName = By.cssSelector(".latest-card h3");
    private final By latestCardLocation = By.cssSelector(".latest-card p");
    private final By latestNoDataMsg = By.cssSelector(".latest-section .no-data");
    private final By latestLoadingMsg = By.cssSelector(".latest-section .loading");

    private final By citiesSection = By.className("cities-section");
    private final By citiesSectionTitle = By.cssSelector(".cities-section h2");
    private final By cityCardsGrid = By.className("city-cards-grid");
    private final By cityCards = By.className("city-card");
    private final By cityCardTitle = By.cssSelector(".city-card h3");
    private final By citiesNoDataMsg = By.cssSelector(".cities-section .no-data");
    private final By citiesLoadingMsg = By.cssSelector(".cities-section .loading");

    public DeceasedPage navigateToDeceasedPage() {
        driver.get("http://localhost:4200/#/deceased");
        return new DeceasedPage();
    }

    public boolean isDisplayed() {
        return wait.until(ExpectedConditions.urlContains("home"));
    }

    // Welcome section methods
    public boolean isWelcomeSectionDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeSection)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getWelcomeTitle() {
        return find(welcomeTitle).getText();
    }

    public String getWelcomeDescription() {
        return find(welcomeDescription).getText();
    }

    // Latest deceased section methods
    public boolean isLatestSectionDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(latestSection)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLatestSectionTitle() {
        return find(latestSectionTitle).getText();
    }

    public boolean isLatestCardsGridDisplayed() {
        try {
            return find(latestCardsGrid).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getLatestCards() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(latestCardsGrid));
        return driver.findElements(latestCards);
    }

    public int getLatestCardsCount() {
        return getLatestCards().size();
    }

    public String getLatestCardName(int index) {
        List<WebElement> cards = getLatestCards();
        if (index < cards.size()) {
            return cards.get(index).findElement(By.tagName("h3")).getText();
        }
        return "";
    }

    public String getLatestCardLocation(int index) {
        List<WebElement> cards = getLatestCards();
        if (index < cards.size()) {
            return cards.get(index).findElement(By.tagName("p")).getText();
        }
        return "";
    }

    public boolean isLatestNoDataMsgDisplayed() {
        try {
            return find(latestNoDataMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isLatestLoadingMsgDisplayed() {
        try {
            return find(latestLoadingMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getLatestNoDataMessage() {
        return find(latestNoDataMsg).getText();
    }

    // Cities section methods
    public boolean isCitiesSectionDisplayed() {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(citiesSection)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCitiesSectionTitle() {
        return find(citiesSectionTitle).getText();
    }

    public boolean isCityCardsGridDisplayed() {
        try {
            return find(cityCardsGrid).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public List<WebElement> getCityCards() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cityCardsGrid));
        return driver.findElements(cityCards);
    }

    public int getCityCardsCount() {
        return getCityCards().size();
    }

    public String getCityCardTitle(int index) {
        List<WebElement> cards = getCityCards();
        if (index < cards.size()) {
            return cards.get(index).findElement(By.tagName("h3")).getText();
        }
        return "";
    }

    public void clickCityCard(int index) {
        List<WebElement> cards = getCityCards();
        if (index < cards.size()) {
            cards.get(index).click();
        }
    }

    public void clickCityCardByName(String cityName) {
        List<WebElement> cards = getCityCards();
        for (WebElement card : cards) {
            String cardTitle = card.findElement(By.tagName("h3")).getText();
            if (cardTitle.equals(cityName)) {
                card.click();
                break;
            }
        }
    }

    public boolean isCitiesNoDataMsgDisplayed() {
        try {
            return find(citiesNoDataMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCitiesLoadingMsgDisplayed() {
        try {
            return find(citiesLoadingMsg).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCitiesNoDataMessage() {
        return find(citiesNoDataMsg).getText();
    }

    public boolean hasDataLoaded() {
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(latestCardsGrid),
                    ExpectedConditions.visibilityOfElementLocated(latestNoDataMsg),
                    ExpectedConditions.visibilityOfElementLocated(cityCardsGrid),
                    ExpectedConditions.visibilityOfElementLocated(citiesNoDataMsg)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean areLatestDeceasedDisplayedCorrectly() {
        if (isLatestCardsGridDisplayed()) {
            List<WebElement> cards = getLatestCards();
            // Verify that we have at most 5 cards (as per component logic)
            if (cards.size() > 5)
                return false;

            // Verify each card has name and location
            for (WebElement card : cards) {
                String name = card.findElement(By.tagName("h3")).getText();
                String location = card.findElement(By.tagName("p")).getText();
                if (name.isEmpty() || location.isEmpty()) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean areCitiesDisplayedAlphabetically() {
        if (isCityCardsGridDisplayed()) {
            List<WebElement> cards = getCityCards();
            String previousCity = "";

            for (WebElement card : cards) {
                String currentCity = card.findElement(By.tagName("h3")).getText();
                if (!previousCity.isEmpty() && currentCity.compareTo(previousCity) < 0) {
                    return false;
                }
                previousCity = currentCity;
            }
            return true;
        }
        return false;
    }
}
