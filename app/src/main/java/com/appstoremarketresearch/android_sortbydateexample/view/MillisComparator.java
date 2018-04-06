package com.appstoremarketresearch.android_sortbydateexample.view;

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

        if (dateOne != null && dateTwo != null) {

            long millisOne = dateOne.getTime();
            long millisTwo = dateTwo.getTime();

            if (mSortAscending) {
                return (int)(millisOne - millisTwo);
            }
            else {
                return (int)(millisTwo - millisOne);
            }
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MillisComparator;
    }
}
