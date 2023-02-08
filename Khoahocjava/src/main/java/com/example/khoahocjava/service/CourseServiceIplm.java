package com.example.khoahocjava.service;

import com.example.khoahocjava.model.Course;
import com.example.khoahocjava.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceIplm implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course insertCourse(Course course) {
        Course cs = new Course();
        cs.setName(course.getName());
        cs.setNumberOfLectures(course.getNumberOfLectures());
        cs.setStudents(course.getStudents());
        courseRepository.save(cs);
        return cs;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepository.findAll();
    }

    @Override
    public Course updateCourse(int id, Course cs) {
        Course course = courseRepository.findById(id).orElse(null);
        course.setName(cs.getName());
        course.setNumberOfLectures(cs.getNumberOfLectures());
        course.setStudents(cs.getStudents());
        courseRepository.save(course);
        return course;
    }

    @Override
    public Course deleteCourse(int id) {
        Course course = courseRepository.findById(id).get();
        courseRepository.delete(course);
        return course;
    }

}
