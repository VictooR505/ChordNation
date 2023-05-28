package com.chordnation.tabservice.controller;


import com.chordnation.tabservice.domain.dto.SongDTO;
import com.chordnation.tabservice.domain.enums.GuitarType;
import com.chordnation.tabservice.domain.enums.Level;
import com.chordnation.tabservice.domain.enums.TabType;
import com.chordnation.tabservice.domain.enums.Tuning;
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

    @GetMapping("/filter")
    public List<SongDTO> getSongsWithFilters(@RequestParam(required = false, defaultValue = "") List<String> artist,
                                             @RequestParam(required = false, defaultValue = "") List<String> genre,
                                             @RequestParam(required = false, defaultValue = "") List<Level> level,
                                             @RequestParam(required = false, defaultValue = "") List<TabType> tabType,
                                             @RequestParam(required = false, defaultValue = "") List<GuitarType> guitarType,
                                             @RequestParam(required = false, defaultValue = "") List<Tuning> tuning
                                             ){
        return songService.getSongsWithFilters(artist, genre, level ,tabType, guitarType, tuning);
    }

    @PostMapping
    public void addSong(@RequestBody SongDTO songDTO) {
        songService.addSong(songDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSong(@PathVariable("id") Long id) {
        songService.deleteSong(id);
    }

    @GetMapping("/suggested")
    public List<SongDTO> findAllSuggestedForUser(@RequestParam(required = false, defaultValue = "") List<String> artist,
                                                 @RequestParam(required = false, defaultValue = "") List<String> genres,
                                                 @RequestParam Level level,
                                                 @RequestParam GuitarType guitarType){
        return songService.findAllSuggestedForUser(artist, genres, level, guitarType);
    }
}
