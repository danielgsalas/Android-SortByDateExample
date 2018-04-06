package com.appstoremarketresearch.android_sortbydateexample.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.appstoremarketresearch.android_sortbydateexample.R;
import com.appstoremarketresearch.android_sortbydateexample.model.ContentFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DateListFragment extends Fragment {

    private boolean mSortAscending = true;
    private int mColumnCount = 1;

    private List<Date> mDates;
    private OnListFragmentInteractionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DateListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.date_list_fragment, container, false);
        RecyclerView.Adapter adapter = initializeRecyclerView(view);
        initializeSortButtons(view, adapter);

        return view;
    }

    /**
     * initializeRecyclerView
     */
    private RecyclerView.Adapter initializeRecyclerView(View view) {

        Context context = view.getContext();
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);

        if (mColumnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        OnListFragmentInteractionListener mListener = (OnListFragmentInteractionListener)getActivity();
        mDates = ContentFactory.buildList();
        RecyclerView.Adapter adapter = new DateListAdapter(mDates, mListener);
        recyclerView.setAdapter(adapter);

        return adapter;
    }

    /**
     * initializeSortButtons
     */
    private void initializeSortButtons(
        View view,
        final RecyclerView.Adapter adapter) {

        Button button = (Button)view.findViewById(R.id.button_sort_by_date);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comparator comparator = new DateComparator(mSortAscending);
                sortDates(comparator, adapter);

                mSortAscending = !mSortAscending;
            }
        });

        button = (Button)view.findViewById(R.id.button_sort_by_millis);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comparator comparator = new MillisComparator(mSortAscending);
                sortDates(comparator, adapter);

                mSortAscending = !mSortAscending;
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * sortDates
     */
    private void sortDates(
        final Comparator comparator,
        final RecyclerView.Adapter adapter) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground( final Void ... params ) {
                Collections.sort(mDates, comparator);
                return null;
            }

            @Override
            protected void onPostExecute( final Void result ) {
                // update the master list
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }
}
