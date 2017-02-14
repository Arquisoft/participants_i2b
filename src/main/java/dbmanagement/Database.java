package dbmanagement;

import domain.User;

/**
 * Created by Nicolás on 14/02/2017.
 */
public interface Database {

    void updateInfo(User user);

    User getParticipant(String email, String password);

    void updatePassword(String email, String password, String newPassword);
}
