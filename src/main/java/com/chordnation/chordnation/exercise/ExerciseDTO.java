package com.chordnation.chordnation.exercise;

import java.util.List;

public record ExerciseDTO(Long id, String name, String description, List<Exercise> exercises) {
}
