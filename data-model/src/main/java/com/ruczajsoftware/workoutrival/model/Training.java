package com.ruczajsoftware.workoutrival.model;

import java.util.Date;
import java.util.List;

import com.arangodb.springframework.annotation.Document;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("Trainings")
public class Training {

	private String trainingName;
	private List<Exercise> exercises;
	@DateTimeFormat(pattern = "yyyy/mm/dd")
	@JsonFormat(pattern = "yyyy/mm/dd")
	private Date trainingDate;
}