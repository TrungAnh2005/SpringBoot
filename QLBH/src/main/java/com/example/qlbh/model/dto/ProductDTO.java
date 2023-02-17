package com.example.qlbh.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {
    private String name;
    private int quantity;
    @NotNull
    private double price;
    private String description;
    private ShopDTO shop;
}
