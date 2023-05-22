package com.chordnation.userservice.mapper;

import com.chordnation.userservice.domain.User;
import com.chordnation.userservice.domain.UserDetails;
import com.chordnation.userservice.domain.dto.UserDTO;
import com.chordnation.userservice.domain.dto.UserDetailsDTO;

public class UserMapper {
    public UserMapper() {
    }

    public UserDTO toDTO(User user){
        UserDetailsDTO userDetailsDTO = userDetailsToDTO(user.getUserDetails());
        UserDTO userDTO = new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                userDetailsDTO
        );
        return userDTO;
    }

    public User toEntity(UserDTO userDTO){
       UserDetails userDetails = userDetailsToEntity(userDTO.userDetails());
        User user = new User(
                userDTO.id(),
                userDTO.name(),
                userDTO.email(),
                userDetails
        );
        return user;
    }

    private static UserDetailsDTO userDetailsToDTO(UserDetails userDetails){
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(
                userDetails.getLevel(),
                userDetails.getGuitarType(),
                userDetails.getFavouriteGenres(),
                userDetails.getFavouriteArtists()
        );
        return userDetailsDTO;
    }

    private static UserDetails userDetailsToEntity(UserDetailsDTO userDetailsDTO){
        UserDetails userDetails = new UserDetails(
                userDetailsDTO.level(),
                userDetailsDTO.guitarType(),
                userDetailsDTO.favouriteGenres(),
                userDetailsDTO.favouriteArtists()
        );
        return userDetails;
    }
}
