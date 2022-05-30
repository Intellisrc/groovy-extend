package com.intellisrc.groovy

import groovy.transform.CompileStatic

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

/**
 * @since 2/19/17.
 */
@CompileStatic
class StringExt {
    static String alphaNumeric(final String self) {
        self =~ /w+/
    }

    static String padRight(final String self, final int n) {
        return String.format('%1$-' + n + 's', self.toString())
    }

    static String padLeft(final String self, final int n) {
        return String.format('%1$' + n + 's', self.toString())
    }

    /**
     * Insert string at specific position
     * @param self
     * @param position
     * @param insert
     * @return
     */ // https://stackoverflow.com/a/46022277/196507
    static String insertAt(final String self, final int position, final String insert) {
        if (insert.isEmpty()) {  return self }
        final int targetLen = self.length()
        if (position < 0 || position > targetLen) {
            throw new IllegalArgumentException("position=${position}")
        } else if (position == 0) {
            return insert.concat(self)
        } else if (position == targetLen) {
            return self.concat(insert)
        }
        final int insertLen = insert.length()
        final char[] buffer = new char[targetLen + insertLen]
        self.getChars(0, position, buffer, 0)
        insert.getChars(0, insertLen, buffer, position)
        self.getChars(position, targetLen, buffer, position + insertLen)
        return new String(buffer)
    }

    ////////////// Conversion to other types //////////////

    static Inet4Address toInet4Address(final String self) throws UnknownHostException {
        return (Inet4Address) InetAddress.getByName(self)
    }

    static Inet6Address toInet6Address(final String self) throws UnknownHostException {
        return (Inet6Address) InetAddress.getByName(self)
    }

    static InetAddress toInetAddress(final String self) throws UnknownHostException  {
        return (InetAddress) InetAddress.getByName(self)
    }
    /**
     * Appends a random number to a String
     * @param str
     * @return
     */
    static appendRandomNum(final String self, int max = 9999) {
        Random rand = new Random()
        return self + rand.nextInt(max)
    }

    /**
     * Convert query to Map
     * @param self
     * @return
     */
    static Map<String,Object> getQueryMap(final String self) {
        String query = self
        if(query.startsWith('?')) {
            query = query.substring(1)
        }
        return query.split('&').inject([:] as Map<String,Object>) {
            Map<String,Object> map, String kv ->
                def parts = kv.split('=').toList()
                String key = parts[0]
                String val = parts[1]
                Object value = val != null ? URLDecoder.decode(val, "UTF-8") : null
                //Auto convert boolean values
                if (val.toLowerCase() == "true" || val.toLowerCase() == "false") {
                    value = val.toLowerCase() == "true"
                }
                //Auto convert numeric values
                try {
                    if (Integer.parseInt(val).toString() == val) {
                        value = Integer.parseInt(val)
                    }
                } catch (NumberFormatException e) {
                    //Ignore
                }
                map[key] = value
                return map
        }
    }
    
