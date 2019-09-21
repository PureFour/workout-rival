package com.ruczajsoftware.workoutrival.model;

import java.util.Date;
import java.util.List;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("Trainings")
public class Training {

	private String trainingName;
	private List<Exercise> exercises;
	private Date trainingDate;
}