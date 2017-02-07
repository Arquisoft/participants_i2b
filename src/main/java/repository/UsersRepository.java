package repository;

import domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Damian on 06/02/2017.
 */

@Repository
public interface UsersRepository extends MongoRepository<User, Long>{

    public User findByEmail(String email);
}
