package com.revature.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.TestRunner;
import com.revature.poms.HomePage;
import com.revature.poms.LoginPage;

import io.cucumber.java.PendingException;

public class UserLoginSteps {

    private static final String EMPTY = "(empty)";

    @When("The user inputs username {string}")
    public void the_user_inputs_username(String username) {
        if(username.equals(EMPTY)) username = "";
        TestRunner.loginPage.inputUsername(username);
    }

    @When("The user inputs password {string}")
    public void the_user_inputs_password(String password) {
        if(password.equals(EMPTY)) password = "";
        TestRunner.loginPage.inputPassword(password);
    }

    @When("The user's password is not visible on the login input")
    public void the_user_s_password_is_not_visible_on_the_login_input() {
        Assert.assertEquals(TestRunner.loginPage.isPasswordInvisible(), true);
    }

    @When("The user clicks on the Login button")
    public void the_user_clicks_on_the_login_button() {
        TestRunner.loginPage.login();
    }

    @Then("The user should be {string} and user's password {string} is not visible")
    public void the_user_should_be_and_user_s_password_is_not_visible(
        String authenticated, String password) {
        boolean isAuthenticated = (authenticated.equals("authenticated")) ? true : false;
        if(password.equals(EMPTY)) password = "";
        if(!isAuthenticated) {
            TestRunner.alertWait.until(ExpectedConditions.alertIsPresent());
            Alert loginFail = TestRunner.driver.switchTo().alert();
            try {
                Assert.assertFalse(loginFail.getText().contains(password));
            } finally {
                loginFail.accept();
                TestRunner.alertWait.until(ExpectedConditions.not(
                    ExpectedConditions.alertIsPresent()));
            }
        }
    }

    @Then("The user should be {string} to their home page")
    public void the_user_should_be_to_their_home_page(String redirected) {
        boolean isRedirected = (redirected.equals("redirected")) ? true : false;
        WebDriverWait titleChange = new WebDriverWait(TestRunner.driver, Duration.ofSeconds(2));
        try {
            titleChange.until(ExpectedConditions.titleIs(HomePage.TITLE));
            if(isRedirected) Assert.assertEquals(HomePage.TITLE, TestRunner.driver.getTitle());
            else Assert.fail("User should have not been redirected to the home page");
        } catch(TimeoutException e) {
            if(isRedirected) Assert.fail("User should have been redirected to the home page");
            else Assert.assertEquals(LoginPage.TITLE, TestRunner.driver.getTitle());
        }
    }

    @Then("The user should be {string}")
    public void the_user_should_be(String authenticated) {
        boolean isAuthenticated = (authenticated.equals("authenticated")) ? true : false;
        if(!isAuthenticated) {
            TestRunner.alertWait.until(ExpectedConditions.alertIsPresent());
            Alert loginFail = TestRunner.driver.switchTo().alert();
            try {
                Assert.assertEquals(LoginPage.ERROR_MESSAGE, loginFail.getText());
            } finally {
                loginFail.accept();
                TestRunner.alertWait.until(ExpectedConditions.not(
                    ExpectedConditions.alertIsPresent()));
            }
                   
        }
    }

    @When("The user is {string}")
    public void the_user_is(String authenticated) {
        boolean isAuthenticated = (authenticated.equals("authenticated")) ? true : false;
        if(!isAuthenticated) {
            TestRunner.alertWait.until(ExpectedConditions.alertIsPresent());
            Alert loginFail = TestRunner.driver.switchTo().alert();
            try {
                Assert.assertEquals(LoginPage.ERROR_MESSAGE, loginFail.getText());
            } finally {
                loginFail.accept();
                TestRunner.alertWait.until(ExpectedConditions.not(
                    ExpectedConditions.alertIsPresent()));
            }
        }
    }

    @When("The user is {string} to their home page")
    public void the_user_is_to_their_home_page(String redirected) {
        boolean isRedirected = (redirected.equals("redirected")) ? true : false;
        WebDriverWait titleChange = new WebDriverWait(TestRunner.driver, Duration.ofSeconds(2));
        try {
            titleChange.until(ExpectedConditions.titleIs(HomePage.TITLE));
            if(isRedirected) Assert.assertEquals(HomePage.TITLE, TestRunner.driver.getTitle());
            else Assert.fail("User should have not been redirected to the home page");
        } catch(TimeoutException e) {
            if(isRedirected) Assert.fail("User should have been redirected to the home page");
            else Assert.assertEquals(LoginPage.TITLE, TestRunner.driver.getTitle());
        }
    }

    @When("The user opens a new window")
    public void the_user_opens_a_new_window() {
        ((JavascriptExecutor)TestRunner.driver).executeScript("window.open()");
        List<String> tabs = new ArrayList<>(TestRunner.driver.getWindowHandles());
        TestRunner.driver.switchTo().window(tabs.get(1));
    }

    @When("The user goes to the home page {string}")
    public void the_user_goes_to_the_home_page(String homePageUrl) {
        TestRunner.homePage.getHomePage();
    }

    @Then("The user should be {string} with {string}")
    public void the_user_should_be_with(String onHomePage, String username) {
        boolean isOnHomePage = (onHomePage.equals("on the home page")) ? true : false;
        if(username.equals(EMPTY)) username = "";
        WebDriverWait titleChange = new WebDriverWait(TestRunner.driver, Duration.ofSeconds(2));
        try {
            titleChange.until(ExpectedConditions.titleIs(HomePage.TITLE));
            if(isOnHomePage) {
                Assert.assertEquals(HomePage.TITLE, TestRunner.driver.getTitle());
                String expectedHeading = "Welcome to the Home Page " + username;
                Assert.assertEquals(expectedHeading, TestRunner.homePage.returnTitle());
            } else Assert.fail("The user should have not been redirected to their home page");
        } catch(TimeoutException e) {
            if(isOnHomePage) Assert.fail(
                "The user should have been redirected to their home page");
            else Assert.assertNull(TestRunner.driver.getTitle());
        }
    }
}
