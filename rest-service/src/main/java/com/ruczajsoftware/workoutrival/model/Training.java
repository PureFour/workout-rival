package com.ruczajsoftware.workoutrival.model;

import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {

    private String trainingName;
    private List<Exercise> exercises;
    private Date trainingDate;
}