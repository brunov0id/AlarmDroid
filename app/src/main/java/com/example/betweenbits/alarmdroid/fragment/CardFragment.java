package com.example.betweenbits.alarmdroid.fragment;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.betweenbits.alarmdroid.R;
import com.example.betweenbits.alarmdroid.adapter.CardAdapter;
import com.example.betweenbits.alarmdroid.dao.CardDaoImpl;
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
    private CardAdapter cardAdapter;
    private CardDaoImpl cardDao;
    private List<Card> listOfCard;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        cardDao = new CardDaoImpl(getActivity());
    }

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
                this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab.attachToRecyclerView(recyclerView);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show(getActivity().getFragmentManager(), TIMEPICKER_TAG);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (cardAdapter == null) {
            listOfCard = cardDao.getListOfCard();
            cardAdapter = new CardAdapter(getActivity(), listOfCard);
        }
        recyclerView.setAdapter(cardAdapter);
    }

    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int hourOfDay, int minuteOfDay) {
        String hour   = (hourOfDay     <= 9 || hourOfDay   == 0) ? "0" + String.valueOf(hourOfDay)   : String.valueOf(hourOfDay);
        String minute = (minuteOfDay   <= 9 || minuteOfDay == 0) ? "0" + String.valueOf(minuteOfDay) : String.valueOf(minuteOfDay);

        if (listOfCard == null) {
            listOfCard = new ArrayList<>();
        }

        Card clock = new Card("Label", hour + ":" + minute, true);

        cardDao.insert(clock);
        listOfCard.add(clock);

        createAlarm(hour, minute);

        cardAdapter.notifyDataSetChanged();
    }

    private void createAlarm(String hour, String minute) {
        Intent intent = new Intent("BroadcastReceiver_Alarm");
        intent.putExtra("title", "Label");
        intent.putExtra("clock", hour + ":" + minute);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 3); //1000 * ((60 * Integer.parseInt(hour)) * Integer.parseInt(minute))

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(getActivity().ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }
}
