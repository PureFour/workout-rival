package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.exceptions.EntityConflictException;
import com.ruczajsoftware.workoutrival.exceptions.EntityNotFoundException;
import com.ruczajsoftware.workoutrival.model.Exercise;
import com.ruczajsoftware.workoutrival.model.Training;
import com.ruczajsoftware.workoutrival.model.User;
import com.ruczajsoftware.workoutrival.repositories.ExerciseRepository;
import com.ruczajsoftware.workoutrival.repositories.TrainingRepository;
import com.ruczajsoftware.workoutrival.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DatabaseService {

	private TrainingRepository trainingRepository;
	private ExerciseRepository exerciseRepository;
	private UserRepository userRepository;

	public void deleteTrainingByTrainingName(String trainingName) throws EntityNotFoundException {
		if (trainingRepository.findTrainingByTrainingName(trainingName).isEmpty()) {
			throw new EntityNotFoundException("Training not found!");
		}
		trainingRepository.deleteByTrainingName(trainingName);
	}

	public void addTrainingToUser(Training training, String userLogin) throws EntityConflictException, EntityNotFoundException {
		User user = this.getUserByLogin(userLogin);
		Optional<Training> trainingFromDB = trainingRepository.findTrainingByTrainingName(training.getTrainingName());

		if (trainingFromDB.isEmpty()) {
			training.setUuid(UUID.randomUUID());
			trainingRepository.save(training);
		}

		if (user.getTrainings().stream().anyMatch((trainingFromDB.isPresent() ? trainingFromDB.get().getUuid() : training.getUuid())::equals)) {
			throw new EntityConflictException("Training exists in user trainings!");
		}

		userRepository.deleteUserByLogin(userLogin);
		user.getTrainings().add(trainingFromDB.isPresent() ? trainingFromDB.get().getUuid() : training.getUuid());
		userRepository.save(user);
	}

	public Training getTrainingByTrainingName(String trainingName) throws EntityNotFoundException {
		Optional<Training> training = trainingRepository.findTrainingByTrainingName(trainingName);
		return training.orElseThrow(() -> new EntityNotFoundException("Training not found!"));
	}

	public List<Training> getUserTrainings(String userLogin) throws EntityNotFoundException {
		User user = this.getUserByLogin(userLogin);
		return user.getTrainings().stream()
				.filter(uuid -> trainingRepository.findByUuid(uuid).isPresent())
				.map(uuid -> trainingRepository.findByUuid(uuid).get())
				.collect(Collectors.toList());
	}

	public void addUser(User user) throws EntityConflictException {
		user.setTrainings(new ArrayList<>());
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

	public void addExercise(Exercise exercise) {
		exerciseRepository.save(exercise);
	}
}