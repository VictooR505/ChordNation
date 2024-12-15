package com.chordnation.chordnation.user;

import com.chordnation.chordnation.tab.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final TabRepository tabRepository;

    public UserService(UserRepository userRepository, TabRepository tabRepository) {
        this.userRepository = userRepository;
        this.tabRepository = tabRepository;
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

    //endpoint do historii cwiczen i utworow- dwa endpointy

    //flaga czy to pierwsze zalogowanie na endpoincie do logowania

    //plus to z messengera

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

}
