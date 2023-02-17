package com.example.kt_jpa.repository;

import com.example.kt_jpa.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
