package com.chordnation.chordnation.tab;

import com.chordnation.chordnation.enums.Genre;
import com.chordnation.chordnation.enums.GuitarType;
import com.chordnation.chordnation.enums.Level;

public record SongDTO(Long id, String artist, String name, Genre genre, Level  level, String imageUrl, GuitarType guitarType) {


}
