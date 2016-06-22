package com.example.revit.atry;

import android.content.SharedPreferences;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements SensorEventListener {
    ListAdapter poststAdapter;
    ListView lstPosts;
    ArrayList<Messages> posts;
    SwipeRefreshLayout swipeLayout;
    SensorManager sensorManager;
    EditText edt;
    Calendar calander;
    SimpleDateFormat simpleDateFormat;
    private SendMsn mAuthTask;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);
        setContentView(R.layout.activity_chat);
        calander = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        lstPosts = (ListView) findViewById(R.id.feed_lvPosts);
        posts = new ArrayList<Messages>();
        poststAdapter = new ListAdapter(this,posts);
        lstPosts.setAdapter(poststAdapter);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        Button send=(Button)findViewById(R.id.send_button);
         edt=(EditText)findViewById(R.id.editText);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = simpleDateFormat.format(calander.getTime());
                Messages item = new Messages();
                item.setTimeStmp(time);
                item.setMsn(edt.getText().toString());
                SharedPreferences sharedPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                //SharedPreferences.Editor ed = sharedPrefs.edit();
                item.setUser("me");
                poststAdapter.add(item);
              //  Messages item =generateSelfPosts(time,);
                //poststAdapter.notifyDataSetChanged();
                mAuthTask = new SendMsn(item);//activate asyc commend of
                mAuthTask.execute();
            }
        });
    }


    /**
     * create new post that has the given argums as timestmp and msn
     * with the user name that is save in the device
     *
     * @param time the time the msn was writen
     * @param msn the msn the was input to edittext
     */
    private Messages generateSelfPosts(String time, String msn) {

        return null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        // register this class as a listener for the orientation and
        // accelerometer sensors
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        // unregister listener
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
           // getAccelerometer(event);
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
