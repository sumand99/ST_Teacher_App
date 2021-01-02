package com.swipetouch.parentsapp.util;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.Locale;


/**
 * Created by sjena on 27/12/17.
 */

public class AppController extends Application {


    private RequestQueue queue;



    @Override
    protected void attachBaseContext(Context context) {
        super.attachBaseContext(context);



    }

    public static void setLocaleFa (Context context){
        Locale locale = new Locale("or_IN");
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(config, null);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        String lang = "de";
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());

        queue = Volley.newRequestQueue(this);



    }






    public RequestQueue getQueue() {
        return queue;
    }


}
