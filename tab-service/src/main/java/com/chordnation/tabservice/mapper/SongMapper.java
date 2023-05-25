package com.chordnation.tabservice.mapper;

import com.chordnation.tabservice.domain.Song;
import com.chordnation.tabservice.domain.dto.SongDTO;

public class SongMapper {

    public SongMapper() {
    }

    public SongDTO toDTO(Song song){
        SongDTO songDTO = new SongDTO(
                song.getId(),
                song.getArtist(),
                song.getName(),
                song.getGenre(),
                song.getTabs()
        );
        return songDTO;
    }

    public Song toEntity(SongDTO songDTO){
        Song song = new Song(
                songDTO.id(),
                songDTO.artist(),
                songDTO.name(),
                songDTO.genre(),
                songDTO.tabs()
        );
        return song;
    }
}
