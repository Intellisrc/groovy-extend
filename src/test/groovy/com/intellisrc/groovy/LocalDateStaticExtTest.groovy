package com.intellisrc.groovy

import spock.lang.Specification

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit


/**
 * @since 2019/10/07.
 */
class LocalDateStaticExtTest extends Specification {
    
    def "LocalDateTime from millis"() {
        setup:
            LocalDateTime now = LocalDateStaticExt.fromMillis(null, System.currentTimeMillis())
        expect:
            assert Math.abs(ChronoUnit.SECONDS.between(LocalDateTime.now(), now)) <= 1
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