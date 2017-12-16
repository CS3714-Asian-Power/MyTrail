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
        if (code == 801 || code == 802 || code == 803 || code == 804)
            id = R.mipmap.icons8_clouds;
        if (code == 100)
            id = R.mipmap.icons8_partly_cloudy;
        if (code == 100)
            id = R.mipmap.icons8_clouds;
        if (code == 300 || code == 301 || code == 302)
            id = R.mipmap.icons8_light_rain;
        if (code == 310 || code == 311 || code == 312 || code == 500 || code == 501)
            id = R.mipmap.icons8_moderate_rain;
        if (code == 313 || code == 314)
            id = R.mipmap.icons8_heavy_rain;
        if (code == 502 || code == 503 || code == 504)
            id = R.mipmap.icons8_torrential_rain;
        if (code == 520 || code == 521 || code == 522)
            id = R.mipmap.icons8_intense_rain;
        if (code == 200 || code == 201 || code == 202)
            id = R.mipmap.icons8_thunder_storm;
        if (code == 100)
            id = R.mipmap.icons8_partly_cloudy_rain;
        if (code == 210 || code == 211 || code == 212)
            id = R.mipmap.icons8_cloud_lightning;
        if (code == 230 || code == 231 || code == 232)
            id = R.mipmap.icons8_cloud_lightning;
        if (code == 100)
            id = R.mipmap.icons8_stormy_weather;
        if (code == 100)
            id = R.mipmap.icons8_light_rain;
        else
            id = R.mipmap.ic_wb_sunny_black_24dp;

        return id;
    }
}
