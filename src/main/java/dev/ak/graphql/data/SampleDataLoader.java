package dev.ak.graphql.data;

import com.github.javafaker.Faker;
import dev.ak.graphql.entity.Address;
import dev.ak.graphql.entity.Person;
import dev.ak.graphql.repository.PersonRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.IntStream;

@Component
public class SampleDataLoader implements CommandLineRunner {

    private final PersonRepository repository;
    private final Faker faker;

    public SampleDataLoader(PersonRepository repository, Faker faker) {
        this.repository = repository;
        this.faker = faker;
    }

    @Override
    public void run(String... args) throws Exception {
        long count = repository.count();
        if (count <= 0) {
            initializeData(1000000);
        }

    }

    private void initializeData(int count) {
        // create 100 rows of people in the database
        List<Person> people = IntStream.rangeClosed(1, count)
                .mapToObj(i -> new Person(
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.phoneNumber().cellPhone(),
                        faker.internet().emailAddress(),
                        new Address(
                                faker.address().streetAddress(),
                                faker.address().city(),
                                faker.address().state(),
                                faker.address().zipCode()
                        )
                )).toList();

        repository.saveAll(people);
    }
}