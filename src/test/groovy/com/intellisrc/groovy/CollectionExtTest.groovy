package com.intellisrc.groovy

import spock.lang.Specification

import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Extends Collection Tests
 * @since 2020/02/18.
 */
class CollectionExtTest extends Specification {
    def "Getting one element randomly"() {
        given:
            ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>()
            queue.addAll(1..10000)
        when:
            Integer num1 = CollectionExt.random(queue)
            Integer num2 = CollectionExt.random(queue)
            Integer num3 = CollectionExt.random(queue)
            println "The random numbers are: $num1, $num2, $num3"
        then:
            assert (num1 != num2 || num2 != num3 || num1 != num3)
    }

    def "Getting a list of elements randomly"() {
        given:
            List<Integer> queue = []
            queue.addAll(1..10000)
        when:
            int elems = 10
            List<Integer> nums = CollectionExt.random(queue, elems)
            println "The random numbers are: " + nums.join(",")
        then:
            assert nums.size() == elems
            assert nums.unique().size() > 1
    }

    def "Getting a list of elements with different element sizes"() {
        given:
            List<Integer> queue = []
            queue.addAll(1..10)
        when:
            List<Integer> nums = CollectionExt.random(queue, elems)
            println "The random numbers are: " + nums.join(",")
        then:
            assert nums.size() == size
        where:
            elems | size
            -1    | 0
            0     | 0
            5     | 5
            20    | 10
    }

}
