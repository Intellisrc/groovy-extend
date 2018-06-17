package jp.sharelock.groovy

import java.time.LocalDate
import java.time.format.DateTimeFormatter

/**
 * Static Extensions to LocalDate
 * NOTE: Instance and Static methods should be defined separately:
 * see: http://docs.groovy-lang.org/next/html/documentation/core-metaprogramming.html#_extending_existing_classes
 * @since 18/06/17.
 */
class LocalDateStaticExt {
    /**
     * Shortcut for parsing Strings to LocalDate
     * @param dateTime
     * @param format
     * @param self
     * @return
     */
    static LocalDate parseStr(final String dateTime, final String format = "yyyy-MM-dd", final LocalDate self) {
        return LocalDate.parse(dateTime, DateTimeFormatter.ofPattern(format))
    }
}
