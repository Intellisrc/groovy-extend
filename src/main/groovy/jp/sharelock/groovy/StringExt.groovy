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
    static Date toDateSTD(final String self, final boolean noTime = false) throws ParseException {
        if(noTime) {
            (self) =~ /\d{4}-\d{2}-\d{2}/
        } else {
            (self) =~ /\d{4}-\d{2}-\d{2}\s\d{2}:\d{2}:\d{2}/
        }
        toDate(self,noTime ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss")
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
}
