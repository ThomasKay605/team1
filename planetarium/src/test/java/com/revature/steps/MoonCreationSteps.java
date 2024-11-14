package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;

import javax.swing.*;

public class MoonCreationSteps {

    /**
     * Since the team agreed that the environment variable points to a celestial image, I wanted the
     * folder, so I could vary the images. This substring manipulation gives you the file path as
     * <code>C:/.../Celestial Images/</code> meaning that you only have to append the image filename
     * to the variable.
     */
    private final String CELESTIAL_FOLDER = System.getenv("PLANET_PHOTO").substring(0,
            System.getenv("PLANET_PHOTO").lastIndexOf('\\') + 1) ;

    /**
     * Enables the moon tag in the selection. Since the planetarium page opens the moon tag by default
     * but does not show the appropriate input textboxes, we have to change to planet and then change
     * back to moon.
     */
    @When("the user has enabled the Moon tag on the home page")
    public void the_user_has_enabled_the_Moon_tag_on_the_home_page() {

        TestRunner.homePage.changeToPlanet();
        // have to switch back and forth in order to work
        TestRunner.homePage.changeToMoon();
    }

    @When("the user inputs moon {string} into Moon Name Input textbox")
    public void the_user_inputs_moon_into_Moon_Name_Input_textbox(String moonName) {
        TestRunner.homePage.addingMoonName(moonName);
    }

    @Then("the user should see that moon created is <Moon Created?>")
    public void the_user_should_see_that_moon_created_is_Moon_Created(String docString) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attaches .\\/Celestial Images\\/moon-{int}.jpeg")
    public void the_user_attaches_Celestial_Images_moon_jpeg(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user should see that moon created is true")
    public void the_user_should_see_that_moon_created_is_true(String docString) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user should see that moon created is false")
    public void the_user_should_see_that_moon_created_is_false(String docString) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attaches .\\/Celestial Images\\/moon-{int}.jpg")
    public void the_user_attaches_Celestial_Images_moon_jpg(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user should see that moon created is true with owner as {int}")
    public void the_user_should_see_that_moon_created_is_true_with_owner_as(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user should see that moon created is false with owner as {int}")
    public void the_user_should_see_that_moon_created_is_false_with_owner_as(Integer int1) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attaches realistic-moon.png")
    public void the_user_attaches_realistic_moon_png() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attaches \\(none)")
    public void the_user_attaches_none() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user attaches <Moon image>")
    public void the_user_attaches_Moon_image() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user should see the moon created is true")
    public void the_user_should_see_the_moon_created_is_true() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user acknowledges the account creation alert")
    public void the_user_acknowledges_the_account_creation_alert() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }


    @Then("The user should be redirected to {string}")
    public void the_user_should_be_redirected_to(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the user should see that the moon {string} visibility is <Moon visible to nonowner>")
    public void the_user_should_see_that_the_moon_visibility_is_Moon_visible_to_nonowner(String moonName, boolean isVisible) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    /**
     * Logically equivalent to submitting planet technically.
     */
    @When("the user clicks submit moon")
    public void theUserClicksSubmitMoon() {
        TestRunner.homePage.pressSubmitButton();
    }

    @And("the user should see that the moon {string} visibility is <Moon Visible to Nonowner?>")
    public void theUserShouldSeeThatTheMoonVisibilityIsMoonVisibleToNonowner(String arg0) {
    }

    /**
     * Inputs the planet ID into the planet ID textbox. Even though it has to be an integer,
     * Cucumber will work fine if it's a string.
     * @author Marcell Fulop
     * @param planetID the ID of the planet
     */
    @When("the user inputs Planet ID {string}")
    public void theUserInputsPlanetID(String planetID) {
        TestRunner.homePage.addingPlanetID(planetID);
    }

    @When("the user attaches {string}")
    public void theUserAttaches(String moonImageFileName) {
        TestRunner.homePage.addingMoonImage(CELESTIAL_FOLDER + moonImageFileName);
    }


}
