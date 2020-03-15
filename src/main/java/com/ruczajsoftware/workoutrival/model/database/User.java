package com.ruczajsoftware.workoutrival.model.database;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("Users")
public class User {

	private String username;
	private String password;
	private String email;
	private PersonalData personalData;
	private List<BodyMeasurement> bodyMeasurements;
	private List<TrainingModel> definedTrainings;
	private List<TrainingPlan> trainingPlans;
}
