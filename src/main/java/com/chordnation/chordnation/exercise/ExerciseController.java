package com.chordnation.chordnation.exercise;

import org.springframework.web.bind.annotation.*;

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
    public List<ExerciseDTO> getAllExercises (){
        return exerciseService.getAllExercises();
    }

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable Long id){
        return exerciseService.findExerciseById(id);
    }

    @PatchMapping("/do/{id}")
    public void doExercise(@PathVariable Long id,
                           @RequestBody Long userId,
                           @RequestBody int level){
        exerciseService.doExercise(userId, id, level);
    }

    @PatchMapping("/{exerciseId}/songs/add/{userId}")
    public void addToFavourites(@PathVariable Long userId,
                                @PathVariable Long exerciseId){
        exerciseService.addToFavourites(userId, exerciseId);
    }

    @PatchMapping("/{exerciseId}/songs/remove/{userId}")
    public void removeFromFavourites(@PathVariable Long userId,
                                @PathVariable Long exerciseId){
        exerciseService.removeFromFavourites(userId, exerciseId);
    }
}
