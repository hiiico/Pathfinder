package com.example.pathfinder.service.dtos;

import com.example.pathfinder.model.enums.UserLevel;

public class UserProfileDto {
    private String username;
    private String fullName;
    private int age;
    private UserLevel level;

    public UserProfileDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }
}
