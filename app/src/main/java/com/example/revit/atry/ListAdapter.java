package com.example.revit.atry;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListAdapter extends ArrayAdapter<Messages>{
    private List<Messages> myMsn;
    private LayoutInflater inflater;
    private AppCompatActivity activity;

    public ListAdapter(Context context, ArrayList<Messages> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Messages item = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(R.layout.left, parent, false);
        TextView post = (TextView) convertView.findViewById(R.id.msgr);
        TextView timestamp = (TextView) convertView
                .findViewById(R.id.time);
        TextView user = (TextView) convertView
                .findViewById(R.id.userText);

       user.setText(item.getUser());
        post.setText(item.getMsn());
        timestamp.setText(item.getTimeStmp());
        return convertView;
    }
}
