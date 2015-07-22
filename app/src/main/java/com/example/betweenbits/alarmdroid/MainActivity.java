package com.example.betweenbits.alarmdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import com.example.betweenbits.alarmdroid.fragment.CardFragment;

import com.dexafree.materialList.cards.BasicButtonsCard;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity implements TimePickerDialog.OnTimeSetListener {

    private CardFragment cardFragment;
    private FloatingActionButton floatingActionButton;

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

        floatingActionButton = (FloatingActionButton) findViewById(R.id.normal_plus);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar now = Calendar.getInstance();
                TimePickerDialog dpd = TimePickerDialog.newInstance(
                        MainActivity.this,
                        now.get(Calendar.HOUR),
                        now.get(Calendar.MINUTE), true);
                dpd.show(getFragmentManager(), "TimePickerDialog");
            }
        });

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

    @Override
    public void onTimeSet(RadialPickerLayout radialPickerLayout, int hourOfDay, int minute) {

        BasicButtonsCard card = new BasicButtonsCard(getApplicationContext());
        card.setTitle(hourOfDay + ":" + minute);

//        mListView.add(card);
    }
}
