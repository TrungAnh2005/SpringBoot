package com.example.kt_jpa.repository;

import com.example.kt_jpa.model.entities.Shiper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipperRepository extends JpaRepository<Shiper, Integer> {
}
