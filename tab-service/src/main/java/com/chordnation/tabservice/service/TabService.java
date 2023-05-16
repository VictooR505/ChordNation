package com.chordnation.tabservice.service;

import com.chordnation.tabservice.domain.dto.TabDTO;
import com.chordnation.tabservice.mapper.TabMapper;
import com.chordnation.tabservice.repository.TabRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TabService {
    private final TabRepository tabRepository;
    private final TabMapper tabMapper;

    public TabService(TabRepository tabRepository) {
        this.tabRepository = tabRepository;
        this.tabMapper = new TabMapper();
    }

    public List<TabDTO> getAllTabs() {
        return tabRepository.findAll().stream().map(tabMapper::toDTO).toList();
    }

    public void addTab(TabDTO tabDTO) {
        tabRepository.save(tabMapper.toEntity(tabDTO));
    }

    public void deleteTab(Long id) {
        tabRepository.deleteById(id);
    }
}
