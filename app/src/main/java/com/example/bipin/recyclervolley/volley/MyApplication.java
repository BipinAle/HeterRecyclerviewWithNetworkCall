package com.example.bipin.recyclervolley.volley;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.Picasso;


public class MyApplication extends Application
{


    private static MyApplication sInstance;
    @Override
    public void onCreate()
    {
         super.onCreate();
        sInstance=this;
    }

    public static MyApplication getInstance()
    {
        return sInstance;
    }

    public static Context getAppContext()
    {
        return sInstance.getApplicationContext();
    }
}
