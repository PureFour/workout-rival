package com.ruczajsoftware.workoutrival.model.database;

import java.util.List;
import java.util.UUID;

import com.arangodb.springframework.annotation.Document;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("TrainingModels")
public class TrainingModel {
	@JsonIgnore
	private UUID uuid;
	private String trainingName;
	private List<Exercise> exercises;
}