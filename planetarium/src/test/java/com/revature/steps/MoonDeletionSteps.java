package com.revature.steps;

import com.revature.TestRunner;
import com.revature.utility.Setup;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;


public class MoonDeletionSteps {
    private final String NO_ALERT = "(no alert, table refresh)";
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


    /**
     * This method abstracts several scenarios. One path expects a browser alert and another doesn't.
     * The only way to check if no alert when expecting that I have found has been made to try to switch
     * to it and see a "timeout" exception that means the driver is unable to find the alert. That means
     * setting a high implicit wait time will make this method slow.
     * @author Marcell Fulop
     * @param result either represents no alert or the alert text that should be made
     * @param docString represents the moon name
     */
    @Then("the user should see {string} in moon deletion")
    public void theUserShouldSeeInMoonDeletion(String result, String docString) {
        // Are we expecting an alert?
        if (result.equals(NO_ALERT)){
            Assert.assertThrows(TimeoutException.class, () -> {
                TestRunner.homePage.getAlertText();
                TestRunner.homePage.closeAlert();
            });
        }
        else {
            String expected = "Failed to delete Moon with name " + docString;
            String alert;
            try {
                alert = TestRunner.homePage.getAlertText();
                Assert.assertEquals(expected, alert);
                TestRunner.homePage.closeAlert();
            }
            catch (TimeoutException e){
                // Alert expected, but no alert found
                Assert.fail("Alert expected for deleting " + docString + " but none generated.");
            }

        }


    }
}
