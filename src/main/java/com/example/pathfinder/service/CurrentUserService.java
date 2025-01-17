package com.example.pathfinder.service;

import com.example.pathfinder.data.UserRepository;
import com.example.pathfinder.model.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrentUserService {

    private static final String ROLE_PREFIX = "ROLE";
    // if we need the entire user
    private final UserRepository userRepository;

    public User getUser() {
        return userRepository.findByUsername(getUserDetails().getUsername())
                .orElse(null);
    }

    public boolean hasRole(String role) {
        return getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(ROLE_PREFIX + role));
    }

    public UserDetails getUserDetails() {
        return (UserDetails) getAuthentication().getPrincipal();
    }

    public boolean isAuthenticated() {
        return !hasRole("ANONYMOUS");
    }

   public Authentication getAuthentication() {
       return SecurityContextHolder.getContext().getAuthentication();
   }

}
