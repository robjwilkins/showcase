package com.wilkins.showcase.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    void canStoreBook() {
        var warAndPeace = new BookEntity();
        warAndPeace.setName("War and Peace");
        var leoTolstoy = new AuthorEntity();
        leoTolstoy.setName("Leo Tolstoy");
        var bookAuthor = new BookAuthor();
        bookAuthor.setAuthorEntity(leoTolstoy);
        warAndPeace.setAuthors(List.of(bookAuthor));
        var saved = bookRepository.save(warAndPeace);
        assertThat(saved).isNotNull();

        var retrieved = bookRepository.findById(saved.getId());
        assertThat(retrieved).contains(warAndPeace);
    }
}
