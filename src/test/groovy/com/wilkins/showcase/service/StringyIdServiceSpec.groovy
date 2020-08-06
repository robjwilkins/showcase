package com.wilkins.showcase.service

import spock.lang.Specification
import spock.lang.Unroll

import java.util.function.Supplier

class StringyIdServiceSpec extends Specification {

    Supplier<String> stringSupplier = Mock()

    def "ID String is generated correctly when using the ConcatenatedStringTraceIdService"() {
        given:
        stringSupplier.get() >> randomUuid
        def traceIdService = new StringyIdService(prefix, stringSupplier, joiner)

        expect:
        expectedId == traceIdService.stringId()

        where:
        prefix | randomUuid | joiner || expectedId
        "abc"  | "123"      | "::"   || "abc::123"
        "1"    | "2"        | "||"   || "1||2"
    }

    @Unroll
    def "Identifier is generated correctly when using the key: #prefix and value: #randomUuid"() {
        given:
        stringSupplier.get() >> randomUuid
        def traceIdService = new StringyIdService(prefix, stringSupplier, joiner)

        when:
        def identifier = traceIdService.identifier()

        then:
        with(identifier) {
            key == prefix
            value == randomUuid
            id == expectedId
            parts == [prefix, randomUuid, expectedId] as List
        }

        where:
        prefix | randomUuid | joiner || expectedId
        "abc"  | "123"      | "::"   || "abc::123"
        "1"    | "2"        | "//"   || "1//2"

    }
}
