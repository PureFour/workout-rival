package com.ruczajsoftware.workoutrival.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Training {

    private String trainingName;
    private List<Exercise> exercises;
    private Date trainingDate;
}