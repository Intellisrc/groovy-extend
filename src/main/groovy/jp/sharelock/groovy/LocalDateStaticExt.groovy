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
     * @param date
     * @param format
     * @param self
     * @return
     */
    static LocalDate parse(final String date, final String format, final LocalDate self) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(format))
    }
    /**
     * Boolean parameter was added to make a distinction with the original parse() method.
     * @param date
     * @param auto
     * @param self
     * @return
     */
    static LocalDate parse(final String date, boolean auto, final LocalDate self) {
        return auto ? parse(date, "yyyy-MM-dd", self) : LocalDate.parse(date)
    }
}
