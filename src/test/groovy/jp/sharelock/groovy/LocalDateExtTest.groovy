package jp.sharelock.groovy

import spock.lang.Specification

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * @since 18/06/15.
 */
class LocalDateExtTest extends Specification {
    def "Test LocalDate shortcuts"() {
        setup:
            String sdate = "2015-12-20 11:23:54"
            LocalDateTime date = LocalDateTime.parse(sdate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
        expect:
            assert LocalDateExt.YMDHms(date) == sdate
            assert LocalDateExt.YMDHm(date) == "2015-12-20 11:23"
            assert LocalDateExt.YMD(date.toLocalDate()) == "2015-12-20"
            assert LocalDateExt.YY(date.toLocalDate()) == "15"
            assert LocalDateExt.MM(date.toLocalDate()) == "12"
            assert LocalDateExt.DD(date.toLocalDate()) == "20"
            assert LocalDateExt.HH(date.toLocalTime()) == "11"
            assert LocalDateExt.HHmm(date.toLocalTime()) == "11:23"
            assert LocalDateExt.HHmmss(date.toLocalTime()) == "11:23:54"
    }
    def "Converting from-to Date"() {
        setup:
            Date date = new Date()
            LocalDateTime local = DateExt.toLocalDateTime(date)
        expect:
            assert LocalDateExt.toDate(local).toInstant().toEpochMilli() == date.toInstant().toEpochMilli()
    }
}