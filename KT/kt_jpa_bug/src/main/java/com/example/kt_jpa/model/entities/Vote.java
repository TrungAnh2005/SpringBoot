package com.example.kt_jpa.model.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class Vote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "shipper_id")
    private Shiper shiper;
//    @JsonManagedReference
//    @ManyToOne
//    @JoinColumn(name = "order_id")
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;
    private double rate;
    private String message;
}
