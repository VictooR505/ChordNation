package com.chordnation.userservice.domain.dto;

import com.chordnation.userservice.domain.enums.GuitarType;
import com.chordnation.userservice.domain.enums.Level;
import com.chordnation.userservice.domain.enums.TabType;
import com.chordnation.userservice.domain.enums.Tuning;

public record TabDTO(Long id, Level level, TabType tabType, GuitarType guitarType,
                     Tuning tuning, int rating, String url) {
}
