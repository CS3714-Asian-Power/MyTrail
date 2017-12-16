package com.example.guhao.mytrail.util;

import com.example.guhao.mytrail.R;

/**
 * Author: GuHao
 * Date: 12/16/17
 * Time: 3:39 AM
 * Desc:
 */

public class WeatherUtil {
    public static int getWeatherID(int code){
        int id;
        if (code == 800)
            id = R.mipmap.icons8_sunny;
        else if (code == 801 || code == 802 || code == 803 || code == 804)
            id = R.mipmap.icons8_clouds;
        else if (code == 100)
            id = R.mipmap.icons8_partly_cloudy;
        else if (code == 100)
            id = R.mipmap.icons8_clouds;
        else if (code == 300 || code == 301 || code == 302)
            id = R.mipmap.icons8_light_rain;
        else if (code == 310 || code == 311 || code == 312 || code == 500 || code == 501)
            id = R.mipmap.icons8_moderate_rain;
        else if (code == 313 || code == 314)
            id = R.mipmap.icons8_heavy_rain;
        else if (code == 502 || code == 503 || code == 504)
            id = R.mipmap.icons8_torrential_rain;
        else if (code == 520 || code == 521 || code == 522)
            id = R.mipmap.icons8_intense_rain;
        else if (code == 200 || code == 201 || code == 202)
            id = R.mipmap.icons8_thunder_storm;
        else if (code == 100)
            id = R.mipmap.icons8_partly_cloudy_rain;
        else if (code == 210 || code == 211 || code == 212)
            id = R.mipmap.icons8_cloud_lightning;
        else if (code == 230 || code == 231 || code == 232)
            id = R.mipmap.icons8_cloud_lightning;
        else if (code == 100)
            id = R.mipmap.icons8_stormy_weather;
        else if (code == 100)
            id = R.mipmap.icons8_light_rain;
        else
            id = R.mipmap.ic_wb_sunny_black_24dp;

        return id;
    }

    public static double fromK(double k){
        double f = (((k - 273) * 9/5) + 32);
        return f;
    }

    public String getWeekDay(int i){
        if (i == 1)
            return "Mon";
        if (i == 2)
            return "Tue";
        if (i == 3)
            return "Wed";
        if (i == 4)
            return "Thu";
        if (i == 5)
            return "Fri";
        if (i == 6)
            return "Sat";
        if (i == 7)
            return "Sun";
        return null;
    }

}
