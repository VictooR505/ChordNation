package com.chordnation.tabservice.domain.dto;

import com.chordnation.tabservice.domain.enums.GuitarType;
import com.chordnation.tabservice.domain.enums.Level;
import com.chordnation.tabservice.domain.enums.TabType;
import com.chordnation.tabservice.domain.enums.Tuning;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

public record TabDTO(Long version, Level level, TabType tabType, GuitarType guitarType,
                     Tuning tuning, @Min(0) @Max(5) int rating, String url) {
}
