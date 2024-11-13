package com.revature.steps;

import com.revature.TestRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class SharedSteps {
    @Then("the user should see that moon created is true")
    public void the_user_should_see_that_moon_created_is_true() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Given("the user has logged into Planetarium and is on the home page")
    public void the_user_has_logged_into_Planetarium_and_is_on_the_home_page() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    // Shared step
    @Given("The user is on the login page {string}")
    public void the_user_is_on_the_login_page(String hostUrl) {
        TestRunner.loginPage.getLoginPage();
    }
}
