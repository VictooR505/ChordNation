package com.chordnation.exerciseservice.mapper;

import com.chordnation.exerciseservice.domain.Exercise;
import com.chordnation.exerciseservice.domain.dto.ExerciseDTO;

public class ExerciseMapper {

    public ExerciseMapper() {
    }

    public ExerciseDTO toDTO(Exercise exercise){
        ExerciseDTO exerciseDTO = new ExerciseDTO(
                exercise.getId(),
                exercise.getLevel(),
                exercise.getDescription(),
                exercise.getImageUrl()
        );
        return exerciseDTO;
    }

    public Exercise toEntity(ExerciseDTO exerciseDTO){
        Exercise exercise = new Exercise(
                exerciseDTO.id(),
                exerciseDTO.level(),
                exerciseDTO.description(),
                exerciseDTO.imageUrl()
        );
        return exercise;
    }
}
