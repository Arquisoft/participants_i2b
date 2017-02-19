package services;

import dbmanagement.Database;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.JasyptEncryptor;

/**
 * Created by Nicolás on 14/02/2017.
 */
@Service
public class PartipantsServiceImpl implements ParticipantsService {

    private final Database dat;
    private final JasyptEncryptor encryptor = new JasyptEncryptor();

    @Autowired
    PartipantsServiceImpl(Database dat){
        this.dat = dat;
    }

    @Override
    public User getParticipant(String email, String password) {
        User user = dat.getParticipant(email);
        if(user != null && encryptor.checkPassword(password, user.getPassword()))
            return user;
        else return null;
    }

    @Override
    public void updateInfo(User user, String newPassword) {
    	//We use the Jasypt library to encrypt the password.
    	String encryptedPassword = encryptor.encryptPassword(newPassword);
    	
    	user.setPassword(encryptedPassword);
    	dat.updateInfo(user);
    	

    }
}
