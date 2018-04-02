package com.ruanjiangongcheng.weather;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Begin_interface extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_begin_interface);

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        new Handler(new Handler.Callback(){
            @Override
            public boolean handleMessage(Message message) {
                startActivity(new Intent(getApplicationContext(),Home.class));
                return false;
            }
        }).sendEmptyMessageDelayed(0,500);

    }
}