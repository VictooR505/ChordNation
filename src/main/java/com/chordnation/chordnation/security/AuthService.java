package com.chordnation.chordnation.security;

import com.chordnation.chordnation.user.User;
import com.chordnation.chordnation.user.UserRepository;
import com.chordnation.chordnation.user.dto.SignupDTO;
import com.chordnation.chordnation.user.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO createUser(SignupDTO signupDTO) {
        User user = new User();
        user.setName(signupDTO.name());
        user.setEmail(signupDTO.email());
        user.getUserDetails().setLevel(signupDTO.level());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.password()));
        User createdUser = userRepository.save(user);
        return new UserDTO(
                createdUser.getId(),
                createdUser.getEmail(),
                createdUser.getName(),
                createdUser.getUserDetails().getLevel()
        );
    }
}