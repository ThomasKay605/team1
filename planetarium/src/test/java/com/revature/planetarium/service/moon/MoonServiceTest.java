package com.revature.planetarium.service.moon;

import com.revature.planetarium.entities.Moon;
import com.revature.planetarium.exceptions.MoonFail;
import com.revature.planetarium.repository.moon.MoonDao;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;


public class MoonServiceTest {

    private MoonService moonService;
    private MoonDao moonDao;

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
    public void selectMoonById() {
        Moon moon = new Moon(1, "Luna", 1);
        MoonDao moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp<> (moonDao);
        Mockito.when(moonDao.readMoon(1)).thenReturn(Optional.of(moon));
        try {
            Assert.assertEquals(moon, moonService.selectMoon(1));
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectMoonByName() {
        Moon moon = new Moon(1, "Luna", 1);
        MoonDao moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp<> (moonDao);
        Mockito.when(moonDao.readMoon("Luna")).thenReturn(Optional.of(moon));
        try {
            Assert.assertEquals(moon, moonService.selectMoon("Luna"));
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectMoonByInvalidId() {
        MoonDao moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp<> (moonDao);
        Mockito.when(moonDao.readMoon(10)).thenThrow(MoonFail.class);
        Assert.assertThrows(MoonFail.class, () -> {
            moonService.selectMoon(10);
        });
    }

    @Test
    public void selectMoonByInvalidName() {
        MoonDao moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp<> (moonDao);
        Mockito.when(moonDao.readMoon("This moon no exist")).thenThrow(MoonFail.class);
        Assert.assertThrows(MoonFail.class, () -> {
            moonService.selectMoon("This moon no exist");
        });
    }

    @Test
    public void selectAllMoons() {
        List<Moon> moonList=
                List.of(new Moon[]{
                        new Moon(1, "Luna", 1),
                        new Moon(2, "Titan", 2),
                        new Moon(3, "Minmus", 1)
                });
        MoonDao moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp<> (moonDao);
        Mockito.when(moonDao.readAllMoons()).thenReturn(moonList);
        try {
            Assert.assertEquals(moonList, moonService.selectAllMoons());
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void selectByPlanetId() {
        List<Moon> moonList=
                List.of(new Moon[]{
                        new Moon(1, "Luna", 1),
                        new Moon(3, "Minmus", 1)
                });
        MoonDao moonDao = Mockito.mock(MoonDao.class);
        moonService = new MoonServiceImp<> (moonDao);
        Mockito.when(moonDao.readMoonsByPlanet(1)).thenReturn(moonList);
        try {
            Assert.assertEquals(moonList, moonService.selectByPlanet(1));
        }
        catch (Exception e){
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void updateMoon() {
    }

    @Test
    public void deleteMoon() {
    }
}