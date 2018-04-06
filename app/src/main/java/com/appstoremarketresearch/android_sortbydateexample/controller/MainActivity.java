package com.appstoremarketresearch.android_sortbydateexample.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Date;

import com.appstoremarketresearch.android_sortbydateexample.R;
import com.appstoremarketresearch.android_sortbydateexample.view.OnListFragmentInteractionListener;

public class MainActivity
    extends AppCompatActivity
    implements OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public void onListFragmentInteraction(Date item) {

    }
}
