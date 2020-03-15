package com.ruczajsoftware.workoutrival.model.database;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonalData {

	private Date birthday;
	private float weight;
	private int height;
	private Sex sex;
}