    static List<String> yearParsers = [
            "yyyy-MM-dd",
            "yyyy.MM.dd",
            "dd.MM.yyyy",
            "dd-MM-yyyy",
            "yyyy/MM/dd",
            "dd/MM/yyyy",
            "yyyyMMdd",
            "ddMMyyyy",
            "yy-MM-dd",
            "yy.MM.dd",
            "yy/MM/dd",
            "dd-MM-yy",
            "dd.MM.yy",
            "dd/MM/yy",
            "yyMMdd",
            "ddMMyy"
    ]
    static String optional = "['T'][' ']"
    static List<String> hourParsers = [
            "HH:mm[:ss][.SSS]",
            "H:mm[:ss][.SSS]",
            "HHmmss[.SSS]",
            "Hmmss[.SSS]",
            "HHmm[.SSS]",
            "Hmm[.SSS]"
    ]
    /**
     * Convert String to LocalDateTime
     * @param format
     * @param self
     * @return
     */
    static LocalDateTime toDateTime(final String self, final String format = null) {
        String fmt = format
        LocalDateTime dateTime
        if(fmt) {
            dateTime = LocalDateTime.parse(self, DateTimeFormatter.ofPattern(format))
        } else {
            List<String> parsers = []
            yearParsers.each {
                String yp ->
                    hourParsers.each {
                        String hp ->
                            parsers << "$yp${optional}$hp".toString()
                    }
            }
            parsers.any {
                String f ->
                    try {
                        dateTime = LocalDateTime.parse(self, DateTimeFormatter.ofPattern(f))
                    } catch(DateTimeParseException ignore) {}
                    return dateTime
            }
        }
        if(!dateTime) {
            throw new DateTimeParseException("Date can not be parsed: ", self, 0)
        }
        return dateTime
    }
    /**
     * Convert String to LocalDate
     * @param format
     * @param self
     * @return
     */
    static LocalDate toDate(final String self, final String format = null) {
        String fmt = format
        LocalDate date
        if(fmt) {
            date = LocalDate.parse(self, DateTimeFormatter.ofPattern(format))
        } else {
            List<String> parsers = []
            yearParsers.each {
                String yp ->
                    hourParsers.each {
                        String hp ->
                            parsers << "$yp${optional}[$hp]".toString()
                    }
            }
            parsers.any {
                String f ->
                    try {
                        date = LocalDate.parse(self, DateTimeFormatter.ofPattern(f))
                    } catch(DateTimeParseException ignore) {}
                    return date
            }
        }
        if(!date) {
            throw new DateTimeParseException("Date can not be parsed: ", self, 0)
        }
        return date
    }
    /**
     * Convert String to Time
     * @param format
     * @param self
     * @return
     */
    static LocalTime toTime(final String self, final String format = null) {
        String fmt = format
        LocalTime time
        if(fmt) {
            time = LocalTime.parse(self, DateTimeFormatter.ofPattern(format))
        } else {
            List<String> parsers = []
            yearParsers.each {
                String yp ->
                    hourParsers.each {
                        String hp ->
                            parsers << "[$yp]${optional}$hp".toString()
                    }
            }
            parsers.any {
                String f ->
                    try {
                        time = LocalTime.parse(self, DateTimeFormatter.ofPattern(f))
                    } catch(DateTimeParseException ignore) {}
                    return time
            }
        }
        if(!time) {
            throw new DateTimeParseException("Time can not be parsed: ", self, 0)
        }
        return time
    }
    /**
     * Convert String to snake_case
     * @param str
     * @return
     */
    static String toSnakeCase(final String self, boolean upperCase = false) {
        boolean areAllUpper = self.toUpperCase() == self
        String conv = self.replaceAll(/\s+/,'_')    // Convert spaces to "_"
                .replaceAll(/[^\w]/,"_")    // Convert any non alphanumeric char to "_"
        // Do not split words if all are uppercase
        if(! areAllUpper) {
            conv = conv.replaceAll(/([A-Z])/, /_$1/).toLowerCase() // prepend "_" to each word
        }
        conv = conv.replaceAll('__','_')       // Remove any double "_"
                   .replaceAll( /^_/, '' )     // Remove leading "_"
        return upperCase ? conv.toUpperCase() : conv
    }
    /**
     * Convert String to CamelCase
     * @param str
     * @param capitalized
     * @return
     */
    static String toCamelCase(final String self, boolean capitalized = false) {
        String conv = self.toLowerCase()
                .replaceAll(/^_/, "")       // Remove leading "_"
                .replaceAll(/[^\w]/,"_")    // Convert any non alphanumeric char to "_"
                .replaceAll( "(_)([A-Za-z0-9])", { List<String> it -> it[2].toUpperCase() } ) // Capitalize words
        return capitalized ? conv.capitalize() : conv
    }
    /**
     * Converts Strings to "dot.case":
     * example: VARIABLE_NAME to variable.name
     *
     * @param self
     * @return
     */
    static String toDotCase(final String self, boolean lowerCase = true) {
        String conv = toSnakeCase(self).replaceAll('_', '.')
        return lowerCase ? conv.toLowerCase() : conv
    }

    /**
     * Replace a phrase for kebab-case
     * @param self
     * @return
     */
    static String toKebabCase(final String self, boolean upperCase = false) {
        return toSnakeCase(self, upperCase).replaceAll('_', '-')
    }
}
