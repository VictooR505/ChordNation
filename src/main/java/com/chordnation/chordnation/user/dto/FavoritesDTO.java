package com.chordnation.chordnation.user.dto;

import java.util.List;

public record FavoritesDTO(List<Long> songs, List<Long> exercises) {
}
