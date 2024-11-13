package com.wilkins.showcase.service;

import com.wilkins.showcase.Book;

import java.util.stream.Stream;

public interface BookRepository {
    Stream<Book> findAll();
}
