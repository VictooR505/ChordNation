package com.chordnation.chordnation.user;

import com.chordnation.chordnation.enums.Genre;
import com.chordnation.chordnation.enums.KeyWord;

import java.util.List;

public record UserPreferencesDTO(List<String> artists, List<Genre> genres, List<KeyWord> keyWords) {
}
