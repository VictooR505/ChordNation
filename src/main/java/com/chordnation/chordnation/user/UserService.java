package com.chordnation.chordnation.user;

import com.chordnation.chordnation.enums.Genre;
import com.chordnation.chordnation.enums.KeyWord;
import com.chordnation.chordnation.enums.Level;
import com.chordnation.chordnation.exercise.Exercise;
import com.chordnation.chordnation.exercise.ExerciseRepository;
import com.chordnation.chordnation.tab.*;
import com.chordnation.chordnation.user.dto.FavoritesDTO;
import com.chordnation.chordnation.user.dto.KeyWordDTO;
import com.chordnation.chordnation.user.dto.StatisticsDTO;
import com.chordnation.chordnation.user.dto.UserPreferencesConfigDTO;
import com.chordnation.chordnation.user.dto.UserPreferencesDTO;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TabRepository tabRepository;
    private final ExerciseRepository exerciseRepository;
    private final SongRepository songRepository;
    Logger logger = Logger.getLogger(getClass().getName());

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
        return songs.stream().map(TabMapper::mapToSongDTO).limit(3).toList();
    }

    public List<Exercise> getSuggestedExercises(Long id){
        User user = findUserById(id);
        Level userLevel = user.getUserDetails().getLevel();
        Set<KeyWord> userKeywords = new HashSet<>(user.getUserDetails().getKeyWords());

        List<Exercise> allExercises = exerciseRepository.findByRequiredPointsLessThanEqual(user.getUserDetails().getPoints());

        allExercises = allExercises.stream()
                .filter(e -> {
                    Optional<ExercisesDone> doneOpt = user.getUserDetails().getExercisesDone().stream()
                            .filter(ed -> ed.getExerciseId().equals(e.getId()))
                            .findFirst();
                    if (doneOpt.isEmpty()) {
                        return true;
                    }
                    ExercisesDone done = doneOpt.get();
                    return done.getLevel() != Level.MASTER;
                })
                .sorted(Comparator.comparing(Exercise::getRequiredPoints)
                        .thenComparing((Exercise e) -> {
                            Optional<ExercisesDone> doneOpt = user.getUserDetails().getExercisesDone().stream()
                                    .filter(ed -> ed.getExerciseId().equals(e.getId()))
                                    .findFirst();
                            if (doneOpt.isEmpty()) {
                                return 0;
                            }
                            ExercisesDone done = doneOpt.get();
                            return done.getLevel().ordinal() < userLevel.ordinal() ? -1 : 1;
                        })
                        .thenComparing((Exercise e) -> {
                            long matchingKeywords = e.getKeyWords().stream()
                                    .filter(userKeywords::contains)
                                    .count();
                            return -matchingKeywords;
                        })
                )
                .limit(5)
                .toList();
        return allExercises;
    }

    public StatisticsDTO getStatistics(Long id){
        User user = userRepository.findById(id).get();
        UserDetails userDetails = user.getUserDetails();
        String lastExercise = userDetails.getExercisesDone().stream()
                .reduce((first, second) -> second)
                .map(ExercisesDone::getExerciseId)
                .flatMap(exerciseId -> exerciseRepository.findById(exerciseId).map(Exercise::getName))
                .orElse("Brak wykonanych ćwiczeń");

        String lastSong = userDetails.getSongsPlayed().stream()
                .reduce((first, second) -> second)
                .map(SongsPlayed::getSongId)
                .flatMap(songId -> songRepository.findById(songId).map(s -> s.getArtist()+": " + s.getName()))
                .orElse("Brak zagranych utworów");

        String totalTime = formatTime(userDetails.getTotalSessionTime());
        String averageTime = formatTime(userDetails.getAverageSessionTime());

        int userCurrentLevelPoints = userDetails.getPoints() - userDetails.getLevel().getMinPoints();
        Level previousUserLevel = userDetails.getLevel().getPreviousLevel();
        int userPointsToNextRank = userDetails.getLevel().getMaxPoints() - previousUserLevel.getMaxPoints();

        // When max level reached
        if (userDetails.getLevel() == Level.MASTER) {
            userCurrentLevelPoints = Level.MASTER.getMinPoints();
            userPointsToNextRank = userCurrentLevelPoints;
        }

        return new StatisticsDTO(
                userDetails.getLevel(),
                userCurrentLevelPoints,
                totalTime,
                averageTime,
                lastExercise,
                lastSong,
                userPointsToNextRank
        );
    }

    public UserPreferencesConfigDTO getUserPreferencesConfig() {

        List<String> artists = songRepository.getAllArtists();
        List<Genre> genres = Arrays.stream(Genre.values()).toList();

        List<KeyWordDTO> keyWords = KeyWord.getGroupedByGroup().entrySet().stream()
                .map(entry -> {
                    String group = entry.getKey();
                    List<Map<String, String>> keyWordsByGroup = entry.getValue().stream()
                            .map(keyword -> Map.of(
                                    "name", keyword.name(),
                                    "description", keyword.getDescription()
                            ))
                            .collect(Collectors.toList());
                    return new KeyWordDTO(group, keyWordsByGroup);
                })
                .collect(Collectors.toList());

        return new UserPreferencesConfigDTO(artists, genres, keyWords);
    }

    public String formatTime(long totalSeconds) {
        Duration duration = Duration.ofSeconds(totalSeconds);
        long hours = duration.toHours();
        long minutes = duration.toMinutesPart();
        long seconds = duration.toSecondsPart();

        return String.format("%02dh:%02dm:%02ds", hours, minutes, seconds);
    }
}
