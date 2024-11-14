package com.revature.steps;

import io.cucumber.java.en.Given;
import com.revature.TestRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.And;
import org.junit.Assert;

public class PlanetandMoonVisibilitySteps {
    @Given("The user is on the login page {string}")
    public void theUserIsOnTheLoginPage(String Host) {
        TestRunner.driver.get(Host);
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

    }


    @Given("The user has logged into the planetarium and has exited the application")
    public void theUserHasLoggedIntoThePlanetariumAndHasExitedTheApplication() {

    }

    @When("When the user inputs the url to the home page <{string}>")
    public void whenTheUserInputsTheUrlToTheHomePage(String url) {
        TestRunner.driver.get(url);
    }

    @Then("User's planets and moons are visible on the home page")
    public void userSPlanetsAndMoonsAreVisibleOnTheHomePage() {
    }

    @When("User performs a successful planet creating operation with the inputted data")
    public void userPerformsASuccessfulPlanetCreatingOperationWithTheInputtedData() {

    }

    @Then("User's original planets and moons and the new planet should be visible")
    public void userSOriginalPlanetsAndMoonsAndTheNewPlanetShouldBeVisible() {
    }

    @When("User's performs a successful planet deletion operation with the inputted data")
    public void userSPerformsASuccessfulPlanetDeletionOperationWithTheInputtedData() {

    }

    @Then("User's planets and moons should be visible, excluding the deleted planet and its moons")
    public void userSPlanetsAndMoonsShouldBeVisibleExcludingTheDeletedPlanetAndItsMoons() {

    }

    @And("User performs a successful moon creation operation")
    public void userPerformsASuccessfulMoonCreationOperation() {

    }

    @Then("User's planets and moons should still be visible \\(including the new moon added).")
    public void userSPlanetsAndMoonsShouldStillBeVisibleIncludingTheNewMoonAdded() {
    }

    @When("User's performs a successful Moon deletion operation with the inputted data")
    public void userSPerformsASuccessfulMoonDeletionOperationWithTheInputtedData() {
    }
}
