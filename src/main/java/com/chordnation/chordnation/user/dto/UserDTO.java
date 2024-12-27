package com.chordnation.chordnation.user.dto;

import com.chordnation.chordnation.enums.Level;

public record UserDTO(Long id, String name, String email, Level level) {
}
