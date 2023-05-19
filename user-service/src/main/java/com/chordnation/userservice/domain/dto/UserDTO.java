package com.chordnation.userservice.domain.dto;

import com.chordnation.userservice.domain.UserDetails;

public record UserDTO(Long id, String name, String email, UserDetails userDetails) {
}
