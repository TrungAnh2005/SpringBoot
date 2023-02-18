package com.example.kt_jpa.service;

import com.example.kt_jpa.model.dto.OrderDTO;
import com.example.kt_jpa.model.entities.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderDTO dto);

    String updateOrder(OrderDTO dto, int id);

    List<OrderDTO> getAllOrder();

}
