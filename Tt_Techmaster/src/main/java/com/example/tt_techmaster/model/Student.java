package com.example.tt_techmaster.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    private String level;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "classz_id")
    private ClassZ classZ;

}
