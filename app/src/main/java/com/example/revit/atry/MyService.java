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
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    private void getAccelerometer(SensorEvent event) {

        }
    }


