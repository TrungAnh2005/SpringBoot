package com.example.qlbh.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@Data
public class OrderDTO {
    @NotNull
    private Set<ProductDTO> products;
    @NotNull
    private CustomerDTO customer;
    @NotNull
    private ShopDTO shop;
    private String status;
    private double total;
}
