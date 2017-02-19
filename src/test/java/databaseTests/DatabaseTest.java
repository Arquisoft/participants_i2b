package databaseTests;

import dbmanagement.Database;
import domain.User;
import main.Application;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 * Created by Nicolás on 15/02/2017.
 */
@SpringBootTest(classes ={ Application.class})
@SpringBootApplication(scanBasePackages = "main")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@IntegrationTest("local.server.port=0")
public class DatabaseTest {

    @Autowired
    private Database dat;

    @Test
    public void testGetParticipant(){
    	//It should be previously encoded if the DB is given so this may be changed.
        User user = dat.getParticipant("prueba01@prueba.es");
        user.setNationality("Espana");
        Assert.assertEquals(user.getNationality(), "Espana");
        user.setNationality("paisAdios");
        User DBUser = dat.getParticipant("prueba01@prueba.es");
        Assert.assertNotEquals(user.getNationality(), DBUser.getNationality()); //Should be different from as we changed a transient one.
    }
    
    @Test
    public void testUpdateInfo(){
    	//It should be previously encoded if the DB is given so this may be changed.
        User user = dat.getParticipant("prueba01@prueba.es");
        user.setPassword("confidencial");
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        String encriptedCorrectPassword = encryptor.encryptPassword("dasPassword");
        
        User user2 = dat.getParticipant("prueba01@prueba.es");
        Assert.assertEquals(user, user2); //They should be the same user by the 
        
    }


}
