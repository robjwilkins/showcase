package com.wilkins.showcase.controller;

import com.wilkins.showcase.domain.Person;
import com.wilkins.showcase.service.PersonService;
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

@WebMvcTest(controllers = PersonController.class)
public class PersonControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonService personService;

    @Test
    void canGetPersons() throws Exception {
        var person1 = new Person("ms-lisa-simpson", "Ms", "Lisa", "Simpson");
        when(personService.findAll()).thenReturn(List.of(person1));
        mockMvc.perform(get("/persons"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", is("ms-lisa-simpson")))
                .andExpect(jsonPath("$[0].title", is("Ms")))
                .andExpect(jsonPath("$[0].forename", is("Lisa")))
                .andExpect(jsonPath("$[0].surname", is("Simpson")));
    }
}
