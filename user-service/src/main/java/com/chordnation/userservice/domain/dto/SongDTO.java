package com.chordnation.userservice.domain.dto;

import java.util.List;

public record SongDTO(Long id, String name, String artist, String genre, List<TabDTO> tabs) {
}
