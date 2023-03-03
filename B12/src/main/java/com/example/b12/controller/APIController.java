package com.example.b12.controller;

import com.example.b12.model.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping(value = "/api")
public class APIController {

    @GetMapping(value = "/products")
    public List<Product> getProducts() {
        List<Product> result = new ArrayList<>();
        result.add(new Product("Coffee Machine", 150));
        result.add(new Product("Apple Watch", 250));
        result.add(new Product("Eink Book Reader", 350));
        return result;
    }
}
