package com.example.pathfinder.web.dtos;

import jakarta.validation.constraints.NotBlank;

public class UserLoginDto {

    @NotBlank
    private String userName;
    @NotBlank
    private String password;

    public UserLoginDto() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
