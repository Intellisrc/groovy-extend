package com.intellisrc.groovy

import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
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
            // These will normally be used as: LocalDateTime.now().YY
            assert LocalDateExt.getYMDHms(date) == sdate
            assert LocalDateExt.getYMDHm(date) == "2015-12-20 11:23"
            assert LocalDateExt.getYMDHmsS(date) == "2015-12-20 11:23:54.000"
            assert LocalDateExt.getYMD(date.toLocalDate()) == "2015-12-20"
            assert LocalDateExt.getYY(date.toLocalDate()) == "15"
            assert LocalDateExt.getMM(date.toLocalDate()) == "12"
            assert LocalDateExt.getDD(date.toLocalDate()) == "20"
            assert LocalDateExt.getHH(date.toLocalTime()) == "11"
            assert LocalDateExt.getHHmm(date.toLocalTime()) == "11:23"
            assert LocalDateExt.getHHmmss(date.toLocalTime()) == "11:23:54"
    }
    def "Converting from-to Date"() {
        setup:
            Date date = new Date()
            LocalDateTime local = DateExt.toLocalDateTime(date)
        expect:
            assert LocalDateExt.toDate(local).toInstant().toEpochMilli() == date.toInstant().toEpochMilli()
    }
    def "Test String conversion"() {
        setup:
            // The way it will be used is: def time = "10:10:00".toTime()
            LocalDate date = StringExt.toDate("2000-01-01")
            LocalTime time = StringExt.toTime("12:15:23")
            LocalDateTime ldt = LocalDateTime.of(date, time)
        expect:
            assert StringExt.toDateTime("2000-01-01 12:15:23") == ldt
    }
}