package com.chordnation.tabservice.service;

import com.chordnation.tabservice.domain.Tab;
import com.chordnation.tabservice.repository.TabRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabService {
    private final TabRepository tabRepository;

    public TabService(TabRepository tabRepository) {
        this.tabRepository = tabRepository;
    }

    public List<Tab> getAllTabs() {
        return tabRepository.findAll();
    }

    public void addTab(Tab tab) {
        tabRepository.save(tab);
    }

    public void deleteTab(Long id) {
        tabRepository.deleteById(id);
    }
}
