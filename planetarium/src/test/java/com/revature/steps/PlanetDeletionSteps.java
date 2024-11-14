package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PlanetDeletionSteps {
    @Then("The user inputs the Planet name {string} in the Planet Delete field")
    public void the_user_inputs_the_Planet_name_in_the_Planet_Delete_field(String planetName, String docString) {
        TestRunner.homePage.deleteCelestial(planetName);
    }

    @When("The user clicks on the Delete Planet button")
    public void the_user_clicks_on_the_Delete_Planet_button() {
        TestRunner.homePage.pressDelete();
    }

    @Then("The user should see a result {string} reflected from deleting a Planet {string}")
    public void the_user_should_see_a_result_reflected_from_deleting_a_Planet(String result, String planet, String docString) {
        // For planet inputs with no value
        if(planet.equals("(empty)")) {
            planet = "";
        }

        if(result.equals("The planet was deleted and the table refreshes to no longer have the deleted planet")) {
            boolean confirm = TestRunner.homePage.confirmPlanet(planet);
            Assert.assertFalse(confirm);
        } else {
            // Handle alert and wait until it is present
            TestRunner.alertWait.until(ExpectedConditions.alertIsPresent());
            Alert alert = TestRunner.driver.switchTo().alert();
            try {
                String expectedResult = "Failed to delete planet with name " + planet;
                String actualResult = alert.getText().trim();
                Assert.assertEquals(expectedResult.trim(), actualResult);
            } catch (NoAlertPresentException e) {
                System.out.println(e);
            } finally {
                // Press okay to dismiss the alert
                alert.accept();
                // Wait for the alert to go away before proceeding
                TestRunner.alertWait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
            }
        }
    }
}
