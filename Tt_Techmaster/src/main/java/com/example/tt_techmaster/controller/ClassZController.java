package com.example.tt_techmaster.controller;

import com.example.tt_techmaster.model.ClassZ;
import com.example.tt_techmaster.service.ClassZService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ClassZController {
    @Autowired
    private ClassZService classZService;
    @GetMapping("classz/all")
    public ResponseEntity<List<ClassZ>> getClasses(){
        return ResponseEntity.ok(classZService.getAllClass());
    }
}
