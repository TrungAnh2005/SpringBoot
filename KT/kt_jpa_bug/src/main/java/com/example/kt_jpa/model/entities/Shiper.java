package com.example.kt_jpa.model.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Table
@Entity
@Getter
@Setter
public class Shiper {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
    @JsonBackReference
    @OneToMany(mappedBy = "shiper")
    private Set<Order> orders;
    @JsonBackReference
    @OneToMany(mappedBy = "shiper")
    private Set<Vote> votes;
}
