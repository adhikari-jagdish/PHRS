package com.vayamtech.healthe_cord.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.vayamtech.healthe_cord.R;

//*Created by Jagadish on 7/10/2018.*/
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    LayoutInflater inflater;
    Integer[] mThumbIds;

    public ImageAdapter(Context mContext, Integer[] mThumbIds) {
        this.mContext = mContext;
        this.mThumbIds = mThumbIds;
        inflater = (LayoutInflater.from(mContext));
    }


    public ImageAdapter(Context c) {
        this.mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.activity_gridview, null); // inflate the layout
        ImageView imageView = convertView.findViewById(R.id.icon);
        imageView.setImageResource(mThumbIds[position]);
        return convertView;
    }


}
