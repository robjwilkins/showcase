package com.wilkins.showcase.domain;

import java.util.List;

public record Book(String id, String name, List<Author> authors) {
}
