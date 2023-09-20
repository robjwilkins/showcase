package com.wilkins.showcase.controller;

import com.wilkins.showcase.service.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GreetingControllerTest {

    MockMvc mockMvc;

    GreetingService greetingService = mock(GreetingService.class);

    @BeforeEach
    void beforeEach() {
        var controller = new GreetingController(greetingService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void returnsGreeting() throws Exception {
        mockMvc.perform(get("/greetings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salutation", is("hello")))
                .andExpect(jsonPath("$.name", is("world")));
    }

    @Test
    void emailsGreeting() throws Exception {
        mockMvc.perform(post("/greetings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salutation", is("hello")))
                .andExpect(jsonPath("$.name", is("world")));
        verify(greetingService).sendGreeting("hello", "world");
    }

}
