package com.wilkins.showcase.repository;

import com.wilkins.showcase.domain.Author;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "BOOK_AUTHOR")
public class BookAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @JoinColumn(name = "book_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private BookEntity bookEntity;
    @JoinColumn(name = "author_id")
    @OneToOne(cascade = CascadeType.ALL)
    private AuthorEntity authorEntity;

    public static BookAuthor from(Author author, BookEntity bookEntity) {
        var authorEntity = new AuthorEntity();
        authorEntity.setName(author.name());
        var bookAuthor = new BookAuthor();
        bookAuthor.setAuthorEntity(authorEntity);
        bookAuthor.setBookEntity(bookEntity);
        return bookAuthor;
    }

    public Author asAuthor() {
        return new Author(authorEntity.getName());
    }
}
