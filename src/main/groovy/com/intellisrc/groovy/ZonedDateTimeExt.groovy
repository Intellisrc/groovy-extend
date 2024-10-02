package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.chrono.ChronoZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

/**
 * Extensions to ZonedDateTime, ZonedDate and ZonedTime
 * @since 2023-05-23
 */
@CompileStatic
class ZonedDateTimeExt {
    /**
     * Converts a ZonedDateTime to String using DateTimeFormatter
     * @param self
     * @param dateFormat
     * @return
     */
    static String format(final ZonedDateTime self, final String format) {
        return self.format(DateTimeFormatter.ofPattern(format))
    }
    /**
     * Convert ZonedDateTime to millis
     * @param self
     * @return
     */
    static long toMillis(final ZonedDateTime self) {
        return self.toInstant().toEpochMilli()
    }
    /**
     * Convert ZonedDateTime to seconds
     * @param self
     * @return
     */
    static long toSeconds(final ZonedDateTime self) {
        return (self.toInstant().toEpochMilli() / 1000d).round() as long
    }
    /**
     * Converts a ZonedDateTime to standard style:  "yyyy-MM-dd HH:mm:ss"
     * @param self
     * @return
     */
    static String getYMD(final ZonedDateTime self, String separatorDate = '-') {
        format(self, "yyyy${separatorDate}MM${separatorDate}dd")
    }
    /**
     * Converts a ZonedDateTime to standard style:  "yyyy-MM-dd HH:mm:ss"
     * @param self
     * @return
     */
    static String getYMDHms(final ZonedDateTime self, String separatorDate = '-', String separator = ' ', String separatorTime = ':') {
        format(self, "yyyy${separatorDate}MM${separatorDate}dd${separator}HH${separatorTime}mm${separatorTime}ss")
    }
    /**
     * Converts a ZonedDateTime to standard style:  "yyyy-MM-dd HH:mm:ss.SSS"
     * @param self
     * @return
     */
    static String getYMDHmsS(final ZonedDateTime self, String separatorDate = '-', String separator = ' ', String separatorTime = ':') {
        format(self, "yyyy${separatorDate}MM${separatorDate}dd${separator}HH${separatorTime}mm${separatorTime}ss.SSS")
    }
    /**
     * Converts a ZonedDateTime to standard style:  "yyyy-MM-dd HH:mm"
     * @param self
     * @return
     */
    static String getYMDHm(final ZonedDateTime self, String separatorDate = '-', String separator = ' ', String separatorTime = ':') {
        format(self, "yyyy${separatorDate}MM${separatorDate}dd${separator}HH${separatorTime}mm")
    }
    /**
     * Returns true if date is between two zoneddatetimes (inclusive or exclusive)
     * @param self
     * @param from
     * @param to
     * @param inclusive (optional, default: true)
     * @return
     */
    static boolean isBetween(final ZonedDateTime self, ChronoZonedDateTime from, ChronoZonedDateTime to, boolean inclusive = true) {
        boolean equal = inclusive ? (self.isEqual(from) || self.isEqual(to)) : false
        return (self.isAfter(from) && self.isBefore(to)) || equal
    }
    /**
     * Reset ZonedDateTime time to the beginning of day
     * @param self
     * @return
     */
    static ZonedDateTime atStartOfDay(final ZonedDateTime self) {
        return self.truncatedTo(ChronoUnit.DAYS)
    }
    /**
     * Reset ZonedDateTime time to the end of day
     * @param self
     * @return
     */
    static ZonedDateTime atEndOfDay(final ZonedDateTime self) {
        return self.truncatedTo(ChronoUnit.DAYS).plusDays(1).minusNanos(1)
    }
}
