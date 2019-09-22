package com.ruczajsoftware.workoutrival.model;

import java.util.List;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document("Exercises")
public class Exercise {

	private String exerciseName;
	private List<GymSet> gymSets;
}