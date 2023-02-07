package com.example.tt_techmaster.service;

import com.example.tt_techmaster.model.Student;

public interface StudentService {
    Student insertStudent(Student student);
    Student updateStudent(int id, Student st);
    Student deleteStudent(int id);
}
