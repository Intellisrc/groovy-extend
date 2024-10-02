package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * @since 2019/10/07.
 */
@CompileStatic
class LocalDateTimeStaticExt {
    /**
     * Convert millis to LocalDateTime with Zone (optional)
     * @param millis
     * @return
     */
    static LocalDateTime fromMillis(final LocalDateTime self, long millis, ZoneId zoneId = ZoneId.systemDefault()) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), zoneId)
    }
    /**
     * Convert seconds to LocalDateTime with Zone (optional)
     * @param self
     * @param seconds
     * @param zoneId
     * @return
     */
    static LocalDateTime fromSeconds(final LocalDateTime self, long seconds, ZoneId zoneId = ZoneId.systemDefault()) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), zoneId)
    }
}
