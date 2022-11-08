package com.intellisrc.groovy

import spock.lang.Specification

/**
 * @since 2022/11/08.
 */
class MathStaticExtTest extends Specification {
    def "Round a number"() {
        expect:
            assert MathStaticExt.roundNumber(100 / 3) == 33
    }
    def "Round a number with decimals"() {
        expect:
            assert MathStaticExt.roundNumber(100 / 3, 2) == 33.33d
    }
    def "Ceil a number"() {
        expect:
            assert MathStaticExt.ceilNumber(100 / 3) == 34
    }
    def "Ceil a number with decimals"() {
        expect:
            assert MathStaticExt.ceilNumber(100 / 3, 2) == 33.34d
    }
    def "Floor a number"() {
        expect:
            assert MathStaticExt.floorNumber(100 / 8) == 12
    }
    def "Floor a number with decimals"() {
        expect:
            assert MathStaticExt.floorNumber(100 / 8, 1) == 12.5d
    }
}
