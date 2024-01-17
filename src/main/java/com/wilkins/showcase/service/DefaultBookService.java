package com.wilkins.showcase.service;

import com.wilkins.showcase.domain.Book;
import com.wilkins.showcase.repository.BookEntity;
import com.wilkins.showcase.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;

    @Override
    public Book save(Book book) {
        return bookRepository.save(BookEntity.from(book)).asBook();
    }

    @Override
    @Transactional
    public Optional<Book> findById(String id) {
        return bookRepository.findByExternalId(id).map(BookEntity::asBook);
    }

    @Override
    public List<Book> books() {
        return bookRepository.findAll(Pageable.ofSize(100)).stream().map(BookEntity::asBook).toList();
    }

    @Override
    @Transactional
    public void deleteBook(String id) {
        bookRepository.deleteByExternalId(id);
    }
}
