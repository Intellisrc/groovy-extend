package jp.sharelock.groovy

import java.time.LocalTime
import java.time.format.DateTimeFormatter

/**
 * Static Extensions to LocalTime
 * NOTE: Instance and Static methods should be defined separately:
 * see: http://docs.groovy-lang.org/next/html/documentation/core-metaprogramming.html#_extending_existing_classes
 * @since 18/06/17.
 */
class LocalTimeStaticExt {
    /**
     * Shortcut for parsing Strings to LocalTime
     * @param dateTime
     * @param format
     * @param self
     * @return
     */
    static LocalTime parseStr(final String dateTime, final String format = "HH:mm:ss", final LocalTime self) {
        return LocalTime.parse(dateTime, DateTimeFormatter.ofPattern(format))
    }
}
