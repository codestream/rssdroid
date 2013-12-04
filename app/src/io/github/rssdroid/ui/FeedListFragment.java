package io.github.rssdroid.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import io.github.rssdroid.R;
import io.github.rssdroid.database.FeedDatabaseHelperAdapter;
import io.github.rssdroid.domain.FeedUrl;

import java.util.LinkedList;

public class FeedListFragment extends Fragment {
    private ListView mListView;
    private FeedDatabaseHelperAdapter feedDatabaseHelperAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FeedUrlDetailsTask(getActivity()).execute();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_lists,  container, false);
        mListView = (ListView) view.findViewById(R.id.view_list_feeds);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), getString(R.string.text_not_implemented_yet), Toast.LENGTH_LONG).show();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    protected class FeedUrlDetailsTask extends AsyncTask<Void, Integer, LinkedList<FeedUrl>> {

        private FeedUrlDetailsTask(Context context){
            feedDatabaseHelperAdapter = new FeedDatabaseHelperAdapter(context);
        }

        @Override
        protected LinkedList<FeedUrl> doInBackground(Void... params) {
            LinkedList<FeedUrl> feedData = new LinkedList<FeedUrl>();
            Cursor cursor = feedDatabaseHelperAdapter.findFeedUrls();
            if(cursor.getCount() != 0){
                while (cursor.moveToNext()){
                    String feedUrl = cursor.getString(cursor.getColumnIndex("FEED_URL"));
                    String feedDescription = cursor.getString(cursor.getColumnIndex("FEED_DESCRIPTION"));
                    FeedUrl url = new FeedUrl();
                    url.setFeedUrl(feedUrl);
                    url.setFeedDescription(feedDescription);
                    feedData.add(url);
                }
            }

            return feedData;
        }

        @Override
        protected void onPostExecute(LinkedList<FeedUrl> feedUrls) {
            FeedUrlAdapter feedUrlAdapter = new FeedUrlAdapter(getActivity(), feedUrls);
            mListView.setAdapter(feedUrlAdapter);
            mListView.setScrollContainer(true);
            feedUrlAdapter.notifyDataSetChanged();
        }
    }
}
