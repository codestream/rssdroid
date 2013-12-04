package io.github.rssdroid.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import io.github.rssdroid.R;
import nl.matshofman.saxrssreader.RssItem;

import java.util.ArrayList;

public class FeedContentAdapter extends ArrayAdapter{
    private Context context;
    private ArrayList<RssItem> rssItems;

    public FeedContentAdapter(Context context, ArrayList<RssItem> rssItems) {
        super(context, R.layout.feed_content_items, rssItems);
        this.context = context;
        this.rssItems = rssItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.feed_content_items, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.mTitle = (TextView) view.findViewById(R.id.text_title);
            viewHolder.mDescription = (TextView) view.findViewById(R.id.text_description);

            String feedTitle = rssItems.get(position).getTitle();
            String feedDescription = rssItems.get(position).getDescription();

            viewHolder.mTitle.setText(feedTitle);
            viewHolder.mDescription.setText(feedDescription);
        }

        return view;
    }

    private class ViewHolder {
        TextView mTitle;
        TextView mDescription;
    }
}
