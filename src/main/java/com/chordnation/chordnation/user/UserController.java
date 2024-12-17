package com.chordnation.chordnation.user;

import com.chordnation.chordnation.exercise.Exercise;
import com.chordnation.chordnation.tab.SongDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id){
        return userService.findUserById(id);
    }

    @PatchMapping("/{id}/preferences")
    public void updatePreferences(@PathVariable Long id,
                                  @RequestBody UserPreferencesDTO preferences){
        userService.updatePreferences(id, preferences);
    }

    @GetMapping("/{id}/preferences")
    public UserPreferencesDTO getPreferences(@PathVariable Long id){
            return userService.getPreferences(id);
    }

    @GetMapping("/{id}/history/exercises")
    public List<ExercisesDone> getExercisesHistory(@PathVariable Long id){
        return userService.getExercisesHistory(id);
    }

    @GetMapping("/{id}/history/songs")
    public List<SongsPlayed> getSongsHistory(@PathVariable Long id){
        return userService.getSongsHistory(id);
    }

    @GetMapping("/{id}/favorites")
    public FavoritesDTO getFavorites(@PathVariable Long id){
        return userService.getFavorites(id);
    }

    @GetMapping("/{id}/suggested/songs")
    public List<SongDTO> getSuggestedSongs(@PathVariable Long id){
        return userService.getSuggestedSongs(id);
    }

    @GetMapping("/{id}/suggested/exercises")
    public List<Exercise> getSuggestedExercises(@PathVariable Long id){
        return userService.getSuggestedExercises(id);
    }


}
