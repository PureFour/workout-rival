package com.ruczajsoftware.workoutrival.model;

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

	private String login;
	private String email;
	private String password;
	private PersonalData personalData;
	private List<BodyMeasurement> bodyMeasurements;
	private List<TrainingModel> definedTrainings;
	private List<TrainingPlan> trainingPlans;

}
