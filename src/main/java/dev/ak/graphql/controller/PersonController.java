package dev.ak.graphql.controller;

import dev.ak.graphql.entity.Address;
import dev.ak.graphql.entity.Person;
import dev.ak.graphql.repository.PersonRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Optional;

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

    @MutationMapping(value = "createPerson")
    public Person create(@Argument Person input) {
        Person person = new Person();
        person.setFirstName(input.getFirstName());
        person.setLastName(input.getLastName());
        person.setEmail(input.getEmail());
        person.setPhoneNumber(input.getPhoneNumber());
        personRepository.save(person);

        return person;
    }

    @MutationMapping(value = "updatePerson")
    public Person updatePerson(@Argument Person input) {
        Person person = null;
        Optional<Person> optionalPerson = personRepository.findById(input.getId());
        if (optionalPerson.isPresent()) {
            person = optionalPerson.get();
            person.setFirstName(input.getFirstName());
            person.setLastName(input.getLastName());
            person.setEmail(input.getEmail());
            person.setPhoneNumber(input.getPhoneNumber());
            Address address = input.getAddress();
            person.setAddress(new Address(address.getAddress(), address.getCity(), address.getState(), address.getZip()));
        }
        if (person != null) {
            personRepository.save(person);
        }
        return person;
    }

    @MutationMapping(value = "updatePersonById")
    public Person update(@Argument int id, @Argument String phoneNumber) {
        Person person = null;
        Optional<Person> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isPresent()) {
            person = optionalPerson.get();
            person.setPhoneNumber(phoneNumber);
        }
        if (person != null) {
            personRepository.save(person);
        }
        return person;
    }

    @MutationMapping(value = "deletePerson")
    public Integer update(@Argument int id) {
        personRepository.deleteById(id);
        return id;
    }
}
