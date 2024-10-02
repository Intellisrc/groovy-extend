package com.intellisrc.groovy

import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 * @since 18/06/15.
 */
class LocalDateExtTest extends Specification {
    def "Test LocalDate shortcuts"() {
        setup:
            String sdate = "2015-12-20 11:23:54"
            LocalDateTime date = StringExt.toDateTime(sdate, "yyyy-MM-dd HH:mm:ss")
        expect:
            // These will normally be used as: LocalDateTime.now().YY
            assert LocalDateTimeExt.getYMDHms(date) == sdate
            assert LocalDateTimeExt.getYMDHm(date) == "2015-12-20 11:23"
            assert LocalDateTimeExt.getYMDHmsS(date) == "2015-12-20 11:23:54.000"
            assert LocalDateExt.getYMD(date.toLocalDate()) == "2015-12-20"
            assert LocalDateExt.getYY(date.toLocalDate()) == "15"
            assert LocalDateExt.getMM(date.toLocalDate()) == "12"
            assert LocalDateExt.getDD(date.toLocalDate()) == "20"
            assert LocalTimeExt.getHH(date.toLocalTime()) == "11"
            assert LocalTimeExt.getHHmm(date.toLocalTime()) == "11:23"
            assert LocalTimeExt.getHHmmss(date.toLocalTime()) == "11:23:54"
    }

    def "LocalTime.isBetween"() {
        setup:
            LocalTime time = StringExt.toTime("12:34:56")
            LocalTime min = StringExt.toTime("12:30:00")
            LocalTime max = StringExt.toTime("12:40:00")
            LocalTime noMin = StringExt.toTime("18:30:00")
            LocalTime noMax = StringExt.toTime("18:40:00")
        expect:
            assert LocalTimeExt.isBetween(time, min, max)
            assert ! LocalTimeExt.isBetween(time, noMin, noMax)
    }
    
    def "Converting from-to Date"() {
        setup:
            Date date = new Date()
            LocalDateTime local = DateExt.toLocalDateTime(date)
        expect:
            assert LocalDateTimeExt.toDate(local).toInstant().toEpochMilli() == date.toInstant().toEpochMilli()
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
            assert LocalDateTimeExt.getYMDHms(LocalDateExt.toDateTime(StringExt.toDate(str))) == str.substring(0, 10) + " " + "00:00:00"
    }
    
    def "Parse Date"() {
        setup:
            def dates = [
                    "20150315",
                    "150315",
                    "15-03-15",
                    "15.03.15",
                    "2015.03.15",
                    "2015/03/15",
                    "15/03/2015",
                    "15.03.2015",
                    "15-03-2015",
                    "2015-03-15",
                    "2015-03-15 10:30",
                    "2015.03.15 10:30",
                    "15.03.2015 10:30:22",
                    "2015-03-15T00:00",
                    "2015-03-15 10:30:22",
                    "2015-03-15T10:30:22",
                    "2015-03-15 10:30:15.203",
                    "2015-03-15T10:30:15.203",
            ]
        expect:
            dates.each {
                assert LocalDateExt.getYMD(StringExt.toDate(it)) == "2015-03-15"
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
                assert LocalDateTimeExt.getYMDHms(StringExt.toDateTime(it)) == it.substring(0, 10) + " " + "00:00:00"
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
                assert LocalDateTimeExt.getYMDHms(StringExt.toDateTime(it)) == it.substring(0, 10) + " " + "10:30:00"
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
                assert LocalDateTimeExt.getYMDHms(StringExt.toDateTime(it)) == it.substring(0, 10) + " " + "10:30:15"
            }
    }
    
    def "Get millis/seconds from LocalDateTime"() {
        expect:
            assert Math.abs(System.currentTimeMillis() - LocalDateTimeExt.toMillis(LocalDateTime.now())) < 1000
            assert Math.abs(System.currentTimeSeconds() - LocalDateTimeExt.toSeconds(LocalDateTime.now())) < 2
    }

    def "Using LocalDate in ranges"() {
        setup:
            LocalDate start = StringExt.toDate("2001-02-20")
            LocalDate finish = StringExt.toDate("2001-03-05")
        expect:
            /* This is the way to use it:
            (start..finish).each {
                LocalDate it ->
                    println LocalDateExt.getYMD(it, "-")
                    assert LocalDateExt.isBetween(it, start, finish)
            }
             */
            LocalDate it = StringExt.toDate(LocalDateExt.getYMD(start, "-"))
            while(it.isBefore(finish)) {
                println LocalDateExt.getYMD(it, "-")
                assert LocalDateExt.isBetween(it, start, finish)
                it = LocalDateExt.next(it)
            }
    }

    def "End of date"() {
        setup:
            LocalDateTime date = StringExt.toDateTime("2020-02-02 22:12:21")
        expect:
            assert LocalDateExt.atEndOfDay(date.toLocalDate()) == LocalDateTimeExt.atEndOfDay(date)
    }
}