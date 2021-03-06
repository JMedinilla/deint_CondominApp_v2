package com.deint.condominapp.others.receivers;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.deint.condominapp.R;

public class InsertReceiver extends BroadcastReceiver {
    public static final String ACTION_INSERTED = "com.deint.condominapp.action.INSERTED";

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getExtras().getString("receiver_message");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(context.getString(R.string.receiver_insert));
        builder.setContentText(msg);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setDefaults(Notification.DEFAULT_LIGHTS);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, builder.build());
    }
}
