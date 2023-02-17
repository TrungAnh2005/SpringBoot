package com.example.qlbh.model.dto;

import com.example.qlbh.model.entity.Customer;
import com.example.qlbh.model.entity.Order;
import lombok.Data;

@Data
public class FeedbackDTO {
    private int customer_id;
    private int order_id;
    private double rate;
    private String message;
}
