package jp.sharelock.groovy

import groovy.transform.CompileStatic

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

/**
 * @since 18/06/15.
 */
@CompileStatic
class LocalDateExt {
    /**
     * Convert LocalDateTime to Date
     * @param self
     * @return
     */
    static Date toDate(final LocalDateTime self) {
        return Date.from(self.atZone(ZoneId.systemDefault()).toInstant())
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
     * Converts a LocalDate to String using DateTimeFormatter
     * @param self
     * @param dateFormat
     * @return
     */
    static String format(final LocalDate self, final String format) {
        return self.format(DateTimeFormatter.ofPattern(format))
    }
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
     * Converts a LocalDateTime to standard style:  "yyyy-MM-dd HH:mm:ss"
     * @param self
     * @return
     */
    static String YMDHms(final LocalDateTime self, String separatorDate = '-', String separator = ' ', String separatorTime = ':') {
        format(self, "yyyy${separatorDate}MM${separatorDate}dd${separator}HH${separatorTime}mm${separatorTime}ss")
    }
    /**
     * Converts a LocalDateTime to standard style:  "yyyy-MM-dd HH:mm"
     * @param self
     * @return
     */
    static String YMDHm(final LocalDateTime self, String separatorDate = '-', String separator = ' ', String separatorTime = ':') {
        format(self, "yyyy${separatorDate}MM${separatorDate}dd${separator}HH${separatorTime}mm")
    }
    /**
     * Converts a LocalDate to standard style:  "yyyy-MM-dd" without time
     * @param self
     * @return
     */
    static String YMD(final LocalDate self, String separator = '-') {
        format(self, "yyyy${separator}MM${separator}dd")
    }
    /**
     * Converts a LocalDate to standard style:  "yy" without time
     * @param self
     * @return
     */
    static String YY(final LocalDate self) {
        format(self, "yy")
    }
    /**
     * Converts a LocalDate to standard style:  "MM" without time
     * @param self
     * @return
     */
    static String MM(final LocalDate self) {
        format(self, "MM")
    }
    /**
     * Converts a LocalDate to standard style:  "dd" without time
     * @param self
     * @return
     */
    static String DD(final LocalDate self) {
        format(self, "dd")
    }
    /**
     * Converts a LocalTime to standard style:  "HH"
     * @param self
     * @return
     */
    static String HH(final LocalTime self) {
        format(self, "HH")
    }
    /**
     * Converts a LocalTime to standard style:  "HHmm"
     * @param self
     * @return
     */
    static String HHmm(final LocalTime self, String separator = ':') {
        format(self, "HH${separator}mm")
    }
    /**
     * Converts a LocalTime to standard style:  "HHmmss"
     * @param self
     * @return
     */
    static String HHmmss(final LocalTime self, String separator = ':') {
        format(self, "HH${separator}mm${separator}ss")
    }
}
