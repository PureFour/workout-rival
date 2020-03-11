package com.ruczajsoftware.workoutrival.model;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DB_COLLECTIONS {
    USERS("Users"),
    EXERCISES("Exercises"),
    TRAINING_MODELS("TrainingModels"),
    TRAINING_HISTORY("TrainingHistory");

    @Getter
    private String collectionName;

    DB_COLLECTIONS(String collectionName) {
        this.collectionName = collectionName;
    }

    public static List<String> getValues() {
        return Stream.of(DB_COLLECTIONS.values())
                .map(DB_COLLECTIONS::getCollectionName)
                .collect(Collectors.toList());
    }
}
