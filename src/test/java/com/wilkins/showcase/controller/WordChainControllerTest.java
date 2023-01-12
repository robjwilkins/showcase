package com.wilkins.showcase.controller;

import com.wilkins.showcase.service.WordChainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = WordChainController.class)
public class WordChainControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    WordChainService wordChainService;

    @Test
    void createsChain() throws Exception {
        var expectedChain = List.of("cat", "cot", "cog", "dog");
        when(wordChainService.chainWords("cat", "dog")).thenReturn(expectedChain);
        mockMvc.perform(get("/words/chains")
                        .param("source", "cat")
                        .param("target","dog"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.chain", is(expectedChain)));
    }

    @Test
    void badRequestWhenSourceAndTargetWordsDifferentLength() throws Exception {
        mockMvc.perform(get("/words/chains")
                .param("source", "abc")
                .param("target","xyzzz"))
                .andExpect(status().isBadRequest());
    }
}
