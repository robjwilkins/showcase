package com.wilkins.showcase.service;

import lombok.Value;

import java.util.List;

@Value
public class Identifier {
    String key;
    String value;
    String id;
    List<String> parts;
}
