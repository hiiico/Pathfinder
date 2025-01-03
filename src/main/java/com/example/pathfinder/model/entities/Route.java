package com.example.pathfinder.model.entities;

import com.example.pathfinder.model.enums.UserLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "routes")
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "gpx_coordinates", columnDefinition = "LONGTEXT")
    private String gpxCoordinates;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private UserLevel level;

    @Column(name = "video_url")
    private String videoUrl;

    @ManyToOne(optional = false)
    private User author;

    @OneToMany(targetEntity = Comment.class, mappedBy = "route")
    private Set<Comment> comments;

    @OneToMany(targetEntity = Picture.class, mappedBy = "route")
    private Set<Picture> pictures;

    @ManyToMany
    private Set<Category> categories;

    public Route() {
        this.comments = new HashSet<>();
        this.pictures = new HashSet<>();
        this.categories = new HashSet<>();
    }

}
