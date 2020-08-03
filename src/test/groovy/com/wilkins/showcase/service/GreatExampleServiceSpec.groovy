package com.wilkins.showcase.service

import spock.lang.Specification
import spock.lang.Unroll

import java.util.function.Supplier

class GreatExampleServiceSpec extends Specification {

    Supplier<String> stringSupplier = Mock()

    @Unroll
    def "ID String is generated correctly when using the GreatExampleService"() {
        given:
        stringSupplier.get() >> suppliedString
        def idService = new GreatExampleService(something, stringSupplier, joiner)

        expect:
        expectedId == idService.findExampleId()

        where:
        something | suppliedString | joiner || expectedId
        "abc"     | "123"          | "::"   || "abc::123"
        "1"       | "2"            | "||"   || "1||2"
    }

    @Unroll
    def "Example is generated correctly when using the key: #prefix and suppliedString: #suppliedString"() {
        given:
        stringSupplier.get() >> suppliedString
        def exampleService = new GreatExampleService(prefix, stringSupplier, joiner)

        when:
        def example = exampleService.createExample()

        then:
        with(example) {
            key == prefix
            value == suppliedString
            id == expectedId
            parts == [prefix, suppliedString, expectedId] as List
        }

        where:
        prefix | suppliedString | joiner || expectedId
        "abc"  | "123"          | "::"   || "abc::123"
        "1"    | "2"            | "//"   || "1//2"

    }
}
