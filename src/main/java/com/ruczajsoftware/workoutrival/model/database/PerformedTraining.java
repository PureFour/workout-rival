package com.ruczajsoftware.workoutrival.model.database;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformedTraining {

	private TrainingModel training;
	private Date trainingDate;

}
