package com.example.khoahocjava.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phone;
    private String email;
    @Column(columnDefinition = "varchar(50) default 'Studying'")
    private String status;
    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;
}
