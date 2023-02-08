package com.example.khoahocjava.controller;

import com.example.khoahocjava.model.Course;
import com.example.khoahocjava.repository.CourseRepository;
import com.example.khoahocjava.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("insert/course")
    public ResponseEntity<Course> insert(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.insertCourse(course));
    }
    @GetMapping(value = "all/course")
    public ResponseEntity<List<Course>> getAll1() {
        return ResponseEntity.ok(courseService.getAllCourse());
    }
    @PostMapping("update/{id}")
    public ResponseEntity<Course> update(@PathVariable int id , @RequestBody Course cs){
        return ResponseEntity.ok(courseService.updateCourse(id, cs));
    }
    @GetMapping("delete/{id}")
    public ResponseEntity<Course> delete(@PathVariable int id ){
        return ResponseEntity.ok(courseService.deleteCourse(id));
    }
}
