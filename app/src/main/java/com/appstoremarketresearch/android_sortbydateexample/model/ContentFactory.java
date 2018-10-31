package com.appstoremarketresearch.android_sortbydateexample.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 */
public class ContentFactory {

    private static final long MILLIS_PER_SECOND = 1000;
    private static final long SECONDS_PER_MINUTE = 60;
    private static final long MINUTES_PER_HOUR = 60;
    private static final long HOURS_PER_DAY = 24;

    private static final long MILLIS_PER_DAY =
        HOURS_PER_DAY * MINUTES_PER_HOUR * SECONDS_PER_MINUTE * MILLIS_PER_SECOND;

    /**
     * buildMillisecondsList
     */
    private static List<Long> buildMillisecondsList() {

        long today = System.currentTimeMillis();
        long yesterday = today - MILLIS_PER_DAY;
        long tomorrow = today + MILLIS_PER_DAY;
        long oneYearAgo = today - MILLIS_PER_DAY * 365L;
        long tenYearsAgo = today - MILLIS_PER_DAY * 365L * 10L;

        List<Long> millis = new ArrayList<>();
        millis.add(today);
        millis.add(yesterday);
        millis.add(tomorrow);
        millis.add(oneYearAgo);
        millis.add(tenYearsAgo);

        return millis;
    }

    /**
     * buildJavaSqlDateList
     */
    public static List<Object> buildJavaSqlDateList() {

        List<Object> dates = new ArrayList<>();
        List<Long> millis = buildMillisecondsList();

        for (Long m : millis) {
            dates.add(new java.sql.Date(m));
        }

        return dates;
    }

    /**
     * buildJavaUtilDateList
     */
    public static List<Object> buildJavaUtilDateList() {

        List<Object> dates = new ArrayList<>();
        List<Long> millis = buildMillisecondsList();

        for (Long m : millis) {
            dates.add(new java.util.Date(m));
        }

        return dates;
    }
}
