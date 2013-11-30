package io.github.rssdroid;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import io.github.rssdroid.database.FeedDatabaseHelperAdapter;
import io.github.rssdroid.ui.AddFeedDialog;
import io.github.rssdroid.ui.FeedActivity;

public class HomeActivity extends SherlockFragmentActivity {
    private FeedDatabaseHelperAdapter mFeedDatabaseHelperAdapter;
    private Cursor mCursor;
    private Intent mIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        getSupportActionBar().show();
        mFeedDatabaseHelperAdapter = new FeedDatabaseHelperAdapter(this);
        mCursor = mFeedDatabaseHelperAdapter.findFeedUrls();
        if(mCursor.getCount() != 0){
            mIntent = new Intent(HomeActivity.this, FeedActivity.class);
            startActivity(mIntent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getSupportMenuInflater();
        menuInflater.inflate(R.menu.layout_main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btn_add_feed:
                showDialog();
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
