package com.example.qlbh.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @JsonManagedReference
    @OneToMany(mappedBy = "order")
    private Set<Product> products;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
    @Column(columnDefinition = "varchar(30) default 'ok'")
    private String status;
    @Column(name = "time_order")
    private Date timeOrder;
    private double total;
}
