package com.revature.planetarium.repository.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.utility.Setup;
import io.javalin.util.FileUtil;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Base64;
import java.util.Optional;


public class MoonDaoTest {

    private static MoonDao moonDao;
    @BeforeClass
    public static void setup() {
        moonDao = new MoonDaoImp();
        Setup.main(new String[]{});
    }

    @AfterClass
    public static void tearDownForEach() {
        Setup.main(new String[]{});
    }

    @Test
    public void zeroLengthNameMoonCreation() {
        Moon moon = new Moon(4, "", 1);
        Optional<Moon> option = moonDao.createMoon(moon);
        if(option.isPresent()) {
            Moon createdMoon = option.get();
            Assert.assertEquals(moon, createdMoon);
        }
        else {
            Assert.fail("No moon created: " + moon.toString());
        }
    }

    @Test
    public void oneCharacterLengthMoonCreation() {
        Moon moon = new Moon(4, "a", 1);

        Optional<Moon> option = moonDao.createMoon(moon);
        if(option.isPresent()) {
            Moon createdMoon = option.get();
            Assert.assertEquals(moon, createdMoon);
        }
        else {
            Assert.fail("No moon created: " + moon.toString());
        }
    }

    @Test
    public void jpegImageMoonCreation() {
        Moon moon = new Moon(4, "Phobos", 2);
        String jpegString = FileUtil.readFile("src/test/resources/Celestial-Images/moon-5.jpg");
        // I am not quite sure how it works, but we get the string representation of the image
        // turn it into an array of bytes to then encode those bytes to a base 64 representation all
        // concatenated into one big string
        moon.setImageData(Base64.getEncoder().encodeToString(jpegString.getBytes()));
        Optional<Moon> option = moonDao.createMoon(moon);
        if(option.isPresent()) {
            Moon createdMoon = option.get();
            Assert.assertEquals(moon, createdMoon);
        }
        else {
            Assert.fail("No moon created: " + moon.toString());
        }
    }

    @Test
    public void pngImageMoonCreation() {
        Moon moon = new Moon(4, "Phobos", 2);
        String jpegString = FileUtil.readFile("src/test/resources/Celestial-Images/realistic-moon.png");
        moon.setImageData(Base64.getEncoder().encodeToString(jpegString.getBytes()));
        Optional<Moon> option = moonDao.createMoon(moon);
        if(option.isPresent()) {
            Moon createdMoon = option.get();
            Assert.assertEquals(moon, createdMoon);
        }
        else {
            Assert.fail("No moon created: " + moon.toString());
        }
    }

    @Test
    public void testReadMoon() {
        Moon moon = new Moon(4, "Minmus", 1);
        moonDao.createMoon(moon);
        moonDao.readMoon()
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