package com.ruczajsoftware.workoutrival.model;

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
