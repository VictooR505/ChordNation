package com.chordnation.tabservice.service;

import com.chordnation.tabservice.domain.dto.SongDTO;
import com.chordnation.tabservice.domain.enums.GuitarType;
import com.chordnation.tabservice.domain.enums.Level;
import com.chordnation.tabservice.domain.enums.TabType;
import com.chordnation.tabservice.domain.enums.Tuning;
import com.chordnation.tabservice.mapper.SongMapper;
import com.chordnation.tabservice.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {
    private final SongRepository songRepository;
    private final SongMapper songMapper;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
        this.songMapper = new SongMapper();
    }

    public List<SongDTO> getAllSongs() {
        return songRepository.findAll().stream().map(songMapper::toDTO).toList();
    }

    public List<SongDTO> getSongsWithFilters(List<String> artist, List<String> genre, List<Level> level, List<TabType> tabType,
                                             List<GuitarType> guitarType, List<Tuning> tuning){
        artist = artist.isEmpty() ? songRepository.getAllArtists() : artist;
        genre = genre.isEmpty() ? songRepository.getAllGenres() : genre;
        level = level.isEmpty() ? List.of(Level.values()) : level;
        tabType = tabType.isEmpty() ? List.of(TabType.values()) : tabType;
        guitarType = guitarType.isEmpty() ? List.of(GuitarType.values()) : guitarType;
        tuning = tuning.isEmpty() ? List.of(Tuning.values()) : tuning;
        return songRepository.findAllWithFilters(artist, genre, level, tabType, guitarType, tuning).stream().map(songMapper::toDTO).toList();
    }

    public void addSong(SongDTO songDTO) {
        songRepository.save(songMapper.toEntity(songDTO));
    }

    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }

}
