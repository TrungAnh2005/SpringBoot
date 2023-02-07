package com.example.tt_techmaster.controller;

import com.example.tt_techmaster.model.Student;
import com.example.tt_techmaster.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("insert/student")
    public ResponseEntity<Student> insertStudentes(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.insertStudent(student));
    }
    @PostMapping("update/{id}")
    public ResponseEntity<Student> updateStudentes(@PathVariable int id , @RequestBody Student student){
        return ResponseEntity.ok(studentService.updateStudent(id,student));
    }
    @GetMapping ("delete/{id}")
    public ResponseEntity<Student> deleteStudentes(@PathVariable int id){
        return ResponseEntity.ok(studentService.deleteStudent(id));
    }
}
