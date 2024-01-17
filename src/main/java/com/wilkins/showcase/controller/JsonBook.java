package com.wilkins.showcase.controller;

import com.wilkins.showcase.domain.Author;
import com.wilkins.showcase.domain.Book;

import java.util.List;
import java.util.UUID;

public record JsonBook(String id, String name, List<String> authors) {

    public static JsonBook fromBook(Book book) {
        return new JsonBook(
                book.id(),
                book.name(),
                book.authors().stream().map(Author::name).toList()
        );
    }

    public Book toBook() {
        return new Book(
                UUID.randomUUID().toString(),
                name,
                authors.stream().map(Author::new).toList()
        );
    }
}
