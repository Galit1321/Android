package com.example.revit.atry;

import android.graphics.Color;
import android.os.Bundle;

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

    }


    }




