package com.chordnation.userservice.domain.dto;

import jakarta.validation.constraints.Email;

public record UserDTO(Long id, String name, @Email String email, UserDetailsDTO userDetails) {
}
