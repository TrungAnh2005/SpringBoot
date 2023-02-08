package com.example.khoahocjava;

import com.example.khoahocjava.model.Course;
import com.example.khoahocjava.model.Student;
import com.example.khoahocjava.repository.CourseRepository;
import com.example.khoahocjava.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Set;

@SpringBootApplication
public class KhoahocjavaApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KhoahocjavaApplication.class, args);
    }
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void run(String... args) throws Exception {
//        Student student = new Student();
//        student.setName("Nam");
//        student.setPhone("0377654130");
//        student.setEmail("nam@gmail.com");
//        Student student1 = new Student();
//        student1.setName("Duc");
//        student1.setPhone("0973605888");
//        student1.setEmail("duc@gmail.com");
//        studentRepository.saveAll(Arrays.asList(student,student1));
//        Course course = new Course();
//        course.setName("Java");
//        course.setNumberOfLectures(13);
//        course.setStudents(Set.of(student));
//        Course course1 = new Course();
//        course1.setName("HTML CSS");
//        course1.setNumberOfLectures(10);
//        course1.setStudents(Set.of(student1));
//        courseRepository.saveAll(Arrays.asList(course,course1));

    }
}
