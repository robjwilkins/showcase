package com.wilkins.showcase.repository;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "AUTHOR")
public class AuthorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
}
