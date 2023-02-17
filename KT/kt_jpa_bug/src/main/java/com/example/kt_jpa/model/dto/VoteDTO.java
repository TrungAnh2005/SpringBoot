package com.example.kt_jpa.model.dto;

import com.example.kt_jpa.model.entities.Shiper;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NonNull;

@Data
public class VoteDTO {
    @NotNull
    private int order_id;
    private double rate;
    private String message;
    private int shipper_id;
    private int customer_id;
}
