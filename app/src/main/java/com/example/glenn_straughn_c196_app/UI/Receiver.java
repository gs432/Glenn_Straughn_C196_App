package com.example.glenn_straughn_c196_app.UI;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

import com.example.glenn_straughn_c196_app.R;

public class Receiver extends BroadcastReceiver {
    String channelId ="test";
    static int notificationID;

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context,intent.getStringExtra("key"),Toast.LENGTH_LONG).show();

        createNotificationChannel(context, channelId);
        Notification n=new NotificationCompat.Builder(context, channelId)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentText(intent.getStringExtra("key"))
                .setContentTitle("NotificationTest").build();

        NotificationManager notificationManager=(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(notificationID++,n);
    }

    private void createNotificationChannel(Context context,String CHANNEL_ID){

        CharSequence channelName = context.getResources().getString(R.string.channel_name);
        String channelDesc = context.getString(R.string.channel_description);
        int notificationImportance = NotificationManager.IMPORTANCE_DEFAULT;

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, notificationImportance);
        channel.setDescription(channelDesc);
        NotificationManager notificationManager = context.getSystemService(NotificationManager.class);
        notificationManager.createNotificationChannel(channel);

    }
}
