package com.example.revit.atry;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

public class ListAdapter extends BaseAdapter {
    private List<Post> myMsn;
    private LayoutInflater inflater;
    private AppCompatActivity activity;

    /***
     * constructor
     * @param lst
     * @param act
     */
    public ListAdapter(List lst, AppCompatActivity act){
        this.myMsn=lst;
        this.activity=act;
    }
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater==null){
            inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (Locale.getDefault().getDisplayLanguage().toString().equals("en")){
            convertView=inflater.inflate(R.layout.left, parent, false);
        }else {
            convertView=inflater.inflate(R.layout.right, parent, false);
        }
        TextView post = (TextView) convertView.findViewById(R.id.msgr);
        TextView timestamp = (TextView) convertView
                .findViewById(R.id.time);
        TextView user = (TextView) convertView
                .findViewById(R.id.user);
        Post item = myMsn.get(position);
       user.setText(item.getUser());
        post.setText(item.getMsn());
        timestamp.setText(item.getTimeStmp());
        return convertView;

    }
}
