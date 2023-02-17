package com.example.kt_jpa.model.dto;

import com.example.kt_jpa.model.entities.Order;
import com.example.kt_jpa.model.entities.Vote;
import com.example.kt_jpa.model.entities.Wallet;

import lombok.Data;


@Data
public class CustomerDTO {
    private String name;
    private String phone;
    private Wallet wallet;

    /*private Set<Order> orders;
    private Set<Vote> votes;*/
}
