package com.revature.steps;

import com.revature.TestRunner;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.PendingException;

public class SharedSteps {

    // Shared step
    @Given("The user is on the login page {string}")
    public void the_user_is_on_the_login_page(String hostUrl) {
        throw new PendingException();
    }
}
