
package com.revature;

import java.time.Duration;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.poms.*;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "com.revature.steps",
        features = "classpath:features",
        plugin = {"pretty","json:src/test/resources/json-report.json", "html:/src/test/resources/html-report.html"}
)
public class TestRunner {

    private static final int TIME_TO_WAIT = 2;
    
    public static WebDriver driver;

    public static LoginPage loginPage;

    public static RegistrationPage registrationPage;

    public static HomePage homePage;

    public static WebDriverWait alertWait;

    @BeforeClass
    public static void setup() {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage = new HomePage(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME_TO_WAIT));
        alertWait = new WebDriverWait(driver, Duration.ofSeconds(TIME_TO_WAIT));
    }

    @AfterClass
    public static void teardown() {
        if(driver != null) driver.quit();
    }

}
