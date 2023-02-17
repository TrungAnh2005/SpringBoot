package com.example.kt_jpa.repository;

import com.example.kt_jpa.model.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
}
