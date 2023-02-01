package com.wilkins.showcase;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

import static java.lang.Character.toUpperCase;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;
import static java.util.stream.IntStream.range;

@ConfigurationProperties(prefix = "kebab")
public record KebabProperties(Map<String, Object> config) {
    public KebabProperties(Map<String, Object> config) {
        this.config = config.entrySet().stream()
                .collect(toMap(e -> fromKebabToCamel(e.getKey()), Map.Entry::getValue));
    }

    private static String fromKebabToCamel(String key) {
        var words = key.split("-");
        return range(0, words.length)
                .mapToObj(
                        i -> {
                            String word = words[i];
                            if (i == 0) {
                                return word.isEmpty() ? word : word.toLowerCase();
                            } else {
                                return word.isEmpty()
                                        ? word
                                        : toUpperCase(word.charAt(0)) + word.substring(1).toLowerCase();
                            }
                        })
                .collect(joining());
    }
}
