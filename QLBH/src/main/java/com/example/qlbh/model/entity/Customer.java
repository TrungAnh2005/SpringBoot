package com.example.qlbh.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String address;
    private String phone;
    @JsonBackReference
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders;
    @OneToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;
    @JsonBackReference
    @OneToMany(mappedBy = "customer")
    private Set<Feedback> feedbacks;
}
