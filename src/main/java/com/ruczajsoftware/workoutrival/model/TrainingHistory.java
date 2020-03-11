package com.ruczajsoftware.workoutrival.model;

import java.util.List;
import java.util.UUID;

import com.arangodb.springframework.annotation.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("TrainingHistory")
public class TrainingHistory {

	private UUID userId;
	private List<PerformedTraining> performedTrainings;
}
