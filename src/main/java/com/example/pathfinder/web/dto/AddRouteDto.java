package com.example.pathfinder.web.dto;

import com.example.pathfinder.model.enums.UserLevel;
import lombok.Data;

@Data
public class AddRouteDto {

    private String name;
    private String description;
    private UserLevel level;
    private String videoUrl;

}
