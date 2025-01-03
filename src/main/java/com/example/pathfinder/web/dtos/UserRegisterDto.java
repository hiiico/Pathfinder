package com.example.pathfinder.web.dtos;

import com.example.pathfinder.model.enums.UserLevel;
import jakarta.validation.constraints.*;

public class UserRegisterDto {

    @NotBlank
    @Size(min = 5, max = 20)
    private String username;

    @NotNull
    @NotEmpty
    @Size(min = 2)
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

    public UserRegisterDto() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public UserLevel getLevel() {
        return level;
    }

    public void setLevel(UserLevel level) {
        this.level = level;
    }
}
