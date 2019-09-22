package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.model.Exercise;
import com.ruczajsoftware.workoutrival.model.Training;
import com.ruczajsoftware.workoutrival.repositories.ExerciseRepository;
import com.ruczajsoftware.workoutrival.repositories.TrainingRepository;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

	private TrainingRepository trainingRepository;
	private ExerciseRepository exerciseRepository;

	public DatabaseService(TrainingRepository trainingRepository, ExerciseRepository exerciseRepository) {
		this.trainingRepository = trainingRepository;
		this.exerciseRepository = exerciseRepository;
	}

	public void addTraining(Training training) {
		trainingRepository.save(training);
	}
	public void addExercise(Exercise exercise) {
		exerciseRepository.save(exercise);
	}
}