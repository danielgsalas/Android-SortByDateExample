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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.appstoremarketresearch.android_sortbydateexample.R;
import com.appstoremarketresearch.android_sortbydateexample.comparator.DateComparator;
import com.appstoremarketresearch.android_sortbydateexample.comparator.MillisComparator;
import com.appstoremarketresearch.android_sortbydateexample.model.ContentFactory;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A fragment representing a list of Items.
 *
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class DateListFragment extends Fragment {

    private boolean mSortAscending = true;
    private int mColumnCount = 1;

    private List<Object> mDates;
    private OnListFragmentInteractionListener mListener;
    private Spinner mSpinner;
    private RecyclerView mRecyclerView;
    private View mTopLevelView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DateListFragment() {
    }

    @Override
    public View onCreateView(
        LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState) {

        mTopLevelView = inflater.inflate(R.layout.date_list_fragment, container, false);
        mSpinner = initializeDateTypeOptions(mTopLevelView);

        RecyclerView.Adapter adapter = initializeRecyclerView(mTopLevelView);
        initializeSortButtons(mTopLevelView, adapter);

        return mTopLevelView;
    }

    /**
     * https://developer.android.com/guide/topics/ui/controls/spinner
     */
    private Spinner initializeDateTypeOptions(final View topLevelView) {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(),
                R.array.date_type_options,
                android.R.layout.simple_spinner_item);

        Spinner spinner = topLevelView.findViewById(R.id.date_type_spinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        return spinner;
    }

    /**
     * initializeRecyclerView
     */
    private RecyclerView.Adapter initializeRecyclerView(View topLevelView) {

        Context context = topLevelView.getContext();
        mRecyclerView = topLevelView.findViewById(R.id.recycler_view);

        if (mColumnCount <= 1) {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
        }

        int startPosition = 0;
        return initializeRecyclerView(mRecyclerView, startPosition);
    }

    /**
     * initializeRecyclerView
     */
    private RecyclerView.Adapter initializeRecyclerView(RecyclerView recyclerView, int position) {

        switch(position) {
            case 0:
                mDates = ContentFactory.buildJavaUtilDateList();
                break;

            case 1:
                mDates = ContentFactory.buildJavaSqlDateList();
                break;
        }

        OnListFragmentInteractionListener mListener = (OnListFragmentInteractionListener)getActivity();
        RecyclerView.Adapter adapter = new DateListAdapter(mDates, mListener);
        recyclerView.setAdapter(adapter);

        return adapter;
    }

    /**
     * initializeSortButtons
     */
    private void initializeSortButtons(
        View topLevelView,
        final RecyclerView.Adapter adapter) {

        Button button = topLevelView.findViewById(R.id.sort_by_date_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Comparator comparator = new DateComparator(mSortAscending);
                sortDates(comparator, adapter);

                mSortAscending = !mSortAscending;
            }
        });

        button = topLevelView.findViewById(R.id.sort_by_millis_button);

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

    @Override
    public void onResume() {
        super.onResume();

        if (mSpinner != null && mRecyclerView != null &&
            mSpinner.getOnItemClickListener() == null) {

            mSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView,
                                           View view, int position, long id) {

                    RecyclerView.Adapter adapter = initializeRecyclerView(mRecyclerView, position);
                    initializeSortButtons(mTopLevelView, adapter);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {
                    // NO OP
                }
            });
        }
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
