package com.example.ql.controller;

import com.example.ql.model.AccountModel;
import com.example.ql.model.WalletModel;
import com.example.ql.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WalletController {
    @Autowired
    private WalletRepository walletRepository;

    @GetMapping(value = "walet/all")

    public ResponseEntity<List<WalletModel>> getAllWallet() {
        List<WalletModel> walletModels = walletRepository.findAll();
        return ResponseEntity.ok(walletModels);

    }

    @GetMapping(value = "walet/balance")

    public ResponseEntity<List<WalletModel>> getAllWalletBalance() {
        List<WalletModel> walletModels = walletRepository.findAllByBalance(180000);
        return ResponseEntity.ok(walletModels);

    }

    @PostMapping(value = "balance/update")
    public ResponseEntity<String> updateWallet(@RequestBody WalletModel walletModel) {
        WalletModel walletModel1 = walletRepository.findById(1).get();
        walletModel1.setBalance(walletModel.getBalance());
        walletRepository.save(walletModel1);
        return ResponseEntity.ok("Update thanh cong");

    }
}
