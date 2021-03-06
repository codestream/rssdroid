package io.github.rssdroid.ui.feed;

import android.app.Activity;
import android.app.ProgressDialog;
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

import java.util.LinkedList;

import io.github.rssdroid.R;
import io.github.rssdroid.database.FeedDatabaseHelperAdapter;
import io.github.rssdroid.model.FeedUrl;

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
                TextView textView = (TextView) view.findViewById(R.id.text_feed_url_item);
                String url = textView.getText().toString();
                Bundle bundle = new Bundle();
                bundle.putString(getString(R.string.text_bundle_feed_url), url);
                Intent intent = new Intent(getActivity(), FeedContentActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    protected class FeedUrlDetailsTask extends AsyncTask<Void, Integer, LinkedList<FeedUrl>> {
        private ProgressDialog progressDialog;

        private FeedUrlDetailsTask(Context context){
            feedDatabaseHelperAdapter = new FeedDatabaseHelperAdapter(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(getActivity());
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage(getString(R.string.text_pd_addition));
            progressDialog.show();
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
            if(progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            FeedUrlAdapter feedUrlAdapter = new FeedUrlAdapter(getActivity(), feedUrls);
            mListView.setAdapter(feedUrlAdapter);
            mListView.setScrollContainer(true);
            feedUrlAdapter.notifyDataSetChanged();
        }
    }
}
