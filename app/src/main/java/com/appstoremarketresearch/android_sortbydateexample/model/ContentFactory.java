package com.appstoremarketresearch.android_sortbydateexample.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class ContentFactory {

    public static List<Date> buildList() {
        long millisPerSecond = 1000;
        long secondsPerMinute = 60;
        long minutesPerHour = 60;
        long hoursPerDay = 24;
        long millisPerDay = hoursPerDay * minutesPerHour * secondsPerMinute * millisPerSecond;

        long today = System.currentTimeMillis();
        long yesterday = today - millisPerDay;
        long tomorrow = today + millisPerDay;
        long oneYearAgo = today - millisPerDay * 365L;
        long tenYearsAgo = today - millisPerDay * 365L * 10L;

        List<Date> dates = new ArrayList<>();
        dates.add(new Date(today));
        dates.add(new Date(yesterday));
        dates.add(new Date(tenYearsAgo));
        dates.add(new Date(tomorrow));
        dates.add(new Date(oneYearAgo));

        return dates;
    }
}
