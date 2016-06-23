package com.example.revit.atry;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MyService extends Service {
    private NotificationManager mNM;
    public static String BROADCAST_ACTION = "revit.ChatActiviy.new_move";

    public MyService() {
    }


        Calendar cur_cal = Calendar.getInstance();
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

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        showNotification();
    }

    private void showNotification() {
        // The PendingIntent to launch our activity if the user selects this notification
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,
                new Intent(this, ChatActivity.class), 0);

        // Set the info for the views that show in the notification panel.
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("New Information")
                        .setContentText("you have new messages");
        // Send the notification.
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


