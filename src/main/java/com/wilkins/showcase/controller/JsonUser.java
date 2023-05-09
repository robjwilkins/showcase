package com.wilkins.showcase.controller;

import com.wilkins.showcase.domain.User;

import java.time.LocalDateTime;

public record JsonUser(String externalId, String name, LocalDateTime createdAt) {
    public static JsonUser from(User user) {
        return new JsonUser(user.getExternalId(), user.getName(), user.getCreatedAt());
    }
}
