package com.wilkins.showcase.service;

import java.util.List;

public record Example (
    String key,
    String value,
    String id,
    List<String> parts) {
}
