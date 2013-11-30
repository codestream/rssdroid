package io.github.rssdroid.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import io.github.rssdroid.R;

public class FeedActivity extends SherlockFragmentActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);
        FragmentManager manager = getSupportFragmentManager();
        FeedListFragment feedListFragment = new FeedListFragment();
        FeedContentFragment feedContentFragment = new FeedContentFragment();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.fragments_container, feedListFragment, getString(R.string.text_list_fragment_tag));
        fragmentTransaction.add(R.id.fragments_container, feedContentFragment, getString(R.string.text_list_fragment_tag));
        fragmentTransaction.commit();
    }
}
