package com.wilkins.showcase.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilkins.showcase.controller.JsonBook;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;

@RequiredArgsConstructor
@Slf4j
@Service
public class DefaultBookService implements BookService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final BookRepository bookRepository;

    @Override
    public void streamBooks(OutputStream outputStream) {
        try (var books = bookRepository.findAll()) {
            books
                    .map(JsonBook::from)
                    .forEach(book -> {
                        try {
                            log.info("getting book {}", book);
                            Thread.sleep(2000);
                            log.info("writing book {}", book.name());
                            outputStream.write(objectMapper.writeValueAsBytes(book));
                            outputStream.flush();
                        } catch (IOException | InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }
}
