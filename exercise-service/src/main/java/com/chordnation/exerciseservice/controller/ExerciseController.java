package com.chordnation.exerciseservice.controller;


import com.chordnation.exerciseservice.domain.dto.ExerciseDTO;
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
    public List<ExerciseDTO> getAllExercises(){
        return exerciseService.getAllExercises();
    }

    @PostMapping
    public void addExercise(@RequestBody ExerciseDTO exerciseDTO){
        exerciseService.addExercise(exerciseDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable("id") Long id){
        exerciseService.deleteExercise(id);
    }
}
