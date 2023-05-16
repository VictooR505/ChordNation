package com.chordnation.tabservice.domain.dto;

import com.chordnation.tabservice.domain.enums.GuitarType;
import com.chordnation.tabservice.domain.enums.Level;
import com.chordnation.tabservice.domain.enums.TabType;
import com.chordnation.tabservice.domain.enums.Tuning;

public record TabDTO(Long id, Level level, TabType tabType, GuitarType guitarType,
                     Tuning tuning, int rating, String url) {
}
