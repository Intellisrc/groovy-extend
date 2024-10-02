package com.intellisrc.groovy

import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit


/**
 * @since 2019/10/07.
 */
class LocalDateStaticExtTest extends Specification {
    
    def "LocalDateTime from millis"() {
        setup:
            long millis = System.currentTimeMillis()
            LocalDateTime now = LocalDateTimeStaticExt.fromMillis(null, millis)
            LocalDate dateMillis = LocalDateStaticExt.fromMillis(null, millis)
        expect:
            assert Math.abs(ChronoUnit.SECONDS.between(LocalDateTime.now(), now)) <= 1
            assert now.toLocalDate() == dateMillis
            assert LocalDateTimeExt.toMillis(now) == millis
    }

    def "LocalDate from seconds"() {
        setup:
            LocalDate date = LocalDateStaticExt.fromSeconds(null, 1727843110, ZoneId.of("GMT"))
        expect:
            assert date.dayOfMonth == 2
            assert date.monthValue == 10
    }

    def "LocalDateTime from seconds"() {
        setup:
            LocalDateTime date = LocalDateTimeStaticExt.fromSeconds(null, 1727843110, ZoneId.of("GMT"))
        expect:
            assert date.dayOfMonth == 2
            assert date.monthValue == 10
            assert date.hour == 4
            assert date.minute == 25
            assert date.second == 10
    }

    def "LocalDateTime should return with time 00:00:00.000"() {
        setup:
            LocalDateTime now = LocalDateTime.now()
            LocalDateTime noTime = LocalDateTimeExt.clearTime(now)
        expect:
            assert now.year == noTime.year
            assert now.month == noTime.month
            assert now.dayOfMonth == noTime.dayOfMonth
            assert ! noTime.hour
            assert ! noTime.minute
            assert ! noTime.second
            assert ! noTime.nano
    }
}