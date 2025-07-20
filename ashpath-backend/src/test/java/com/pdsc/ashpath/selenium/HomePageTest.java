package com.pdsc.ashpath.selenium;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.pdsc.ashpath.pages.home.HomePage;

public class HomePageTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void setUpHomePageTest() {
        homePage = loginPage.logIntoApp("helmuth@yahoo.com", validPwd);

        Assert.assertTrue(homePage.isDisplayed(), "Home page should be displayed");
        homePage.hasDataLoaded();
    }

    @Test
    public void verifyHomePageLayout() {
        // Verify all main sections are displayed
        Assert.assertTrue(homePage.isWelcomeSectionDisplayed(),
                "Welcome section should be displayed");
        Assert.assertTrue(homePage.isLatestSectionDisplayed(),
                "Latest deceased section should be displayed");
        Assert.assertTrue(homePage.isCitiesSectionDisplayed(),
                "Cities section should be displayed");
    }

    @Test
    public void verifyWelcomeSection() {
        // Verify welcome section content
        Assert.assertTrue(homePage.isWelcomeSectionDisplayed(),
                "Welcome section should be visible");

        String welcomeTitle = homePage.getWelcomeTitle();
        Assert.assertEquals(welcomeTitle, "Bem-vindo ao AshPath",
                "Welcome title should match expected text");

        String welcomeDescription = homePage.getWelcomeDescription();
        Assert.assertTrue(welcomeDescription.contains("Seu portal para consulta de informações de obituários"),
                "Welcome description should contain expected text");
        Assert.assertTrue(welcomeDescription.contains("🕊️"),
                "Welcome description should contain dove emoji");
    }

    @Test
    public void verifyLatestDeceasedSection() {
        // Verify latest deceased section title
        String latestSectionTitle = homePage.getLatestSectionTitle();
        Assert.assertEquals(latestSectionTitle, "ÚLTIMOS FALECIDOS",
                "Latest section title should match expected text");

        // Check if data is displayed or no-data message is shown
        if (homePage.isLatestCardsGridDisplayed()) {
            // Verify that we don't show more than 5 latest deceased (as per component
            // logic)
            int cardCount = homePage.getLatestCardsCount();
            Assert.assertTrue(cardCount <= 5,
                    "Should display at most 5 latest deceased cards, but found: " + cardCount);
            Assert.assertTrue(cardCount > 0,
                    "Should display at least 1 deceased card when cards grid is visible");

            // Verify each card has proper content
            Assert.assertTrue(homePage.areLatestDeceasedDisplayedCorrectly(),
                    "Latest deceased cards should display name and location");

            // Test individual card content access
            for (int i = 0; i < cardCount; i++) {
                String name = homePage.getLatestCardName(i);
                String location = homePage.getLatestCardLocation(i);

                Assert.assertFalse(name.isEmpty(),
                        "Deceased name should not be empty for card " + i);
                Assert.assertFalse(location.isEmpty(),
                        "Deceased location should not be empty for card " + i);
            }
        } else if (homePage.isLatestNoDataMsgDisplayed()) {
            // Verify no-data message
            String noDataMsg = homePage.getLatestNoDataMessage();
            Assert.assertEquals(noDataMsg, "Nenhum registro recente encontrado.",
                    "No data message should match expected text");
        } else {
            Assert.fail("Either latest cards grid or no-data message should be displayed");
        }
    }

    @Test
    public void verifyCitiesSection() {
        // Verify cities section title
        String citiesSectionTitle = homePage.getCitiesSectionTitle();
        Assert.assertEquals(citiesSectionTitle, "OBITUÁRIOS DISPONÍVEIS",
                "Cities section title should match expected text");

        // Check if data is displayed or no-data message is shown
        if (homePage.isCityCardsGridDisplayed()) {
            int cityCardCount = homePage.getCityCardsCount();
            Assert.assertTrue(cityCardCount > 0,
                    "Should display at least 1 city card when cities grid is visible");

            // Verify cities are displayed alphabetically
            Assert.assertTrue(homePage.areCitiesDisplayedAlphabetically(),
                    "Cities should be displayed in alphabetical order");

            // Test individual city card content access
            for (int i = 0; i < cityCardCount; i++) {
                String cityName = homePage.getCityCardTitle(i);
                Assert.assertFalse(cityName.isEmpty(),
                        "City name should not be empty for card " + i);
            }
        } else if (homePage.isCitiesNoDataMsgDisplayed()) {
            // Verify no-data message
            String noDataMsg = homePage.getCitiesNoDataMessage();
            Assert.assertEquals(noDataMsg, "Nenhuma localidade de obituário disponível no momento.",
                    "Cities no data message should match expected text");
        } else {
            Assert.fail("Either city cards grid or no-data message should be displayed");
        }
    }

    @Test
    public void testCityCardNavigation() {
        // This test only runs if there are city cards to click
        if (homePage.isCityCardsGridDisplayed()) {
            int cityCardCount = homePage.getCityCardsCount();

            if (cityCardCount > 0) {
                // Get the first city name before clicking
                String firstCityName = homePage.getCityCardTitle(0);

                // Click on the first city card
                homePage.clickCityCard(0);

                // Verify navigation occurred (URL should contain 'deceased' and the city name)
                // Note: This assumes the routing is working as expected
                // You might need to adjust this based on your actual routing implementation
                String currentUrl = driver.getCurrentUrl();
                Assert.assertTrue(currentUrl.contains("deceased"),
                        "Should navigate to deceased page after clicking city card");

                // Navigate back to home to continue testing
                driver.navigate().back();
                homePage.hasDataLoaded();
            }
        }
    }

    @Test
    public void testCityCardNavigationByName() {
        // This test ONLY RUNS if there are city cards to click
        if (homePage.isCityCardsGridDisplayed()) {
            int cityCardCount = homePage.getCityCardsCount();

            if (cityCardCount > 0) {
                // Get a city name to test with
                String cityToTest = homePage.getCityCardTitle(0);
                homePage.clickCityCardByName(cityToTest);

                String currentUrl = driver.getCurrentUrl();
                Assert.assertTrue(currentUrl.contains("deceased"),
                        "Should navigate to deceased page after clicking city card by name");

                driver.navigate().back();
                homePage.hasDataLoaded();
            }
        }
    }

    @Test
    public void verifyLoadingStatesHandling() {
        Assert.assertFalse(homePage.isLatestLoadingMsgDisplayed(),
                "Loading message should not be displayed after data has loaded");
        Assert.assertFalse(homePage.isCitiesLoadingMsgDisplayed(),
                "Loading message should not be displayed after data has loaded");
    }

    @Test
    public void verifyResponsiveDesign() {
        // Test that page elements are still accessible when window is resized
        // Simulate mobile viewport
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(375, 667));

        // Verify main sections are still displayed
        Assert.assertTrue(homePage.isWelcomeSectionDisplayed(),
                "Welcome section should be displayed on mobile viewport");
        Assert.assertTrue(homePage.isLatestSectionDisplayed(),
                "Latest section should be displayed on mobile viewport");
        Assert.assertTrue(homePage.isCitiesSectionDisplayed(),
                "Cities section should be displayed on mobile viewport");

        // Restore window size
        driver.manage().window().maximize();
    }

    @Test
    public void verifyDataConsistency() {
        // Verify that the displayed data is consistent
        if (homePage.isCityCardsGridDisplayed() && homePage.isLatestCardsGridDisplayed()) {
            // Check that if we have deceased records, we should also have cities
            int latestCount = homePage.getLatestCardsCount();
            int citiesCount = homePage.getCityCardsCount();

            if (latestCount > 0) {
                Assert.assertTrue(citiesCount > 0,
                        "If there are deceased records, there should be cities available");
            }
        }
    }

    // @Test
    // public void verifyPageAccessibilityForAdmin() {
    //     homePage = loginPage.logIntoApp("mohg.silva@gmail.com", validPwd);
    //     Assert.assertTrue(homePage.isDisplayed(), "Admin should be able to access home page");
    //     Assert.assertTrue(homePage.isWelcomeSectionDisplayed(), "Admin should see welcome section");
    // }

    // @Test
    // public void verifyPageAccessibilityForViewer() {
    //     homePage = loginPage.logIntoApp("jakubfarobek@yahoo.com", validPwd);
    //     Assert.assertTrue(homePage.isDisplayed(), "Viewer should be able to access home page");
    //     Assert.assertTrue(homePage.isWelcomeSectionDisplayed(), "Viewer should see welcome section");
    // }
}
