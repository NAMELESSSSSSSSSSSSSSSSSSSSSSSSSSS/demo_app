package com.example.a41908.weather_forecast;


/**
 * time时间
 * pic_weather天气图标
 * pic_airquality空气质量
 * temperature温度
 * desc_weather简要描述天气
 * windpower风力
 */

public class weatherperhour {
    public String perhpurday;
    public String perhourtime;
    public int perhourpic_weather;
    public String perhourairquality;
    public String perhourtemperature;
    public String perhourdesc_weather;
    public String windpower;

    public weatherperhour(String perhpurday, String perhourtime, int perhourpic_weather, String perhourairquality, String perhourtemperature, String perhourdesc_weather, String windpower) {
        this.perhpurday = perhpurday;
        this.perhourtime = perhourtime;
        this.perhourpic_weather = perhourpic_weather;
        this.perhourairquality = perhourairquality;
        this.perhourtemperature = perhourtemperature;
        this.perhourdesc_weather = perhourdesc_weather;
        this.windpower = windpower;
    }

    public String getPerhpurday() {
        return perhpurday;
    }

    public String getPerhourtime() {
        return perhourtime;
    }

    public int getPerhourpic_weather() {
        return perhourpic_weather;
    }

    public String getPerhourairquality() {
        return perhourairquality;
    }

    public String getPerhourtemperature() {
        return perhourtemperature;
    }

    public String getPerhourdesc_weather() {
        return perhourdesc_weather;
    }

    public String getWindpower() {
        return windpower;
    }

    public void setPerhpurday(String perhpurday) {
        this.perhpurday = perhpurday;
    }

    public void setPerhourtime(String perhourtime) {
        this.perhourtime = perhourtime;
    }

    public void setPerhourpic_weather(int perhourpic_weather) {
        this.perhourpic_weather = perhourpic_weather;
    }

    public void setPerhourairquality(String perhourairquality) {
        this.perhourairquality = perhourairquality;
    }

    public void setPerhourtemperature(String perhourtemperature) {
        this.perhourtemperature = perhourtemperature;
    }

    public void setPerhourdesc_weather(String perhourdesc_weather) {
        this.perhourdesc_weather = perhourdesc_weather;
    }

    public void setWindpower(String windpower) {
        this.windpower = windpower;
    }
}

