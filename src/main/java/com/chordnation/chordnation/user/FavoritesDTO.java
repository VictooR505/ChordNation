package com.chordnation.chordnation.user;

import java.util.List;

public record FavoritesDTO(List<Long> songs, List<Long> exercises) {
}
