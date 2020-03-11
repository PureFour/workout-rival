package com.ruczajsoftware.workoutrival.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BodyMeasurement {

	private Date actualDay;
	private float weight;
}
