package com.intellisrc.groovy

import spock.lang.Specification

import java.time.LocalDateTime

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
}
