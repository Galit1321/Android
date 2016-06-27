package com.example.revit.atry;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.DataSetObserver;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements SensorEventListener {
   public static ChatActivity gainAcess;
    private static boolean check;
  //members that relate to the layout
    private ListView lstPosts;
    private ArrayList<Messages> posts;
    private SwipeRefreshLayout swipeLayout;
    private SensorManager sensorManager;
    private EditText edt;
    //java class members for function in class
    private Calendar calander;
    private SimpleDateFormat simpleDateFormat;
    private InnSendMsn mAuthTask;//inner class
    private ListAdapter poststAdapter;
    private  boolean newData;
    private InnerCheckAsyc checkAsyc;
    private GetMsgAsyc listAsyc;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Make update.

            Toast.makeText(getApplicationContext(), "received", Toast.LENGTH_SHORT).show();
        }
    };
    private int lastId;//the id of the last message that listview contain
    private int firstId;//the id of the first massage the we have in listview

    public boolean isNewData() {
        return newData;
    }

    public void setNewData(boolean newData) {
        this.newData = newData;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        setContentView(R.layout.activity_chat);
        lastId=0;
newData=false;
        check=false;
        gainAcess=this;
        firstId=0;
        calander = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        lstPosts = (ListView) findViewById(R.id.feed_lvPosts);
        posts = new ArrayList<Messages>();
        poststAdapter = new ListAdapter(this, posts);
        poststAdapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                lstPosts.setSelection(poststAdapter.getCount() - 1);
            }
        });
        lstPosts.setAdapter(poststAdapter);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Button send = (Button) findViewById(R.id.send_button);
        edt = (EditText) findViewById(R.id.editText);
        //Swipe
        swipeLayout=(SwipeRefreshLayout)findViewById(R.id.feed_swipeLayout);
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                resetListView("swipe");
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = simpleDateFormat.format(calander.getTime());
                Messages item = new Messages();
                item.setTimeStmp(time);
                item.setMsn(edt.getText().toString());
                SharedPreferences sharedPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                item.setUser(sharedPrefs.getString("name", ""));
                poststAdapter.add(item);
                //hide keyboard
                InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                edt.setText("");
                mAuthTask = new InnSendMsn(item);//activate asyc commend of
                mAuthTask.execute();
            }
        });
       resetListView("shake");
        startService(new Intent(ChatActivity.this, MyIntentService.class));
    }


    public void resetListView(String method){
        this.listAsyc = new GetMsgAsyc(method);

            this.listAsyc.execute();

    }
 @Override
 protected void onStop(){
     super.onStop();
     gainAcess=null;
 }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);

        //for the 5 min update
        IntentFilter filter = new IntentFilter();
        filter.addAction(MyService.BROADCAST_ACTION);
        registerReceiver(receiver, filter);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
        unregisterReceiver(receiver);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // getAccelerometer(event);
        }

    }
    /***
     * check if the num pf last massage in
     * the data base
     */
    public void checkForLastMsg() {
        checkAsyc= new InnerCheckAsyc(lastId);
        checkAsyc.execute();
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
    public Boolean isNew() {
        while (!newData){
            //do in background didn't finish it's work
        }
        return check;
    }
    /*
    public void notification(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyService.BROADCAST_ACTION);
        registerReceiver(receiver, intentFilter);
        AlarmManager alarmMgr = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        //300000
        Long timeToAlert = new GregorianCalendar().getTimeInMillis() + 300000;
        Intent intent = new Intent(ChatActivity.this,NotificationReceiver.class);
        intent.putExtra("sender", "one");
        alarmMgr.set(AlarmManager.RTC_WAKEUP, timeToAlert, PendingIntent.getBroadcast(ChatActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
    }
*/
    /***
     * inner class incharge of sending this post to the server
     */
    public class InnSendMsn extends AsyncTask<Void, Void, Void> {
        private Messages p;

        /**
         * constructor
         * the inner class in charge of
         *sending this texts to the server
         * @param item
         */
        public InnSendMsn(Messages item) {
            this.p = item;
        }

        /**
         * send this.p object to the servlet
         * in the web server that add to the database of massges
         */


        @Override
        protected Void doInBackground(Void... params) {
            try {
                URL url = new URL("http://10.0.2.2:8080//RecMsnServlet?msn=" + this.p.getMsn() + "&timeStmp=" + this.p.getTimeStmp()
                        + "&user=" + this.p.getUser());
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                try {
                    urlConnection.getInputStream();
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
        protected void onCancelled() {
            mAuthTask = null;
        }

    }
    /***
     * inner class to check if there is need for a notification
     * meaning that we have new massages in the data base
     */
    public class InnerCheckAsyc extends AsyncTask<Void, Void,String> {
        public int last;
        public String cookie = null;

        public InnerCheckAsyc(int last) {
            this.last = last;
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL("http://10.0.2.2:8080//ChecLastServlet?last=" + this.last);
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
                    return json.getString("haveUpdate");
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
        protected void onPostExecute(String isItNew) {
            if (isItNew != null) {
                newData = true;
                if (isItNew.equals("yes"))
                    check = true;
            } else {

                Toast.makeText(ChatActivity.this, "lost connection to server", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        protected void onCancelled(){
            checkAsyc=null;
        }
    }
    /**
     *inner class to update the listview layout
     */
    public class GetMsgAsyc extends AsyncTask<Void, Void, MsnList> {

        private String method;
        public  String cookie=null;
        public GetMsgAsyc(String type) {
            this.method = type;
        }

        @Override
        protected MsnList doInBackground(Void... params) {
            try {
                URL url = new URL("http://10.0.2.2:8080///SendMsnServlet?first=" + firstId + "&last=" + lastId
                        + "&type=" + this.method);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setDoInput(true);
                urlConnection.setDoOutput(true);
                try {
                    urlConnection.setRequestProperty("Cookie",cookie);
                    InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                    cookie=urlConnection.getHeaderField("Set-Cookie");
                    BufferedReader streamReader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
                    StringBuilder responseStrBuilder = new StringBuilder();
                    String inputStr;
                    while ((inputStr = streamReader.readLine()) != null)
                        responseStrBuilder.append(inputStr);
                    JSONObject json = new JSONObject(responseStrBuilder.toString());
                    firstId = json.getInt("first");
                    lastId = json.getInt("last");
                    return new MsnList(json);
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
        protected void onPostExecute(final MsnList mLst) {
            if (mLst != null) {
                List<Messages> lst = mLst.getList();
                for (int i = 0; i < lst.size(); i++) {
                    poststAdapter.add(lst.get(i));
                }
                if (method.equals("swipe")) {
                    swipeLayout.setRefreshing(false);
                }
            } else {
                //dont need to go here
                Toast.makeText(ChatActivity.this, R.string.fail_update, Toast.LENGTH_LONG).show();
            }
        }
        @Override
        protected void onCancelled() {
            listAsyc = null;
        }
    }
}


