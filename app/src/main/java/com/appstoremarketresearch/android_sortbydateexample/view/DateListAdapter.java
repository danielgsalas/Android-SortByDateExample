package com.appstoremarketresearch.android_sortbydateexample.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.appstoremarketresearch.android_sortbydateexample.R;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display Dates and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class DateListAdapter extends RecyclerView.Adapter<DateListAdapter.ViewHolder> {

    private final List<Object> mDates;
    private final OnListFragmentInteractionListener mListener;

    public DateListAdapter(List<Object> dates,
                           OnListFragmentInteractionListener listener) {
        mDates = dates;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.date_list_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mDate = mDates.get(position);

        String text = holder.mDate.toString() + " (";
        text += holder.mDate.getClass().getName() + ")";
        holder.mContentView.setText(text);

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mDate);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDates.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mContentView;
        public Object mDate;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
