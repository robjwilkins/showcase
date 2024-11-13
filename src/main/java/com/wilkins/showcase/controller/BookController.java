package com.wilkins.showcase.controller;

import com.wilkins.showcase.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.http11.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/books")
@Slf4j
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    ResponseEntity<StreamingResponseBody> streamBooks() {
        log.info("streaming books");
        return ok()
                .header(HttpHeaders.CONTENT_ENCODING, Constants.CHUNKED)
                .body(bookService::streamBooks);
    }
}
