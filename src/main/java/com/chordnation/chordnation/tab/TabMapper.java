package com.chordnation.chordnation.tab;

public class TabMapper {
    public static SongDTO mapToSongDTO(Tab tab) {
        return new SongDTO(
                tab.getSong().getArtist(),
                tab.getSong().getName(),
                tab.getSong().getGenre(),
                tab.getLevel(),
                tab.getUrl()
        );
    }
}
