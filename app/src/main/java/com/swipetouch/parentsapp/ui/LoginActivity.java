package com.swipetouch.parentsapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.swipetouch.parentsapp.R;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);




    }

    public void login(View v){

        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
    }

}
