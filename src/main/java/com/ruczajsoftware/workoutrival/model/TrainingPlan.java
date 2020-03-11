package com.ruczajsoftware.workoutrival.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingPlan {

	private List<TrainingModel> trainingDays;
	private String name;

}
