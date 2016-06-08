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
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    String textSwitcherText[] = {"TextSwitcher Example", "Next Text in TextSwitcher", "Android TextSwitcher", "TextSwitcher Tutorial", "TextSwitcher in Android"};
    Button btnNext;
    TextSwitcher textSwitcher;

    int switcherText = textSwitcherText.length;

    int currentIndex=-1;
    int messageCount=textSwitcherText.length;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//go to layout file and bring me activitymain file
        /*btnNext=(Button)findViewById(R.id.buttonNext);
        textSwitcher = (TextSwitcher) findViewById(R.id.textS);

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
        textSwitcher.setInAnimation(animationIn);
        // ClickListener for NEXT button
        // When clicked on Button TextSwitcher will switch between texts
        // The current Text will go OUT and next text will come in with specified animation
        btnNext.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                currentIndex++;
                // If index reaches maximum reset it
                if(currentIndex==messageCount)
                    currentIndex=0;
                textSwitcher.setText(textSwitcherText[currentIndex]);
            }
        });*/
    }

    }




