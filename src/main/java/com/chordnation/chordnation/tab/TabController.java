package com.chordnation.chordnation.tab;


import com.chordnation.chordnation.enums.Genre;
import com.chordnation.chordnation.enums.GuitarType;
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

    @GetMapping("/get-by-song-id/{id}")
    public List<Tab> getAllTabsBySong(@PathVariable Long id){
        return tabService.getTabsBySong(id);
    }

    @GetMapping("/song/{id}")
    public SongDTO getSongById(@PathVariable Long id){
        return tabService.getSongById(id);
    }

    @GetMapping()
    public List<SongDTO> getAllSongs(@RequestParam(required = false, defaultValue = "") List<Genre> genres,
                                     @RequestParam(required = false, defaultValue = "") List<String> levels,
                                     @RequestParam(required = false, defaultValue = "") String name,
                                     @RequestParam(required = false, defaultValue = "") List<GuitarType> guitarTypes,
                                     @RequestParam(required = false, defaultValue = "id") String sortBy,
                                     @RequestParam(required = false,  defaultValue = "ASC") String sortOrder){
        List<Level> levelList = new ArrayList<>();
        for (String level : levels) {
            levelList.add(Level.valueOf(level));
        }
        return tabService.getAllSongs(levelList, genres, guitarTypes, name,  sortBy, sortOrder);
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
    public List<Genre> getGenres(){
        return tabService.getGenres();
    }

    @GetMapping("/artists")
    public List<String> getArtists(){
        return tabService.getArtists();
    }

    @PatchMapping("/rate/{tabId}/user/{userId}")
    public void rareTab(@PathVariable Long tabId,
                        @RequestBody int rate,
                        @PathVariable Long userId){
        tabService.rateTab(tabId, rate, userId);
    }

    @PatchMapping("/play/{id}")
    public void playSong(@PathVariable Long id,
                           @RequestBody Long userId) {
        tabService.playSong(id, userId);
    }




}
