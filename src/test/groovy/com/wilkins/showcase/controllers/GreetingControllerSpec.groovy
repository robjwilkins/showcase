package com.wilkins.showcase.controllers

import com.wilkins.showcase.service.ExampleService
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import spock.lang.Specification
import spock.lang.Unroll

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GreetingControllerSpec extends Specification {

    @Autowired
    TestRestTemplate restTemplate;

    @SpringBean
    ExampleService exampleService = Mock()

    @Unroll
    def "should wish #providedName an appropriate greeting"() {
        given:
        exampleService.findExampleId() >> "mock-id"

        when:
        def response= restTemplate
                .getForEntity("/greeting?name={name}&salutation={salutation}", Greeting.class, providedName, providedSalutation)

        then:
        response.statusCode.value() == 200
        response.headers.getFirst('x-trace-id') == 'mock-id'
        with(response.body) {
            salutation == providedSalutation
            name == providedName
        }

        where:
        providedName | providedSalutation
        'world'      | 'hello'
        'robin'      | "good afternoon"
    }

}
