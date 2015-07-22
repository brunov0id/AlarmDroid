package com.example.betweenbits.alarmdroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.betweenbits.alarmdroid.*;
import com.example.betweenbits.alarmdroid.domain.Card;

import java.util.List;

/**
 * Created by brunov0id on 21/07/15.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyCarAdapter> {

    private List<Card> listCard;
    private LayoutInflater layoutInflater;

    public CardAdapter(Context context, List<Card> listCard) {
        this.listCard = listCard;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public MyCarAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item, parent, false);

        MyCarAdapter myCarAdapter = new MyCarAdapter(view);

        return myCarAdapter;
    }

    @Override
    public void onBindViewHolder(MyCarAdapter holder, int position) {
        holder.txtClock.setText(this.listCard.get(position).getClock());
        holder.aSwitch.setChecked(this.listCard.get(position).getStatus());
        holder.txtTitle.setText(this.listCard.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return this.listCard.size();
    }

    public class MyCarAdapter extends RecyclerView.ViewHolder {

        public TextView txtClock;
        public Switch aSwitch;
        public TextView txtTitle;
        public ImageButton imgDelete;

        public MyCarAdapter(View itemView) {
            super(itemView);

            txtClock  = (TextView) itemView.findViewById(R.id.txt_Clock);
            aSwitch   = (Switch) itemView.findViewById(R.id.a_Switch);
            txtTitle  = (TextView) itemView.findViewById(R.id.txt_Title);
            imgDelete = (ImageButton) itemView.findViewById(R.id.img_Delete);
        }
    }
}
