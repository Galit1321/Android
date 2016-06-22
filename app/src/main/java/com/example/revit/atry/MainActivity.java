package com.example.revit.atry;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      getSupportActionBar().hide();
        setContentView(R.layout.activity_main);//go to layout file and bring me activitymain file
        Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg) {
               // Intent i=new Intent(MainActivity.this,LoginActivity.class);//move to the explition window
                SharedPreferences sharedPrefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                SharedPreferences.Editor ed = sharedPrefs.edit();
                //If user exist go to chat.
                if(sharedPrefs.contains("user")){
                    Intent i=new Intent(MainActivity.this,ChatActivity.class);//move to the explition window
                    startActivity(i);
                    //If user connect
                } else if (sharedPrefs.contains("connect")) {
                    Intent i=new Intent(MainActivity.this,LoginActivity.class);//move to the explition window
                    startActivity(i);
                    //First time.
                } else {
                    ed.putBoolean("connect", true);
                    Intent i=new Intent(MainActivity.this,ExpActivity.class);//move to the explition window
                    startActivity(i);
                }
            }
        };
        h.sendEmptyMessageDelayed(0, 4000); // wait  seconds and move to next page
    }


    }




