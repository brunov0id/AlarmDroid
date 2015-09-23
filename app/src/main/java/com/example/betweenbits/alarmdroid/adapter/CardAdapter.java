package com.example.betweenbits.alarmdroid.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.betweenbits.alarmdroid.*;
import com.example.betweenbits.alarmdroid.dao.CardDaoImpl;
import com.example.betweenbits.alarmdroid.domain.Card;

import java.util.List;

/**
 * Created by brunov0id on 21/07/15.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyCarAdapter>  {

    private Context context;
    private List<Card> listCard;
    private LayoutInflater layoutInflater;
    private CardDaoImpl cardDao;

    public CardAdapter(Context context, List<Card> listCard) {
        this.context = context;
        this.listCard = listCard;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cardDao = new CardDaoImpl(context);
    }

    @Override
    public MyCarAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        MyCarAdapter myCarAdapter = new MyCarAdapter(view);
        return myCarAdapter;
    }

    @Override
    public void onBindViewHolder(MyCarAdapter holder, final int position) {
        holder.txtTitle.setText(this.listCard.get(position).getTitle());
        holder.txtTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MaterialDialog.Builder(context)
                        .title(listCard.get(position).getTitle())
                        .inputMaxLengthRes(40, R.color.red)
                        .inputType(InputType.TYPE_CLASS_TEXT)
                        .positiveText("OK")
                        .negativeText("CANCEL")
                        .input(null, null, new MaterialDialog.InputCallback() {
                            @Override
                            public void onInput(MaterialDialog materialDialog, CharSequence charSequence) {
                                updateCard(charSequence.toString(), position);
                            }
                        })
                        .show();
            }
        });
        holder.txtClock.setText(this.listCard.get(position).getClock());
        holder.aSwitch.setChecked(this.listCard.get(position).getStatus());
        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeItem(position);
            }
        });
    }

    private void removeItem(int position) {
        this.listCard.remove(position);
        notifyDataSetChanged();
    }

    private void updateCard(String title, int position) {
        this.listCard.get(position).setTitle(title);
        cardDao.update(this.listCard.get(position));
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return this.listCard.size();
    }

    public class MyCarAdapter extends RecyclerView.ViewHolder {
        public TextView txtClock;
        public SwitchCompat aSwitch;
        public TextView txtTitle;
        public ImageButton imgDelete;

        public MyCarAdapter(View itemView) {
            super(itemView);

            txtClock  = (TextView) itemView.findViewById(R.id.txt_Clock);
            aSwitch   = (SwitchCompat) itemView.findViewById(R.id.a_Switch);
            txtTitle  = (TextView) itemView.findViewById(R.id.txt_Title);
            imgDelete = (ImageButton) itemView.findViewById(R.id.img_Delete);
        }
    }
}
