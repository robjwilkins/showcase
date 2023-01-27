package com.wilkins.showcase.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.springframework.http.MediaType.TEXT_PLAIN_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UploadControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void fileUpload() throws Exception {
        var file = new MockMultipartFile("file", "test.txt", TEXT_PLAIN_VALUE, "Hello world".getBytes(UTF_8));
        mockMvc.perform(multipart("/upload").file(file))
                .andExpect(status().isCreated());
    }
}