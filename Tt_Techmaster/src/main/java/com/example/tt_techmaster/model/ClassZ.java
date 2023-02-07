package com.example.tt_techmaster.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table
public class ClassZ {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String address;
    @JsonManagedReference
    @OneToMany(mappedBy = "classZ")
    private Set<Student>students;
}
