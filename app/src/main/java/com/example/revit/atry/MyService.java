package com.example.revit.atry;

import android.app.AlarmManager;
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

import java.util.GregorianCalendar;

public class MyService extends Service {
    SensorManager sensorManager;
    private long lastUpdate;
    public static String BROADCAST_ACTION = "revit.ChatActiviy.new_move";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {return  0;}
}

    /*    Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    Intent intent = new Intent();
                    intent.setAction(BROADCAST_ACTION);
                    sendBroadcast(intent);

                    try {
                        Thread.sleep(300000);


                        new NotificationCompat.Builder(MyService.this)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("My notification")
                                .setContentText("ttt");
                        //specifying an action and its category to be triggered once clicked on the notification
                        Intent resultIntent = new Intent(MyService.this, ChatActivity.class);
                        resultIntent.setAction("android.intent.action.MAIN");
                        resultIntent.addCategory("android.intent.category.LAUNCHER");

                        PendingIntent resultPendingIntent = PendingIntent.getActivity(MyService.this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                        //building the notification
                        mBuilder.setContentIntent(resultPendingIntent);
                        int mNotificationId = 001;
                        NotificationManager mNotifyMgr =
                                (NotificationManager) MyService.this.getSystemService(MyService.this.NOTIFICATION_SERVICE);
                        mNotifyMgr.notify(mNotificationId, mBuilder.build());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }*/
               // stopSelf();

     /*      }
        });

        thread.start();
        return START_STICKY;

    }


    private void getAccelerometer(SensorEvent event) {

        }
    }*/


