package com.example.revit.atry;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    private List<Messages> myMsn;
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
