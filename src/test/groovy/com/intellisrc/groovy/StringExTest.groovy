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
            assert StringExt.toSnakeCase(a, c) == b
        where:
            a            |   b              | c
            "someWord"   |  "some_word"     | false
            "SomeWord"   |  "some_word"     | false
            "Some Word"  |  "some_word"     | false
            "some_word"  |  "some_word"     | false
            "Some_Word"  |  "some_word"     | false
            "Some-Word"  |  "some_word"     | false
            "Some.Word"  |  "some_word"     | false
            "SOME WORD"  |  "SOME_WORD"     | false
            "SOME-WORD"  |  "SOME_WORD"     | false
            "SOME.WORD"  |  "SOME_WORD"     | false
            "SOME_WORD"  |  "SOME_WORD"     | false
            "someWord"   |  "SOME_WORD"     | true
            "SomeWord"   |  "SOME_WORD"     | true
            "Some Word"  |  "SOME_WORD"     | true
            "some_word"  |  "SOME_WORD"     | true
            "Some_Word"  |  "SOME_WORD"     | true
            "Some-Word"  |  "SOME_WORD"     | true
            "Some.Word"  |  "SOME_WORD"     | true
            "SOME WORD"  |  "SOME_WORD"     | true
            "SOME.WORD"  |  "SOME_WORD"     | true
            "SOME-WORD"  |  "SOME_WORD"     | true
    }

    def "String to CamelCase"() {
        expect :
            assert StringExt.toCamelCase(a, c) == b
        where:
            a            |   b              | c
            "some_word"  |  "someWord"      | false
            "some_word"  |  "SomeWord"      | true
            "SOME_WORD"  |  "SomeWord"      | true
            "SOME_WORD"  |  "someWord"      | false
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
            "Some-Word"  |  "SomeWord"      | true
            "Some-Word"  |  "someWord"      | false
    }

    def "String to DotCase"() {
        expect :
            assert StringExt.toDotCase(a, c) == b
        where:
            a            |   b              | c
            "someWord"   |  "some.word"     | false
            "SomeWord"   |  "some.word"     | false
            "Some Word"  |  "some.word"     | false
            "some.word"  |  "some.word"     | false
            "Some.Word"  |  "some.word"     | false
            "Some.Word"  |  "some.word"     | false
            "Some.Word"  |  "some.word"     | false
            "SOME WORD"  |  "SOME.WORD"     | false
            "SOME.WORD"  |  "SOME.WORD"     | false
            "SOME.WORD"  |  "SOME.WORD"     | false
            "SOME.WORD"  |  "SOME.WORD"     | false
            "someWord"   |  "some.word"     | true
            "SomeWord"   |  "some.word"     | true
            "Some Word"  |  "some.word"     | true
            "some.word"  |  "some.word"     | true
            "Some.Word"  |  "some.word"     | true
            "Some.Word"  |  "some.word"     | true
            "Some.Word"  |  "some.word"     | true
            "SOME WORD"  |  "some.word"     | true
            "SOME.WORD"  |  "some.word"     | true
            "SOME.WORD"  |  "some.word"     | true
    }

    def "String to KebabCase"() {
        expect :
            assert StringExt.toKebabCase(a, c) == b
        where:
            a            |   b              | c
            "someWord"   |  "some-word"     | false
            "SomeWord"   |  "some-word"     | false
            "Some Word"  |  "some-word"     | false
            "some-word"  |  "some-word"     | false
            "Some-Word"  |  "some-word"     | false
            "Some-Word"  |  "some-word"     | false
            "Some.Word"  |  "some-word"     | false
            "SOME WORD"  |  "SOME-WORD"     | false
            "SOME-WORD"  |  "SOME-WORD"     | false
            "SOME.WORD"  |  "SOME-WORD"     | false
            "SOME-WORD"  |  "SOME-WORD"     | false
            "someWord"   |  "SOME-WORD"     | true
            "SomeWord"   |  "SOME-WORD"     | true
            "Some Word"  |  "SOME-WORD"     | true
            "some-word"  |  "SOME-WORD"     | true
            "Some-Word"  |  "SOME-WORD"     | true
            "Some-Word"  |  "SOME-WORD"     | true
            "Some.Word"  |  "SOME-WORD"     | true
            "SOME WORD"  |  "SOME-WORD"     | true
            "SOME.WORD"  |  "SOME-WORD"     | true
            "SOME-WORD"  |  "SOME-WORD"     | true
    }
}
