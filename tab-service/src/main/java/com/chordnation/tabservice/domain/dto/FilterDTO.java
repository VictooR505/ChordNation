package com.chordnation.tabservice.domain.dto;

import com.chordnation.tabservice.domain.enums.GuitarType;
import com.chordnation.tabservice.domain.enums.Level;
import com.chordnation.tabservice.domain.enums.TabType;
import com.chordnation.tabservice.domain.enums.Tuning;

import java.util.List;

public record FilterDTO(List<Level> levels, List<TabType> tabTypes, List<GuitarType> guitarTypes,
                        List<Tuning> tunings) {
}
