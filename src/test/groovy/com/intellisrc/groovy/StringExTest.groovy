package com.intellisrc.groovy

import spock.lang.Specification

/**
 * Created by lepe on 17/02/23.
 */
class StringExTest extends Specification {
    def "toIP4"() {
        given:
            def ipStr = "192.168.10.100"
        when:
            def ipNet = StringExt.toInet4Address(ipStr)
        then:
            assert ipNet
            ipNet.getHostName() == ipStr
    }
    def "padLeft"() {
        expect:
            "200".padLeft(5) == "  200"
    }
    def "Query to Map"() {
        setup:
            //NOTE: for queries, %20 and + are the same
            def uri = new URI("http://localhost/?one=1&two=2&three=third+member&four=true")
            def uri2 = new URI("http://localhost/?one=1&two=2&three=third%20member&four=true")
        when:
            def map = StringExt.getQueryMap(uri.query)
            def map2 = StringExt.getQueryMap(uri2.query)
        then:
            assert map.size() == 4
            assert map.one == 1
            assert map.three == "third member"
            assert map.four == true
            assert map.three == map2.three
    }
    def "String to snake_case"() {
        expect :
            assert StringExt.toSnakeCase(a) == b
        where:
            a            |   b
            "someWord"   |  "some_word"
            "SomeWord"   |  "some_word"
            "Some Word"  |  "some_word"
            "some_word"  |  "some_word"
            "Some_Word"  |  "some_word"
    }

    def "String to CamelCase"() {
        expect :
            assert StringExt.toCamelCase(a, c) == b
        where:
            a            |   b              | c
            "some_word"  |  "someWord"      | false
            "some_word"  |  "SomeWord"      | true
            // prefix: _ will be removed:
            "_some_word" |  "someWord"      | false
            // Spaces will become "_"
            "some word"  |  "someWord"      | false
            "some word"  |  "SomeWord"      | true
            "Some Word"  |  "someWord"      | false
            "Some Word"  |  "SomeWord"      | true
            // All non-alphanumeric characters will become "_"
            "Some.Word"  |  "someWord"      | false
            "Some.Word"  |  "SomeWord"      | true
    }
}
