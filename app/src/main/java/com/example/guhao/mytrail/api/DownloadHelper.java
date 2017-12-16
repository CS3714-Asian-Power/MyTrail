package com.example.guhao.mytrail.api;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by DanielPham on 12/9/17.
 */

public class DownloadHelper {
//    private static final String KEY_1 = "AIzaSyCyvbfMfwNyEs4_7C1oXuc7ZjMf_UhXe6c";
    private static final String KEY_1 = "AIzaSyA7pmEvkGaCTCbKys5mPQaAGWjNIxEcj2c";
//    private static final String KEY_1 = "AIzaSyBjPJspWVbnt-pyCrlky5osRy-H3jfAWKs";


    public String getResponses(String theUrl) throws IOException{
        String data ="";
        InputStream Stream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(theUrl);

            //create connection
            urlConnection = (HttpURLConnection) url.openConnection();

            //connect url
            urlConnection.connect();

            //get data from url
            Stream = urlConnection.getInputStream();
            BufferedReader buffer = new BufferedReader(new InputStreamReader(Stream));
            StringBuffer stringBuffer = new StringBuffer();
            String lines ="";
            while ((lines = buffer.readLine()) != null){
                stringBuffer.append(lines);
            }

            data = stringBuffer.toString();
           // Log.d("DownloadHelper:", data.toString() );
            buffer.close();
        } catch (Exception e){
            Log.d("DownloadHelper:", e.toString());
        } finally {
            Stream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    //Search by coordinate
    public String getUrlCoordinate(double latitude, double longitude, int radius, String activity) {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json?query=");
        googlePlacesUrl.append(activity);
        googlePlacesUrl.append("&location=" + latitude + "," + longitude);
        googlePlacesUrl.append("&radius=" + radius);
        //googlePlacesUrl.append("&type=" + nearbyPlace);
        //googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + KEY_1);
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }

    //search by city name
    public String getUrlCityName(String city, double radius, String activity) {

        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/textsearch/json?query=");
        googlePlacesUrl.append(activity);
        googlePlacesUrl.append("+" + city);
        googlePlacesUrl.append("&radius=" + radius);
       // googlePlacesUrl.append("&sensor=true");
        googlePlacesUrl.append("&key=" + KEY_1);
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }

    //get detail of a place by place ID
    public String getUrlPlaceDetail(String PlaceID) {
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json?placeid=");
        googlePlacesUrl.append(PlaceID);
        googlePlacesUrl.append("&key=" + KEY_1);
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }
    public String getPhotoURL(int maxWidth, String photoID ){
        StringBuilder googlePlacesUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo?maxwidth=");
        googlePlacesUrl.append(maxWidth);
        googlePlacesUrl.append("&photoreference=" + photoID);
        googlePlacesUrl.append("&key=" + KEY_1);
        Log.d("getUrl", googlePlacesUrl.toString());
        return (googlePlacesUrl.toString());
    }
}
