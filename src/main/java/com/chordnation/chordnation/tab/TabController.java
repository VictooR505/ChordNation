package com.chordnation.chordnation.tab;


import com.chordnation.chordnation.enums.Level;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tab")
public class TabController {

    private final TabService tabService;

    public TabController(TabService tabService) {
        this.tabService = tabService;
    }

    @GetMapping("/{id}")
    public Tab getAllTabs(@PathVariable Long id){
        return tabService.findById(id);
    }

    @GetMapping()
    public List<SongDTO> getAllSongs(@RequestParam(required = false, defaultValue = "") List<String> genres,
                                     @RequestParam(required = false, defaultValue = "") List<String> levels,
                                     @RequestParam(required = false, defaultValue = "") String name,
                                     @RequestParam(required = false, defaultValue = "id") String sortBy,
                                     @RequestParam(required = false,  defaultValue = "ASC") String sortOrder){
        List<Level> levelList = new ArrayList<>();
        for (String level : levels) {
            levelList.add(Level.valueOf(level));
        }
        return tabService.getAllSongs(levelList, genres, name, sortBy, sortOrder);
    }

    @PatchMapping("/{songId}/songs/add/{userId}")
    public void addToFavourites(@PathVariable Long userId,
                                @PathVariable Long songId){
        tabService.addToFavourites(userId, songId);
    }

    @PatchMapping("/{songId}/songs/remove/{userId}")
    public void removeFromFavourites(@PathVariable Long userId,
                                     @PathVariable Long songId){
        tabService.removeFromFavourites(userId, songId);
    }

    @GetMapping("/genres")
    public List<String> getGenres(){
        return tabService.getGenres();
    }

    @GetMapping("/artists")
    public List<String> getArtists(){
        return tabService.getArtists();
    }

    @PatchMapping("/rate/{id}")
    public void rareTab(@PathVariable Long id,
                        @RequestBody int rate){
        tabService.rateTab(id, rate);
    }

    @PatchMapping("/play/{id}")
    public void playSong(@PathVariable Long id,
                           @RequestBody Long userId) {
        tabService.playSong(id, userId);
    }




}
