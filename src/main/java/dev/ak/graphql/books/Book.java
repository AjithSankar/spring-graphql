package dev.ak.graphql.books;

public record Book(Integer id, String title, Integer pages, Rating rating, Author author) {
}
