package com.chordnation.chordnation.enums;

import java.util.Arrays;

public enum Level {
    BEGINNER(0, 1000),
    INTERMEDIATE(1001, 1999),
    ADVANCED(2000, 2999),
    MASTER(3000, Integer.MAX_VALUE);

    private final int minPoints;
    private final int maxPoints;

    Level(int minPoints, int maxPoints) {
        this.minPoints = minPoints;
        this.maxPoints = maxPoints;
    }

    public static Level calculateLevel(int points) {
        return Arrays.stream(values())
                .filter(level -> points >= level.minPoints && points <= level.maxPoints)
                .findFirst()
                .orElse(BEGINNER);
    }

    public int getMaxPoints() {
        return maxPoints;
    }
}
