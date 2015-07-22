package com.example.betweenbits.alarmdroid.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by brunov0id on 21/07/15.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyCarAdapter> {

    @Override
    public MyCarAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyCarAdapter holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyCarAdapter extends RecyclerView.ViewHolder {
        public MyCarAdapter(View itemView) {
            super(itemView);
        }
    }
}
