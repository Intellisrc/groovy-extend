package com.intellisrc.groovy

import spock.lang.Specification

/**
 * @since 2022/11/08.
 */
class DoubleExtTest extends Specification {
    def "Ceil a number"() {
        expect:
            assert DoubleExt.ceil(100 / 3) == 34
    }
    def "Ceil a number with decimals"() {
        expect:
            assert DoubleExt.ceil(100 / 3, 2) == 33.34d
    }
    def "Floor a number"() {
        expect:
            assert DoubleExt.floor(100 / 8) == 12
    }
    def "Floor a number with decimals"() {
        expect:
            assert DoubleExt.floor(100 / 8, 1) == 12.5d
    }
}
