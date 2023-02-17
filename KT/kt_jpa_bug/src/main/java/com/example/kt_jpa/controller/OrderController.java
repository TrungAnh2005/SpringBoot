package com.example.kt_jpa.controller;

import com.example.kt_jpa.model.dto.OrderDTO;
import com.example.kt_jpa.model.entities.Order;
import com.example.kt_jpa.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/order/create")
    public ResponseEntity<Order> createOrder(@RequestBody OrderDTO orderDTO) {

        return ResponseEntity.ok(orderService.createOrder(orderDTO));
    }

    @PostMapping(value = "update/{id}")
    public ResponseEntity<String> update(@RequestBody OrderDTO orderDTO, @PathVariable int id) {
        return ResponseEntity.ok(orderService.updateOrder(orderDTO,id));
    }
    @GetMapping("getall")
    public ResponseEntity<List<Order>> getALL(){
        return ResponseEntity.ok(orderService.getAllorder());
    }
}
