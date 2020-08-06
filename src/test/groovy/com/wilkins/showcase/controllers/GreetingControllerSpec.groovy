package com.wilkins.showcase.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification
import spock.lang.Unroll

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@AutoConfigureMockMvc
@WebMvcTest
class GreetingControllerSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    static ObjectMapper MAPPER = new ObjectMapper()

    @Unroll
    def "should wish #providedName an appropriate greeting"() {
        given:
        def get = get('/greeting')
                .param('name', providedName)
                .param('salutation', providedSalutation)


        when:
        def jsonGreeting = mockMvc.perform(get)
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.name').value(providedName))
                .andExpect(jsonPath('$.salutation').value(providedSalutation))
                .andReturn().response.contentAsString

        then:
        def greeting = MAPPER.readValue(jsonGreeting, Greeting.class)
        with(greeting) {
            salutation == providedSalutation
            name == providedName
        }

        where:
        providedName | providedSalutation
        'world'      | 'hello'
        'robin'      | "good afternoon"
    }

}
