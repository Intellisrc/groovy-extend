package com.intellisrc.groovy

import spock.lang.Specification

/**
 * @since 2022/11/08.
 */
class BigDecimalExtTest extends Specification {
    def "Ceil a number"() {
        expect:
            assert BigDecimalExt.ceil(100 / 3) == 34
    }
    def "Ceil a number with decimals"() {
        expect:
            assert BigDecimalExt.ceil(100 / 3, 2) == 33.34
    }
    def "Floor a number"() {
        expect:
            assert BigDecimalExt.floor(100 / 8) == 12
    }
    def "Floor a number with decimals"() {
        expect:
            assert BigDecimalExt.floor(100 / 8, 1) == 12.5
    }
}
