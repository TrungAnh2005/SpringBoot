package com.example.tt_techmaster.service;

import com.example.tt_techmaster.Repository.StudentRepository;
import com.example.tt_techmaster.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceIplm implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public Student insertStudent(Student student) {
        Student st = new Student();
        st.setName(student.getName());
        st.setAge(student.getAge());
        st.setLevel(student.getLevel());
        st.setClassZ(student.getClassZ());
        studentRepository.save(st);
        return st;
    }

    @Override
    public Student updateStudent(int id, Student st) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student != null) {
            student.setName(st.getName());
            student.setAge(st.getAge());
            student.setLevel(st.getLevel());
            studentRepository.save(student);
            return student;
        } else return null;
    }

    @Override
    public Student deleteStudent(int id) {
        Student student = studentRepository.findById(id).get();
        studentRepository.delete(student);
        return student;
    }
}
