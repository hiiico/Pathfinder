package com.example.pathfinder.service.dtos;

import com.example.pathfinder.model.enums.UserLevel;
import lombok.Data;

@Data
public class UserProfileDto {
    private String username;
    private String fullName;
    private int age;
    private UserLevel level;

}
