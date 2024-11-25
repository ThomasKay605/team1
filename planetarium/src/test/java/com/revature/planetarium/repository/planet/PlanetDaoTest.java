package com.revature.planetarium.repository.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.utility.Setup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class PlanetDaoTest {

    public static final String NON_UNIQUE_NAME = "Mars";
    public static final String PATH_FILE_JPG = "PLANET_PATH";
    public static final String PATH_FILE_PNG = "PLANET_PATH_PNG";
    public static final int PLANET_OWNER_ID = 1;

    private Planet planet;
    private PlanetDaoImp testClass;

    @BeforeClass
    public static void clearDatabase() {
        Setup.main(new String[]{});
    }

    @Before
    public void init() {
        planet = new Planet();
        planet.setImageData(convertImage(PATH_FILE_JPG));
        planet.setOwnerId(PLANET_OWNER_ID);
        testClass = new PlanetDaoImp();
    }

    public void setPlanetName(String planetName) {
        planet.setPlanetName(planetName);
    }

    public String convertImage(String file) {
        String imageDataAsString = "";
        try {
            File image = new File(System.getenv(file));
            byte[] arr = Files.readAllBytes(image.toPath());
            imageDataAsString = Base64.getEncoder().encodeToString(arr);
            return imageDataAsString;
        } catch(IOException e) {
            System.out.println("Failed to create planet with image data");
        } catch(IllegalArgumentException e) {
            System.out.println("Image type is not of JPG");
        }
        return imageDataAsString;
    }
    @Test
    public void createPlanetPositive() {
        setPlanetName("Buster Cannon");
        try {
            Optional<Planet> actual = testClass.createPlanet(planet);
            Assert.assertEquals(actual.get(), planet);
        } catch(Exception e) {
            Assert.fail("Failed to create planet");
        }
    }

    // Use Case: Planet Character Limit Requirement
    @Test
    public void createPlanetNegativeEmptyPlanetName() {
        setPlanetName("");
        Optional<Planet> actual = testClass.createPlanet(planet);
        Assert.assertEquals(actual.get(), Optional.empty());
    }

    @Test
    public void createPlanetPositiveOneCharPlanetName() {
        setPlanetName("a");
        Optional<Planet> actual = testClass.createPlanet(planet);
        Assert.assertEquals(actual.get(), planet);
    }

    @Test
    public void createPlanetPositiveThirtyCharPlanetName() {
        setPlanetName("Do not listen to the Narrator!");
        Optional<Planet> actual = testClass.createPlanet(planet);
        Assert.assertEquals(actual.get(), planet);
    }

    @Test
    public void createPlanetNegativeThirtyOneCharPlanetName() {
        setPlanetName("I am the Narrator of this story");
        try {
            Optional<Planet> actual = testClass.createPlanet(planet);
            Assert.assertEquals(actual.get(), Optional.empty());
        } catch (Exception e) {
            Assert.fail("Is throwing a SQLiteException; should also be returning an empty Optional. Source code should be catching it");
        }
    }

    //************************************************************************************************************//

    // Use Case: Planet Unique Name Requirement
    @Test
    public void createPlanetPositiveUniqueName() {
        setPlanetName("Cyndaquil");
        Optional<Planet> actual = testClass.createPlanet(planet);
        Assert.assertEquals(actual.get(), planet);
    }

    @Test
    public void createPlanetNegativeNonUniqueName() {
        setPlanetName(NON_UNIQUE_NAME);
        Optional<Planet> actual = testClass.createPlanet(planet);
        Assert.assertEquals(actual.get(), Optional.empty());
    }

    //************************************************************************************************************//

    // Use Case: Planet Has an Optional Image Requirement
    @Test
    public void createPlanetWithImageTypeJPG() {
        setPlanetName("Treeko");
        Optional<Planet> actual = testClass.createPlanet(planet);
        Assert.assertEquals(actual.get(), planet);
    }

    @Test
    public void createPlanetWithImageTypePNG() {
        try {
            Planet p = new Planet();
            p.setPlanetName("Grovyle");
            p.setImageData(PATH_FILE_PNG);
            Optional<Planet> actual = testClass.createPlanet(p);
            Assert.assertEquals(actual.get(), p);
        } catch (Exception e) {
            Assert.fail("No logic on converting png to a byte array");
        }
    }

    @Test
    public void createPlanetWithoutImage() {
        try {
            Planet p = new Planet();
            p.setPlanetName("Sceptile");
            Optional<Planet> actual = testClass.createPlanet(p);
            Assert.assertEquals(actual.get(), p);
        } catch (Exception e) {
            Assert.fail("Is throwing a SQLiteException; Should be able to create a planet without an associated image");
        }
    }

    //************************************************************************************************************//

    @Test
    public void readPlanet() {
        try {
            Optional<Planet> actual = testClass.readPlanet("Earth");
            Assert.assertNotNull(actual.get());
        } catch (Exception e) {
            Assert.fail("Failed to find the planet");
        }
    }

    @Test
    public void testReadPlanet() {
        try {
            Optional<Planet> actual = testClass.readPlanet(1);
            Assert.assertNotNull(actual.get());
        } catch (Exception e) {
            Assert.fail("Failed to find the planet");
        }
    }

    @Test
    public void readAllPlanets() {
        List<Planet> actualList = testClass.readAllPlanets();
        for(Planet p : actualList) {
            System.out.println("ID: " + p.getPlanetId() + "    Planet Name: " + p.getPlanetName());
        }
        Assert.assertNotNull(actualList);
    }

    @Test
    public void readPlanetsByOwner() {
        List<Planet> actualList = testClass.readPlanetsByOwner(1);
        Assert.assertNotNull(actualList);
    }

    @Test
    public void updatePlanet() {
        setPlanetName("Doug");
        Optional<Planet> created = testClass.createPlanet(planet);
        Planet newPlanet = created.get();
        planet.setPlanetId(newPlanet.getPlanetId());
        planet.setOwnerId(newPlanet.getOwnerId());
        String planetName = "Jupiter";
        setPlanetName(planetName);
        Optional<Planet> actual = testClass.updatePlanet(planet);
        Assert.assertEquals(actual.get().getPlanetName(), planetName);
    }

    @Test
    public void deletePlanet() {
        setPlanetName("Kentucky");
        Optional<Planet> created = testClass.createPlanet(planet);
        int planetID = created.get().getPlanetId();
        Assert.assertTrue(testClass.deletePlanet(planetID));
    }

    // Use Case: Planet Ownership Deletion Requirement
    @Test
    public void deleteMyPlanetPositive() {
        setPlanetName("Articuno");
        Optional<Planet> created = testClass.createPlanet(planet);
        String planetName = created.get().getPlanetName();
        Assert.assertTrue(testClass.deletePlanet(planetName));
    }

    @Test
    public void deleteYourPlanetNegative() {
        setPlanetName("Articuno");
        planet.setOwnerId(PLANET_OWNER_ID+1);
        Optional<Planet> created = testClass.createPlanet(planet);
        String planetName = created.get().getPlanetName();
        Assert.assertThrows(PlanetFail.class, () -> testClass.deletePlanet(planetName));
    }

    //************************************************************************************************************//

}