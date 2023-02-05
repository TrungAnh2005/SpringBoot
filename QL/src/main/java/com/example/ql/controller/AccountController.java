package com.example.ql.controller;

import com.example.ql.model.AccountModel;
import com.example.ql.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @PostMapping(value = "account/all")
    public ResponseEntity<List<AccountModel>> postAllAccount(){
        List<AccountModel> accountModels = accountRepository.findAll();
        return ResponseEntity.ok(accountModels);
    }
    @PostMapping(value = "account/{id}")
    public Optional<AccountModel> postAllAccountId(@PathVariable int id){
        return accountRepository.findAllById(id);
    }


}
