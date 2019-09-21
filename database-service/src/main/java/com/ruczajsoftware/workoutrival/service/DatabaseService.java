package com.ruczajsoftware.workoutrival.service;

import com.ruczajsoftware.workoutrival.model.Training;
import org.springframework.stereotype.Service;

@Service
public class DatabaseService {

	private TrainingRepository trainingRepository;

	public DatabaseService(TrainingRepository trainingRepository) {
		this.trainingRepository = trainingRepository;
	}

	public void addTraining(Training training) {
		trainingRepository.save(training);
		System.out.println(training + " has been saved in database...");
	}
}
