package com.revature.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.revature.TestRunner;
import com.revature.poms.LoginPage;
import com.revature.poms.RegistrationPage;

import io.cucumber.java.PendingException;

public class UserRegistrationSteps {

    private static final String EMPTY = "(empty)";

    @When("The user clicks on the Create Account button")
    public void the_user_clicks_on_the_create_account_button() {
        TestRunner.loginPage.goToRegister();
    }

    @When("The user registers the username {string}")
    public void the_user_registers_the_username(String username) {
        if(username.equals(EMPTY)) username = "";
        TestRunner.registrationPage.inputUsername(username);
    }

    @When("The user registers the password {string}")
    public void the_user_registers_the_password(String password) {
        if(password.equals(EMPTY)) password = "";
        TestRunner.registrationPage.inputPassword(password);
    }

    @When("The user's password is not visible on the register input field")
    public void the_user_s_password_is_not_visible_on_the_register_input_field() {
        Assert.assertTrue(TestRunner.registrationPage.isPasswordInvisible());
    }

    @When("The user clicks on the Create button")
    public void the_user_clicks_on_the_create_button() {
        TestRunner.registrationPage.createAccount();
    }

    @Then("The user's account is {string} via a notification and user's password {string} should not be visible")
    public void the_user_s_account_is_via_a_notification_and_user_s_password_should_not_be_visible(
        String accountCreated, String password) {
        boolean isCreated = (accountCreated.equals("created")) ? true : false;
        TestRunner.alertWait.until(ExpectedConditions.alertIsPresent());
        Alert notification = TestRunner.driver.switchTo().alert();
        try {
            String notificationMessage = notification.getText();
            if(isCreated) {
                Assert.assertTrue(notificationMessage.contains("Account created successfully"));
            } else {
                Assert.assertTrue(notificationMessage.contains("Account creation failed"));
            }
            Assert.assertFalse(notificationMessage.contains(password));
        } finally {
            notification.accept();
            TestRunner.alertWait.until(ExpectedConditions.not(
                ExpectedConditions.alertIsPresent()));
        }
    }

    @Then("The user should be {string} back to the login page")
    public void the_user_should_be_back_to_the_login_page(String redirected) {
        boolean isRedirected = (redirected.equals("redirected")) ? true : false;
        if(isRedirected) {
            Assert.assertEquals(LoginPage.TITLE, TestRunner.driver.getTitle());
        } else {
            Assert.assertEquals(RegistrationPage.TITLE, TestRunner.driver.getTitle());
        }

    }

    @Then("The user should receive a notification that their account is {string} for {string}") 
    public void the_user_should_receive_a_notification_that_their_account_is_for(
        String accountCreated, String username) {
        boolean isCreated = (accountCreated.equals("created")) ? true : false;
        if(username.equals(EMPTY)) username = "";
        TestRunner.alertWait.until(ExpectedConditions.alertIsPresent());
        Alert notification = TestRunner.driver.switchTo().alert();
        try {
            String notificationMessage = notification.getText();
            String expectedMessage;
            if(isCreated) {
                expectedMessage = RegistrationPage.SUCCESS_MESSAGE + "\"" + username + "\"";
            } else {
                expectedMessage = RegistrationPage.FAIL_MESSAGE + "\"" + username + "\"";
            }
            Assert.assertEquals(expectedMessage, notificationMessage);
        } finally {
            notification.accept();
            TestRunner.alertWait.until(ExpectedConditions.not(ExpectedConditions.alertIsPresent()));
        }
    }

}
