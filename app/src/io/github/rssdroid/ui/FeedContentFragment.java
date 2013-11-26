package io.github.rssdroid.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import io.github.rssdroid.R;

public class FeedContentFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed_content, container, false);
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.mTitle = (TextView) view.findViewById(R.id.text_title);
        return view;
    }

    private class ViewHolder {
       TextView mTitle;
    }
}
