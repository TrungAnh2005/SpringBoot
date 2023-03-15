package com.example.ontap3.controller;

import com.example.ontap3.model.Product;
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
        List<Product> products = new ArrayList<>();
        products.add(new Product("coffee machine", 150));
        products.add(new Product("coffee machine", 250));
        products.add(new Product("coffee machine", 350));
        return products;
    }

    @GetMapping(value = "/backoffice")
    public String getSensitiveData() {
        return "Back Office Data";
    }

    @GetMapping(value = "/secret")
    public String getSecretData() {
        return "This is secret";
    }

}
