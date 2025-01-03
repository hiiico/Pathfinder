package com.example.pathfinder.model.entities;

import com.example.pathfinder.model.enums.UserLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    private Integer age;

    @Column(unique = true, nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    private UserLevel level;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
        this.roles = new HashSet<>();
    }

}
