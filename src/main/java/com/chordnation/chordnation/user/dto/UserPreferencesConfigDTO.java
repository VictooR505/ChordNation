package com.chordnation.chordnation.user.dto;

import com.chordnation.chordnation.enums.Genre;

import java.util.List;

public record UserPreferencesConfigDTO(List<String> artists, List<Genre> genres, List<KeyWordDTO> keyWords) {
}

