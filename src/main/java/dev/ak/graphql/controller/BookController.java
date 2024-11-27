package dev.ak.graphql.controller;

import dev.ak.graphql.books.Book;
import dev.ak.graphql.repository.BookRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @SchemaMapping(typeName = "Query", value = "allBooks")
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @QueryMapping(value = "findOne")
    public Book findById(@Argument Integer id) {
        return bookRepository.findOne(id);
    }
}
