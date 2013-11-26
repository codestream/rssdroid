package io.github.rssdroid.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import io.github.rssdroid.R;

public class FeedActivity extends SherlockFragmentActivity {
    private FeedContentFragment feedContentFragment;
    private FeedListFragment feedListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            feedContentFragment = new FeedContentFragment();
            feedListFragment = new FeedListFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_list, feedListFragment);
            transaction.add(R.id.fragment_content, feedContentFragment);
        }

        setContentView(R.layout.activity_feeds);
    }
}
