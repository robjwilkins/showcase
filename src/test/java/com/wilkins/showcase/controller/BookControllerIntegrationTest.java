package com.wilkins.showcase.controller;

import com.wilkins.showcase.domain.Author;
import com.wilkins.showcase.domain.Book;
import com.wilkins.showcase.service.BookService;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookControllerIntegrationTest {

    @LocalServerPort
    int port;
    @Autowired
    BookService bookService;

    @Test
    void canCreateBookWithAuthors() {
        var newBook = given()
                .body("""
                        {
                          "name": "War and Peace",
                          "authors": ["Leo Tolstoy"]
                        }
                        """)
                .contentType(ContentType.JSON)
                .when()
                .post("http://localhost:%S/books".formatted(port))
                .then()
                .statusCode(isOk())
                .extract().response().body().as(JsonBook.class);

        assertThat(newBook.id()).isNotNull();
        var savedBook = bookService.findById(newBook.id()).orElseThrow();
        assertThat(savedBook)
                .extracting(Book::name)
                .isEqualTo("War and Peace");
        assertThat(savedBook.authors().stream().map(Author::name).toList())
                .containsExactly("Leo Tolstoy");
    }

    private int isOk() {
        return 200;
    }
}
