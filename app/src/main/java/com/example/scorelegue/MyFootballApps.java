package com.example.scorelegue;

import android.app.Application;

import com.androidnetworking.AndroidNetworking;

public class MyFootballApps extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AndroidNetworking.initialize(getApplicationContext());
    }
}
