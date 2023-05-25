package com.chordnation.tabservice.domain.dto;

import com.chordnation.tabservice.domain.Tab;

import java.util.List;

public record SongDTO(Long id, String artist, String name, String genre, List<Tab> tabs) {
}
