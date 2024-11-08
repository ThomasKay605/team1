package com.revature.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage {

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;

    @FindBy(id = "passwordInput")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@type='submit']")
    private WebElement submitButton;

    private WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void inputUsername(String username) {
        usernameInput.sendKeys(username);
    }

    public void inputPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void createAccount() {
        submitButton.click();
    }

    // For testing purposes, the user is not meant to press back
    // Filler step for other tests, subject to be removed in "future implementations"
    public void goBackToLoginScreen() {
        driver.navigate().back();
    }
}
