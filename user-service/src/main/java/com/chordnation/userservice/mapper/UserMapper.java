package com.chordnation.userservice.mapper;

import com.chordnation.userservice.domain.User;
import com.chordnation.userservice.domain.dto.UserDTO;

public class UserMapper {
    public UserMapper() {
    }

    public UserDTO toDTO(User user){
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserDetails()
        );
        return userDTO;
    }

    public User toEntity(UserDTO userDTO){
        User user = new User(
                userDTO.id(),
                userDTO.name(),
                userDTO.email(),
                userDTO.userDetails()
        );
        return user;
    }
}
