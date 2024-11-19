package com.revature.planetarium.service.user;

import org.junit.Test;
import org.mockito.Mockito;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.repository.user.UserDao;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;

public class UserServiceTest {

    private static final String GOOD_USERNAME = "The Long Quiet";
    private static final String GOOD_PASSWORD = "Slay the princess";

    private static UserService userService;
    private static UserDao userDao;

    @Before
    public void setupForEachTest() {
        userDao = Mockito.mock(UserDao.class);
        userService = new UserServiceImp(userDao);
    }

    @Test
    public void positiveCreateUserTest() {
        String username = GOOD_USERNAME;
        String password = GOOD_PASSWORD;
        User inputUser = new User();
        inputUser.setUsername(username);
        inputUser.setPassword(password);
        Mockito.when(userDao.createUser(inputUser)).thenReturn(Optional.of(inputUser));
        String expectedMessage = "Account created successfully with username \"" + username + "\"";
        String actualMessage = userService.createUser(inputUser);
        // include or exclude?
        Mockito.verify(userDao, Mockito.times(1)).createUser(inputUser);
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void negativeCreateUserTestUsernameLength0() {
        String username = "";
        String password = GOOD_PASSWORD;
        User inputUser = new User();
        inputUser.setUsername(username);
        inputUser.setPassword(password);
        Mockito.when(userDao.createUser(inputUser)).thenReturn(Optional.empty());
        String expectedMessage = "Account creation failed with username \"" + username + "\"";
        String actualMessage = userService.createUser(inputUser);
        // include or exclude?
        Mockito.verify(userDao, Mockito.times(0)).createUser(inputUser);
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void negativeCreateUserTestUsernameLength31() {
        String username = "There is a cabin in the woods..";
        String password = GOOD_PASSWORD;
        User inputUser = new User();
        inputUser.setUsername(username);
        inputUser.setPassword(password);
        Mockito.when(userDao.createUser(inputUser)).thenReturn(Optional.empty());
        String expectedMessage = "Account creation failed with username \"" + username + "\"";
        String actualMessage = userService.createUser(inputUser);
        // include or exclude?
        Mockito.verify(userDao, Mockito.times(0)).createUser(inputUser);
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void negativeCreateUserTestPasswordLength0() {
        String username = GOOD_USERNAME;
        String password = "";
        User inputUser = new User();
        inputUser.setUsername(username);
        inputUser.setPassword(password);
        Mockito.when(userDao.createUser(inputUser)).thenReturn(Optional.empty());
        String expectedMessage = "Account creation failed with username \"" + username + "\"";
        String actualMessage = userService.createUser(inputUser);
        // include or exclude?
        Mockito.verify(userDao, Mockito.times(0)).createUser(inputUser);
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void negativeCreateUserTestPasswordLength31() {
        String username = GOOD_USERNAME;
        String password = "Slay, not romance the princess!";
        User inputUser = new User();
        inputUser.setUsername(username);
        inputUser.setPassword(password);
        Mockito.when(userDao.createUser(inputUser)).thenReturn(Optional.empty());
        String expectedMessage = "Account creation failed with username \"" + username + "\"";
        String actualMessage = userService.createUser(inputUser);
        // include or exclude?
        Mockito.verify(userDao, Mockito.times(0)).createUser(inputUser);
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void negativeCreateUserTestDuplicateUsername() {
        String username = "Batman";
        String password = GOOD_PASSWORD;
        User inputUser = new User();
        inputUser.setUsername(username);
        inputUser.setPassword(password);
        Mockito.when(userDao.createUser(inputUser)).thenReturn(Optional.empty());
        String expectedMessage = "Account creation failed with username \"" + username + "\"";
        String actualMessage = userService.createUser(inputUser);
        // include or exclude?
        Mockito.verify(userDao, Mockito.times(0)).createUser(inputUser);
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void positiveAuthenticateTest() {
        String username = "Batman";
        String password = "I am the night";
        User expectedUser = new User();
        expectedUser.setUsername(username);
        expectedUser.setPassword(password);
        Mockito.when(userDao.findUserByUsername(username)).thenReturn(Optional.of(expectedUser));
        User actualUser = userService.authenticate(expectedUser);
        Assert.assertEquals(expectedUser, actualUser);
    }

    @Test
    public void negativeAuthenticateTestIncorrectUsername() {
        String username = "Robin";
        String password = "I am the night";
        User inputUser = new User();
        inputUser.setUsername(username);
        inputUser.setPassword(password);
        User returnedUser = new User();
        Mockito.when(userDao.findUserByUsername(username)).thenReturn(Optional.of(inp))
    }
}