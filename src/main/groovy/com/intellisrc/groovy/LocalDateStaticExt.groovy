package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * @since 2019/10/07.
 */
@CompileStatic
class LocalDateStaticExt {
    /**
     * Convert millis to LocalDateTime with Zone (optional)
     * @param millis
     * @return
     */
    static LocalDate fromMillis(final LocalDate self, long millis, ZoneId zoneId = ZoneId.systemDefault()) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), zoneId).toLocalDate()
    }
    /**
     * Convert seconds to LocalDateTime with Zone (optional)
     * @param self
     * @param seconds
     * @param zoneId
     * @return
     */
    static LocalDate fromSeconds(final LocalDate self, long seconds, ZoneId zoneId = ZoneId.systemDefault()) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(seconds), zoneId).toLocalDate()
    }
}
