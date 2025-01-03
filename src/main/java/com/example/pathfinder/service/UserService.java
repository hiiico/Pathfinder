package com.example.pathfinder.service;

import com.example.pathfinder.data.UserRepository;
import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.web.dtos.UserLoginDto;
import com.example.pathfinder.web.dtos.UserRegisterDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    public void register(UserRegisterDto userRegisterDto) {
        User user = modelMapper.map(userRegisterDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    public void login(UserLoginDto loginData) {
        User user = userRepository.findByUsername(loginData.getUserName());
        if(user == null) {
            // TODO throw exception
            return;
        }
        if(passwordEncoder.matches(loginData.getPassword(), user.getPassword())
                && !currentUser.isLoggedIn()) {
            currentUser.setUser(user);
        }

    }

    public void logout() {
        currentUser.setUser(null);
    }
}
