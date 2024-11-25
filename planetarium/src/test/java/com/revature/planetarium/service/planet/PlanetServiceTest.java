package com.revature.planetarium.service.planet;

import com.revature.planetarium.entities.Planet;
import com.revature.planetarium.exceptions.PlanetFail;
import com.revature.planetarium.repository.planet.PlanetDaoImp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;
import java.util.Optional;

public class PlanetServiceTest {

    public static final String NON_UNIQUE_NAME = "Mars";
    public static final String PATH_FILE_JPG = "PLANET_PATH";
    public static final int PLANET_OWNER_ID = 1;


    private static PlanetDaoImp planetDao;
    private static PlanetService service;
    private Planet testPlanet;

    @Before
    public void init() {
        planetDao = Mockito.mock(PlanetDaoImp.class);
        service = new PlanetServiceImp(planetDao);
        testPlanet = new Planet();
        testPlanet.setOwnerId(PLANET_OWNER_ID);
        testPlanet.setImageData(convertImage(PATH_FILE_JPG));
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
    public void createPlanetNegativeLowerBoundaryLimit() {
        testPlanet.setPlanetName("");
        Assert.assertThrows(PlanetFail.class, () -> {
            service.createPlanet(testPlanet);
        });
    }

    @Test
    public void createPlanetLowerBoundaryLimit() {
        testPlanet.setPlanetName("a");
        Mockito.when(planetDao.readPlanet(testPlanet.getPlanetName())).thenReturn(Optional.empty());
        Mockito.when(planetDao.createPlanet(testPlanet)).thenReturn(Optional.of(testPlanet));
        Planet actual = service.createPlanet(testPlanet);
        Assert.assertEquals(actual, testPlanet);
    }

    @Test
    public void createPlanetUpperBoundaryLimit() {
        testPlanet.setPlanetName("Do not listen to the Narrator!");
        Mockito.when(planetDao.readPlanet(testPlanet.getPlanetName())).thenReturn(Optional.empty());
        Mockito.when(planetDao.createPlanet(testPlanet)).thenReturn(Optional.of(testPlanet));
        Planet actual = service.createPlanet(testPlanet);
        Assert.assertEquals(actual, testPlanet);
    }

    @Test
    public void createPlanetNegativeUpperBoundaryLimit() {
        testPlanet.setPlanetName("I am the Narrator of this story");
        Assert.assertThrows(PlanetFail.class, () -> {
            service.createPlanet(testPlanet);
        });
    }

    @Test
    public void createPlanetUniqueNameService() {
        testPlanet.setPlanetName("Cyndaquil");
        Mockito.when(planetDao.readPlanet(testPlanet.getPlanetName())).thenReturn(Optional.empty());
        Mockito.when(planetDao.createPlanet(testPlanet)).thenReturn(Optional.of(testPlanet));
        Planet actual = service.createPlanet(testPlanet);
        Assert.assertEquals(actual, testPlanet);
    }

    @Test
    public void createPlanetNonUniqueNameService() {
        testPlanet.setPlanetName("I am the Narrator of this story");
        Mockito.when(planetDao.readPlanet(testPlanet.getPlanetName())).thenReturn(Optional.of(testPlanet));
        Assert.assertThrows(PlanetFail.class, () -> {
            service.createPlanet(testPlanet);
        });
    }

    @Test
    public void deletePlanetPositive() {
        String planetName = "Articuno";
        String expectedResult = "Planet deleted successfully";
        Mockito.when(planetDao.deletePlanet(planetName)).thenReturn(true);
        Assert.assertEquals(service.deletePlanet(planetName), expectedResult);
    }

    @Test
    public void deletePlanetNegative() {
        String planetName = "Articuno";
        Mockito.when(planetDao.deletePlanet(planetName)).thenReturn(false);
        Assert.assertThrows(PlanetFail.class, () -> service.deletePlanet(planetName));
    }
}