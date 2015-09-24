package com.example.betweenbits.alarmdroid.broadcast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

import com.example.betweenbits.alarmdroid.MainActivity;
import com.example.betweenbits.alarmdroid.R;

/**
 * Created by brunov0id on 19/07/15.
 */
public class BroadcastReceiverAlarm extends BroadcastReceiver {

    private static final int NOTIF_ID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();

        if(action.equals("BroadcastReceiver_Alarm")){

            String title = intent.getExtras().getString("title");
            String clock = intent.getExtras().getString("clock");

            dispatchNotification(context, new Intent(context, MainActivity.class), "New Message", title, clock);
        }
    }

    public void dispatchNotification(Context context, Intent intent, CharSequence ticker,  CharSequence title, CharSequence description) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        Bundle bundle = new Bundle();
        bundle.putInt("NOTIF_ID", NOTIF_ID);
        intent.putExtras(bundle);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(description);
        builder.setSmallIcon(R.drawable.ic_alarm_white_18dp);
        builder.addAction(R.drawable.ic_alarm_off_black_18dp, "DISMISS", pendingIntent);

        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notification.number += 1;
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notification.vibrate = new long[]{150, 300, 150, 600};
        notificationManager.notify(R.drawable.ic_alarm_white_18dp, notification);

//        try {
//            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//            Ringtone ringtone = RingtoneManager.getRingtone(context, sound);
//            ringtone.play();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
