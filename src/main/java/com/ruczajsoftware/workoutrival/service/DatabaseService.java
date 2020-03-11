package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.exceptions.EntityConflictException;
import com.ruczajsoftware.workoutrival.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.model.Exercise;
import com.ruczajsoftware.workoutrival.model.TrainingModel;
import com.ruczajsoftware.workoutrival.model.User;
import com.ruczajsoftware.workoutrival.repositories.ExerciseRepository;
import com.ruczajsoftware.workoutrival.repositories.TrainingRepository;
import com.ruczajsoftware.workoutrival.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DatabaseService {

	private TrainingRepository trainingRepository;

	private UserRepository userRepository;


	public void addUser(User user) throws EntityConflictException {
		if (userRepository.findByLogin(user.getLogin()).isPresent()) {
			throw new EntityConflictException("User exists in database!");
		}
		userRepository.save(user);
	}

	public User getUserByLogin(String login) throws EntityNotFoundException {
		Optional<User> user = userRepository.findByLogin(login);
		return user.orElseThrow(() -> new EntityNotFoundException("User not found in database!"));
	}

	public void deleteUserByLogin(String login) throws EntityNotFoundException {
		if (userRepository.findByLogin(login).isEmpty()) {
			throw new EntityNotFoundException("User not found!");
		}
		userRepository.deleteUserByLogin(login);
	}

	public void updateUserPassword(String login, String password) throws EntityNotFoundException, EntityConflictException {
		User user = this.getUserByLogin(login);
		if (user.getPassword().equals(password)) {
			throw new EntityConflictException("Password must be different!");
		}
		user.setPassword(password);
		userRepository.deleteUserByLogin(login);
		userRepository.save(user);
	}

	public void updateUserEmail(String login, String email) throws EntityNotFoundException, EntityConflictException {
		User user = this.getUserByLogin(login);
		if (user.getEmail().equals(email)) {
			throw new EntityConflictException("Email must be different!");
		}
		user.setEmail(email);
		userRepository.deleteUserByLogin(login);
		userRepository.save(user);
	}

}