package com.revature.steps;

import com.revature.poms.HomePage;
import io.cucumber.java.en.Given;
import com.revature.TestRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;

import java.util.List;
import java.util.ArrayList;

public class PlanetandMoonVisibilitySteps {
    @Given("The user is on the login page {string}")
    public void theUserIsOnTheLoginPage(String Host) {
        TestRunner.loginPage.getLoginPage();
    }

    @When("The user enters the username {string}")
    public void the_user_enters_the_username(String username){
        TestRunner.loginPage.inputUsername(username);
    }

    @And("The user enters the password {string}")
    public void the_user_enters_the_password(String password){
        TestRunner.loginPage.inputPassword(password);
    }

    @And("The user clicks the login button")
    public void the_user_clicks_the_login_button(){
        TestRunner.loginPage.login();
    }

    @Then("The user has logged into Planetarium and is on the home page")
    public void theUserHasLoggedIntoPlanetariumAndIsOnTheHomePage() {
        Assert.assertEquals("Welcome to the Home Page", TestRunner.driver.getTitle());
    }

    @And("The user's planets and moons should be visible to on the home page")
    public void theUserSPlanetsAndMoonsShouldBeVisibleToOnTheHomePage() {
        Assert.assertEquals(4, TestRunner.homePage.getTableRows().size());
        Assert.assertEquals("Earth",TestRunner.homePage.getTableRows().get(0).getName());
        Assert.assertEquals("Mars",TestRunner.homePage.getTableRows().get(1).getName());
        Assert.assertEquals("Luna",TestRunner.homePage.getTableRows().get(2).getName());
        Assert.assertEquals("Titan",TestRunner.homePage.getTableRows().get(3).getName());
    }

    @Given("The user has logged into the planetarium and has exited the application")
    public void theUserHasLoggedIntoThePlanetariumAndHasExitedTheApplication() {
        ((JavascriptExecutor)TestRunner.driver).executeScript("window.open()");
        List<String> tabs = new ArrayList<>(TestRunner.driver.getWindowHandles());
        TestRunner.driver.switchTo().window(tabs.get(1));
        TestRunner.loginPage.getLoginPage();
        Assert.assertEquals("Welcome to the Home Page", TestRunner.driver.getTitle());
    }

    @When("When the user inputs the url to the home page <{string}>")
    public void whenTheUserInputsTheUrlToTheHomePage(String url) {
        TestRunner.homePage.getHomePage();
    }

    @When("User performs a successful planet creating operation with the inputted data")
    public void userPerformsASuccessfulPlanetCreatingOperationWithTheInputtedData() {
        TestRunner.homePage.addingPlanetName("Mercury");
        TestRunner.homePage.addingPlanetImage(System.getenv("PLANET_PHOTO"));
        TestRunner.homePage.pressSubmitButton();
        Assert.assertThrows(TimeoutException.class,()-> {TestRunner.homePage.getAlertText();});
    }

    @Then("User's original planets and moons and the new planet should be visible")
    public void userSOriginalPlanetsAndMoonsAndTheNewPlanetShouldBeVisible() {
        Assert.assertEquals(5, TestRunner.homePage.getTableRows().size());
        Assert.assertEquals("Earth",TestRunner.homePage.getTableRows().get(0).getName());
        Assert.assertEquals("Mars",TestRunner.homePage.getTableRows().get(1).getName());
        Assert.assertEquals("Luna",TestRunner.homePage.getTableRows().get(2).getName());
        Assert.assertEquals("Titan",TestRunner.homePage.getTableRows().get(3).getName());
        Assert.assertEquals("Mercury",TestRunner.homePage.getTableRows().get(4).getName());
        Assert.assertEquals("Planet",TestRunner.homePage.getTableRows().get(4).getCelestialType());
    }

    @When("User's performs a successful planet deletion operation with the inputted data")
    public void userSPerformsASuccessfulPlanetDeletionOperationWithTheInputtedData() {
        TestRunner.homePage.changeToPlanet();
        TestRunner.homePage.deleteCelestial("Mercury");
    }

    @And("User performs a successful moon creation operation")
    public void userPerformsASuccessfulMoonCreationOperation() {
        TestRunner.homePage.addingMoonName("Deimos");
        TestRunner.homePage.addingMoonImage(System.getenv("MOON_PHOTO"));
        TestRunner.homePage.addingPlanetID("2");
        TestRunner.homePage.pressSubmitButton();
        Assert.assertThrows(TimeoutException.class,()-> {TestRunner.homePage.getAlertText();});
    }

    @Then("User's planets and moons should still be visible \\(including the new moon added).")
    public void userSPlanetsAndMoonsShouldStillBeVisibleIncludingTheNewMoonAdded() {
        Assert.assertEquals(5, TestRunner.homePage.getTableRows().size());
        Assert.assertEquals("Earth",TestRunner.homePage.getTableRows().get(0).getName());
        Assert.assertEquals("Mars",TestRunner.homePage.getTableRows().get(1).getName());
        Assert.assertEquals("Luna",TestRunner.homePage.getTableRows().get(2).getName());
        Assert.assertEquals("Titan",TestRunner.homePage.getTableRows().get(3).getName());
        Assert.assertEquals("Deimos",TestRunner.homePage.getTableRows().get(4).getName());
        Assert.assertEquals("moon",TestRunner.homePage.getTableRows().get(4).getCelestialType());
        Assert.assertEquals(2,TestRunner.homePage.getTableRows().get(4).getOwnerId());
    }

    @When("User's performs a successful Moon deletion operation with the inputted data")
    public void userSPerformsASuccessfulMoonDeletionOperationWithTheInputtedData() {
        TestRunner.homePage.changeToMoon();
        TestRunner.homePage.deleteCelestial("Deimos");
    }

    @Then("User's planets and moons should be visible, excluding the deleted planet and its moons")
    public void userSPlanetsAndMoonsShouldBeVisibleExcludingTheDeletedPlanetAndItsMoons() {
        Assert.assertEquals(4, TestRunner.homePage.getTableRows().size());
        Assert.assertEquals("Earth",TestRunner.homePage.getTableRows().get(0).getName());
        Assert.assertEquals("Mars",TestRunner.homePage.getTableRows().get(1).getName());
        Assert.assertEquals("Luna",TestRunner.homePage.getTableRows().get(2).getName());
        Assert.assertEquals("Titan",TestRunner.homePage.getTableRows().get(3).getName());
    }
}
