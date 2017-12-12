package jp.sharelock.groovy

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
    def "toDate Normal"() {
        given:
            def dateStr = "2017-10-10 12:00:00"
        expect:
            "2017-10-10 12:00:00" == DateExt.toYMDHms(StringExt.fromYMDHms(dateStr))
    }
    def "toDate With millisecond"() {
        given:
            def dateStr = "2017-10-10 12:00:00.0"
        expect:
            "2017-10-10 12:00:00" == DateExt.toYMDHms(StringExt.fromYMDHms(dateStr))
    }
    def "toDate With Timezone"() {
        given:
            def dateStr = "2017-10-10 12:00:00.999 GMT"
        expect:
            "2017-10-10 12:00:00" == DateExt.toYMDHms(StringExt.fromYMDHms(dateStr))
    }
    def "date from HashMap"() {
        given:
            def map = [ id : 200, date_time : "2017-10-10 12:00:00.0" ]
        when:
            Date date = StringExt.fromYMDHms(map["date_time"].toString())
        then:
            assert date != null
    }
    def "date without time"() {
        given:
            Date date = StringExt.fromYMDHms("2000-01-01")
        expect:
            assert DateExt.toYMDHms(date) == "2000-01-01 00:00:00"
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
    def "Test shortcuts"() {
        setup:
        String sdate = "2015-12-20 11:23:54"
        Date date = StringExt.fromYMDHms(sdate)
        expect:
        assert DateExt.toYYYY(date) == 2015
        assert DateExt.toYY(date) == 15
        assert DateExt.toMM(date) == 12
        assert DateExt.toDD(date) == 20
        assert DateExt.toHH(date) == 11
        assert DateExt.toYMDHms(date) == sdate
        assert DateExt.toYMDHm(date) == "2015-12-20 11:23"
    }
}
