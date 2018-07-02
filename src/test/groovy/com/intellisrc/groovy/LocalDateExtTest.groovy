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
            LocalDateTime date = StringExt.toDateTime("yyyy-MM-dd HH:mm:ss", sdate)
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
    def "Date to DateTime"() {
        setup:
            def str = "2011-01-01"
        expect:
            assert LocalDateExt.getYMDHms(LocalDateExt.toDateTime(StringExt.toDate(str))) == str.substring(0, 10) + " " + "00:00:00"
    }
    def "Parse Date"() {
        setup:
            def dates = [
                    "2015-03-15",
                    "2015-03-15 10:30",
                    "2015-03-15T00:00",
                    "2015-03-15 10:30:22",
                    "2015-03-15T10:30:22",
                    "2015-03-15 10:30:15.203",
                    "2015-03-15T10:30:15.203",
            ]
        expect:
            dates.each {
                assert LocalDateExt.getYMD(StringExt.toDate(it)) == it.substring(0, 10)
            }
    }
    def "Parse DateTime Zero"() {
        setup:
        def dates = [
                "2015-03-15 00:00",
                "2015-03-15T00:00",
                "2015-03-15 00:00:00",
                "2015-03-15T00:00:00",
                "2015-03-15 00:00:00.000",
                "2015-03-15T00:00:00.000",
        ]
        expect:
        dates.each {
            assert LocalDateExt.getYMDHms(StringExt.toDateTime(it)) == it.substring(0, 10) + " " + "00:00:00"
        }
    }
    def "Parse DateTime Hours"() {
        setup:
        def datehours = [
                "2015-03-15 10:30",
                "2015-03-15T10:30",
                "2015-03-15 10:30:00",
                "2015-03-15T10:30:00",
                "2015-03-15 10:30:00.000",
                "2015-03-15T10:30:00.000",
        ]
        expect:
        datehours.each {
            assert LocalDateExt.getYMDHms(StringExt.toDateTime(it)) == it.substring(0, 10) + " " + "10:30:00"
        }
    }
    def "Parse DateTime with seconds"() {
        setup:
        def datesecs = [
                "2015-03-15 10:30:15",
                "2015-03-15T10:30:15",
                "2015-03-15 10:30:15.000",
                "2015-03-15T10:30:15.000",
        ]
        expect:
        datesecs.each {
            assert LocalDateExt.getYMDHms(StringExt.toDateTime(it)) == it.substring(0, 10) + " " + "10:30:15"
        }
    }
}