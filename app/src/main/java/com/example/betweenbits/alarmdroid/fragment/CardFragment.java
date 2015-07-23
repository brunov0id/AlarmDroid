package com.example.betweenbits.alarmdroid.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.betweenbits.alarmdroid.R;
import com.example.betweenbits.alarmdroid.adapter.CardAdapter;
import com.example.betweenbits.alarmdroid.domain.Card;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by brunov0id on 21/07/15.
 */
public class CardFragment extends Fragment implements TimePickerDialog.OnTimeSetListener {

    public static final String TIMEPICKER_TAG = "timepicker";
    private RecyclerView recyclerView;
    private List<Card> listCard = new ArrayList<>();
    private CardAdapter cardAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_card, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView.setLayoutManager(linearLayoutManager);

        Calendar calendar = Calendar.getInstance();
        final TimePickerDialog timePickerDialog = TimePickerDialog.newInstance(
                this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.attachToRecyclerView(recyclerView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show(getActivity().getFragmentManager(), TIMEPICKER_TAG);
            }
        });

        cardAdapter = new CardAdapter(getActivity(), listCard);
        return view;
    }

    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int hourOfDay, int minuteOfDay) {

        Card card = new Card();
        card.setTitle("Label");
        String hour   = (hourOfDay   == 0) ? "00" : String.valueOf(hourOfDay);
        String minute = (minuteOfDay == 0) ? "00" : String.valueOf(minuteOfDay);
        card.setClock(hour+":"+minute);
        card.setStatus(true);

        listCard.add(card);
        recyclerView.setAdapter(cardAdapter);
        cardAdapter.notifyDataSetChanged();
    }
}
