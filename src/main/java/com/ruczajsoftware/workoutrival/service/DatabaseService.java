package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.model.Exercise;
import com.ruczajsoftware.workoutrival.model.Training;
import com.ruczajsoftware.workoutrival.model.User;
import com.ruczajsoftware.workoutrival.repositories.ExerciseRepository;
import com.ruczajsoftware.workoutrival.repositories.TrainingRepository;
import com.ruczajsoftware.workoutrival.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

	private TrainingRepository trainingRepository;
	private ExerciseRepository exerciseRepository;
	private UserRepository userRepository;

	public DatabaseService(TrainingRepository trainingRepository, ExerciseRepository exerciseRepository, UserRepository userRepository) {
		this.trainingRepository = trainingRepository;
		this.exerciseRepository = exerciseRepository;
		this.userRepository = userRepository;
	}

	public void addTraining(Training training) {
		trainingRepository.save(training);
	}
	public void addUser(User user) { userRepository.save(user); }

	public User getUserByLogin(String login){
		return userRepository.findByLogin(login).isPresent() ? userRepository.findByLogin(login).get() : new User("","","") ;
	}

	public void addExercise(Exercise exercise) {
		exerciseRepository.save(exercise);
	}
}