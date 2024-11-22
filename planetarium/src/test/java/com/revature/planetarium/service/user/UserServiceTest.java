package com.revature.planetarium.service.user;

import org.junit.Test;
import org.mockito.Mockito;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.planetarium.repository.user.UserDao;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;

public class UserServiceTest {

    private static final String GOOD_USERNAME = "The Long Quiet";
    private static final String GOOD_PASSWORD = "Slay the princess";
    private static final String EXISTING_USERNAME = "Batman";

    private static UserService userService;
    private static UserDao userDao;
    private static User testUser;

    @Before
    public void setupForEachTest() {
        userDao = Mockito.mock(UserDao.class);
        userService = new UserServiceImp(userDao);
        testUser = new User();
        testUser.setUsername(GOOD_USERNAME);
        testUser.setPassword(GOOD_PASSWORD);
    }

    @Test
    public void positiveCreateUserTest() {
        Mockito.when(userDao.createUser(testUser)).thenReturn(Optional.of(testUser));
        Mockito.when(userDao.findUserByUsername(testUser.getUsername())).thenReturn(
            Optional.empty());
        String expectedMessage = "Created user with username " + GOOD_USERNAME;
        String actualMessage = userService.createUser(testUser);
        Assert.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    public void negativeCreateUserTestUsernameLength0() {
        testUser.setUsername("");
        UserFail failed = Assert.assertThrows(UserFail.class, () -> {
            userService.createUser(testUser);
        });
        Assert.assertEquals("Username must be between 1 and 30 characters", failed.getMessage());
    }

    @Test
    public void negativeCreateUserTestUsernameLength31() {
        testUser.setUsername("The pristine blade is gleaming!");
        UserFail failed = Assert.assertThrows(UserFail.class, () -> {
            userService.createUser(testUser);
        });
        Assert.assertEquals("Username must be between 1 and 30 characters", failed.getMessage());
    }

    @Test
    public void negativeCreateUserTestPasswordLength0() {
        testUser.setPassword("");
        UserFail failed = Assert.assertThrows(UserFail.class, () -> {
            userService.createUser(testUser);
        });
        Assert.assertEquals("Password must be between 1 and 30 characters", failed.getMessage());
    }

    @Test
    public void negativeCreateUserTestPasswordLength31() {
        testUser.setPassword("Slay, not romance the princess!");
        UserFail failed = Assert.assertThrows(UserFail.class, () -> {
            userService.createUser(testUser);
        });
        Assert.assertEquals("Password must be between 1 and 30 characters", failed.getMessage());
    }

    @Test
    public void negativeCreateUserTestDuplicateUsername() {
        testUser.setUsername(EXISTING_USERNAME);
        Mockito.when(userDao.findUserByUsername(testUser.getUsername())).thenReturn(
            Optional.of(testUser));
        UserFail failed = Assert.assertThrows(UserFail.class, () -> {
            userService.createUser(testUser);
        });
        Assert.assertEquals("Username is already in use", failed.getMessage());
    }

    @Test
    public void negativeCreateUserTestInvalidUser() {
        Mockito.when(userDao.createUser(testUser)).thenReturn(Optional.empty());
        Mockito.when(userDao.findUserByUsername(testUser.getUsername())).thenReturn(
            Optional.empty());
        UserFail failed = Assert.assertThrows(UserFail.class, () -> {
            userService.createUser(testUser);
        });
        Assert.assertEquals("Failed to create user, please try again", failed.getMessage());
    }

    @Test
    public void positiveAuthenticateTest() {
        Mockito.when(userDao.findUserByUsername(testUser.getUsername())).thenReturn(
            Optional.of(testUser));
        User actualUser = userService.authenticate(testUser);
        Assert.assertEquals(testUser, actualUser);
    }

    @Test
    public void negativeAuthenticateTestWrongUsername() {
        Mockito.when(userDao.findUserByUsername(testUser.getUsername())).thenReturn(
            Optional.empty());
        UserFail failed = Assert.assertThrows(UserFail.class, () -> {
            userService.authenticate(testUser);
        });
        Assert.assertEquals("Username and/or password do not match", failed.getMessage()); 
    }

    @Test
    public void negativeAuthenticateTestWrongPassword() {
        User returnedUser = new User();
        returnedUser.setPassword("The cycle never ends");
        Mockito.when(userDao.findUserByUsername(testUser.getUsername())).thenReturn(
            Optional.of(returnedUser));
        UserFail failed = Assert.assertThrows(UserFail.class, () -> {
            userService.authenticate(testUser);
        });
        Assert.assertEquals("Username and/or password do not match", failed.getMessage());
    }

}