package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

/**
 * @since 2019/10/07.
 */
@CompileStatic
class LocalDateStaticExt {
    /**
     * Convert millis to LocalDateTime with default Zone
     * @param millis
     * @return
     */
    static LocalDateTime fromMillis(final LocalDateTime self, long millis, ZoneId zoneId = ZoneId.systemDefault()) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), zoneId)
    }
}
