package com.wilkins.showcase.controller;

import com.wilkins.showcase.service.GreetingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GreetingController.class)
public class GreetingControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    GreetingService greetingService;

    @Test
    void returnsGreeting() throws Exception {
        mockMvc.perform(get("/greetings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salutation", is("hello")))
                .andExpect(jsonPath("$.name", is("world")));
    }

}
