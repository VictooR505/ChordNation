package com.chordnation.tabservice.controller;

import com.chordnation.tabservice.domain.dto.TabDTO;
import com.chordnation.tabservice.service.TabService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tabs")
public class TabController {
    private final TabService tabService;

    public TabController(TabService tabService) {
        this.tabService = tabService;
    }

    @GetMapping
    public List<TabDTO> getAllTabs() {
        return tabService.getAllTabs();
    }

    @PostMapping
    public void addTab(@RequestBody TabDTO tabDTO) {
        tabService.addTab(tabDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteTab(@PathVariable("id") Long id) {
        tabService.deleteTab(id);
    }
}
