package com.appstoremarketresearch.android_sortbydateexample.comparator;

import java.util.Comparator;
import java.util.Date;

/**
 * MillisComparator
 */
public class MillisComparator implements Comparator<Date> {

    private boolean mSortAscending;

    /**
     * MillisComparator
     */
    public MillisComparator(boolean ascending) {
        mSortAscending = ascending;
    }

    @Override
    public int compare(Date dateOne, Date dateTwo) {

        long difference = 0;

        if (dateOne != null && dateTwo != null) {

            long millisOne = dateOne.getTime();
            long millisTwo = dateTwo.getTime();

            if (mSortAscending) {
                difference = millisOne - millisTwo;
            }
            else {
                difference = millisTwo - millisOne;
            }

            if (difference > Integer.MAX_VALUE) {
                difference = 1;
            }
            else if (difference < Integer.MIN_VALUE) {
                difference = -1;
            }
        }

        return (int)difference;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MillisComparator;
    }
}
