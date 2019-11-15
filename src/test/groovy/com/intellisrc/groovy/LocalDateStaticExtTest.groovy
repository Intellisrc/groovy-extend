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
}