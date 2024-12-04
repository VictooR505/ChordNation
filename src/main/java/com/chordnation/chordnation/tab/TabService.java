package com.chordnation.chordnation.tab;

import com.chordnation.chordnation.enums.Genre;
import com.chordnation.chordnation.enums.Level;
import com.chordnation.chordnation.user.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TabService {

    private final TabRepository tabRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;
    Logger logger = Logger.getLogger(getClass().getName());

    public TabService(TabRepository tabRepository, SongRepository songRepository, UserRepository userRepository) {
        this.tabRepository = tabRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }

    public List<Tab> getTabsBySong(Long id){
        Song song = songRepository.findById(id).get();
        return tabRepository.findAllBySong(song);
    }

    public List<SongDTO> getAllSongs(List<Level> level, List<Genre> genre, String fullName, String sortBy, String sortOrder){
        level = level.isEmpty() ? List.of(Level.values()) : level;
        genre = genre.isEmpty() ? List.of(Genre.values()) : genre;

        if(fullName.isEmpty()){
            tabRepository.findAll(genre, level, Sort.by(Sort.Direction.valueOf(sortOrder.toUpperCase()), sortBy)).stream().map(TabMapper::mapToSongDTO).distinct().toList();
        }
        String artist = fullName;
        String name = fullName;
        return tabRepository.findAllWithName(genre, level, artist, name, Sort.by(Sort.Direction.valueOf(sortOrder.toUpperCase()), sortBy))
                .stream().map(TabMapper::mapToSongDTO).distinct().toList();
    }

    public void rateTab(Long id, int rate){
        Tab tab = tabRepository.findById(id).get();
        double currentRateNumber = tab.getRateNumber();
        double currentRate = tab.getRate()*currentRateNumber;
        tab.setRateNumber(currentRateNumber + 1);
        tab.setRate((currentRate+rate)/(currentRateNumber+1));
        tabRepository.save(tab);
    }

    public void playSong(Long songId, Long userId){
        User user = userRepository.findById(userId).get();
        UserDetails userDetails = user.getUserDetails();
        List<SongsPlayed> songsPlayed = userDetails.getSongsPlayed();
        SongsPlayed song = new SongsPlayed(songId, LocalDateTime.now());
        songsPlayed.add(song);
        userDetails.setSongsPlayed(songsPlayed);
        userRepository.save(user);
    }

    public void addToFavourites(Long userId, Long songId){
        User user = userRepository.findById(userId).get();
        List<Long> songs = user.getUserDetails().getFavoriteSongs();
        songs.add(songId);
        user.getUserDetails().setFavoriteSongs(songs);
        userRepository.save(user);
    }

    public void removeFromFavourites(Long userId, Long songId){
        User user = userRepository.findById(userId).get();
        List<Long> songs = user.getUserDetails().getFavoriteSongs();
        songs.remove(songId);
        user.getUserDetails().setFavoriteSongs(songs);
        userRepository.save(user);
    }

    public List<Genre> getGenres(){
        return List.of(Genre.values());
    }

    public List<String> getArtists(){
        return songRepository.getAllArtists();
    }
}
