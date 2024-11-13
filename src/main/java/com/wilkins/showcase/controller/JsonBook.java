package com.wilkins.showcase.controller;

import com.wilkins.showcase.Book;

public record JsonBook(String id, String name, String author) {
    public static JsonBook from(Book book) {
        return new JsonBook(book.id(), book.name(), book.author());
    }
}
