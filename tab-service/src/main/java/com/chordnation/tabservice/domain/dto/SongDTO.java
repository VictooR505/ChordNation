package com.chordnation.tabservice.domain.dto;

import java.util.List;

public record SongDTO(Long id, String artist, String name, String genre, List<TabDTO> tabs) {
}
