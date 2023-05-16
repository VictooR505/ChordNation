package com.chordnation.tabservice.service;

import com.chordnation.tabservice.domain.dto.SongDTO;
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

    public void addSong(SongDTO songDTO) {
        songRepository.save(songMapper.toEntity(songDTO));
    }

    public void deleteSong(Long id) {
        songRepository.deleteById(id);
    }
}
