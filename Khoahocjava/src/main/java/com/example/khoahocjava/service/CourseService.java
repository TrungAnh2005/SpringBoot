package com.example.khoahocjava.service;

import com.example.khoahocjava.model.Course;

import java.util.List;

public interface CourseService {
    Course insertCourse(Course course);

    List<Course> getAllCourse();

    Course updateCourse(int id, Course cs);

    Course deleteCourse(int id );
}
