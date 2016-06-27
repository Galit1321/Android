package com.example.revit.atry;

import android.app.Application;

import java.net.CookieHandler;
import java.net.CookieManager;

/**
 * Created by revit on 27/06/2016.
 */
public class App extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        CookieHandler.setDefault((new CookieManager()));

    }
}
