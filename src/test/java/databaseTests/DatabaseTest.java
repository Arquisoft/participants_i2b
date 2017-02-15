package databaseTests;

import dbmanagement.Database;
import domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Nicolás on 15/02/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan({"main.java.dbmanagement"})
@ContextConfiguration
public class DatabaseTest {

    @Autowired
    private Database dat;

    @Test
    public void testGetUser(){
        User user = dat.getParticipant("das", "dasPassword");
        Assert.assertEquals(user.getNationality(), "paisHola");
    }
}
