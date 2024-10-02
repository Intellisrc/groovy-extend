package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.time.*
import java.time.chrono.ChronoLocalDate
import java.time.format.DateTimeFormatter

/**
 * Extensions to LocalDate
 * @since 18/06/15.
 */
@CompileStatic
class LocalDateExt {
    /**
     * Convert LocalDate to LocalDateTime (at 00:00:00)
     * @param self
     * @return
     */
    static LocalDateTime toDateTime(final LocalDate self) {
        return LocalDateTime.of(self, LocalTime.MIN)
    }
    /**
     * Converts a LocalDate to String using DateTimeFormatter
     * @param self
     * @param dateFormat
     * @return
     */
    static String format(final LocalDate self, final String format) {
        return self.format(DateTimeFormatter.ofPattern(format))
    }
    /**
     * Converts a LocalDate to standard style:  "yyyy-MM-dd" without time
     * @param self
     * @return
     */
    static String getYMD(final LocalDate self, String separator = '-') {
        format(self, "yyyy${separator}MM${separator}dd")
    }
    /**
     * Converts a LocalDate to standard style:  "yy" without time
     * @param self
     * @return
     */
    static String getYY(final LocalDate self) {
        format(self, "yy")
    }
    /**
     * Converts a LocalDate to standard style:  "MM" without time
     * @param self
     * @return
     */
    static String getMM(final LocalDate self) {
        format(self, "MM")
    }
    /**
     * Converts a LocalDate to standard style:  "dd" without time
     * @param self
     * @return
     */
    static String getDD(final LocalDate self) {
        format(self, "dd")
    }
    /**
     * Next day
     * @param self
     * @return
     */
    static LocalDate next(final LocalDate self) {
        return self.plusDays(1)
    }
    /**
     * Previous day
     * @param self
     * @return
     */
    static LocalDate previous(final LocalDate self) {
        return self.minusDays(1)
    }
    /**
     * Returns true if date is between two dates (inclusive or exclusive)
     * @param self
     * @param from
     * @param to
     * @param inclusive (optional, default: true)
     * @return
     */
    static boolean isBetween(final LocalDate self, ChronoLocalDate from, ChronoLocalDate to, boolean inclusive = true) {
        boolean equal = inclusive ? (self.isEqual(from) || self.isEqual(to)) : false
        return (self.isAfter(from) && self.isBefore(to)) || equal
    }
    /**
     * Return the last LocalDateTime of that date
     * @param self
     * @return
     */
    static LocalDateTime atEndOfDay(final LocalDate self) {
        return LocalDateTime.of(self, LocalTime.MAX)
    }
    /**
     * Return the last DateTime of a date using a specific zone id
     * @param self
     * @param zoneId
     * @return
     */
    static ZonedDateTime atEndOfDay(final LocalDate self, ZoneId zoneId) {
        return LocalDateTime.of(self, LocalTime.MAX).atZone(zoneId)
    }
}
