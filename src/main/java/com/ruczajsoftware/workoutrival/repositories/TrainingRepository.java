package com.ruczajsoftware.workoutrival.repositories;

import java.util.Optional;
import java.util.UUID;

import com.arangodb.springframework.repository.ArangoRepository;
import com.ruczajsoftware.workoutrival.model.Training;

public interface TrainingRepository extends ArangoRepository<Training, Integer> {
	Optional<Training> findTrainingByTrainingName(String trainingName);

	Optional<Training> findByUuid(UUID uuid);

	void deleteByTrainingName(String trainingName);
}
