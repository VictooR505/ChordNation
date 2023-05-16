package com.chordnation.exerciseservice.domain.dto;

import com.chordnation.exerciseservice.domain.enums.Level;

public record ExerciseDTO(Long id, Level level, String description, String imageUrl) {
}
