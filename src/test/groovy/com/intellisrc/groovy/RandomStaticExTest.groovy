package com.intellisrc.groovy

import spock.lang.Specification


/**
 * @since 18/07/04.
 */
class RandomStaticExTest extends Specification {
    def "Random range"() {
        expect:
            int num = RandomStaticExt.range(null, a,b)
            assert num >= a && num <= b
        where:
            a   |  b
            0   |  20
            1   | 100
            5   | 5
            100 | 200
    }
}