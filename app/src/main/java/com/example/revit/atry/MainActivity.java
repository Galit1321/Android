package com.example.revit.atry;

import android.content.Intent;

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
                Intent i=new Intent(MainActivity.this,ChatActivity.class);//move to the explition window
                startActivity(i);
            }
        };
        h.sendEmptyMessageDelayed(0, 5000); // wait  seconds and move to next page
    }


    }




