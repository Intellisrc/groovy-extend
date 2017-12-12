package jp.sharelock.groovy

import java.text.DateFormat
import java.text.SimpleDateFormat

@groovy.transform.CompileStatic
/**
 * Created by lepe on 17/02/20.
 */
class DateExt {
    /**
     * Gets a date to the nearest minute
     * @param d
     * @return
     */
    static Date toNearestWholeMinute(final Date self) {
        Calendar cal = new GregorianCalendar()
        cal.setTime(self)

        if (cal.get(Calendar.SECOND) >= 30) {
            cal.add(Calendar.MINUTE, 1)
        }

        cal.set(Calendar.SECOND, 0)

        return cal.getTime()
    }

    /**
     * Gets a date to the nearest hour
     * @param d
     * @return
     */
    static Date toNearestWholeHour(final Date self) {
        Calendar cal = new GregorianCalendar()
        cal.setTime(self)

        if (cal.get(Calendar.MINUTE) >= 30) {
            cal.add(Calendar.HOUR, 1)
        }

        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)

        return cal.getTime()
    }

    /**
     * Converts a Date to String following using SimpleDateFormat
     * @param self
     * @param dateFormat
     * @return
     */
    static String toString(final Date self, final String dateFormat) {
        DateFormat df = new SimpleDateFormat(dateFormat)
        return df.format(self)
    }

    /**
     * Converts a Date to standard style:  "yyyy-MM-dd HH:mm:ss"
     * @param self
     * @param dateFormat
     * @return
     */
    static String toYMDHms(final Date self) {
        toString(self, "yyyy-MM-dd HH:mm:ss")
    }
    /**
     * Converts a Date to standard style:  "yyyy-MM-dd HH:mm"
     * @param self
     * @param dateFormat
     * @return
     */
    static String toYMDHm(final Date self) {
        toString(self, "yyyy-MM-dd HH:mm")
    }
    /**
     * Converts a Date to standard style:  "yyyy-MM-dd" without time
     * @param self
     * @param dateFormat
     * @return
     */
    static String toYMD(final Date self, String separator = '-') {
        toString(self, "yyyy"+separator+"MM"+separator+"dd")
    }
    /**
     * Converts a Date to standard style:  "yyyy" without time
     * @param self
     * @param dateFormat
     * @return
     */
    static int toYYYY(final Date self) {
        Integer.parseInt(toString(self, "yyyy"))
    }
    /**
     * Converts a Date to standard style:  "yy" without time
     * @param self
     * @param dateFormat
     * @return
     */
    static int toYY(final Date self) {
        Integer.parseInt(toString(self, "yy"))
    }
    /**
     * Converts a Date to standard style:  "MM" without time
     * @param self
     * @param dateFormat
     * @return
     */
    static int toMM(final Date self) {
        Integer.parseInt(toString(self, "MM"))
    }
    /**
     * Converts a Date to standard style:  "dd" without time
     * @param self
     * @param dateFormat
     * @return
     */
    static int toDD(final Date self) {
        Integer.parseInt(toString(self, "dd"))
    }
    /**
     * Converts a Date to standard style:  "HH"
     * @param self
     * @param dateFormat
     * @return
     */
    static int toHH(final Date self) {
        Integer.parseInt(toString(self, "HH"))
    }
}
