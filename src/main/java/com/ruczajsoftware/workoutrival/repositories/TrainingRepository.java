package com.ruczajsoftware.workoutrival.repositories;

import com.arangodb.springframework.repository.ArangoRepository;
import com.ruczajsoftware.workoutrival.model.Training;

public interface TrainingRepository extends ArangoRepository<Training, Integer> {

}
