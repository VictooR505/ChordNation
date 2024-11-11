package com.chordnation.chordnation.tab;


import com.chordnation.chordnation.enums.Level;
import org.springframework.data.repository.query.Param;
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
        for (int i=0;i<levels.size();i++){
            levelList.add(Level.valueOf(levels.get(i)));
        }
        return tabService.getAllSongs(levelList, genres, name, sortBy, sortOrder);
    }

    @GetMapping("/genres")
    public List<String> getGenres(){
        return tabService.getGenres();
    }

    @PatchMapping("/rate/{id}")
    public void rareTab(@PathVariable Long id,
                        @RequestBody int rate){
        tabService.rateTab(id, rate);
    }

   // @GetMapping("/{id}")

  //  @GetMapping("/song/{id}")

}
