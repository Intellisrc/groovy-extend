package com.intellisrc.groovy

import spock.lang.Specification

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

/**
 * @since 2024/10/02.
 */
class ZonedDateTimeExtTest extends Specification {
    def "at start and end of day"() {
        setup:
            LocalDateTime now = LocalDateTime.now()
            ZonedDateTime zdt = now.atZone(ZoneOffset.UTC)
            ZonedDateTime adjusted = zdt.withZoneSameLocal(ZoneId.systemDefault())
        expect:
            assert ZonedDateTimeExt.getYMDHms(ZonedDateTimeExt.atStartOfDay(adjusted)) == LocalDateTimeExt.getYMDHms(LocalDateTimeExt.atStartOfDay(now))
            assert ZonedDateTimeExt.getYMDHms(ZonedDateTimeExt.atEndOfDay(adjusted)) == LocalDateTimeExt.getYMDHms(LocalDateTimeExt.atEndOfDay(now))
            assert LocalDateExt.atEndOfDay(now.toLocalDate(), ZoneOffset.UTC) == ZonedDateTimeExt.atEndOfDay(zdt)
    }

    def "YMD and is between"() {
        setup:
            ZonedDateTime zdt = StringExt.toDateTime("2001-02-03 12:34:56").atZone(ZoneOffset.UTC)
            ZonedDateTime min = StringExt.toDateTime("2001-01-01 00:00:00").atZone(ZoneOffset.UTC)
            ZonedDateTime max = StringExt.toDateTime("2001-12-31 23:59:59").atZone(ZoneOffset.UTC)
            ZonedDateTime notMin = StringExt.toDateTime("2002-01-01 00:00:00").atZone(ZoneOffset.UTC)
            ZonedDateTime notMax = StringExt.toDateTime("2002-12-31 23:59:59").atZone(ZoneOffset.UTC)
        expect:
            assert ZonedDateTimeExt.getYMD(zdt, "/") == "2001/02/03"
            assert ZonedDateTimeExt.isBetween(zdt, min, max)
            assert ! ZonedDateTimeExt.isBetween(zdt, notMin, notMax)
    }

    def "From and to millis / seconds"() {
        setup:
            long millis = System.currentTimeMillis()
            ZonedDateTime mill = ZonedDateTimeStaticExt.fromMillis(null, millis, ZoneOffset.UTC)
            ZonedDateTime secs = ZonedDateTimeStaticExt.fromSeconds(null, (millis / 1000d).round(), ZoneOffset.UTC)
            ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC)
        expect:
            assert Math.abs(ChronoUnit.SECONDS.between(now, mill)) <= 1
            assert Math.abs(ZonedDateTimeExt.toSeconds(secs) - ZonedDateTimeExt.toSeconds(now)) < 1000
            assert ZonedDateTimeExt.toMillis(mill) == millis
    }
}
