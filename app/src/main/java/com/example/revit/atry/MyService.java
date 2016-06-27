package com.example.revit.atry;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;


public class MyService extends Service {
    private NotificationManager mNM;
    Calendar cur_cal = Calendar.getInstance();
    public static String BROADCAST_ACTION = "revit.ChatActiviy.new_move";
    InnerCheckAsyc checkAsyc;
    private  Boolean check = false;
    private  Boolean newData = false;
    private NotificationManager nfc;
    public MyService() {
    }



        @Override
        public void onCreate() {
            super.onCreate();
            Intent intent = new Intent(this, MyService.class);
           nfc= (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
            PendingIntent pintent = PendingIntent.getService(this, 0, intent, 0);
            AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            cur_cal.setTimeInMillis(System.currentTimeMillis());
            alarm.cancel(pintent);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, cur_cal.getTimeInMillis(), 60 * 500*1, pintent);
        }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        boolean aged;
        ChatActivity c_act=ChatActivity.gainAcess;
        if (c_act==null){//meaning the activity is not active
            checkForLastMsg();
            aged=isNew();
        }else{
            c_act.checkForLastMsg();
            aged=c_act.isNew();
        }
        if(aged){
            showNotification(c_act);
        }
        stopSelf();
        return START_NOT_STICKY;
    }



    /***
     * check if the num pf last massage in
     * the data base
     */
    public void checkForLastMsg() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        int last = pref.getInt("last", -1);//acsess last for the ayc class
        checkAsyc= new InnerCheckAsyc(last);
        checkAsyc.execute();
    }

    private void showNotification(ChatActivity chatActivity) {
        Intent resultIntent;
        if (chatActivity!=null){
            resultIntent = new Intent(chatActivity, ChatActivity.class);
        }else{
            resultIntent = new Intent(MyService.this, ChatActivity.class);
        }
        // The PendingIntent to launch our activity if the user selects this notification
        //specifying an action and its category to be triggered once clicked on the notification
        resultIntent.setAction("android.intent.action.MAIN");
        resultIntent.addCategory("android.intent.category.LAUNCHER");
        PendingIntent contentIntent = PendingIntent.getActivity(MyService.this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Set the info for the views that show in the notification panel.
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("New Information")
                        .setContentText("you have new messages");
        notification.setContentIntent( contentIntent);
        // Send the notification. 8191 is the id to this notifiction

        notification.setAutoCancel(true);//dimiss the notification when is press
        nfc.notify(8191,notification.build());
    }

    public Boolean isNew() {
        while (!newData){
            //do in background didn't finish it's work
        }
        return check;
    }


    /***
     * inner class to check if there is need for a notification
     * meaning that we have new massages in the data base
     */
    public class InnerCheckAsyc extends AsyncTask<Void, Void,String> {
        public int last;
        public String cookie = null;
        public InnerCheckAsyc(int last){
            this.last=last;
        }
        @Override
        protected String doInBackground(Void... params) {
            try{
                URL url = new URL("http://10.0.2.2:36182//ChecLastServlet?last="+this.last);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                try {
                    cookie = urlConnection.getHeaderField("Set-Cookie");
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    StringBuilder responseStrBuilder = new StringBuilder();
                    String inputStr;
                    while ((inputStr = streamReader.readLine()) != null)
                        responseStrBuilder.append(inputStr);
                    JSONObject json = new JSONObject(responseStrBuilder.toString());
                    return  json.getString("have");
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String isItNew) {
            if (isItNew!=null){
                newData = true;
                if (isItNew.equals("yes"))
                    check = true;
            } else
            {
                Toast.makeText(MyService.this, "lost connection to server", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled() {
            checkAsyc= null;//stop asyc
        }
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


