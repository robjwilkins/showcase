package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book save(Book book);
    Optional<Book> findById(String id);

    List<Book> books();

    void deleteBook(String id);
}
