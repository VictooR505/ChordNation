package com.chordnation.userservice.service;

import com.chordnation.userservice.domain.dto.UserDTO;
import com.chordnation.userservice.mapper.UserMapper;
import com.chordnation.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.userMapper = new UserMapper();
    }

    public List<UserDTO> getAllUsers(){
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }

    public void addUser(UserDTO userDTO){
        userRepository.save(userMapper.toEntity(userDTO));
    }

    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
