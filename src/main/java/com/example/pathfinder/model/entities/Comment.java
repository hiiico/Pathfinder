package com.example.pathfinder.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@Entity
@NoArgsConstructor
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private boolean approved;

    @Column(nullable = false)
    private Instant created;

    @Column(name = "text_content", columnDefinition = "TEXT", nullable = false)
    private String textContent;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private User author;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Route route;

}
