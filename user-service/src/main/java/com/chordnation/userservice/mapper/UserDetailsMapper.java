package com.chordnation.userservice.mapper;

import com.chordnation.userservice.domain.UserDetails;
import com.chordnation.userservice.domain.dto.UserDetailsDTO;

public class UserDetailsMapper {
    public UserDetailsMapper() {
    }

    public UserDetailsDTO toDTO(UserDetails userDetails){
        UserDetailsDTO userDetailsDTO = new UserDetailsDTO(
                userDetails.getId(),
                userDetails.getLevel(),
                userDetails.getGuitarType(),
                userDetails.getFavouriteGenres(),
                userDetails.getFavouriteArtists()
        );
        return userDetailsDTO;
    }

    public UserDetails toEntity(UserDetailsDTO userDetailsDTO){
        UserDetails userDetails = new UserDetails(
                userDetailsDTO.id(),
                userDetailsDTO.level(),
                userDetailsDTO.guitarType(),
                userDetailsDTO.favouriteGenres(),
                userDetailsDTO.favouriteArtists()
        );
        return userDetails;
    }
}
