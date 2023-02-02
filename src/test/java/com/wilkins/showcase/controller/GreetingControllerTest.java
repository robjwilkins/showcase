package com.wilkins.showcase.controller;

import com.wilkins.showcase.DefaultProperties;
import com.wilkins.showcase.KebabProperties;
import com.wilkins.showcase.domain.Greeting;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class GreetingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    KebabProperties kebabProperties;

    @Autowired
    DefaultProperties defaultProperties;

    @Test
    void returnsGreeting() throws Exception {
        mockMvc.perform(get("/greetings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salutation", is("good morning")))
                .andExpect(jsonPath("$.name", is("Vietnam!")));
    }

    @Test
    void propertiesAreLoaded() {
        assertThat(kebabProperties.config()).containsEntry("keyOne", "a");
        assertThat(defaultProperties.greeting()).isEqualTo(Greeting.of("good morning", "Vietnam!"));
    }

    @DynamicPropertySource
    static void dynamicPropertySource(DynamicPropertyRegistry registry) {
        registry.add("app.default.greeting.salutation", () -> "good morning");
        registry.add("app.default.greeting.name", () -> "Vietnam!");
    }
}
