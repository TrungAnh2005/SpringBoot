package com.example.ql.repository;

import com.example.ql.model.WalletModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<WalletModel, Integer> {
    List<WalletModel>findAllByBalance(double balance);
}
