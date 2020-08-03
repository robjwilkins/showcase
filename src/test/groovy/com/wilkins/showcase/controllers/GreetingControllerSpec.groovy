package com.wilkins.showcase.controllers


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

    @Unroll
    def "should wish #name an appropriate greeting"() {
        given:
        def get = get('/greeting')
                .param('name', name)
                .param('salutation', salutation)


        when:
        def jsonGreeting = mockMvc.perform(get)
                .andExpect(status().isOk())
                .andExpect(jsonPath('$.name').value(name))
                .andExpect(jsonPath('$.salutation').value(salutation))
                .andReturn().response.contentAsString

        then:
        jsonGreeting == expectedGreeting

        where:
        name    | salutation       | expectedGreeting
        'world' | 'hello'          | '{"salutation":"hello","name":"world"}'
        'robin' | "good afternoon" | '{"salutation":"good afternoon","name":"robin"}'
    }

}
