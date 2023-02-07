package com.example.tt_techmaster.service;

import com.example.tt_techmaster.Repository.ClassZRepository;
import com.example.tt_techmaster.model.ClassZ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassZServiceIplm implements ClassZService{
    @Autowired
    private ClassZRepository classZRepository;
    @Override
    public List<ClassZ> getAllClass() {
        return classZRepository.findAll();
    }
}
