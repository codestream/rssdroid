package io.github.rssdroid.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import io.github.rssdroid.HomeActivity;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getSupportMenuInflater();
        menuInflater.inflate(R.menu.feed_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_add_new_feed_url:
                showDialog();
                return true;
            case R.id.btn_save_feed_content:
                Toast.makeText(this, getString(R.string.text_not_implemented_yet), Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void showDialog() {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        final Fragment fragment = getSupportFragmentManager().findFragmentByTag("dialog");
        if(fragment != null){
            transaction.remove(fragment);
        }

        transaction.addToBackStack(null);

        DialogFragment dialogFragment = AddFeedDialog.newInstance();
        dialogFragment.show(transaction, "dialog");
    }
}
