package com.chordnation.chordnation.enums;

import java.util.Arrays;

public enum Level {
    NO_RANK(-1, 0),
    BEGINNER(0, 1000),
    INTERMEDIATE(1000, 2000),
    ADVANCED(2000, 3000),
    MASTER(3000, Integer.MAX_VALUE);

    private final int minPoints;
    private final int maxPoints;

    Level(int minPoints, int maxPoints) {
        this.minPoints = minPoints;
        this.maxPoints = maxPoints;
    }

    public static Level calculateLevel(int points) {
        return Arrays.stream(values())
                .filter(level -> points >= level.minPoints && points < level.maxPoints)
                .findFirst()
                .orElse(NO_RANK);
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public int getMinPoints() {
        return minPoints;
    }

    public Level getPreviousLevel() {
        Level[] levels = Level.values();
        int currentIndex = Arrays.asList(levels).indexOf(this);
        return currentIndex > 0 ? levels[currentIndex - 1] : NO_RANK;
    }
}
