package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.chrono.ChronoLocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Extensions to LocalDateTime
 * @since 18/06/15.
 */
@CompileStatic
class LocalDateTimeExt {
    /**
     * Convert LocalDateTime to Date
     * @param self
     * @return
     */
    static Date toDate(final LocalDateTime self) {
        return Date.from(self.atZone(ZoneId.systemDefault()).toInstant())
    }
    /**
     * Convert LocalDateTime to millis
     * @param self
     * @return
     */
    static long toMillis(final LocalDateTime self, ZoneId zoneId = ZoneId.systemDefault()) {
        return self.atZone(zoneId).toInstant().toEpochMilli()
    }
    /**
     * Converts a LocalDateTime to String using DateTimeFormatter
     * @param self
     * @param dateFormat
     * @return
     */
    static String format(final LocalDateTime self, final String format) {
        return self.format(DateTimeFormatter.ofPattern(format))
    }
    /**
     * Converts a LocalDateTime to standard style:  "yyyy-MM-dd HH:mm:ss"
     * @param self
     * @return
     */
    static String getYMDHms(final LocalDateTime self, String separatorDate = '-', String separator = ' ', String separatorTime = ':') {
        format(self, "yyyy${separatorDate}MM${separatorDate}dd${separator}HH${separatorTime}mm${separatorTime}ss")
    }
    /**
     * Converts a LocalDateTime to standard style:  "yyyy-MM-dd HH:mm:ss.SSS"
     * @param self
     * @return
     */
    static String getYMDHmsS(final LocalDateTime self, String separatorDate = '-', String separator = ' ', String separatorTime = ':') {
        format(self, "yyyy${separatorDate}MM${separatorDate}dd${separator}HH${separatorTime}mm${separatorTime}ss.SSS")
    }
    /**
     * Converts a LocalDateTime to standard style:  "yyyy-MM-dd HH:mm"
     * @param self
     * @return
     */
    static String getYMDHm(final LocalDateTime self, String separatorDate = '-', String separator = ' ', String separatorTime = ':') {
        format(self, "yyyy${separatorDate}MM${separatorDate}dd${separator}HH${separatorTime}mm")
    }
    /**
     * Returns true if date is between two datetimes (inclusive or exclusive)
     * @param self
     * @param from
     * @param to
     * @param inclusive (optional, default: true)
     * @return
     */
    static boolean isBetween(final LocalDateTime self, ChronoLocalDateTime from, ChronoLocalDateTime to, boolean inclusive = true) {
        boolean equal = inclusive ? (self.isEqual(from) || self.isEqual(to)) : false
        return (self.isAfter(from) && self.isBefore(to)) || equal
    }
}
