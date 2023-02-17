package com.example.qlbh.service;

import com.example.qlbh.model.dto.OrderDTO;
import com.example.qlbh.model.entity.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(OrderDTO orderDTO);

    List<Order> getAll();

    String updateStatus(int id, OrderDTO orderDTO);
}
