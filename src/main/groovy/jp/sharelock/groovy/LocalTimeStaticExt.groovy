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
     * @param time
     * @param format
     * @param self
     * @return
     */
    static LocalTime parse(final String time, final String format, final LocalTime self) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(format))
    }
    /**
     * Boolean parameter was added to make a distinction with the original parse() method.
     * @param time
     * @param auto
     * @param self
     * @return
     */
    static LocalTime parse(final String time, boolean auto, final LocalTime self) {
        return auto ? parse(time, "HH:mm:ss", self) : LocalTime.parse(time)
    }
}
