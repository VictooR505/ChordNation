package com.chordnation.tabservice.controller;


import com.chordnation.tabservice.domain.dto.SongDTO;
import com.chordnation.tabservice.service.SongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<SongDTO> getAllSongs() {
        return songService.getAllSongs();
    }

    @PostMapping
    public void addSong(@RequestBody SongDTO songDTO) {
        songService.addSong(songDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable("id") Long id) {
        songService.deleteSong(id);
    }
}
