package service.implementation;

import service.UpdateInfo;
import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.UsersRepository;

/**
 * Created by Damian on 07/02/2017.
 */

@Service("updateInfo")
public class UpdateInfoImpl implements UpdateInfo{

    @Autowired
    UsersRepository usersRepository;

    @Override
    public void updateInfo(User user) {
        usersRepository.save(user);
    }
}
