package com.revature.planetarium.service.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.repository.moon.MoonDao;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class MoonServiceTest {

    private MoonService<Moon> moonService;
    private MoonDao moonDao;


    @Test
    public void toDelete(){
        List<Moon> moonList = moonService.selectAllMoons();
        //System.out.println(moonList);
    }


    @Test
    public void createMoonWithOneCharacterName() {
        String name = "e";
        Moon moon = new Moon(4, name, 1);
        // For whatever reason the @Before does not work when setting up the mock,
        // and it must be within the method and cannot be abstracted away.
        MoonDao moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp<> (moonDao);
        Mockito.when(moonDao.createMoon(moon)).thenReturn(Optional.of(moon));
        Moon observed = moonService.createMoon(moon);
        Mockito.verify(moonDao).createMoon(moon);
        Assert.assertEquals(moon, observed);
    }

    @Test
    public void createMoonWithZeroCharacterName() {
        String name = "";
        Moon moon = new Moon(4, name, 1);
        MoonDao moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp<> (moonDao);
        Mockito.when(moonDao.createMoon(moon)).thenReturn(Optional.of(moon));
        try {
            Moon observed = moonService.createMoon(moon);
            Mockito.verify(moonDao).createMoon(moon);
            Assert.assertEquals(moon, observed);
        }
        catch (MoonFail moonFail){
            Assert.fail(moonFail.getMessage());
        }
    }

    @Test
    public void createMoonWithThirtyCharacterName() {
        String name = "Thirty character moon nametest";
        Moon moon = new Moon(4, name, 1);
        MoonDao moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp<> (moonDao);
        Mockito.when(moonDao.createMoon(moon)).thenReturn(Optional.of(moon));
        try {
            Moon observed = moonService.createMoon(moon);
            Mockito.verify(moonDao).createMoon(moon);
            Assert.assertEquals(moon, observed);
        }
        catch (MoonFail moonFail){
            Assert.fail(moonFail.toString());
        }
    }

    @Test
    public void createMoonWithThirtyOneCharacterName() {
        String name = "Thirty character moon nametest1";
        Moon moon = new Moon(4, name, 1);
        MoonDao moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp<> (moonDao);
        Mockito.when(moonDao.createMoon(moon)).thenReturn(Optional.empty());
        Assert.assertThrows(MoonFail.class, ()->{
            Moon observed = moonService.createMoon(moon);
        });
    }

    @Test
    public void createMoonWithInvalidPlanetId(){
        Moon moon = new Moon(4, "doesn't matter", 300);
        MoonDao moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp<> (moonDao);
        Mockito.when(moonDao.createMoon(moon)).thenThrow(MoonFail.class);
        Assert.assertThrows(MoonFail.class, ()->{Moon observed = moonService.createMoon(moon);});
    }

    @Test
    public void selectMoon() {
    }

    @Test
    public void selectAllMoons() {
    }

    @Test
    public void selectByPlanetId() {
        List<Moon> expectedMoonList = new ArrayList<>();
        expectedMoonList.add(new Moon(1, "Luna", 1));
        Mockito.when(moonService.selectByPlanet(1)).thenReturn(expectedMoonList);
        List<Moon> moonList = moonService.selectByPlanet(1);
        Mockito.verify(moonDao).readMoonsByPlanet(1);
        Assert.assertEquals(expectedMoonList, moonList);
    }

    @Test
    public void updateMoon() {
    }

    @Test
    public void deleteMoon() {
    }
}