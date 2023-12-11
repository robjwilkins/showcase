package com.wilkins.showcase.domain;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Comparator;

import static java.util.UUID.randomUUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String externalId;
    private String name;
    @CreationTimestamp
    private LocalDateTime createdAt;

    public User() {
    }

    public User(Long id, String externalId, String name, LocalDateTime createdAt) {
        this.id = id;
        this.externalId = externalId;
        this.name = name;
        this.createdAt = createdAt;
    }

    public static User with(String name) {
        return new User(null, randomUUID().toString(), name, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public static Comparator<User> userComparator() {
        return (a, b) -> {
            if (!a.getId().equals(b.getId())) {
                return a.getId().compareTo(b.getId());
            }
            if (!a.getExternalId().equals(b.getExternalId())) {
                return a.getExternalId().compareTo(b.getExternalId());
            }
            if (!a.getName().equals(b.getName())) {
                return a.getName().compareTo(b.getName());
            }
            if (!a.getCreatedAt().isEqual(b.getCreatedAt())) {
                return a.getCreatedAt().compareTo(b.getCreatedAt());
            }
            return 0;
        };
    }
}
