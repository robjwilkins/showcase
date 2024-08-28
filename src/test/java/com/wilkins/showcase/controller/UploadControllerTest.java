package com.wilkins.showcase.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UploadControllerTest {

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        var messageConverter = new MappingJackson2XmlHttpMessageConverter();
        var xmlMapper = new XmlMapper();
        xmlMapper.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        messageConverter.setObjectMapper(xmlMapper);
        mockMvc = MockMvcBuilders
                .standaloneSetup(new UploadController())
                .setMessageConverters(messageConverter)
                .build();
    }

    @Test
    void canUploadXml() throws Exception {
        var response = mockMvc.perform(post("/upload")
                        .contentType(MediaType.APPLICATION_XML)
                        .content("""
                                    <Person>
                                      <firstName>Bob</firstName>
                                      <lastName>Fish</lastName>
                                    </Person>
                                """))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(response).doesNotContain("E+");
    }

    @Test
    void canUploadXmlWithEmptyValues() throws Exception {
        var response = mockMvc.perform(post("/upload")
                        .contentType(MediaType.APPLICATION_XML)
                        .content("""
                                    <Person>
                                      <firstName>Bob</firstName>
                                      <lastName></lastName>
                                    </Person>
                                """))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        assertThat(response).doesNotContain("E+");
    }
}