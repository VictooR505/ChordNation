package com.chordnation.chordnation.user;

import jakarta.persistence.Embeddable;

import java.time.LocalDateTime;

@Embeddable
public class ExercisesDone {
    private Long exerciseId;
    private LocalDateTime doneDate;
    private Integer level;

    public ExercisesDone() {
    }

    public ExercisesDone(Long exerciseId, LocalDateTime doneDate, Integer level) {
        this.exerciseId = exerciseId;
        this.doneDate = doneDate;
        this.level = level;
    }

    public Long getExerciseId() {
        return exerciseId;
    }

    public void setExerciseId(Long exerciseId) {
        this.exerciseId = exerciseId;
    }

    public LocalDateTime getDoneDate() {
        return doneDate;
    }

    public void setDoneDate(LocalDateTime doneDate) {
        this.doneDate = doneDate;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
