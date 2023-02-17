package com.example.kt_jpa.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Table(name = "orders")
@Entity
@Getter
@Setter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "shiper_id")
    private Shiper shiper;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    private String address;
    private Date timeOrder;
    private Date estimateTime;
    private String status;
    private double price;
//    @JsonManagedReference
//    @OneToMany(mappedBy = "order")
//    private Set<Vote> votes;
    @JsonManagedReference
    @OneToMany(mappedBy = "order")
    private Set<Product> products;
}
