package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime

/**
 * Extensions to ZonedDateTime, ZonedDate and ZonedTime
 * @since 2023-05-23
 */
@CompileStatic
class ZonedDateTimeStaticExt {
    /**
     * Convert millis to ZonedDateTime with default Zone
     * @param millis
     * @return
     */
    static ZonedDateTime fromMillis(final ZonedDateTime self, long millis, ZoneId zoneId = ZoneId.systemDefault()) {
        return ZonedDateTime.ofInstant(Instant.ofEpochMilli(millis), zoneId)
    }
    /**
     * Convert seconds to LocalDateTime with Zone (optional)
     * @param self
     * @param seconds
     * @param zoneId
     * @return
     */
    static ZonedDateTime fromSeconds(final ZonedDateTime self, long seconds, ZoneId zoneId = ZoneId.systemDefault()) {
        return ZonedDateTime.ofInstant(Instant.ofEpochSecond(seconds), zoneId)
    }
}
