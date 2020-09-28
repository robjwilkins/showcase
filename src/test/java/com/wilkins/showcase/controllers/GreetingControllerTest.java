package com.wilkins.showcase.controllers;

import org.junit.jupiter.api.Test;
import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest
public class GreetingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    Scheduler scheduler;

    @Test
    void returnsGreeting() throws Exception {
        mockMvc.perform(get("/greeting"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salutation", is("hello")))
                .andExpect(jsonPath("$.name", is("world")));
    }

    @Test
    void scheduleGreeting() throws Exception {
        mockMvc.perform(post("/greeting"))
                .andExpect(status().isOk())
                .andExpect(content().string(notNullValue()));
        verify(scheduler).scheduleJob(any(), any());
    }
}
