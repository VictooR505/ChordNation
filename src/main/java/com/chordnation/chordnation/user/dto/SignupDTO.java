package com.chordnation.chordnation.user.dto;

import com.chordnation.chordnation.enums.Level;

public record SignupDTO(String name, String email, String password, Level level) {
}
