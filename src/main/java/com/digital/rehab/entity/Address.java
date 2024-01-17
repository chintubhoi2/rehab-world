package com.digital.rehab.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String landmark;

    private String country;

    private String state;

    private String street;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // getters, setters, and constructors

}

