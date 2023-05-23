package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * @since 2019/10/07.
 */
@CompileStatic
class MillisToDateTimeStaticExt {
    /**
     * Convert millis to LocalDateTime with default Zone
     * @param millis
     * @return
     */
    static LocalDateTime fromMillis(final LocalDateTime self, long millis, ZoneId zoneId = ZoneId.systemDefault()) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), zoneId)
    }
    /**
     * Convert millis to ZonedDateTime with default Zone
     * @param millis
     * @return
     */
    static ZonedDateTime fromMillis(final ZonedDateTime self, long millis, ZoneId zoneId = ZoneId.systemDefault()) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), zoneId)
    }
}
