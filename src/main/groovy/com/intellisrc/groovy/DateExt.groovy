package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.time.*

/**
 * Created by lepe on 17/02/20.
 */
@CompileStatic
class DateExt {
    /**
     * Convert a Date to LocalDateTime
     * @param self
     * @return
     */
    static ZonedDateTime toZonedDateTime(final Date self, ZoneId zoneId = ZoneId.systemDefault()) {
        return self.toInstant().atZone(zoneId)
    }
    /**
     * Convert a Date to LocalDateTime
     * @param self
     * @return
     */
    static LocalDateTime toLocalDateTime(final Date self) {
        return toZonedDateTime(self).toLocalDateTime()
    }
    /**
     * Convert a Date to LocalDate
     * @param self
     * @return
     */
    static LocalDate toLocalDate(final Date self) {
        return toZonedDateTime(self).toLocalDate()
    }
    /**
     * Convert a Date to LocalDateTime
     * @param self
     * @return
     */
    static LocalTime toLocalTime(final Date self) {
        return toZonedDateTime(self).toLocalTime()
    }
}
