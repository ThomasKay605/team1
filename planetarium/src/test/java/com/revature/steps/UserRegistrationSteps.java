package com.revature.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.PendingException;

public class UserRegistrationSteps {
    // @When("The user clicks on the Create button")
    // public void the_user_clicks_on_the_Create_button() {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }
    // @Then("The user's account is {string} via a notification")
    // public void the_user_s_account_is_via_a_notification(String string, String docString) {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }
    // @Then("The user's password should not be visible in the notification")
    // public void the_user_s_password_should_not_be_visible_in_the_notification(String docString) {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }
    // @Then("The user should be {string} back to the login page")
    // public void the_user_should_be_back_to_the_login_page(String string, String docString) {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }
    // @Then("The user should receive a notification that their account is {string}")
    // public void the_user_should_receive_a_notification_that_their_account_is(String string, String docString) {
    //     // Write code here that turns the phrase above into concrete actions
    //     throw new io.cucumber.java.PendingException();
    // }

    // Shared step
    @Given("The user is on the login page {string}")
    public void the_user_is_on_the_login_page(String hostUrl) {
        throw new PendingException();
    }

    @When("The user clicks on the Create Account button")
    public void the_user_clicks_on_the_create_account_button() {
        throw new PendingException();
    }

    @When("The user registers the username {string}")
    public void the_user_registers_the_username(String username) {
        throw new PendingException();
    }

    @When("The user registers the password {string}")
    public void the_user_registers_the_password(String password) {
        throw new PendingException();
    }

    @When("The user's password is not visible on the text input field")
    public void the_user_s_password_is_not_visible_on_the_text_input_field() {
        throw new PendingException();
    }

    @When("The user clicks on the Create button")
    public void the_user_clicks_on_the_create_button() {
        throw new PendingException();
    }

    @Then("The user's account is {string} via a notification")
    public void the_user_s_account_is_via_a_notification(String accountCreated) {
        throw new PendingException();
    }

    @Then("The user's password {string} should not be visible in the notification")
    public void the_user_s_password_should_not_be_visible_in_the_notification(String password) {
        throw new PendingException();
    }

    @Then("The user should be {string} back to the login page")
    public void the_user_should_be_back_to_the_login_page(String redirected) {
        throw new PendingException();
    }

    @Then("The user should receive a notification that their account is {string} for {string}") 
    public void the_user_should_receive_a_notification_that_their_account_is_for(
        String accountCreated, String username) {
        throw new PendingException();
    }

}
