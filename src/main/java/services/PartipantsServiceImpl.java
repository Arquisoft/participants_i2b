package services;

import dbmanagement.Database;
import domain.User;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.ParticipantsService;

/**
 * Created by Nicolás on 14/02/2017.
 */
@Service
public class PartipantsServiceImpl implements ParticipantsService {

    private final Database dat;

    @Autowired
    PartipantsServiceImpl(Database dat){
        this.dat = dat;
    }

    @Override
    public User getParticipant(String email, String password) {
        return dat.getParticipant(email, password);
    }

    @Override
    public void updateInfo(User user, String newPassword) {
    	//We use the Jasypt library to encrypt the password.
    	StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
    	
    	String encryptedPassword = encryptor.encryptPassword(newPassword);
    	
    	user.setPassword(encryptedPassword);
    	dat.updateInfo(user);
    	

    }
}
