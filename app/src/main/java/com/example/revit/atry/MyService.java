package com.example.revit.atry;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import java.util.Calendar;


public class MyService extends Service {
    private NotificationManager mNM;
    Calendar cur_cal = Calendar.getInstance();
    public static String BROADCAST_ACTION = "revit.ChatActiviy.new_move";

    public MyService() {
    }



        @Override
        public void onCreate() {
            super.onCreate();
            Intent intent = new Intent(this, MyService.class);
            mNM = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            PendingIntent pintent = PendingIntent.getService(getApplicationContext(), 0, intent, 0);
            AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            cur_cal.setTimeInMillis(System.currentTimeMillis());
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, cur_cal.getTimeInMillis(), 60 * 500*1, pintent);
        }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handleStart(intent, startId);
        return START_NOT_STICKY;
    }

    private void handleStart(Intent intent, int startId) {
        showNotification();
    }

    private void showNotification() {
        Intent resultIntent = new Intent(MyService.this, ChatActivity.class);
        resultIntent.setAction("android.intent.action.MAIN");
        resultIntent.addCategory("android.intent.category.LAUNCHER");
        PendingIntent contentIntent = PendingIntent.getActivity(MyService.this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Set the info for the views that show in the notification panel.
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("New Information")
                        .setContentText("you have new messages");
        notification.setContentIntent( contentIntent);
        // Send the notification. 8191 is the id to this notifiction
       // nfc.notify(8191,notification.build());
        // The PendingIntent to launch our activity if the user selects this notification
     /*   PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, ChatActivity.class), 0);

        // Set the info for the views that show in the notification panel.
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("New Information")
                        .setContentText("you have new messages");
        // Send the notification.*/
        mNM.notify(001,notification.build());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service
            return null;
        }

        // This is the object that receives interactions from clients.  See
        // RemoteService for a more complete example.
        @Override
        public void onDestroy() {
            // Cancel the persistent notification.
            mNM.cancel(001);
        }




        }


