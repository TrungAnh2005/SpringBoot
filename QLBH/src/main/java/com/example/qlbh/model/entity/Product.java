package com.example.qlbh.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int quantity;
    private double price;
    private String description;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
