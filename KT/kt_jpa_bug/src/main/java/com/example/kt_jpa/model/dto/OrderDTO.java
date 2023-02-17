package com.example.kt_jpa.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class OrderDTO {
    @NotNull
    private int shipperId;
    private CustomerDTO customer;
    @NotNull
    private String address;

    //@DateTimeFormat(fallbackPatterns = "yyyy-MM-dd hh:mm")
    private Date estimateTime;
    private Set<ProductDTO> products;
    /*private String status;
    private double price;*/
}
