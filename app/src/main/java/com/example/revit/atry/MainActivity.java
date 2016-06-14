package com.example.revit.atry;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);//go to layout file and bring me activitymain file
        Handler h = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                Intent i=new Intent(MainActivity.this,LoginActivity.class);//move to the explition window
                startActivity(i);
            }
        };

        h.sendEmptyMessageDelayed(0, 5000); // wait  seconds and move to next page


    }


    }




