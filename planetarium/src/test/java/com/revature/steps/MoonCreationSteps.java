package com.revature.steps;

import com.revature.TestRunner;
import com.revature.poms.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.TimeoutException;


import static com.revature.TestRunner.driver;
import static com.revature.TestRunner.homePage;

public class MoonCreationSteps {

    private static final String NO_ALERT = "(no alert, table refresh)";

    /**
     * Many steps return a docstring that's like <code>moonName, planetID</code>.
     * This method returns the moon name from the doc string.
     * That also means have moon names with commas will break this function, but that's not my problem now.
     * @param docString
     * @return the moon name extracted from the <code>docString</code>
     */
    private String getMoonNameFromDocString(String docString){
        return docString.substring(0, docString.indexOf(','));
    }

    /**
     * Similar to <code>getMoonNameFromDocString</code> but with Planet ID.
     * @param docString
     * @return the Planet ID from <code>docString</code> as a string
     */
    private String getPlanetIDFromDocString(String docString){
        return docString.substring(docString.indexOf(',') + 2);
    }

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

        homePage.changeToPlanet();
        // have to switch back and forth in order to work
        homePage.changeToMoon();
    }

    @When("the user inputs moon {string} into Moon Name Input textbox")
    public void the_user_inputs_moon_into_Moon_Name_Input_textbox(String moonName) {
        homePage.addingMoonName(moonName);
    }

    /**
     * For whatever reason the argument-less version of this step is only used in moon creation.
     * @author Marcell Fulop
     */
    @Given("The user is on the login page")
    public void the_user_is_on_the_login_page() {
        TestRunner.loginPage.getLoginPage();
    }

    /**
     * Logically equivalent to submitting planet technically.
     */
    @When("the user clicks submit moon")
    public void theUserClicksSubmitMoon() {
        homePage.pressSubmitButton();
    }

    /**
     * Inputs the planet ID into the planet ID textbox. Even though it has to be an integer,
     * Cucumber will work fine if it's a string.
     * @author Marcell Fulop
     * @param planetID the ID of the planet
     */
    @When("the user inputs Planet ID {string}")
    public void theUserInputsPlanetID(String planetID) {
        homePage.addingPlanetID(planetID);
    }

    @When("the user attaches {string}")
    public void theUserAttaches(String moonImageFileName) {
        homePage.addingMoonImage(CELESTIAL_FOLDER + moonImageFileName);
    }


    @Then("the user should see {string} in moon creation")
    public void theUserShouldSeeInMoonCreation(String result, String docString) {
        // are we expecting an alert if no ...
        if (result.equals(NO_ALERT)){
            // try to fail to get alert
            // if it doesn't throw then that means an alert was shown which is not what we want
            Assert.assertThrows(TimeoutException.class, () -> {
                homePage.getAlertText();
                homePage.closeAlert();
            });
        }
        // else we are expecting an alert
        else{
            String expected = "Failed to create Moon orbiting planet " + getPlanetIDFromDocString(docString) +
                    " with name " + getMoonNameFromDocString(docString);
            try {
                Assert.assertEquals(expected, homePage.getAlertText());
                homePage.closeAlert();
            }
            catch (TimeoutException e) {
                Assert.fail("No alert created when expected with " + docString);
            }
        }
    }

    @Then("the user should see that moon created is true")
    public void the_user_should_see_that_moon_created_is_true(String docString) {

        // Write code here that turns the phrase above into concrete actions
        String moonName = getMoonNameFromDocString(docString);
        int planetID;
        try {
            planetID = Integer.parseInt(getPlanetIDFromDocString(docString));
        }
        catch (Exception e){
            Assert.fail("Failed to parse planet id from doc string <" + docString + ">.");
            return;
        }
        Assert.assertTrue(homePage.confirmMoon(moonName, planetID));
    }

    @Then("the user should see that moon created is false")
    public void the_user_should_see_that_moon_created_is_false(String docString) {
        // Write code here that turns the phrase above into concrete actions
        String moonName = getMoonNameFromDocString(docString);

        Assert.assertFalse(homePage.confirmMoon(moonName));
    }

    @Then("the user should see that moon created is true with owner as {string}")
    public void the_user_should_see_that_moon_created_is_true_with_owner_as(String string, String docString) {
        int planetID;
        try{
            planetID = Integer.parseInt(string);
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(homePage.confirmMoon(docString, planetID));
    }

    @Then("the user should see that moon created is false with owner as {string}")
    public void the_user_should_see_that_moon_created_is_false_with_owner_as(String string, String docString) {
        // Write code here that turns the phrase above into concrete actions
        Assert.assertFalse(homePage.confirmMoon(docString));
    }

    @When("the user should see the moon created is true")
    public void the_user_should_see_the_moon_created_is_true(String docString) {
        String moonName = getMoonNameFromDocString(docString);
        int planetID;
        try{
            planetID = Integer.parseInt(getPlanetIDFromDocString(docString));
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
            return;
        }
        Assert.assertTrue(homePage.confirmMoon(moonName, planetID));
    }

    @When("the user acknowledges the account creation alert")
    public void theUserAcknowledgesTheAccountCreationAlert() {
        driver.switchTo().alert().accept();
    }

    @Then("The user should be redirected to homepage")
    public void theUserShouldBeRedirectedToHomepage() {
        Assert.assertEquals(TestRunner.driver.getCurrentUrl(), HomePage.HOME_URL);
    }

    @And("the user should see that the moon {string} visibility is <Moon Visible to Nonowner?>")
    public void theUserShouldSeeThatTheMoonVisibilityIsMoonVisibleToNonowner(String arg0) {
    }
}
