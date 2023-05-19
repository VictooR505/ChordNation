package com.chordnation.userservice.domain.dto;

import com.chordnation.userservice.domain.enums.GuitarType;
import com.chordnation.userservice.domain.enums.Level;

import java.util.List;

public record UserDetailsDTO(Long id, Level level, GuitarType guitarType,
                             List<String> favouriteGenres, List<String> favouriteArtists) {
}
