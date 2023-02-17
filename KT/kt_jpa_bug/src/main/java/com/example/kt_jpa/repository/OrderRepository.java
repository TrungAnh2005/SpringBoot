package com.example.kt_jpa.repository;

import com.example.kt_jpa.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
