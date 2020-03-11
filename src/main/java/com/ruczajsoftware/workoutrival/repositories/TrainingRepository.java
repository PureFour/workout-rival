package com.ruczajsoftware.workoutrival.repositories;

import java.util.Optional;
import java.util.UUID;

import com.arangodb.springframework.repository.ArangoRepository;
import com.ruczajsoftware.workoutrival.model.TrainingModel;

public interface TrainingRepository extends ArangoRepository<TrainingModel, Integer> {
	Optional<TrainingModel> findTrainingByTrainingName(String trainingName);

	Optional<TrainingModel> findByUuid(UUID uuid);

	void deleteByTrainingName(String trainingName);
}
