package com.example.ontap2.controller;

import com.example.ontap2.model.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
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
        products.add(new Product("coffee machine", 150));
        products.add(new Product("coffee machine", 150));
        return products;
    }

    @GetMapping(value = "/you")
    public String get() {

        return "you";
    }

    @GetMapping(value = "/coffee")
    public String coffee() {
        return "coffee";
    }

    @GetMapping(value = "/un-authorizes")
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public String unAuthorizes() {
        return "403";
    }
}
