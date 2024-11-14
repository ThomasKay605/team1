package com.revature.steps;

import com.revature.TestRunner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.TimeoutException;

import javax.swing.*;

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

        TestRunner.homePage.changeToPlanet();
        // have to switch back and forth in order to work
        TestRunner.homePage.changeToMoon();
    }

    @When("the user inputs moon {string} into Moon Name Input textbox")
    public void the_user_inputs_moon_into_Moon_Name_Input_textbox(String moonName) {
        TestRunner.homePage.addingMoonName(moonName);
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
        TestRunner.homePage.pressSubmitButton();
    }

    /**
     * Inputs the planet ID into the planet ID textbox. Even though it has to be an integer,
     * Cucumber will work fine if it's a string.
     * @author Marcell Fulop
     * @param planetID the ID of the planet
     */
    @When("the user inputs Planet ID {string}")
    public void theUserInputsPlanetID(String planetID) {
        TestRunner.homePage.addingPlanetID(planetID);
    }

    @When("the user attaches {string}")
    public void theUserAttaches(String moonImageFileName) {
        TestRunner.homePage.addingMoonImage(CELESTIAL_FOLDER + moonImageFileName);
    }


    @Then("the user should see {string} in moon creation")
    public void theUserShouldSeeInMoonCreation(String result, String docString) {
        // are we expecting an alert if no ...
        if (result.equals(NO_ALERT)){
            // try to fail to get alert
            // if it doesn't throw then that means an alert was shown which is not what we want
            Assert.assertThrows(TimeoutException.class, () -> {
                TestRunner.homePage.getAlertText();
                TestRunner.homePage.closeAlert();
            });
        }
        // else we are expecting an alert
        else{
            String expected = "Failed to create Moon orbiting planet " + getPlanetIDFromDocString(docString) +
                    " with name " + getMoonNameFromDocString(docString);
            try {
                Assert.assertEquals(expected, TestRunner.homePage.getAlertText());
                TestRunner.homePage.closeAlert();
            }
            catch (TimeoutException e) {
                Assert.fail("No alert created when expected with ");
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
        // JOptionPane.showMessageDialog(null, moonName + "\n<" + String.valueOf(planetID) + ">");
        Assert.assertTrue(TestRunner.homePage.confirmMoon(moonName, planetID));
    }
}
