package com.example.qlbh.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ShopDTO {
    private int id;
    private String name;
    @NotNull
    private String phone;
}
