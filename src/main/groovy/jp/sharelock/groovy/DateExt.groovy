package jp.sharelock.groovy

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime

@groovy.transform.CompileStatic
/**
 * Created by lepe on 17/02/20.
 */
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
