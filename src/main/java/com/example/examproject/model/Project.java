package com.example.examproject.model;



import jakarta.persistence.*;
import lombok.Data;


import java.util.HashSet;
import java.util.Set;

@Entity
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double budget;

    @ManyToMany(mappedBy = "projects")
    private Set<Employee> employees = new HashSet<>();

}

