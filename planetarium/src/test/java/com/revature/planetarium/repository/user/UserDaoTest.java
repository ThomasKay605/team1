package com.revature.planetarium.repository.user;

import com.revature.planetarium.entities.User;
import com.revature.planetarium.exceptions.UserFail;
import com.revature.utility.Setup;

import java.util.Optional;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.After;

public class UserDaoTest {

    private static final String GOOD_USERNAME = "Robin";
    private static final String GOOD_PASSWORD = "BatmanAndRobinRocks";
    private static final String EXISTING_USERNAME = "Batman";

    private static UserDao userDao;
    private static User testUser;

    @BeforeClass
    public static void setup() {
        userDao = new UserDaoImp();
        Setup.main(new String[]{});
    }

    @Before
    public void setupForEachTest() {
        testUser = new User();
        testUser.setUsername(GOOD_USERNAME);
        testUser.setPassword(GOOD_PASSWORD);
    }

    @After
    public void teardownForEachTest() {
        Setup.main(new String[]{});
    }

    @Test
    public void positiveCreateUserTest() {
        Optional<User> option = userDao.createUser(testUser);
        if(option.isPresent()) {
            User actualUser = option.get();
            testUser.setId(actualUser.getId());
            Assert.assertEquals(testUser, actualUser);
        } else Assert.fail("Option should have a user present");
    }

    @Test
    public void negativeCreateUserTestEmptyUser() {
        testUser = new User();
        Optional<User> option = userDao.createUser(testUser);
        Assert.assertTrue(option.isEmpty());
    }

    @Test
    public void negativeCreateUserTestUsernameLength0() {
        testUser.setUsername("");
        Assert.assertThrows(UserFail.class, () -> {
            userDao.createUser(testUser);
        });
    }

    @Test
    public void negativeCreateUserTestUsernameLength31() {
        testUser.setUsername("IHaveTheTwoBestDogsInTheWorld!!");
        Assert.assertThrows(UserFail.class, () -> {
            userDao.createUser(testUser);
        });
    }

    @Test
    public void negativeCreateUserTestPasswordLength0() {
        testUser.setPassword("");
        Assert.assertThrows(UserFail.class, () -> {
            userDao.createUser(testUser);
        });
    }

    @Test
    public void negativeCreateUserTestPasswordLength31() {
        testUser.setPassword("ICannotThinkOfAGoodPassword1234");
        Assert.assertThrows(UserFail.class, () -> {
            userDao.createUser(testUser);
        });
    }

    @Test
    public void negativeCreateUserTestDuplicateUsername() {
        testUser.setUsername(EXISTING_USERNAME);
        Assert.assertThrows(UserFail.class, () -> {
            userDao.createUser(testUser);
        });
    }

    @Test
    public void positiveFindUserByUsernameTest() {
        testUser.setUsername(EXISTING_USERNAME);
        testUser.setPassword("I am the night");
        Optional<User> option = userDao.findUserByUsername(EXISTING_USERNAME);
        if(option.isPresent()) {
            User actualUser = option.get();
            testUser.setId(actualUser.getId());
            Assert.assertEquals(testUser, actualUser);
        } else Assert.fail("Option should have a user present");
    }

    @Test
    public void negativeFindUserByUsernameTest() {
        Optional<User> option = userDao.findUserByUsername("Joker");
        Assert.assertTrue(option.isEmpty());
    }

}