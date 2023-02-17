package com.example.qlbh.controller;

import com.example.qlbh.model.dto.OrderDTO;
import com.example.qlbh.model.entity.Order;
import com.example.qlbh.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "create/order")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO){
        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<Order>> get() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @PostMapping(value = "update/{id}")
    public ResponseEntity<String> updateStatus(@RequestBody OrderDTO orderDTO, @PathVariable int id){
        return ResponseEntity.ok(orderService.updateStatus(id, orderDTO));
    }
}
