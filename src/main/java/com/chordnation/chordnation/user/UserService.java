package com.chordnation.chordnation.user;

import com.chordnation.chordnation.enums.KeyWord;
import com.chordnation.chordnation.enums.Level;
import com.chordnation.chordnation.exercise.Exercise;
import com.chordnation.chordnation.exercise.ExerciseRepository;
import com.chordnation.chordnation.tab.*;
import com.chordnation.chordnation.user.dto.FavoritesDTO;
import com.chordnation.chordnation.user.dto.StatisticsDTO;
import com.chordnation.chordnation.user.dto.UserPreferencesDTO;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TabRepository tabRepository;
    private final ExerciseRepository exerciseRepository;
    private final SongRepository songRepository;

    public UserService(UserRepository userRepository, TabRepository tabRepository, ExerciseRepository exerciseRepository, SongRepository songRepository) {
        this.userRepository = userRepository;
        this.tabRepository = tabRepository;
        this.exerciseRepository = exerciseRepository;
        this.songRepository = songRepository;
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public User findUserById(Long id){
        return userRepository.findById(id).get();
    }

    public void updatePreferences(Long id, UserPreferencesDTO preferences){
        User user = userRepository.findById(id).get();
        user.getUserDetails().setFavouriteArtists(preferences.artists());
        user.getUserDetails().setFavouriteGenres(preferences.genres());
        user.getUserDetails().setKeyWords(preferences.keyWords());
        userRepository.save(user);
    }

    public UserPreferencesDTO getPreferences(Long id){
        User user = findUserById(id);
        return new UserPreferencesDTO(user.getUserDetails().getFavouriteArtists(),
                user.getUserDetails().getFavouriteGenres(), user.getUserDetails().getKeyWords());
    }

    public List<ExercisesDone> getExercisesHistory(Long id, LocalDateTime date){
        User user = findUserById(id);
        if (date == null){
            date = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        }
        LocalDateTime finalDate = date;
        return user.getUserDetails().getExercisesDone().stream().filter(e -> e.getDoneDate().isAfter(finalDate)).toList();
    }

    public List<SongsPlayed> getSongsHistory(Long id, LocalDateTime date){
        User user = findUserById(id);
        if (date == null){
            date = LocalDateTime.of(2020, 1, 1, 0, 0, 0);
        }
        LocalDateTime finalDate = date;
        return user.getUserDetails().getSongsPlayed().stream().filter(s -> s.getPlayDate().isAfter(finalDate)).toList();
    }

    public FavoritesDTO getFavorites(Long id){
        User user = findUserById(id);
        return new FavoritesDTO(user.getUserDetails().getFavoriteSongs(),
                user.getUserDetails().getFavoriteExercises());
    }

    public List<SongDTO> getSuggestedSongs(Long id){
        User user = findUserById(id);
        List<Tab> songs = tabRepository.findAllSuggested(user.getUserDetails().getFavouriteGenres(), List.of(user.getUserDetails().getLevel()),
                user.getUserDetails().getFavouriteArtists(), user.getUserDetails().getKeyWords());
        List<Long> songsPlayed = user.getUserDetails().getSongsPlayed().stream().map(SongsPlayed::getSongId).toList();
        for(int i=0; i<songs.size(); i++){
            if(songsPlayed.contains(songs.get(i).getSong().getId())){
                songs.remove(i);
            }
        }
        return songs.stream().map(TabMapper::mapToSongDTO).toList();
    }

    public List<Exercise> getSuggestedExercises(Long id){
        User user = findUserById(id);
        Level userLevel = user.getUserDetails().getLevel();
        Set<KeyWord> userKeywords = new HashSet<>(user.getUserDetails().getKeyWords());

        List<Exercise> allExercises = exerciseRepository.findAllSuggested(user.getUserDetails().getPoints());

        return allExercises.stream()
                .sorted(Comparator.comparing(Exercise::getRequiredPoints)
                        .thenComparing((Exercise e) -> {
                            ExercisesDone done = user.getUserDetails().getExercisesDone().stream()
                                    .filter(ed -> ed.getExerciseId().equals(e.getId()))
                                    .findFirst()
                                    .orElse(null);
                            if (done == null) {
                                return 0;
                            }
                            return done.getLevel().ordinal() < userLevel.ordinal() ? -1 : 1;
                        })
                        .thenComparing((Exercise e) -> {
                            long matchingKeywords = e.getKeyWords().stream()
                                    .filter(userKeywords::contains)
                                    .count();
                            return -matchingKeywords;
                        })
                )
                .collect(Collectors.toList());
    }

    public StatisticsDTO getStatistics(Long id){
        User user = userRepository.findById(id).get();
        UserDetails userDetails = user.getUserDetails();
        String lastExercise = userDetails.getExercisesDone().stream()
                .reduce((first, second) -> second)
                .map(ExercisesDone::getExerciseId)
                .flatMap(exerciseId -> exerciseRepository.findById(exerciseId).map(Exercise::getName))
                .orElse("No exercises done");

        String lastSong = userDetails.getSongsPlayed().stream()
                .reduce((first, second) -> second)
                .map(SongsPlayed::getSongId)
                .flatMap(songId -> songRepository.findById(songId).map(Song::getName))
                .orElse("No songs played");

        String totalTime = formatTime(userDetails.getTotalSessionTime());
        String averageTime = formatTime(userDetails.getAverageSessionTime());

        return new StatisticsDTO(
                userDetails.getLevel(),
                userDetails.getPoints(),
                totalTime,
                averageTime,
                lastExercise,
                lastSong
        );
    }

    public String formatTime(long totalSeconds) {
        Duration duration = Duration.ofSeconds(totalSeconds);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format("%02dh:%02dm:%02ds", hours, minutes, seconds);
    }
}
