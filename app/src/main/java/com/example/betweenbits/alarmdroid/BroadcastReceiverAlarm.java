package com.example.betweenbits.alarmdroid;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by brunov0id on 19/07/15.
 */
public class BroadcastReceiverAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        dispatchNotification(context, new Intent(context, MainActivity.class), "New Message", "Alarm", "Ready for Start an Activity ?");
    }

    public void dispatchNotification(Context context, Intent intent, CharSequence ticker,  CharSequence title, CharSequence description) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setTicker(ticker);
        builder.setContentTitle(title);
        builder.setContentText(description);
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher));
        builder.setContentIntent(pendingIntent);

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        notification.vibrate = new long[]{150, 300, 150, 600};
        notificationManager.notify(R.drawable.ic_launcher, notification);

        try {
            Uri sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone ringtone = RingtoneManager.getRingtone(context, sound);
            ringtone.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
