package jp.sharelock.groovy

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Static Extensions to LocalDateTime
 * NOTE: Instance and Static methods should be defined separately:
 * see: http://docs.groovy-lang.org/next/html/documentation/core-metaprogramming.html#_extending_existing_classes
 * @since 18/06/17.
 */
class LocalDateTimeStaticExt {
    /**
     * Shortcut for parsing Strings to LocalDateTime
     * @param dateTime
     * @param format
     * @param self
     * @return
     */
    static LocalDateTime parse(final String dateTime, final String format, final LocalDateTime self) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(format))
    }
    /**
     * Boolean parameter was added to make a distinction with the original parse() method.
     * @param dateTime
     * @param auto
     * @param self
     * @return
     */
    static LocalDateTime parse(final String dateTime, boolean auto, final LocalDateTime self) {
        return auto ? parse(dateTime, "yyyy-MM-dd HH:mm:ss", self) : LocalDateTime.parse(dateTime)
    }
}
