package com.chordnation.chordnation.tab;

import com.chordnation.chordnation.enums.*;

import java.util.List;

public record TabDTO(
        Integer userRate,
        Long id,
        Level level,
        TabType tabType,
        GuitarType guitarType,
        Tuning tuning,
        List<KeyWord> keyWords,
        double rate,
        double rateNumber,
        String url) {
}
