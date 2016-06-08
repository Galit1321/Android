package com.example.ex04db;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

    String textSwitcherText[] = {"TextSwitcher Example", "Next Text in TextSwitcher", "Android TextSwitcher", "TextSwitcher Tutorial", "TextSwitcher in Android"};

    TextSwitcher textSwitcher;

    int switcherText = textSwitcherText.length;
    int counter = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//go to layout file and bring me activitymain file
      /*textSwitcher = (TextSwitcher) findViewById(R.id.text);

        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView switcherTextView = new TextView(getApplicationContext());
                switcherTextView.setTextSize(24);
                switcherTextView.setTextColor(Color.RED);
                switcherTextView.setText("Click The Below Button");
                switcherTextView.setShadowLayer(6, 6, 6, Color.BLACK);
                return switcherTextView;
            }
        });

        Animation animationOut = AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right);
        Animation animationIn = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left);

        textSwitcher.setOutAnimation(animationOut);
        textSwitcher.setInAnimation(animationIn);*/
    }


    }




