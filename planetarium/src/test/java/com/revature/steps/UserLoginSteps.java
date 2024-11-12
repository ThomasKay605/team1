package com.revature.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import io.cucumber.java.PendingException;

public class UserLoginSteps {
    // @When("The user's password is not visible on the text input field")
    // public void the_user_s_password_is_not_visible_on_the_text_input_field() {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }

    // @Then("The user should be {string} and user's password is not visible")
    // public void the_user_should_be_and_user_s_password_is_not_visible(String string, String docString) {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }

    // @Then("The user should be {string} to their home page")
    // public void the_user_should_be_to_their_home_page(String string) {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }

    // @Then("The user should be {string}")
    // public void the_user_should_be(String string) {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }

    // @When("The user is {string}")
    // public void the_user_is(String string) {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }

    // @When("The user is {string} to their home page")
    // public void the_user_is_to_their_home_page(String string) {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }

    // @When("The user opens a new window")
    // public void the_user_opens_a_new_window() {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }

    // @When("The user goes to the home page {string}")
    // public void the_user_goes_to_the_home_page(String string, String docString) {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }

    // @Then("The user should be on the home page")
    // public void the_user_should_be_on_the_home_page(String docString) {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }

    // @Then("The user should be not on the home page")
    // public void the_user_should_be_not_on_the_home_page(String docString) {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }

    // Shared step
    @Given("The user is on the login page {string}")
    public void the_user_is_on_the_login_page(String hostUrl) {
        throw new PendingException();
    }

    @When("The user inputs username {string}")
    public void the_user_inputs_username(String username) {
        throw new PendingException();
    }

    @When("The user inputs password {string}")
    public void the_user_inputs_password(String password) {
        throw new PendingException();
    }

    @When("The user's password is not visible on the text input field")
    public void the_user_s_password_is_not_visible_on_the_text_input_field() {
        throw new PendingException();
    }

    @When("The user clicks on the Login button")
    public void the_user_clicks_on_the_login_button() {
        throw new PendingException();
    }

    @Then("The user should be {string} and user's password {string} is not visible")
    public void the_user_should_be_and_user_s_password_is_not_visible(
        String authenticated, String password) {
        throw new PendingException();
    }

    @Then("The user should be {string} to their home page")
    public void the_user_should_be_to_their_home_page(String redirected) {
        throw new PendingException();
    }

    @Then("The user should be {string}")
    public void the_user_should_be(String authenticated) {
        throw new PendingException();
    }

    @When("The user is {string}")
    public void the_user_is(String authenticated) {
        throw new PendingException();
    }

    @When("The user is {string} to their home page")
    public void the_user_is_to_their_home_page(String redirected) {
        throw new PendingException();
    }

    @When("The user opens a new window")
    public void the_user_opens_a_new_window() {
        throw new PendingException();
    }

    @When("The user goes to the home page {string}")
    public void the_user_goes_to_the_home_page(String homePageUrl) {
        throw new PendingException();
    }

    @Then("The user should be {string} with {string}")
    public void the_user_should_be_with(String onHomePage, String username) {
        throw new PendingException();
    }
}
