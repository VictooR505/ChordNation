package com.chordnation.chordnation.user;

import com.chordnation.chordnation.enums.KeyWord;
import com.chordnation.chordnation.enums.Level;
import com.chordnation.chordnation.exercise.Exercise;
import com.chordnation.chordnation.exercise.ExerciseRepository;
import com.chordnation.chordnation.tab.*;
import com.chordnation.chordnation.user.dto.FavoritesDTO;
import com.chordnation.chordnation.user.dto.UserPreferencesDTO;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TabRepository tabRepository;
    private final ExerciseRepository exerciseRepository;

    public UserService(UserRepository userRepository, TabRepository tabRepository, ExerciseRepository exerciseRepository) {
        this.userRepository = userRepository;
        this.tabRepository = tabRepository;
        this.exerciseRepository = exerciseRepository;
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

    //flaga czy to pierwsze zalogowanie na endpoincie do logowania
    //endpoint do statystyk (czas cwiczen/ sredni czas sesji/ ostatnio cwiczony utwor/cwiczenie

    public UserPreferencesDTO getPreferences(Long id){
        User user = findUserById(id);
        return new UserPreferencesDTO(user.getUserDetails().getFavouriteArtists(),
                user.getUserDetails().getFavouriteGenres(), user.getUserDetails().getKeyWords());
    }

    public List<ExercisesDone> getExercisesHistory(Long id){
        User user = findUserById(id);
        return user.getUserDetails().getExercisesDone();
    }

    public List<SongsPlayed> getSongsHistory(Long id){
        User user = findUserById(id);
        return user.getUserDetails().getSongsPlayed();
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
                            ExercisesDone done = user.getUserDetails().getExercisesDone().get(e.getId().intValue());
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
}
