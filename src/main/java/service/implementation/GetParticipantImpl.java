package service.implementation;

import service.GetParticipant;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UsersRepository;

/**
 * Created by Damian on 07/02/2017.
 */
@Service("getParticipant")
public class GetParticipantImpl implements GetParticipant{

    @Autowired
    UsersRepository usersRepository;

    @Override
    public User getParticipant(String email) {
        return usersRepository.findByEmail(email);
    }
}
