package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Extensions to LocalTime
 * @since 18/06/15.
 */
@CompileStatic
class LocalTimeExt {
    /**
     * Converts a LocalTime to String using DateTimeFormatter
     * @param self
     * @param dateFormat
     * @return
     */
    static String format(final LocalTime self, final String format) {
        return self.format(DateTimeFormatter.ofPattern(format))
    }
    /**
     * Converts a LocalTime to standard style:  "HH"
     * @param self
     * @return
     */
    static String getHH(final LocalTime self) {
        format(self, "HH")
    }
    /**
     * Converts a LocalTime to standard style:  "HHmm"
     * @param self
     * @return
     */
    static String getHHmm(final LocalTime self, String separator = ':') {
        format(self, "HH${separator}mm")
    }
    /**
     * Converts a LocalTime to standard style:  "HHmmss"
     * @param self
     * @return
     */
    static String getHHmmss(final LocalTime self, String separator = ':') {
        format(self, "HH${separator}mm${separator}ss")
    }
    /**
     * Returns true if date is between two times (inclusive or exclusive)
     * @param self
     * @param from
     * @param to
     * @param inclusive (optional, default: true)
     * @return
     */
    static boolean isBetween(final LocalTime self, LocalTime from, LocalTime to, boolean inclusive = true) {
        boolean equal = inclusive ? (self == from || self == to) : false
        return (self.isAfter(from) && self.isBefore(to)) || equal
    }
}
