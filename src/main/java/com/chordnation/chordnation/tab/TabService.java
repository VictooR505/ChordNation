package com.chordnation.chordnation.tab;

import com.chordnation.chordnation.enums.Level;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class TabService {

    private final TabRepository tabRepository;
    private final SongRepository songRepository;
    Logger logger = Logger.getLogger(getClass().getName());

    public TabService(TabRepository tabRepository, SongRepository songRepository) {
        this.tabRepository = tabRepository;
        this.songRepository = songRepository;
    }

    public List<Tab> findAll(){
        return tabRepository.findAll();
    }

    public Tab findById(Long id){
        return tabRepository.findById(id).get();
    }

    public Song getSong(Long id){
        return songRepository.findById(id).get();
    }

    public List<SongDTO> getAllSongs(List<Level> level, List<String> genre, String fullName, String sortBy, String sortOrder){
        level = level.isEmpty() ? List.of(Level.values()) : level;
        genre = genre.isEmpty() ? songRepository.getAllGenres() : genre;

        if(fullName.isEmpty()){
            tabRepository.findAll(genre, level, Sort.by(Sort.Direction.valueOf(sortOrder.toUpperCase()), sortBy)).stream().map(TabMapper::mapToSongDTO).toList();
        }
        String artist = fullName;
        String name = fullName;
        return tabRepository.findAllWithName(genre, level, artist, name, Sort.by(Sort.Direction.valueOf(sortOrder.toUpperCase()), sortBy))
                .stream().map(TabMapper::mapToSongDTO).toList();
    }

    public void rateTab(Long id, int rate){
        Tab tab = tabRepository.findById(id).get();
        double currentRateNumber = tab.getRateNumber();
        double currentRate = tab.getRate()*currentRateNumber;
        tab.setRateNumber(currentRateNumber + 1);
        tab.setRate((currentRate+rate)/(currentRateNumber+1));
        tabRepository.save(tab);
    }

    public List<String> getGenres(){
        return songRepository.getAllGenres();
    }
}
