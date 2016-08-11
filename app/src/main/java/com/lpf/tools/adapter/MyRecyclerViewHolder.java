package com.lpf.tools.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lpf.tools.R;

/**
 * Created by lpf on 16/8/10.
 */
public class MyRecyclerViewHolder extends RecyclerView.ViewHolder {

    public TextView mTextView;

    public MyRecyclerViewHolder(View itemView) {
        super(itemView);

        mTextView = (TextView)itemView.findViewById(R.id.id_textview);
    }
}
