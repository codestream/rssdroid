package io.github.rssdroid.ui.feed;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import io.github.rssdroid.R;

public class FeedActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
    }
}
