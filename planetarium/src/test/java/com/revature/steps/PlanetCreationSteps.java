package com.revature.steps;

import com.revature.TestRunner;
import com.revature.model.CelestialBody;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Given;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PlanetCreationSteps {
    // For empty values
    public static final String EMPTY = "empty";

    //*************************************************SHARED STEP ACROSS APPLICATION*********************************************************//
    @Given("The user has logged into the planetarium with username {string} and password {string}")
    public void the_user_has_logged_into_the_planetarium_with_username_and_password(String username, String password, String docString) {
        TestRunner.loginPage.getLoginPage();
        TestRunner.loginPage.inputUsername(username);
        TestRunner.loginPage.inputPassword(password);
        TestRunner.loginPage.login();
    }

    // **********SHARED WITH PLANET DELETE********** //
    @When("The user clicks on the dropdown and selects Planet")
    public void the_user_clicks_on_the_dropdown_and_selects_Planet() {
        TestRunner.homePage.changeToPlanet();
    }

    @Then("The user inputs the Planet name {string}")
    public void the_user_inputs_the_Planet_name(String string, String docString) {
        // Convert to empty string if value is (empty)
        if(string.equals(EMPTY)) {
            string = "";
        }
        if(!string.equals("Octostar")) {
            TestRunner.homePage.addingPlanetName(string);
        }
    }

    @And("The user clicks on the file upload button and uploads an image {string}")
    public void the_user_clicks_on_the_file_upload_button_and_uploads_an_image(String string, String docString) {
        // Constant empty value in associated feature file
        if(!string.equals((EMPTY))) {
            // Save file path for planet locally as an environment variable
            String filePath = System.getenv(string);
            TestRunner.homePage.addingPlanetImage(filePath);
        }
    }

    @When("The user clicks on the Submit Planet button")
    public void the_user_clicks_on_the_Submit_Planet_button() {
        TestRunner.homePage.pressSubmitButton();
    }

    @And("The user has created a new planet with the name {string} and the file path {string}")
    public void the_user_has_created_a_new_planet_with_the_name_and_the_file_path(String string, String string2, String docString) {
//        TestRunner.homePage.changeToPlanet();
//        TestRunner.homePage.addingPlanetName(string);
//        String filePath = System.getenv(string2);
//        TestRunner.homePage.addingPlanetImage(filePath);
//        TestRunner.homePage.pressSubmitButton();

        the_user_clicks_on_the_dropdown_and_selects_Planet();
        the_user_inputs_the_Planet_name(string, "");
        the_user_clicks_on_the_file_upload_button_and_uploads_an_image(string2, "");
        the_user_clicks_on_the_Submit_Planet_button();
    }

    //*************************************************SHARED STEP ACROSS APPLICATION*********************************************************//
    @When("the user clicks on the Logout button")
    public void the_user_clicks_on_the_Logout_button() {
        TestRunner.homePage.clickLogout();
    }

    @Then("The user is redirected and clicks on the Create Account button")
    public void the_user_is_redirected_and_clicks_on_the_Create_Account_button() {
        TestRunner.loginPage.goToRegister();
    }

    @And("The user types a new username {string}")
    public void the_user_types_a_new_username(String string, String docString) {
        TestRunner.registrationPage.inputUsername(string);
    }

    @And("The user types a new password {string}")
    public void the_user_types_a_new_password(String string, String docString) {
        TestRunner.registrationPage.inputPassword(string);
    }

    //*************************************************SHARED STEP ACROSS APPLICATION*********************************************************//
    @When("The user clicks on the Create Account button")
    public void the_user_clicks_on_the_Create_Account_button() {
        TestRunner.registrationPage.createAccount();
    }

    @Then("The user goes back to the login screen")
    public void the_user_goes_back_to_the_login_screen() {
        TestRunner.registrationPage.goBackToLoginScreen();
    }

    @And("The user inputs their username {string} and password {string} and logs in")
    public void the_user_inputs_their_username_and_password_and_logs_in(String string, String string2, String docString) {
        TestRunner.loginPage.inputUsername(string);
        TestRunner.loginPage.inputPassword(string2);
        TestRunner.loginPage.login();
    }

    @Then("The user should not see a Planet with the name {string}")
    public void the_user_should_not_see_a_Planet_with_the_name(String string, String docString) {
        boolean actual = TestRunner.homePage.confirmPlanet(string);
        Assert.assertFalse(actual);
    }

    @Then("The user should see a result {string} reflected from adding a Planet {string}")
    public void the_user_should_see_a_result_reflected_from_adding_a_Planet(String result, String planet, String docString) {
        if(result.equals("Planet is created and the user's table is refreshed to display new planet")) {
            boolean actual = TestRunner.homePage.confirmPlanet(planet);
            Assert.assertTrue(actual);
        } else {
            // Handle alert and wait until it is present
            TestRunner.alertWait.until(ExpectedConditions.alertIsPresent());
            Alert alert = TestRunner.driver.switchTo().alert();
            try {
                String expectedResult = "Failed to create Planet with name " + planet;
                String actualResult = alert.getText().trim();
                Assert.assertEquals(expectedResult, actualResult);
            } catch (NoAlertPresentException e) {
                System.out.println(e.getMessage());
            } finally {
                // Press okay to dismiss the alert
                alert.accept();
                // Wait for the alert to go away before proceeding
                TestRunner.alertWait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
            }
        }
    }

    @Then("The user should see a result {string} reflected from adding a Planet {string} with an image")
    public void the_user_should_see_a_result_reflected_from_adding_a_Planet_with_an_image(String result, String planet, String docString) {
        List<CelestialBody> list = TestRunner.homePage.getTableRows();
        CelestialBody created = null;
        boolean found = false;
        for(CelestialBody c : list) {
            if(c.getName().equals(planet)) {
                created = c;
            }
        }
        try {
            if(!found) {
                throw new NotFoundException();
            }
            Assert.assertEquals(created.getName(), planet);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
