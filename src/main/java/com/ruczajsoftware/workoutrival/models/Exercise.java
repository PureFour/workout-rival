package com.ruczajsoftware.workoutrival.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
class Exercise {

    private String exerciseName;
    private List<GymSet> gymSets;
}