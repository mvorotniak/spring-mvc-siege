package com.demo.springmvcsiege.controller;

import com.demo.springmvcsiege.entity.Person;
import com.demo.springmvcsiege.repository.PersonsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Controller
public class PersonsDataController {

    public static final String MESSAGE = "message";
    private final Random random = new Random();

    private final PersonsRepository repository;

    @GetMapping
    public String home() {
        log.info("At Home page...");
        return "home";
    }

    @GetMapping("/all-persons")
    public String getAllPersons(Model model) {
        List<Person> persons = this.repository.findAll();
        model.addAttribute(MESSAGE, persons);

        log.info("Retrieving [{}] persons from database", persons.size());

        return "home";
    }

    // Non thread safe version without error handling for testing purposes
    @PostMapping("/save-random-person")
    public String saveRandomPerson(Model model) {
        Person person = new Person("person-" + random.nextInt(1, 5));

        if (this.repository.findPersonByName(person.getName()).isPresent()) {
            model.addAttribute(MESSAGE, "Randomly created Person exists in the database!");
            log.info("Randomly created [{}] exists in the database", person.getName());

            return "home";
        }

        this.repository.save(person);
        model.addAttribute(MESSAGE, "Random Person was saved in the database!");
        log.info("Successfully saved [{}] in the database", person.getName());

        return "home";
    }

    // Error handling
//    @PostMapping("/save-random-person")
//    public String saveRandomPerson(Model model) {
//        AtomicInteger retries = new AtomicInteger(0);
//        int maxRetries = 3;
//
//        while (retries.get() < maxRetries) {
//            retries.incrementAndGet();
//            try {
//                Person person = new Person("person-" + random.nextInt(1, 100));
//
//                if (this.repository.findPersonByName(person.getName()).isPresent()) {
//                    model.addAttribute(MESSAGE, "Randomly created Person exists in the database!");
//                    log.info("Randomly created [{}] exists in the database", person.getName());
//
//                    return "home";
//                }
//
//                this.repository.save(person);
//                model.addAttribute(MESSAGE, "Random Person was saved in the database!");
//                log.info("Successfully saved [{}] in the database", person.getName());
//            } catch (DataIntegrityViolationException e) {
//                log.info("Issue occurred while saving person into the database. Retry [{}]. Trying again...",
//                        retries.get());
//
//                if (retries.get() == maxRetries) {
//                    log.info("Failed to save Random Person after [{}] retries", retries);
//                }
//            }
//        }
//
//        return "home";
//    }

}
