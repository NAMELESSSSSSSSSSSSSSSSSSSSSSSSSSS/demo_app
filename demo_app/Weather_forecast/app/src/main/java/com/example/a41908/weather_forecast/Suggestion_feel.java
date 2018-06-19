package com.example.a41908.weather_forecast;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Suggestion_feel extends AppCompatActivity {
    ImageButton back;
    String weather_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion_feel);

        if (getSupportActionBar() != null){//去掉标题栏
            getSupportActionBar().hide();
        }

        String txt;
        Intent intent = getIntent();
        txt = intent.getStringExtra("txt");

        String new_id;
        new_id = intent.getStringExtra("ID");
        if(new_id!=null)
            weather_id = new_id;

        TextView show_txt = (TextView)findViewById(R.id.txt);
        show_txt.setText(txt);

        back = (ImageButton)findViewById(R.id.button_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Suggestion_feel.this, Home.class);
                intent.putExtra("ID", weather_id);
                startActivity(intent);
            }
        });
    }
}

