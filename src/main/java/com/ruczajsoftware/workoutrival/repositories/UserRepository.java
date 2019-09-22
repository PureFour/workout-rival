package com.ruczajsoftware.workoutrival.repositories;

import java.util.Optional;

import com.arangodb.springframework.repository.ArangoRepository;
import com.ruczajsoftware.workoutrival.model.User;

public interface UserRepository extends ArangoRepository<User, Integer> {
	Optional<User> findByLogin(String login);
}
