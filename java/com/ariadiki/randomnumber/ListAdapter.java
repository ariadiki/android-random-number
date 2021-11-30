package com.ariadiki.randomnumber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    Context context;
    int[] images;
    String[] text;
    String[] txttries;
    LayoutInflater inflater;

    public ListAdapter(Context context, int[] images, String[] text,String[] txttries) {
        this.context = context;
        this.images = images;
        this.text = text;
        this.txttries = txttries;
        inflater = (LayoutInflater.from(context.getApplicationContext()));
    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.list_row,null);
        ImageView imageView = convertView.findViewById(R.id.img_class);
        TextView textView = convertView.findViewById(R.id.txt_player);
        TextView textView1 = convertView.findViewById(R.id.txt_tries);

        imageView.setImageResource(images[position]);
        textView.setText(text[position]);
        textView1.setText(txttries[position]);

        return convertView;
    }
}
