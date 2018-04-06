package com.appstoremarketresearch.android_sortbydateexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.appstoremarketresearch.android_sortbydateexample.dummy.DummyContent;

public class MainActivity
    extends AppCompatActivity
    implements OnListFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }

    @Override
    public void onListFragmentInteraction(DummyContent.DummyItem item) {

    }
}
