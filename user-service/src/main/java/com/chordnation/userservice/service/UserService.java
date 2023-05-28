package com.chordnation.userservice.service;

import com.chordnation.userservice.domain.User;
import com.chordnation.userservice.domain.dto.SongDTO;
import com.chordnation.userservice.domain.dto.UserDTO;
import com.chordnation.userservice.exception.UserNotFoundException;
import com.chordnation.userservice.mapper.UserMapper;
import com.chordnation.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TabFeignClient tabFeignClient;

    public UserService(UserRepository userRepository, TabFeignClient tabFeignClient) {
        this.userRepository = userRepository;
        this.tabFeignClient = tabFeignClient;
        this.userMapper = new UserMapper();
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    public List<SongDTO> getSongs(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        return tabFeignClient.getSongs(user.getUserDetails().getFavouriteArtists(),
                user.getUserDetails().getFavouriteGenres(), user.getUserDetails().getLevel(), user.getUserDetails().getGuitarType());
    }

    public void addUser(UserDTO userDTO){
        userRepository.save(userMapper.toEntity(userDTO));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
