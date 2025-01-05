package com.chordnation.chordnation.user.dto;

import java.util.List;
import java.util.Map;

public record KeyWordDTO(String group, List<Map<String, String>> keyWordsByGroup) {
}
