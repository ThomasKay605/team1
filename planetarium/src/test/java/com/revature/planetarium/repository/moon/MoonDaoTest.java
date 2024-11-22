package com.revature.planetarium.repository.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.utility.Setup;
import io.javalin.util.FileUtil;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLOutput;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
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
    public void testReadMoonById() {
        Moon moon = new Moon(4, "Minmus", 1);
        moonDao.createMoon(moon);
        Optional<Moon> option = moonDao.readMoon(4);
        if(option.isPresent()) {
            Moon createdMoon = option.get();
            Assert.assertEquals(moon, createdMoon);
        }
        else {
            Assert.fail("No moon created: " + moon.toString());
        }
    }

    @Test
    public void testReadMoonByName() {
        Moon moon = new Moon(4, "Minmus", 1);
        moonDao.createMoon(moon);
        Optional<Moon> option = moonDao.readMoon("Minmus");
        if(option.isPresent()) {
            Moon createdMoon = option.get();
            Assert.assertEquals(moon, createdMoon);
        }
        else {
            Assert.fail("No moon created: " + moon.toString());
        }
    }

    @Test
    public void readAllMoons() {
        List<Moon> moons = moonDao.readAllMoons();
        for (Moon moon: moons){
            Assert.assertNotNull(moon);
        }
        Assert.assertEquals(3, moons.size());
    }

    @Test
    public void readMoonsByPlanet() {
        Moon newMoon = new Moon(4, "Another moon", 1);
        Moon originalMoon = new Moon(1, "Luna", 1);
        moonDao.createMoon(newMoon);
        List<Moon> moons = moonDao.readMoonsByPlanet(1);
        Assert.assertArrayEquals(new Moon[]{originalMoon, newMoon},moons.toArray());
    }

    @Test
    public void updateMoon() {
        Moon difMoon = new Moon(1, "The Moon", 1);
        Optional<Moon> option = moonDao.updateMoon(difMoon);
        if(option.isPresent()) {
            Moon createdMoon = option.get();
            Assert.assertEquals(difMoon, createdMoon);
        }
        else {
            Assert.fail("No moon created: " + difMoon.toString());
        }
        // make sure Luna does not exist
        Optional<Moon> noExist = moonDao.readMoon("Luna");
        if (noExist.isPresent()){
            Assert.fail(noExist.toString() + " exists when shouldn't");
        }

    }

    @Test
    public void updateMoonToBeEmpty(){
        Moon difMoon = new Moon(1, "", 1);
        Optional<Moon> option = moonDao.updateMoon(difMoon);
        if(option.isPresent()) {
            Moon createdMoon = option.get();
            Assert.assertEquals(difMoon, createdMoon);
        }
        else {
            Assert.fail("No moon created: " + difMoon.toString());
        }

        // make sure Luna does not exist
        Assert.assertThrows(NoSuchElementException.class, ()->{moonDao.readMoon("Luna").get();});
    }

    @Test
    public void deleteMoonById() {
        boolean isDeleted = moonDao.deleteMoon(1);
        Assert.assertTrue("Moon not deleted when should have", isDeleted);
        // make sure Luna is actually deleted
        Assert.assertThrows(NoSuchElementException.class, ()->{moonDao.readMoon(1).get();});
        Assert.assertThrows(NoSuchElementException.class, ()->{moonDao.readMoon("Luna").get();});
    }

    @Test
    public void deleteMoonByName() {
        boolean isDeleted = moonDao.deleteMoon("Titan");
        Assert.assertTrue("Moon not deleted when should have", isDeleted);
        // make sure Luna is actually deleted
        Assert.assertThrows(NoSuchElementException.class, ()->{moonDao.readMoon(2).get();});
        Assert.assertThrows(NoSuchElementException.class, ()->{moonDao.readMoon("Titan").get();});
    }

}