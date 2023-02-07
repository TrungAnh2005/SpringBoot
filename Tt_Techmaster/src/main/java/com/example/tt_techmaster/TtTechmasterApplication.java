package com.example.tt_techmaster;

import com.example.tt_techmaster.Repository.ClassZRepository;
import com.example.tt_techmaster.Repository.StudentRepository;
import com.example.tt_techmaster.model.ClassZ;
import com.example.tt_techmaster.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
public class TtTechmasterApplication implements CommandLineRunner {
    @Autowired
    private ClassZRepository classZRepository;
    @Autowired
    private StudentRepository studentRepository;

    public static void main(String[] args) {
        SpringApplication.run(TtTechmasterApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        ClassZ classZ = new ClassZ();
//        classZ.setAddress("Nguyen Dinh Chieu");
//        ClassZ classZ2 = new ClassZ();
//        classZ2.setAddress("To Huu");
//        classZRepository.saveAll(Arrays.asList(classZ, classZ2));
//        Student student = new Student();
//        student.setName("Nam");
//        student.setAge(18);
//        student.setLevel("inter");
//        student.setClassZ(classZ);
//        Student student2 = new Student();
//        student2.setName("Tuan");
//        student2.setAge(19);
//        student2.setLevel("inter");
//        student2.setClassZ(classZ2);
//        studentRepository.saveAll(Arrays.asList(student,student2));


    }
}
