package com.intellisrc.groovy

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@groovy.transform.CompileStatic
/**
 * @since 2/19/17.
 */
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
    ////////////// Conversion to other types //////////////

    static Inet4Address toInet4Address(final String self) throws UnknownHostException {
        return (Inet4Address) InetAddress.newInstance().getByName(self)
    }

    static Inet6Address toInet6Address(final String self) throws UnknownHostException {
        return (Inet6Address) InetAddress.newInstance().getByName(self)
    }

    static InetAddress toInetAddress(final String self) throws UnknownHostException  {
        return (Inet4Address) InetAddress.newInstance().getByName(self)
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
    /**
     * Convert String to LocalDateTime
     * @param format
     * @param self
     * @return
     */
    static LocalDateTime toDateTime(final String format = "yyyy-MM-dd['T'][' '][HH:mm][:ss][.SSS]", final String self) {
        return LocalDateTime.parse(self, DateTimeFormatter.ofPattern(format))
    }
    /**
     * Convert String to LocalDate
     * @param format
     * @param self
     * @return
     */
    static LocalDate toDate(final String format = "yyyy-MM-dd['T'][' '][HH:mm][:ss][.SSS]", final String self) {
        return LocalDate.parse(self, DateTimeFormatter.ofPattern(format))
    }
    /**
     * Convert String to Time
     * @param format
     * @param self
     * @return
     */
    static LocalTime toTime(final String format = "[yyyy-MM-dd]['T'][' ']HH:mm[:ss][.SSS]", final String self) {
        return LocalTime.parse(self, DateTimeFormatter.ofPattern(format))
    }
}
