package com.revature.planetarium.repository.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.utility.Setup;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.IOException;
import java.io.OptionalDataException;
import java.nio.file.Files;
import java.util.*;

public class PlanetDaoTest {

    private Planet planet;
    private PlanetDaoImp testClass;

    @BeforeClass
    public static void clearDatabase() {
        Setup.main(new String[]{});
    }

    @Before
    public void init() throws IOException {
        planet = new Planet();

        try {
            File image = new File(System.getenv("PLANET_PATH"));
            byte[] arr = Files.readAllBytes(image.toPath());
            String imageDataAsString = Base64.getEncoder().encodeToString(arr);
            planet.setImageData(imageDataAsString);
            planet.setOwnerId(1);
        } catch(IOException e) {
            System.out.println("Failed to create planet with image data");
        }

        testClass = new PlanetDaoImp();
    }

    public void setPlanetName(String planetName) {
        planet.setPlanetName(planetName);
    }

    // Also Make Negative Tests
    @Test
    public void createPlanetPositive() {
        setPlanetName("Buster Cannon");
        try {
            Optional<Planet> actual = testClass.createPlanet(planet);
            System.out.println(actual.get().getPlanetId());
            Assert.assertEquals(actual.get(), planet);
        } catch(Exception e) {
            Assert.fail("Failed to create planet");
        }
    }

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

    // Also Make Negative Tests
    @Test
    public void testDeletePlanet() {
        setPlanetName("Tristan");
        Optional<Planet> created = testClass.createPlanet(planet);
        String planetName = created.get().getPlanetName();
        Assert.assertTrue(testClass.deletePlanet(planetName));
    }
}