package jp.sharelock.groovy

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

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
        return (Inet4Address) new InetAddress().getByName(self)
    }

    static Inet6Address toInet6Address(final String self) throws UnknownHostException {
        return (Inet6Address) new InetAddress().getByName(self)
    }

    static InetAddress toInetAddress(final String self) throws UnknownHostException  {
        return (Inet4Address) new InetAddress().getByName(self)
    }
    /**
     * Converts a string date into Date
     * @param String Date
     * @return
     */
    static Date toDate(final String self, final String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format)
        return df.parse(self)
    }
    /**
     * Converts a string date: yyyy-MM-dd HH:mm:ss into Date
     * @param String Date
     * @return
     */
    static Date fromYMDHms(final String self) throws ParseException {
        if(self =~ /^\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}:\d{2}/) {
            toDate(self, "yyyy-MM-dd HH:mm:ss")
        } else {
            throw new ParseException("Unknown date format", 0)
        }
    }
    /**
     * Converts a string date: yyyy-MM-dd into Date
     * @param String Date
     * @return
     */
    static Date fromYMD(final String self) throws ParseException {
        if(self =~ /^\d{4}-\d{2}-\d{2}/) {
            return toDate(self, "yyyy-MM-dd")
        } else {
            throw new ParseException("Unknown date format", 0)
        }
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
    static Map getQueryMap(final String self) {
        String query = self
        if(query.startsWith('?')) {
            query = query.substring(1)
        }
        return query.split('&').inject([:]) {
            Map map, String kv ->
                def parts = kv.split('=').toList()
                String key = parts[0]
                String val = parts[1]
                def value = val != null ? URLDecoder.decode(val, "UTF-8") : null
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
}