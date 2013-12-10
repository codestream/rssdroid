package io.github.rssdroid.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import io.github.rssdroid.R;
import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FeedContentFragment extends Fragment {
    private String mFeedUrl;
    private ListView mListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        new FeedParserTask(getActivity()).execute();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        Intent intent = activity.getIntent();
        Bundle bundle = intent.getExtras();
        if(bundle != null){
            mFeedUrl = bundle.getString(getString(R.string.text_bundle_feed_url_dialog));
        } else {
            mFeedUrl = "";
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_content, container, false);
        mListView = (ListView) view.findViewById(R.id.list_feed_content);
        return view;
    }

    public class FeedParserTask extends AsyncTask<Void, Integer, ArrayList<RssItem>> {
        private Context context;
        private ProgressDialog progressDialog;

        public FeedParserTask(Context context){
            this.context = context;
            progressDialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Loading...");
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }

        @Override
        protected ArrayList<RssItem> doInBackground(Void... params) {
            List<RssItem> rssItemList = new ArrayList<RssItem>();
            try {
                URL url = new URL(mFeedUrl);
                try {
                    RssFeed feed = RssReader.read(url);
                    ArrayList<RssItem> rssItems = feed.getRssItems();
                    for(RssItem rssItem : rssItems) {
                        RssItem item = new RssItem();
                        String rssTitle = rssItem.getTitle();
                        String rssDescription = rssItem.getDescription();
                        item.setTitle(rssTitle);
                        item.setDescription(rssDescription);
                        rssItemList.add(item);
                    }
                } catch (SAXException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            return (ArrayList<RssItem>) rssItemList;
        }

        @Override
        protected void onPostExecute(ArrayList<RssItem> rssItems) {
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
            FeedContentAdapter adapter = new FeedContentAdapter(getActivity(), rssItems);
            mListView.setAdapter(adapter);
            mListView.setScrollContainer(true);
            adapter.notifyDataSetChanged();
        }
    }
}
