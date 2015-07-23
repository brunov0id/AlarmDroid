package com.example.betweenbits.alarmdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import com.example.betweenbits.alarmdroid.domain.Card;
import com.example.betweenbits.alarmdroid.fragment.CardFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    private CardFragment cardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cardFragment = (CardFragment) getSupportFragmentManager().findFragmentByTag("mainFrag");
        if(cardFragment == null) {
            cardFragment = new CardFragment();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.layoutFragment, cardFragment, "mainFrag");
            fragmentTransaction.commit();
        }

//        Intent intent = new Intent("BroadcastReceiver_Alarm");
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(System.currentTimeMillis());
//        calendar.add(Calendar.SECOND, 3);
//
//        AlarmManager alarmManager = (AlarmManager getSystemService(ALARM_SERVICE);
//        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public List<Card> getCards() {
        List<Card> aux = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Card card = new Card();
            card.setClock(10 + ":" + i);
            card.setTitle("Label");
            card.setStatus(true);

            aux.add(card);
        }
        return aux;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        cancel();
    }

    private void cancel() {
        Intent intent = new Intent("BroadcastReceiver_Alarm");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);
    }
}
