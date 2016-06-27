package com.example.revit.atry;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {
    // TODO: Rename actions, choose action names that describe tasks that this
    // IntentService can perform, e.g. ACTION_FETCH_NEW_ITEMS
    private static final String ACTION_FOO = "com.example.revit.atry.action.FOO";
    private static final String ACTION_BAZ = "com.example.revit.atry.action.BAZ";
    // TODO: Rename parameters
    private static final String EXTRA_PARAM1 = "com.example.revit.atry.extra.PARAM1";
    private static final String EXTRA_PARAM2 = "com.example.revit.atry.extra.PARAM2";
    InnerCheckAsyc checkAsyc;
//    private NewMsgTask lAuthTask;
    private  Boolean check;
    private  Boolean newData;
    private NotificationManager nfc;

    public MyIntentService() {
        super("MyIntentService");
check=false;
        newData=false;

    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionFoo(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_FOO);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    /**
     * Starts this service to perform action Baz with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */
    // TODO: Customize helper method
    public static void startActionBaz(Context context, String param1, String param2) {
        Intent intent = new Intent(context, MyIntentService.class);
        intent.setAction(ACTION_BAZ);
        intent.putExtra(EXTRA_PARAM1, param1);
        intent.putExtra(EXTRA_PARAM2, param2);
        context.startService(intent);
    }

    public Boolean isNew() {
        while (!newData){
            //do in background didn't finish it's work
        }
        return check;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

       while (true){
           try {
               Thread.sleep(30000);
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
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
        }
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
            chatActivity.resetListView("shake");
            resultIntent = new Intent(chatActivity, ChatActivity.class);
            chatActivity.setCheck(false);

        }else{
            resultIntent = new Intent(MyIntentService.this, ChatActivity.class);
        }
        // The PendingIntent to launch our activity if the user selects this notification
        //specifying an action and its category to be triggered once clicked on the notification
        resultIntent.setAction("android.intent.action.MAIN");
        resultIntent.addCategory("android.intent.category.LAUNCHER");
        PendingIntent contentIntent = PendingIntent.getActivity(MyIntentService.this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Set the info for the views that show in the notification panel.
        NotificationCompat.Builder notification =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.logo)
                        .setContentTitle("New Information")
                        .setContentText("you have new messages");
        notification.setContentIntent( contentIntent);
        // Send the notification. 8191 is the id to this notifiction
        nfc = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        notification.setAutoCancel(true);//dimiss the notification when is press
        nfc.notify(8191,notification.build());
    }

    /**
     * Handle action Foo in the provided background thread with the provided
     * parameters.
     */
    private void handleActionFoo(String param1, String param2) {
        // TODO: Handle action Foo
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Handle action Baz in the provided background thread with the provided
     * parameters.
     */
    private void handleActionBaz(String param1, String param2) {
        // TODO: Handle action Baz
        throw new UnsupportedOperationException("Not yet implemented");
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
            URL url = new URL("http://10.0.2.2:8080//ChecLastServlet?last="+this.last);
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
                return  json.getString("haveUpdate");
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
            Toast.makeText(MyIntentService.this, "lost con to server in service", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCancelled() {
        checkAsyc= null;//stop asyc
    }
}
}
