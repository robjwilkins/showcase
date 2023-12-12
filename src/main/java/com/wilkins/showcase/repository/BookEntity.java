package com.wilkins.showcase.repository;

import com.wilkins.showcase.domain.Book;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "BOOK")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String externalId;
    private String name;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "bookEntity", cascade = CascadeType.ALL)
    private List<BookAuthor> authors;

    public static BookEntity from(Book book) {
        var bookEntity = new BookEntity();
        bookEntity.setName(book.name());
        bookEntity.setExternalId(book.id());
        bookEntity.setAuthors(book.authors().stream().map(a -> BookAuthor.from(a, bookEntity)).toList());
        return bookEntity;
    }

    public Book asBook() {
        return new Book(externalId, name, authors.stream().map(BookAuthor::asAuthor).toList());
    }
}
