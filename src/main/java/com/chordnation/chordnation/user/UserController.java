package com.chordnation.chordnation.user;

import com.chordnation.chordnation.exercise.Exercise;
import com.chordnation.chordnation.tab.SongDTO;
import com.chordnation.chordnation.user.dto.FavoritesDTO;
import com.chordnation.chordnation.user.dto.StatisticsDTO;
import com.chordnation.chordnation.user.dto.UserPreferencesDTO;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public List<ExercisesDone> getExercisesHistory(@PathVariable Long id,
                                                   @RequestParam LocalDateTime date){
        return userService.getExercisesHistory(id, date);
    }

    @GetMapping("/{id}/history/songs")
    public List<SongsPlayed> getSongsHistory(@PathVariable Long id,
                                             @RequestParam LocalDateTime date){
        return userService.getSongsHistory(id, date);
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

    @GetMapping("/{id}/statistics")
    public StatisticsDTO getStatistics(@PathVariable Long id){
        return userService.getStatistics(id);
    }


}
