package com.intellisrc

import spock.lang.Specification

/**
 * @since 2020/07/29.
 */
class LimitedLinkedListTest extends Specification {
    def "Adding elements should work fine"() {
        setup:
            LimitedLinkedList<Integer> queue = new LimitedLinkedList<>()
        when:
            queue.add(1200)
            queue << 3999
        then:
            assert queue.size() == 2
    }
    def "Adding elements with 'add' or 'leftShift'"() {
        setup:
            LimitedLinkedList<Integer> queue = new LimitedLinkedList<>(10)
        when:
            (1..20).each {
                queue.add(it)
            }
        then:
            assert queue.size() == 10
        when:
            queue.clear()
        then:
            assert queue.size() == 0 && queue.empty
        when:
            (1..20).each {
                queue << it
            }
        then:
            assert queue.size() == 10
            assert queue.full
    }
    def "Limit number of elements after they were set"() {
        setup:
            LimitedLinkedList<Integer> queue = new LimitedLinkedList<>()
        when:
            queue.addAll([1,2,3,4,5,6,7,8,9,10,11,12])
            queue.length = 10
        then:
            assert queue.size() == 10
    }
    def "Limit number of elements before they were set"() {
        setup:
            LimitedLinkedList<Integer> queue = new LimitedLinkedList<>()
        when:
            queue.length = 10
            queue.addAll([1,2,3,4,5,6,7,8,9,10,11,12])
        then:
            assert queue.size() == 10
    }
    def "Limit number of elements before they were set - left shift"() {
        setup:
            LimitedLinkedList<Integer> queue = new LimitedLinkedList<>()
        when:
            queue.length = 10
            (1..20).each {
                queue << it
            }
        then:
            assert queue.size() == 10
    }
    def "Limit number of elements by assignment"() {
        setup:
            LimitedLinkedList<Integer> queue = [1,2,3,4,5,6,7,8,9,10,11,12] as LimitedLinkedList<Integer>
        when:
            queue.length = 10
        then:
            assert queue.size() == 10
            assert queue.last() == 12
            assert queue.first() != 1
    }
    def "When all queue is full, it should be true"() {
        setup:
            LimitedLinkedList<Integer> queue = new LimitedLinkedList<>(3)
        when:
            queue.add(1)
        then:
            assert ! queue.full
        when:
            queue.add(2)
            queue.add(3)
        then:
            assert queue.full
        when:
            queue.poll()
        then:
            assert ! queue.full
    }
}
