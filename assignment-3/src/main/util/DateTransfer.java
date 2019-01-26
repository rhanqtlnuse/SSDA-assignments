package main.util;

import java.util.Date;

public class DateTransfer {

    public static Date before(Date d, int days) {
        if (d == null) {
            throw new IllegalArgumentException("d: null");
        }
        if (days < 0) {
            throw new IllegalArgumentException("days < 0");
        }
        return new Date(d.getTime() - daysToMillis(days));
    }

    public static Date after(Date d, int days) {
        if (d == null) {
            throw new IllegalArgumentException("d: null");
        }
        if (days < 0) {
            throw new IllegalArgumentException("days < 0");
        }
        return new Date(d.getTime() + daysToMillis(days));
    }

    private static long daysToMillis(long days) {
        return days * 24 * 60 * 60 * 1000;
    }

}
