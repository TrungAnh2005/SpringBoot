package com.example.qlbh.model.dto;

import com.example.qlbh.model.entity.Wallet;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CustomerDTO {
    private String name;
    @NotNull
    private String address;
    @NotNull
    private String phone;
    private Wallet wallet;
}
