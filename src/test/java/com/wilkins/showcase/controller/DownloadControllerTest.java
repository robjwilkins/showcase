package com.wilkins.showcase.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DownloadControllerTest {

    MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(new DownloadController()).build();
    }

    @Test
    void canDownloadFile() throws Exception {
        var result = mockMvc.perform(MockMvcRequestBuilders.get("/download"))
                .andExpect(status().isOk())
                .andExpect(request().asyncStarted())
                .andDo(log())
                .andReturn();
    }
}