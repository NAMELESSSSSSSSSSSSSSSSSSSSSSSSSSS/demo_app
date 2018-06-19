package com.example.a41908.weather_forecast;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class Set extends AppCompatActivity implements View.OnClickListener{
    FrameLayout layout1;
    FrameLayout layout2;
    FrameLayout layout3;

    String weather_id;

    static int temperatureUnitFlag = 0;
    Switch _showUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        if (getSupportActionBar() != null){//去掉标题栏
            getSupportActionBar().hide();
        }

        Intent intent = getIntent();
        String new_id;
        new_id = intent.getStringExtra("ID");
        if(new_id!=null)
            weather_id = new_id;

        _showUnit = (Switch)findViewById(R.id.ShowUnit);
        layout1 = (FrameLayout)findViewById(R.id.layout1);
        layout2 = (FrameLayout)findViewById(R.id.layout2);
        layout3 = (FrameLayout)findViewById(R.id.layout3);

        LinearLayout _checkNewVersion            =   (LinearLayout)findViewById(R.id.CheckNewVersion);
        LinearLayout _aboutUs                    =   (LinearLayout)findViewById(R.id.AboutUs);
        LinearLayout _smallWinsowTransparency    =   (LinearLayout)findViewById(R.id.SmallWinsowTransparency);
        LinearLayout _temperatureUnit            =   (LinearLayout)findViewById(R.id.TemperatureUnit);
        LinearLayout _notificationBarSwitch      =   (LinearLayout)findViewById(R.id.NotificationBarSwitch);
        LinearLayout _cleanCache                 =   (LinearLayout)findViewById(R.id.CleanCache);
        LinearLayout _feedBack                   =   (LinearLayout)findViewById(R.id.FeedBack);
        LinearLayout _newIntroduction            =   (LinearLayout)findViewById(R.id.NewIntroduction);

        _checkNewVersion.           setOnClickListener(this);
        _aboutUs.                   setOnClickListener(this);
        _smallWinsowTransparency.   setOnClickListener(this);
        _temperatureUnit.           setOnClickListener(this);
        _notificationBarSwitch.     setOnClickListener(this);
        _cleanCache.                setOnClickListener(this);
        _feedBack.                  setOnClickListener(this);
        _newIntroduction.           setOnClickListener(this);

        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//跳转至Home
                Intent intent = new Intent(Set.this, Home.class);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });
        layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//跳转至Around
                Intent intent = new Intent(Set.this, around.class);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });
        layout3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//跳转至Set
                Intent intent = new Intent(Set.this, Set.class);
                intent.putExtra("ID",weather_id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.AboutUs:
                aboutUs();
                break;
            case R.id.CheckNewVersion:
                checkNewVersion();
                break;
            case R.id.SmallWinsowTransparency:
                smallWinsowTransparency();
                break;
            case R.id.TemperatureUnit:
                temperatureUnit();
                break;
            case R.id.NotificationBarSwitch:
                notificationBarSwitch();
                break;
            case R.id.CleanCache:
                cleanCache();
                break;
            case R.id.FeedBack:
                feedBack();
                break;
            case R.id.NewIntroduction:
                newIntroduction();
                break;
        }
    }

    void aboutUs(){
        Intent itaboutus = new Intent(Set.this,About_us.class);
        startActivity(itaboutus);
    }
    void checkNewVersion(){
        Toast.makeText(this,"已经是最新版本",Toast.LENGTH_SHORT).show();
    }
    void smallWinsowTransparency(){
    }
    void temperatureUnit(){
        if(0==temperatureUnitFlag){
            _showUnit.setText("°F");
            temperatureUnitFlag=1;
        }else{
            _showUnit.setText("°C");
            temperatureUnitFlag=0;
        }
    }
    void notificationBarSwitch(){
    }
    void cleanCache(){}
    void feedBack(){};
    void newIntroduction(){};
}
