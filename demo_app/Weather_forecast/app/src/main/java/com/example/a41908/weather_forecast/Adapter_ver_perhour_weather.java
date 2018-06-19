package com.example.a41908.weather_forecast;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class Adapter_ver_perhour_weather extends RecyclerView.Adapter<Adapter_ver_perhour_weather.ViewHolder>{
    private List<weatherperhour> mPerhourList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView perhourday;
        TextView perhourtime;
        ImageView perpicweather;
        TextView perhourquality;
        TextView perhourtemplatent;
        TextView perhourweather;
        TextView perhourwind;

        public ViewHolder(View view){
            super(view);
            perhourday = (TextView)view.findViewById(R.id.perhour_day);
            perhourtime = (TextView)view.findViewById(R.id.perhour_time);
            perpicweather = (ImageView)view.findViewById(R.id.perhour_pic_weather);
            perhourquality = (TextView) view.findViewById(R.id.perhour_quality);
            perhourtemplatent = (TextView) view.findViewById(R.id.perhour_templetent);
            perhourweather = (TextView)view.findViewById(R.id.perhour_weather);
            perhourwind = (TextView)view.findViewById(R.id.perhour_wind);
        }
    }

    public Adapter_ver_perhour_weather(List<weatherperhour> PerhourList){
        mPerhourList = PerhourList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.weatherperhour,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        weatherperhour perhour = mPerhourList.get(position);
        holder.perhourday.setText(perhour.getPerhpurday());
        holder.perhourtime.setText(perhour.getPerhourtime());
        holder.perpicweather.setImageResource(perhour.getPerhourpic_weather());
        holder.perhourquality.setText(perhour.getPerhourairquality());
        holder.perhourtemplatent.setText(perhour.getPerhourtemperature());
        holder.perhourweather.setText(perhour.getPerhourdesc_weather());
        holder.perhourwind.setText(perhour.getWindpower());
    }

    @Override
    public int getItemCount() {
        return mPerhourList.size();
    }
}

