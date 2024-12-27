package com.chordnation.chordnation.exercise;

import java.time.LocalDateTime;

public record doExerciseDTO(Long userId,
                            int level,
                            LocalDateTime start,
                            LocalDateTime end) {
}
