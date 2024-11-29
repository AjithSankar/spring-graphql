package dev.ak.graphql.controller;

import dev.ak.graphql.entity.Person;
import dev.ak.graphql.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
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

    @QueryMapping(value = "allPeoplePaged")
    public Page<Person> allPeoplePaged(@Argument int page, @Argument int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return personRepository.findAll(pageRequest);
    }
}
