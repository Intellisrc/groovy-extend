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
            def ipNet = ipStr.toInet4Address()
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
            "2017-10-10 12:00:00" == dateStr.toDateSTD().toStringSTD()
    }
    def "toDate With millisecond"() {
        given:
            def dateStr = "2017-10-10 12:00:00.0"
        expect:
            "2017-10-10 12:00:00" == dateStr.toDateSTD().toStringSTD()
    }
    def "toDate With Timezone"() {
        given:
            def dateStr = "2017-10-10 12:00:00.999 GMT"
        expect:
            "2017-10-10 12:00:00" == dateStr.toDateSTD().toStringSTD()
    }
    def "date from HashMap"() {
        given:
            LinkedHashMap<String,String> map = [ id : 200, date_time : "2017-10-10 12:00:00.0" ]
        when:
            Date date = map["date_time"].toDateSTD()
        then:
            assert date != null
    }
    def "date without time"() {
        given:
            Date date = "2000-01-01 12:30:54".toDateSTD(true)
        expect:
            assert date.toStringSTD() == "2000-01-01 00:00:00"
    }
}
