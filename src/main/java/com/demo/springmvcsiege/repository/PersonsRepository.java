package com.demo.springmvcsiege.repository;

import com.demo.springmvcsiege.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonsRepository extends JpaRepository<Person, Long> {
    
    Optional<Person> findPersonByName(String name);
}
