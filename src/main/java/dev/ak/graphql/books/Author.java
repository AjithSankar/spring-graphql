package dev.ak.graphql.books;

public record Author(Integer id, String firstName, String lastName) {

    public String fullName() {
       return firstName + " " + lastName;
    }
}
