package com.practice.payment.models;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class EndUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ElementCollection
    private Set<String> roles;
    // Getters and setters
}

