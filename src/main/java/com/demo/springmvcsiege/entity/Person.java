package com.demo.springmvcsiege.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Table(name = "persons")
@Entity
public class Person {
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    
    private String name;

    public Person(String name) {
        this.name = name;
    }
}
