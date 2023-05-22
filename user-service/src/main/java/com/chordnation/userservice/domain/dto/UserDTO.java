package com.chordnation.userservice.domain.dto;

public record UserDTO(Long id, String name, String email, UserDetailsDTO userDetails) {
}
