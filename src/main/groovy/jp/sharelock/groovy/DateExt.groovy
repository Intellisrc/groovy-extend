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
    static String toStringSTD(final Date self) {
        toString(self, "yyyy-MM-dd HH:mm:ss")
    }
}
