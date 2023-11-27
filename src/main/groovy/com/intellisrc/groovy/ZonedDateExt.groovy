package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.time.ZonedDateTime
import java.time.chrono.ChronoZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Extensions to ZonedDateTime, ZonedDate and ZonedTime
 * @since 2023-05-23
 */
@CompileStatic
class ZonedDateExt {
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
}
