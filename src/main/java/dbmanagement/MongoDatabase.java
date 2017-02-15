package dbmanagement;

import org.springframework.beans.factory.annotation.Autowired;

import domain.User;

public class MongoDatabase implements Database{
	
	@Autowired
	private UsersRepository users;

	@Override
	public void updateInfo(User user) {
		users.save(user);
	}

	@Override
	public User getParticipant(String email, String password) {
		return users.findByEmailAndPassword(email, password);
	}

}
