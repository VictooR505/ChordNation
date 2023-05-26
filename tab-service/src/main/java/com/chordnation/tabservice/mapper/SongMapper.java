package com.chordnation.tabservice.mapper;

import com.chordnation.tabservice.domain.Song;
import com.chordnation.tabservice.domain.dto.SongDTO;

public class SongMapper {
    private final TabMapper tabMapper;
    public SongMapper() {
        this.tabMapper = new TabMapper();
    }

    public SongDTO toDTO(Song song){
        SongDTO songDTO = new SongDTO(
                song.getId(),
                song.getArtist(),
                song.getName(),
                song.getGenre(),
                song.getTabs().stream().map(tabMapper::toDTO).toList()
        );
        return songDTO;
    }

    public Song toEntity(SongDTO songDTO){
        Song song = new Song(
                songDTO.id(),
                songDTO.artist(),
                songDTO.name(),
                songDTO.genre(),
                songDTO.tabs().stream().map(tabMapper::toEntity).toList()
        );
        return song;
    }
}
