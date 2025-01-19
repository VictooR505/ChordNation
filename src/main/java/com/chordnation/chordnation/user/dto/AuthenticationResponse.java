package com.chordnation.chordnation.user.dto;

public record AuthenticationResponse(String JwtToken, boolean firstLogin, String username, String email, Long id) {
}
