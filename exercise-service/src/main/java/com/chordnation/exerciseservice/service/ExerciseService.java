package com.chordnation.exerciseservice.service;

import com.chordnation.exerciseservice.domain.Exercise;
import com.chordnation.exerciseservice.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseService {
    private final ExerciseRepository exerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository) {
        this.exerciseRepository = exerciseRepository;
    }

    public List<Exercise> getAllExercises(){
        return exerciseRepository.findAll();
    }

    public void addExercise(Exercise exercise){
        exerciseRepository.save(exercise);
    }

    public void deleteExercise(Long id){
        exerciseRepository.deleteById(id);
    }
}
