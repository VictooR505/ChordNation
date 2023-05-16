package com.chordnation.exerciseservice.domain.dto;

import com.chordnation.exerciseservice.domain.enums.Level;

public record CourseDTO(Long id, Level level, String description, String link) {
}
