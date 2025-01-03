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
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, name = "date_time")
    private Instant dateTime;

    @Column(name = "text-content", columnDefinition = "TEXT")
    private String textContent;

    @ManyToOne(optional = false)
    private User author;

    @ManyToOne(optional = false)
    private User recipient;

}
