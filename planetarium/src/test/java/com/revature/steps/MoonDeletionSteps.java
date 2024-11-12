package com.revature.steps;

import com.revature.TestRunner;
import com.revature.utility.Setup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import javax.swing.*;

public class MoonDeletionSteps {

    /**
     * Given the user is "Batman" and is on the homepage
     */
    @Given("the user has logged into Planetarium and is on the home page")
    public void the_user_has_logged_into_Planetarium_and_is_on_the_home_page() {
        Setup.main(new String[]{});
        TestRunner.loginPage.getLoginPage();
        TestRunner.loginPage.inputUsername("Batman");
        TestRunner.loginPage.inputPassword("I am the night");
        TestRunner.loginPage.login();
    }

    /**
     * Inputting the moon name to be deleted.
     * @param moonName the name of a valid moon
     */
    @When("the user inputs moon {string}")
    public void the_user_inputs_moon(String moonName) {
        TestRunner.homePage.deleteCelestial(moonName);
    }

    @Then("the user should see that moon deleted is true")
    public void the_user_should_see_that_moon_deleted_is_true(String docString) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertFalse(TestRunner.homePage.confirmMoon(docString));
    }

    @Then("the user should see that moon deleted is false")
    public void the_user_should_see_that_moon_deleted_is_false(String docString) {
        // Confirm that the moon does not exist anymore
        Assert.assertTrue(TestRunner.homePage.confirmMoon(docString));
    }

    @And("the user clicks the Delete button")
    public void theUserClicksTheDeleteButton() {
        TestRunner.homePage.pressDelete();
    }


    @Then("the user should see {string} in moon deletion")
    public void theUserShouldSeeInMoonDeletion(String moonName, String docString) {
        // Failed to delete Moon with name ${name}
        String expected = "Failed to delete Moon with name " + moonName;

    }
}
