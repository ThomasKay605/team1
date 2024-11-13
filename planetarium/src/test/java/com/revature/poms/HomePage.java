package com.revature.poms;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import com.revature.TestRunner;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.revature.model.CelestialBody;
import com.revature.model.CelestialType;

// Good enough for now
public class HomePage {
    
    private static final int WAIT = 2;

    public static final String TITLE = "Home";

    @FindBy(id = "greeting")
    private WebElement greetings;

    @FindBy(id = "logoutButton")
    private WebElement logoutButton;

    @FindBy(id = "locationSelect")
    private Select moonOrPlanet;

    @FindBy(id = "deleteInput")
    private WebElement deleteInput;

    @FindBy(id = "deleteButton")
    private WebElement deleteButton;

    //@FindBy(id = "planetNameInput")
    private WebElement planetNameInput;

    //@FindBy(id = "planetImageInput")
    private WebElement planetImageInput;

    //@FindBy(id = "moonNameInput")
    private WebElement moonNameInput;

    //@FindBy(id = "orbitedPlanetInput")
    private WebElement orbitedPlanetInput;

    //@FindBy(id = "moonImageInput")
    private WebElement moonImageInput;

    //@FindBy(className = "submit-button")
    private WebElement createButton;

    // Keep?
    @FindBy(xpath = "//tr")
    private List<WebElement> rows;

    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String returnTitle(){
        return greetings.getText();
    }

    public void clickLogout(){
        this.logoutButton.click();
    }

    public void changeToMoon(){
        //moonOrPlanet.selectByVisibleText("Moon");
        moonOrPlanet = new Select(driver.findElement(By.id("locationSelect")));
        moonOrPlanet.selectByValue(CelestialType.MOON.getType());
        this.changeToMoonHelper();
    }

    private void changeToMoonHelper() {
        this.moonNameInput = new WebDriverWait(this.driver, Duration.ofSeconds(WAIT)).until(
            ExpectedConditions.visibilityOfElementLocated(By.id("moonNameInput"))
        );
        this.moonImageInput = new WebDriverWait(this.driver, Duration.ofSeconds(WAIT)).until(
            ExpectedConditions.visibilityOfElementLocated(By.id("moonImageInput"))
        );
        this.orbitedPlanetInput = new WebDriverWait(this.driver, Duration.ofSeconds(WAIT)).until(
            ExpectedConditions.visibilityOfElementLocated(By.id("orbitedPlanetInput"))
        );
        this.createButton = new WebDriverWait(this.driver, Duration.ofSeconds(WAIT)).until(
            ExpectedConditions.elementToBeClickable(By.className("submit-button"))
        );
    }

    public void changeToPlanet(){
        moonOrPlanet = new Select(driver.findElement(By.id("locationSelect")));
        moonOrPlanet.selectByValue(CelestialType.PLANET.getType());
        this.changeToPlanetHelper();
    }

    private void changeToPlanetHelper() {
        this.planetNameInput = new WebDriverWait(this.driver, Duration.ofSeconds(WAIT)).until(
            ExpectedConditions.visibilityOfElementLocated(By.id("planetNameInput"))
        );
        this.planetImageInput = new WebDriverWait(this.driver, Duration.ofSeconds(WAIT)).until(
            ExpectedConditions.visibilityOfElementLocated(By.id("planetImageInput"))
        );
        this.createButton = new WebDriverWait(this.driver, Duration.ofSeconds(WAIT)).until(
            ExpectedConditions.elementToBeClickable(By.className("submit-button"))  
        );
    }

    public void deleteCelestial(String celestialName){
        deleteInput.sendKeys(celestialName);
    }

    public void pressDelete(){
        this.deleteButton.click();
    }

    public void addingPlanetName(String celestialName){
        planetNameInput.sendKeys(celestialName);
    }

    public void addingPlanetImage(String filepath){
        planetImageInput.sendKeys(filepath);
    }

    public void addingMoonName(String celestialName){
        moonNameInput.sendKeys(celestialName);
    }

    public void addingMoonImage(String filepath){
        moonImageInput.sendKeys(filepath);
    }

    public void addingPlanetID(String id){
        orbitedPlanetInput.sendKeys(id);
    }

    public void pressSubmitButton(){
        createButton.click();
    }

    public List<WebElement> getTable(){
        return rows;
    }

    public boolean confirmPlanet(String celestialName){
        List <WebElement> table = driver.findElements(By.xpath("//*[@id='celestialTable']/tbody/tr[position()>1]" ));
        for (WebElement row : table){
            if(row.findElement(By.xpath("//td[3]")).getText().equals(celestialName)&&
                    row.findElement(By.xpath("//td[1]")).getText().equals("planet")){
                return true;
            }
        }
        return false;
    }

    public boolean confirmMoon(String celestialName){
        List <WebElement> table = driver.findElements(By.xpath("//*[@id='celestialTable']/tbody/tr[position()>1]" ));
        for (WebElement row : table){
            if(row.findElement(By.xpath("//td[3]")).getText().equals(celestialName)&&
                    row.findElement(By.xpath("//td[1]")).getText().equals("moon")){
                return true;
            }
        }
        return false;
    }

    public boolean confirmOrbitPlanet(String celestialName, int planetID){
        List <WebElement> table = driver.findElements(By.xpath("//*[@id='celestialTable']/tbody/tr[position()>1]" ));
        for (WebElement row : table){
            if(row.findElement(By.xpath("//td[3]")).getText().equals(celestialName)&&
                    row.findElement(By.xpath("//td[4]")).getText().equals(String.valueOf(planetID))){
                return true;
            }
        }
        return false;
    }

    public List<CelestialBody> getTableRows() {
        List<CelestialBody> celestialBodies = new ArrayList<>();
        List<WebElement> table = new WebDriverWait(this.driver, Duration.ofSeconds(WAIT)).until(
            ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//tr[position()>1]"))
        );
        for(WebElement row : table) {
            List<WebElement> attributes = row.findElements(By.tagName("td"));
            CelestialType type = CelestialType.getCelestialType(attributes.get(0).getText());
            int id = Integer.parseInt(attributes.get(1).getText());
            String name = attributes.get(2).getText();
            int ownerId = Integer.parseInt(attributes.get(3).getText());
            String imgPath = attributes.get(4).getAttribute("src");
            celestialBodies.add(new CelestialBody(type, id, name, ownerId, imgPath));
        }
        return celestialBodies;
    }
   
     // does not work as expected 
     public String getAlertText() {
        TestRunner.alertWait.until(ExpectedConditions.alertIsPresent());
        return TestRunner.driver.switchTo().alert().getText();
    }

   // does not work as expected 
    public void closeAlert() {
        Alert alert = TestRunner.driver.switchTo().alert();
        alert.accept();
    }
}
