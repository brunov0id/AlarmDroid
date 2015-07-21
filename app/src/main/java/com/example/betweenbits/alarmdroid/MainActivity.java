package com.example.betweenbits.alarmdroid;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;

import com.dexafree.materialList.cards.BasicButtonsCard;
import com.dexafree.materialList.cards.OnButtonPressListener;
import com.dexafree.materialList.cards.WelcomeCard;
import com.dexafree.materialList.model.Card;
import com.dexafree.materialList.view.MaterialListView;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.wdullaer.materialdatetimepicker.time.RadialPickerLayout;
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

import java.util.Calendar;

public class MainActivity extends ActionBarActivity implements TimePickerDialog.OnTimeSetListener {

    private FloatingActionButton floatingActionButton;
    private MaterialListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (MaterialListView) findViewById(R.id.material_listview);

        welcome();

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

        mListView.add(card);
    }

    private void welcome() {
        WelcomeCard welcomeCard = new WelcomeCard(getApplicationContext());
        welcomeCard.setTitle("Welcome to Alarm");
        welcomeCard.setSubtitle("Add Your Alarms Here!");
        welcomeCard.setDescription("Add Way Anytime.");
        welcomeCard.setButtonText("OKAY!");

        welcomeCard.setOnButtonPressedListener(new OnButtonPressListener() {
            @Override
            public void onButtonPressedListener(View view, Card card) {
                mListView.remove(card);
            }
        });
        welcomeCard.setDismissible(true);
        welcomeCard.setBackgroundColorRes(R.color.background_material_dark);

        mListView.add(welcomeCard);
    }
}
