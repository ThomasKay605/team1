package com.revature.planetarium.repository.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.utility.Setup;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class MoonDaoTest {

    private static MoonDao moonDao;
    @BeforeClass
    public static void setup() {
        moonDao = new MoonDaoImp();
        Setup.main(new String[]{});
        System.out.println("BeforeClass has been executed");
    }

    @AfterClass
    public static void teardownForEachest() {
        Setup.main(new String[]{});
        System.out.println("After has been executed");
    }

    @Test
    public void zeroLengthNameMoonCreation() {
        Moon moon = new Moon(3, "", 1);
        Optional<Moon> option = moonDao.createMoon(moon);
        if(option.isPresent()) {
            Moon createdMoon = option.get();
            Assert.assertEquals(moon, createdMoon);
        }
        else {
            Assert.fail("User is supposed to be created");
        }
    }

    @Test
    public void createMoon() {
    }

    @Test
    public void readMoon() {
    }

    @Test
    public void testReadMoon() {
    }

    @Test
    public void readAllMoons() {
    }

    @Test
    public void readMoonsByPlanet() {
    }

    @Test
    public void updateMoon() {
    }

    @Test
    public void deleteMoon() {
    }

    @Test
    public void testDeleteMoon() {
    }
}