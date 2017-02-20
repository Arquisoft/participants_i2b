package database_tests;

import dbmanagement.Database;
import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;
import main.Application;
import util.JasyptEncryptor;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    public void testUpdateInfoWithPassword(){
    	//It should be previously encoded if the DB is given so this may be changed.
        User user = dat.getParticipant("prueba01@prueba.es");
        user.setPassword("confidencial");
        JasyptEncryptor encryptor = new JasyptEncryptor();
        String encriptedCorrectPassword = encryptor.encryptPassword("confidencial");
        dat.updateInfo(user);
        Assert.assertTrue(encryptor.checkPassword("confidencial", encriptedCorrectPassword)); //They should be the same when we introduce the password.
        
        User user2 = dat.getParticipant("prueba01@prueba.es");
        Assert.assertEquals(user, user2); //They should be the same user by the equals.
        
    }
    
    /*
     * Para este test se necesita el siguiente documento en la base de datos:
    {
    "_id" : ObjectId("5893a06ace8c8e1b79d8a9a9"),
    "_class" : "Model.User",
    "firstName" : "Maria",
    "lastName" : "MamaMia",
    "password" : "9gvHm9TI57Z9ZW8/tTu9Nk10NDZayLIgKcFT8WdCVXPeY5gF57AFjS/l4nKNY1Jq",
    "dateOfBirth" : ISODate("1982-12-27T23:00:00.000Z"),
    "address" : "Hallo",
    "nationality" : "Core",
    "UserId" : "321",
    "email" : "asd"
}
     */
    @Test
    public void testUpdateInfoAndAdaptation(){
    	 User user = dat.getParticipant("asd");
    	 Assert.assertEquals("Maria", user.getFirstName());
    	 Assert.assertEquals("MamaMia", user.getLastName());
    	 Assert.assertEquals("1982-12-27T23:00:00.000Z", user.getDateOfBirth());
    	 Assert.assertEquals("Hallo", user.getAddress());
    	 Assert.assertEquals("Core", user.getNationality());
    	 Assert.assertEquals("321", user.getUserId());
    	 Assert.assertEquals("asd", user.getEmail());
    	 
    	 UserInfoAdapter userAdapter= new UserInfoAdapter(user);
    	 
    	 UserInfo userInfo=userAdapter.userToInfo();
    	 
    	 Assert.assertEquals(user.getFirstName(), userInfo.getFirstName());
    	 Assert.assertEquals(user.getLastName(), userInfo.getLastName());
    	 Assert.assertEquals(user.getEmail(), userInfo.getEmail());
    	 Assert.assertEquals(user.getUserId(), userInfo.getUserId());
    	 Assert.assertEquals(34, userInfo.getAge());
    	 
    	 }


}
