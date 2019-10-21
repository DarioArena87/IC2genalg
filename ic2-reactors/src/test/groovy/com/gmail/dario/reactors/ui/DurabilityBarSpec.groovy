package com.gmail.dario.reactors.ui


import spock.lang.Specification
import spock.lang.Unroll

class DurabilityBarSpec extends Specification {

    @Unroll
    def "durability bar test"() {
        given:
        def bar = new DurabilityBar(durabilityLeft)

        expect:
        bar.style.get("height") == "3px"
        bar.style.get("width") == "${expected}px"

        where:
        durabilityLeft | expected
        0              | 0
        0.5            | 16
        0.75           | 24
        1              | 32
    }
}
