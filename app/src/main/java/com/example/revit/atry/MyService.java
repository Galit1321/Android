package com.example.revit.atry;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.SensorEvent;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.widget.Toast;

import java.util.GregorianCalendar;

public class MyService extends Service {
    SensorManager sensorManager;
    private long lastUpdate;
    public static String BROADCAST_ACTION = "ap2.biu.new_move";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    Intent intent = new Intent();
                    intent.setAction(BROADCAST_ACTION);
                    sendBroadcast(intent);

                    try {
                        Thread.sleep(300000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        });

        thread.start();
        return START_STICKY;

    }


    private void getAccelerometer(SensorEvent event) {

        }
    }


