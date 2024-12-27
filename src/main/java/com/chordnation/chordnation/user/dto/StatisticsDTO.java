package com.chordnation.chordnation.user.dto;

import com.chordnation.chordnation.enums.Level;

public record StatisticsDTO(Level level, int points, String totalSessionTime, String averageSessionTime, String lastExercise, String lastSong) {
}
