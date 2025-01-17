package com.example.pathfinder.web.dto;

import com.example.pathfinder.model.enums.UserLevel;
import jakarta.validation.constraints.*;
import lombok.Data;


@Data
public class UserRegister {

    @NotBlank
    @Size(min = 5, max = 20, message = "Username must be at least 5 symbols")
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 2, message = "Full name must be at least 2 symbol")
    private String fullName;

    @Email
    private String email;

    @Min(0)
    @Max(90)
    private Integer age;

    @Size(min = 5, max = 20)
    private String password;

    @Size(min = 5, max = 20)
    private String confirmPassword;

    private UserLevel level;

}
