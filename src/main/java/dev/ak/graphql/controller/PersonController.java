package dev.ak.graphql.controller;

import dev.ak.graphql.entity.Person;
import dev.ak.graphql.repository.PersonRepository;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @QueryMapping(value = "allPerson")
    public List<Person> findAll() {
        return personRepository.findAll();
    }
}
