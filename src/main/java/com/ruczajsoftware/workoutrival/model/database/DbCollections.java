package com.ruczajsoftware.workoutrival.model.database;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum DbCollections {
    USERS("Users"),
    EXERCISES("Exercises"),
    TRAINING_MODELS("TrainingModels"),
    TRAINING_HISTORY("TrainingHistory");

    @Getter
    private String collectionName;

    DbCollections(String collectionName) {
        this.collectionName = collectionName;
    }

    public static List<String> getValues() {
        return Stream.of(DbCollections.values())
                .map(DbCollections::getCollectionName)
                .collect(Collectors.toList());
    }
}
