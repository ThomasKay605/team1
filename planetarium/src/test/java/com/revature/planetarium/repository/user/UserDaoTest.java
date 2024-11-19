package com.revature.planetarium.repository.user;

import com.revature.planetarium.entities.User;
import com.revature.utility.Setup;

import java.util.Optional;

import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.After;

public class UserDaoTest {

    private static final String GOOD_USERNAME = "Robin";
    private static final String GOOD_PASSWORD = "BatmanAndRobinRocks";

    private static UserDao userDao;

    @BeforeClass
    public static void setup() {
        userDao = new UserDaoImp();
        Setup.main(new String[]{});
        System.out.println("BeforeClass has been executed");
    }

    @After
    public void teardownForEachest() {
        Setup.main(new String[]{});
        System.out.println("After has been executed");
    }

    @Test
    public void positiveCreateUserTest() {
        User expectedUser = new User();
        expectedUser.setUsername(GOOD_USERNAME);
        expectedUser.setPassword(GOOD_PASSWORD);
        Optional<User> option = userDao.createUser(expectedUser);
        if(option.isPresent()) {
            User actualUser = option.get();
            expectedUser.setId(actualUser.getId());
            Assert.assertEquals(expectedUser, actualUser);
        } else Assert.fail("User is supposed to be created");
    }

    // Keep or delete?
    @Test
    public void negativeCreateUserTestUsernameLength0() {
        User invalidUser = new User();
        invalidUser.setUsername("");
        invalidUser.setPassword(GOOD_PASSWORD);
        Optional<User> option = userDao.createUser(invalidUser);
        Assert.assertTrue(option.isEmpty());
    }

    @Test
    public void negativeCreateUserTestUsernameLength31() {
        User invalidUser = new User();
        invalidUser.setUsername("IHaveTheTwoBestDogsInTheWorld!!");
        invalidUser.setPassword(GOOD_PASSWORD);
        Optional<User> option = userDao.createUser(invalidUser);
        Assert.assertTrue(option.isEmpty());
    }

    // Keep or delete?
    @Test
    public void negativeCreateUserTestPasswordLength0() {
        User invalidUser = new User();
        invalidUser.setUsername(GOOD_USERNAME);
        invalidUser.setPassword("");
        Optional<User> option = userDao.createUser(invalidUser);
        Assert.assertTrue(option.isEmpty());
    }

    @Test
    public void negativeCreateUserTestPasswordLength31() {
        User invalidUser = new User();
        invalidUser.setUsername(GOOD_USERNAME);
        invalidUser.setPassword("ICannotThinkOfAGoodPassword1234");
        Optional<User> option = userDao.createUser(invalidUser);
        Assert.assertTrue(option.isEmpty());
    }

    @Test
    public void negativeCreateUserTestDuplicateUsername() {
        User duplicate = new User();
        duplicate.setUsername("Batman");
        duplicate.setPassword(GOOD_PASSWORD);
        Optional<User> option = userDao.createUser(duplicate);
        Assert.assertTrue(option.isEmpty());
    }

    @Test
    public void positiveFindUserByUsernameTest() {
        User expectedUser = new User();
        String username = "Batman";
        expectedUser.setUsername(username);
        expectedUser.setPassword("I am the night");
        Optional<User> option = userDao.findUserByUsername(username);
        if(option.isPresent()) {
            User actualUser = option.get();
            expectedUser.setId(actualUser.getId());
            Assert.assertEquals(expectedUser, actualUser);
        } else Assert.fail("User is supposed to be found");
    }

    @Test
    public void negativeFindUserByUsernameTest() {
        String username = "Joker";
        Optional<User> option = userDao.findUserByUsername(username);
        Assert.assertTrue(option.isEmpty());
    }



}