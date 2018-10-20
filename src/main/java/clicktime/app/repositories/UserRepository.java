package clicktime.app.repositories;

import org.springframework.stereotype.Repository;

import clicktime.app.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;


@Repository
public interface UserRepository extends MongoRepository<User, Integer>{

	User findByUserNameAndPassword(String userName, String password);
}
