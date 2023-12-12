package com.wilkins.showcase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<BookEntity, String> {
    Optional<BookEntity> findByExternalId(String externalId);

    void deleteByExternalId(String externalId);
}
