package com.example.pathfinder.service;

import com.example.pathfinder.data.UserRepository;
import com.example.pathfinder.exception.DomainException;
import com.example.pathfinder.model.entities.User;
import com.example.pathfinder.service.dtos.UserProfileDto;
import com.example.pathfinder.web.dto.UserRegister;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final CurrentUserService currentUserService;

    public void register(UserRegister userRegister) {

        Optional<User> userOptional = userRepository.findByUsername(userRegister.getUsername());
        if (userOptional.isPresent()){
            throw new DomainException("Username [%s] is already taken!".formatted(userRegister.getUsername()));
        }
        User user = modelMapper.map(userRegister, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
//        userRepository.save(initilizeUser(userRegister));
//        return user;
    }

/*    private User initilizeUser(UserRegister userRegister) {
        return User.builder()
                .userName(userRegister.getUsername())
                .
    }
*/

    public UserProfileDto getProfileData() {
        return modelMapper.map(currentUserService.getUser(), UserProfileDto.class);
    }

}
