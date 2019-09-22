package com.ruczajsoftware.workoutrival.repositories;

import com.arangodb.springframework.repository.ArangoRepository;
import com.ruczajsoftware.workoutrival.model.Exercise;

public interface ExerciseRepository extends ArangoRepository<Exercise> {
}
