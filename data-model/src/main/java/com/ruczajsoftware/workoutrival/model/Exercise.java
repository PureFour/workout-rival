package com.ruczajsoftware.workoutrival.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Exercise {

	private String exerciseName;
	private List<GymSet> gymSets;
}