package com.example.betweenbits.alarmdroid;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by brunov0id on 19/07/15.
 */
public class BroadcastReceiverAlarm extends BroadcastReceiver {

    @Override    public void onReceive(Context context, Intent intent) {
        Log.i("Scrpt","-> Alarm");

        gerarNotificacao(context, new Intent(context, MainActivity.class), "New Message", "Alarm", "Information about AlarmManager");
    }

    public void gerarNotificacao(Context context, Intent intent, CharSequence ticker,  CharSequence titulo, CharSequence descricao) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(titulo);
        builder.setContentText(descricao);
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notification.vibrate = new long[] {150, 300, 150, 600};
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        notificationManager.notify(0, notification);

        try {
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(context, sound);
            ringtone.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
