package databaseTests;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import dbmanagement.Database;
import dbmanagement.UsersRepository;
import domain.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Nicolás on 15/02/2017.
 */
@ContextConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private Database dat;

    @Test
    public void testGetUser(){
        User user = dat.getParticipant("das", "dasPassword");
        Assert.assertEquals(user.getNationality(), "paisHola");
    }

    @org.springframework.context.annotation.Configuration
    @EnableMongoRepositories(basePackageClasses = UsersRepository.class)
    class Configuration extends AbstractMongoConfiguration{

        public @Bean MongoDbFactory mongoDbFactory() throws Exception {
            return new SimpleMongoDbFactory(new MongoClient(), "test");
        }

        @Override
        protected String getDatabaseName() {
            return "test";
        }

        @Override
        public Mongo mongo() throws Exception {
            return new MongoClient();
        }

        public @Bean
        MongoTemplate mongoTemplate() throws Exception {

            return new MongoTemplate(mongoDbFactory());

        }
    }


}
