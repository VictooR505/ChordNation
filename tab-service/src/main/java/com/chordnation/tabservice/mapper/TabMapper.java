package com.chordnation.tabservice.mapper;

import com.chordnation.tabservice.domain.Tab;
import com.chordnation.tabservice.domain.dto.TabDTO;

public class TabMapper {

    public TabMapper() {
    }

    public TabDTO toDTO(Tab tab){
        TabDTO tabDTO = new TabDTO(
                tab.getId(),
                tab.getLevel(),
                tab.getTabType(),
                tab.getGuitarType(),
                tab.getTuning(),
                tab.getRate(),
                tab.getUrl()
        );
        return tabDTO;
    }

    public Tab toEntity(TabDTO tabDTO){
        Tab tab = new Tab(
                tabDTO.id(),
                tabDTO.level(),
                tabDTO.tabType(),
                tabDTO.guitarType(),
                tabDTO.tuning(),
                tabDTO.rating(),
                tabDTO.url()
        );
        return tab;
    }
}
