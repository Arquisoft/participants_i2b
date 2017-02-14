package services;

import domain.User;

/**
 * Created by Nicolás on 14/02/2017.
 */
public interface ParticipantsService {

    User getParticipant(String email, String password);

    void updateInfo(String email, String password, String oldPassword);


}
