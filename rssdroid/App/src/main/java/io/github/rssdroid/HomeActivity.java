package io.github.rssdroid;

import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.github.rssdroid.model.DrawerItem;
import io.github.rssdroid.ui.feed.AddFeedFragment;
import io.github.rssdroid.ui.menu.DrawerListAdapter;

public class HomeActivity extends FragmentActivity {
    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @InjectView(R.id.list_slidermenu)
    ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;

    private CharSequence mTitle;

    private String[] navMenuTitles;
    private TypedArray navMenuIcons;

    private ArrayList<DrawerItem> navDrawerItems;
    private DrawerListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mTitle = mDrawerTitle = getTitle();
        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        navDrawerItems = new ArrayList<DrawerItem>();

        navDrawerItems.add(new DrawerItem(navMenuTitles[0]));
        navDrawerItems.add(new DrawerItem(navMenuTitles[1], true, "22"));
        navDrawerItems.add(new DrawerItem(navMenuTitles[2]));
        navDrawerItems.add(new DrawerItem(navMenuTitles[3]));
        navDrawerItems.add(new DrawerItem(navMenuTitles[4]));

        mDrawerList.setOnItemClickListener(new DrawerClickListener());

        adapter = new DrawerListAdapter(getApplicationContext(),
                navDrawerItems);
        mDrawerList.setAdapter(adapter);

        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.drawable.ic_drawer,
                R.string.app_name,
                R.string.app_name
            ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu();
            }
        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    private class DrawerClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            displayView(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayView(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                showAddFeedDialog();
                break;
            case 1:
                Toast.makeText(getBaseContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
                break;
            case 2:
                Toast.makeText(getBaseContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
                break;
            case 3:
                Toast.makeText(getBaseContext(), Integer.toString(position), Toast.LENGTH_LONG).show();
                break;
            case 4:

            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.frame_container, fragment).commit();


            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(navMenuTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);
        } else {
            Log.e("HomeActivity", "Error in creating fragment");
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    void showAddFeedDialog(){
        final FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        final Fragment fragment = getFragmentManager().findFragmentByTag("dialog");
        if(fragment != null){
            fragmentTransaction.remove(fragment);
        }

        fragmentTransaction.addToBackStack(null);

        DialogFragment dialogFragment = AddFeedFragment.newInstance();
        dialogFragment.show(fragmentTransaction, "dialog");
    }
}
