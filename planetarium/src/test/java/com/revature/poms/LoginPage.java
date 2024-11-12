package com.revature.poms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    public static final String TITLE = "Planetarium Login";

    public static final String LOGIN_URL = "http://localhost:8080/";

    public static final String ERROR_MESSAGE = "login attempt failed: please try again";

    @FindBy(id = "usernameInput")
    private WebElement usernameInput;

    @FindBy(id = "passwordInput")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[3]")
    private WebElement loginButton;

    @FindBy(partialLinkText = "Create")
    private WebElement accountCreateLink;

    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void getLoginPage() {
        this.driver.get(LOGIN_URL);
    }

    public void inputUsername(String username) {
        this.usernameInput.sendKeys(username);
    }

    public void inputPassword(String password) {
        this.passwordInput.sendKeys(password);
    }

    public void login() {
        this.loginButton.click();
    }

    public void goToRegister() {
        this.accountCreateLink.click();
        // String registerLink = this.accountCreateLink.getAttribute("href");
        // this.driver.navigate.to(registerLink);
    }

}
