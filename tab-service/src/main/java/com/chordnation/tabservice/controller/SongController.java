package com.chordnation.tabservice.controller;


import com.chordnation.tabservice.domain.Song;
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
    public List<Song> getAllSongs() {
        return songService.getAllSongs();
    }

    @PostMapping
    public void addSong(@RequestBody Song song) {
        songService.addSong(song);
    }

    @DeleteMapping
    public void deleteSong(@RequestBody Long id) {
        songService.deleteSong(id);
    }
}
