package com.chordnation.exerciseservice.controller;


import com.chordnation.exerciseservice.domain.Exercise;
import com.chordnation.exerciseservice.service.ExerciseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping
    public List<Exercise> getAllExercises(){
        return exerciseService.getAllExercises();
    }

    @PostMapping
    public void addExercise(@RequestBody Exercise exercise){
        exerciseService.addExercise(exercise);
    }

    @DeleteMapping
    public void deleteExercise(@RequestBody Long id){
        exerciseService.deleteExercise(id);
    }
}
