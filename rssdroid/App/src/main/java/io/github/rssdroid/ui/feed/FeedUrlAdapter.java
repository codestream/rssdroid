package io.github.rssdroid.ui.feed;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.LinkedList;

import io.github.rssdroid.R;
import io.github.rssdroid.model.FeedUrl;

public class FeedUrlAdapter extends ArrayAdapter {
    private final Context context;
    private LinkedList<FeedUrl> feedUrls;

    public FeedUrlAdapter(Context context, LinkedList<FeedUrl> feedUrls){
        super(context, R.layout.feed_list_items, feedUrls);
        this.context = context;
        this.feedUrls = feedUrls;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.feed_list_items, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mFeedUrl = (TextView) view.findViewById(R.id.text_feed_url_item);
            viewHolder.mFeedDescription = (TextView) view.findViewById(R.id.text_feed_description_item);

            String feedUrl = feedUrls.get(position).getFeedUrl();
            String feedDescription = feedUrls.get(position).getFeedDescription();

            viewHolder.mFeedUrl.setText(feedUrl);
            viewHolder.mFeedDescription.setText(feedDescription);
        }

        return view;
    }

    private class ViewHolder {
        TextView mFeedUrl;
        TextView mFeedDescription;
    }
}
