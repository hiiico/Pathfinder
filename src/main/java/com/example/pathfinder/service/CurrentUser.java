package com.example.pathfinder.service;

import com.example.pathfinder.model.entities.User;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CurrentUser {
    private User user;

    public boolean isLoggedIn() {
        return user != null;
    }

    public boolean isAdmin(){
        return this.user.getRoles()
                .stream()
                .anyMatch(role -> role.getName().equals("ADMIN"));
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
