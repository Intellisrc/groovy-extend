package com.intellisrc.groovy

import spock.lang.Specification

/**
 * @since 2023/10/11.
 */
class ObjectExtTest extends Specification {
    static class TestMap {
        boolean ok = false
        int number = 100
        String text = "hello"
        List someList = []
    }
    def "toMap should return a map"() {
        setup:
            TestMap testMap = new TestMap()
        expect:
            assert ObjectExt.toMap(testMap) == [
                ok : false,
                number : 100,
                text : "hello",
                someList : []
            ]
    }
    def "toSnakeMap should return names with snake_case"() {
        setup:
            TestMap testMap = new TestMap()
        expect:
            assert ObjectExt.toSnakeMap(testMap) == [
                ok : false,
                number : 100,
                text : "hello",
                some_list : []
            ]
    }
}
