package com.chordnation.userservice.controller;

import com.chordnation.userservice.domain.dto.SongDTO;
import com.chordnation.userservice.domain.dto.UserDTO;
import com.chordnation.userservice.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}/suggestedSongs")
    public List<SongDTO> getSongs(@PathVariable("id") Long id){
        return userService.getSongs(id);
    }

    @PostMapping
    public void addUser(@RequestBody UserDTO userDTO){
        userService.addUser(userDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
