package com.appstoremarketresearch.android_sortbydateexample.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class ContentFactory {

    /**
     * An array of dates.
     */
    public static final List<Date> ITEMS = new ArrayList<>();

    static {
        long millisPerSecond = 1000;
        long secondsPerMinute = 60;
        long minutesPerHour = 60;
        long totalHours = 24;
        long totalMillis = totalHours * minutesPerHour * secondsPerMinute * millisPerSecond;

        long today = System.currentTimeMillis();
        long yesterday = today - totalMillis;
        long tomorrow = today + totalMillis;

        addItem( new Date(today));
        addItem( new Date(yesterday));
        addItem( new Date(tomorrow));
    }

    private static void addItem(Date date) {
        ITEMS.add(date);
    }
}
