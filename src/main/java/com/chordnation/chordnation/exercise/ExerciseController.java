package com.chordnation.chordnation.exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/exercise")
public class ExerciseController {

    private final ExerciseService exerciseService;

    public ExerciseController(ExerciseService exerciseService) {
        this.exerciseService = exerciseService;
    }

    @GetMapping("/section/{id}")
    public List<Exercise> getAllExercisesBySection(@PathVariable Long id){
        return exerciseService.findAllExercisesBySection(id);
    }

    @GetMapping()
    public List<Section> getAllExercises (){
        return exerciseService.getAllExercises();
    }

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable Long id){
        return exerciseService.findExerciseById(id);
    }
}
