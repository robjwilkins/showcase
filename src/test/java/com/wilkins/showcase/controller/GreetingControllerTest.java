package com.wilkins.showcase.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GreetingController.class)
public class GreetingControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser
    void returnsGreeting() throws Exception {
        mockMvc.perform(get("/greetings"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salutation", is("hello")))
                .andExpect(jsonPath("$.name", is("world")));
    }

    @Test
    @WithMockUser
    void returnsGreetingWhenLoggedIn() throws Exception {
        mockMvc.perform(get("/greetings")
                        .param("username", "bobfish")
                        .param("password", "Passw0rd!"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.salutation", is("hello")))
                .andExpect(jsonPath("$.name", is("world")));
    }

    @Test
    void unauthorisedWhenNoUsernameAndPassword() throws Exception {
        mockMvc.perform(get("/greetings"))
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("http://localhost/oauth2/authorization/google"));
    }
}
