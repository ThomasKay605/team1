package com.revature.poms;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

// Good enough for now
public class HomePage {
    
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

    @FindBy(id = "planetNameInput")
    private WebElement planetNameInput;

    @FindBy(id = "planetImageInput")
    private WebElement planetImageInput;

    @FindBy(id = "moonNameInput")
    private WebElement moonNameInput;

    @FindBy(id = "orbitedPlanetInput")
    private WebElement orbitedPlanetInput;

    @FindBy(id = "moonImageInput")
    private WebElement moonImageInput;

    @FindBy(className = "submit-button")
    private WebElement createButton;

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
        moonOrPlanet.selectByVisibleText("Moon");
    }

    public void changeToPlanet(){
        moonOrPlanet.selectByVisibleText("Planet");
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
        moonNameInput.sendKeys(filepath);
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
}
