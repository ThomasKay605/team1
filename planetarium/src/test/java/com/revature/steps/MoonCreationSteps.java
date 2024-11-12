package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.junit.Assert;

import javax.swing.*;

public class MoonCreationSteps {


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

    @When("The user inputs username {string}")
    public void the_user_inputs_username(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("The user inputs password {string}")
    public void the_user_inputs_password(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("The user clicks on the Login button")
    public void the_user_clicks_on_the_Login_button() {
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

    @When("the user clicks on the {string} button")
    public void the_user_clicks_on_the_button(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("The user clicks on the Create Account button")
    public void the_user_clicks_on_the_Create_Account_button() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("The user registers the username {string}")
    public void the_user_registers_the_username(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("The user registers the password {string}")
    public void the_user_registers_the_password(String string) {
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

    @When("the user clicks submit moon")
    public void theUserClicksSubmitMoon() {
        TestRunner.homePage.pressSubmitButton();
    }

    @And("the user should see that the moon {string} visibility is <Moon Visible to Nonowner?>")
    public void theUserShouldSeeThatTheMoonVisibilityIsMoonVisibleToNonowner(String arg0) {
    }

}
