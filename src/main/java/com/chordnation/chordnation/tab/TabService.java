package com.chordnation.chordnation.tab;

import com.chordnation.chordnation.enums.Genre;
import com.chordnation.chordnation.enums.GuitarType;
import com.chordnation.chordnation.enums.Level;
import com.chordnation.chordnation.user.*;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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

    public List<TabDTO> getTabsBySong(Long id, Long userId){
        User user = userRepository.findById(userId).get();
        Song song = songRepository.findById(id).get();
        List<Tab> tabList = tabRepository.findAllBySong(song);
        Map<Long, Integer> ratedTabs = user.getUserDetails().getRatedTabs();

        return tabList.stream()
                .map(tab -> mapToTabDTO(tab, ratedTabs.getOrDefault(tab.getId(), 0)))
                .collect(Collectors.toList());
    }

    public SongDTO getSongById(Long id){
        Tab tab = tabRepository.findById(id).get();
        return TabMapper.mapToSongDTO(tab);
    }

    public List<SongDTO> getAllSongs(List<Level> level, List<Genre> genre, List<GuitarType> guitarType, String fullName, String sortBy, String sortOrder){
        level = level.isEmpty() ? List.of(Level.values()) : level;
        genre = genre.isEmpty() ? List.of(Genre.values()) : genre;
        guitarType = guitarType.isEmpty() ? List.of(GuitarType.values()) : guitarType;


        if(fullName.isEmpty()){
            tabRepository.findAll(genre, level, guitarType, Sort.by(Sort.Direction.valueOf(sortOrder.toUpperCase()), sortBy)).stream().map(TabMapper::mapToSongDTO).filter(distinctByKey(SongDTO::id)).toList();
        }
        String artist = fullName;
        String name = fullName;
        return tabRepository.findAllWithName(genre, level, guitarType, artist, name, Sort.by(Sort.Direction.valueOf(sortOrder.toUpperCase()), sortBy))
                .stream().map(TabMapper::mapToSongDTO).filter(distinctByKey(SongDTO::id)).toList();
    }

    public void rateTab(Long tabId, int rate, Long userId){
        User user = userRepository.findById(userId).get();
        Tab tab = tabRepository.findById(tabId).get();

        Map<Long, Integer> ratedTabs = user.getUserDetails().getRatedTabs();
        double currentRateNumber = tab.getRateNumber();
        double currentRate = tab.getRate() * currentRateNumber;

        if (ratedTabs.containsKey(tabId)) {
            double previousRate = ratedTabs.get(tabId);
            ratedTabs.put(tabId, rate);
            double rateDifference = rate - previousRate;
            tab.setRate((currentRate + rateDifference) / currentRateNumber);
        } else {
            ratedTabs.put(tabId, rate);
            tab.setRateNumber(currentRateNumber + 1);
            tab.setRate((currentRate + rate) / (currentRateNumber + 1));
        }
        userRepository.save(user);
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

    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Set<Object> seen = ConcurrentHashMap.newKeySet();
        return t -> seen.add(keyExtractor.apply(t));
    }

    private TabDTO mapToTabDTO(Tab tab, int userRate) {
        return new TabDTO(
                userRate,
                tab.getId(),
                tab.getLevel(),
                tab.getTabType(),
                tab.getGuitarType(),
                tab.getTuning(),
                tab.getKeyWords(),
                tab.getRate(),
                tab.getRateNumber(),
                tab.getUrl()
        );
    }
}
