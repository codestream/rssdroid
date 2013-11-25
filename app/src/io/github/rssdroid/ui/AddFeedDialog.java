package io.github.rssdroid.ui;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import io.github.rssdroid.R;

public class AddFeedDialog extends DialogFragment {

    public static AddFeedDialog newInstance(){
        return new AddFeedDialog();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AddFeedDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_feed_dialog, container, false);
        final ViewHolder holder = new ViewHolder();
        holder.feedDescriptionField = (EditText) view.findViewById(R.id.edit_feed_description);
        holder.feedUrlField = (EditText) view.findViewById(R.id.edit_feed_url);
        holder.addFeedButton = (Button) view.findViewById(R.id.btn_add_new_feed);
        holder.cancel = (Button) view.findViewById(R.id.btn_cancel);

        getDialog().setTitle(getString(R.string.text_add_dialog_title));

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        holder.addFeedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(holder.feedUrlField.getText())){
                    Toast toast = Toast.makeText(getActivity(), getString(R.string.text_toast_empty_feed_url),
                            Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    toast.show();
                } else if(TextUtils.isEmpty(holder.feedDescriptionField.getText())){
                    Toast feedDescriptionToast = Toast.makeText(getActivity(), getString(R.string.text_toast_empty_feed_description),
                            Toast.LENGTH_LONG);
                    feedDescriptionToast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                    feedDescriptionToast.show();
                } else {
                    //insert feed url and description into database
                }
            }
        });

        return view;
    }

    private class ViewHolder {
        EditText feedUrlField;
        EditText feedDescriptionField;
        Button addFeedButton;
        Button cancel;
    }
}
