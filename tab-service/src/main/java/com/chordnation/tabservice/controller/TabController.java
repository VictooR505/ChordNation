package com.chordnation.tabservice.controller;

import com.chordnation.tabservice.domain.Tab;
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
    public List<Tab> getAllTabs() {
        return tabService.getAllTabs();
    }

    @PostMapping
    public void addTab(@RequestBody Tab tab) {
        tabService.addTab(tab);
    }

    @DeleteMapping
    public void deleteTab(@RequestBody Long id) {
        tabService.deleteTab(id);
    }
}
