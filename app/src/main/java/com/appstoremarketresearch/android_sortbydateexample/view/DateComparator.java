package com.appstoremarketresearch.android_sortbydateexample.view;

import java.util.Comparator;
import java.util.Date;

/**
 * DateComparator
 */
public class DateComparator implements Comparator<Date>{

    private boolean mSortAscending;

    /**
     * DateComparator
     */
    public DateComparator(boolean ascending) {
        mSortAscending = ascending;
    }

    @Override
    public int compare(Date dateOne, Date dateTwo) {

        if (dateOne != null && dateTwo != null) {
            if (mSortAscending) {
                return dateOne.compareTo(dateTwo);
            }
            else {
                return dateOne.compareTo(dateTwo) * -1;
            }
        }

        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof DateComparator;
    }
}
