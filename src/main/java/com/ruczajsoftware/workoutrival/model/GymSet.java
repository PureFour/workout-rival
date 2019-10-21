package com.ruczajsoftware.workoutrival.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
class GymSet {

    private int reps;
    private double weight;
}
