package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PlanetDeletionSteps {
    @Then("The user inputs the Planet name {string} in the Planet Delete field")
    public void the_user_inputs_the_Planet_name_in_the_Planet_Delete_field(String planetName, String docString) {
        TestRunner.homePage.deleteCelestial(planetName);
    }

    @When("The user clicks on the Delete Planet button")
    public void the_user_clicks_on_the_Delete_Planet_button() {
        TestRunner.homePage.pressDelete();
    }

    @Then("The user should see a result {string} reflected from deleting a Planet")
    public void the_user_should_see_a_result_reflected_from_deleting_a_Planet(String result, String docString) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}
