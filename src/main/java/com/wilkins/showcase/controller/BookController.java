package com.wilkins.showcase.controller;

import com.wilkins.showcase.domain.Book;
import com.wilkins.showcase.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.wilkins.showcase.controller.JsonBook.fromBook;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    JsonBook createBook(@RequestBody JsonBook body) {
        return fromBook(bookService.save(body.toBook()));
    }

    @DeleteMapping(path = "/{bookId}")
    ResponseEntity<Void> deleteBook(@PathVariable String bookId) {
        bookService.deleteBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    List<Book> getBooks() {
        return bookService.books();
    }
    @GetMapping(path = "/{bookId}")
    ResponseEntity<JsonBook> getBook(@PathVariable String bookId) {
        return ResponseEntity.of(bookService.findById(bookId)
                .map(JsonBook::fromBook));
    }

}
