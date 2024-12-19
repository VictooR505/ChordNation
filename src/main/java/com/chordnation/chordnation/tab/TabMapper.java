package com.chordnation.chordnation.tab;

public class TabMapper {
    public static SongDTO mapToSongDTO(Tab tab) {
        return new SongDTO(
                tab.getSong().getId(),
                tab.getSong().getArtist(),
                tab.getSong().getName(),
                tab.getSong().getGenre(),
                tab.getLevel(),
                tab.getSong().getImageUrl(),
                tab.getGuitarType()
        );
    }
    public static SongDTO mapToTabDTO(Tab tab) {
        return new SongDTO(
                tab.getSong().getId(),
                tab.getSong().getArtist(),
                tab.getSong().getName(),
                tab.getSong().getGenre(),
                tab.getLevel(),
                tab.getSong().getImageUrl(),
                tab.getGuitarType()
        );
    }
}
