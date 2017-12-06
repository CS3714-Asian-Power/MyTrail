package com.example.guhao.mytrail;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by DanielPham on 12/5/17.
 */

public class MapAPI extends IntentService{
    public static final String API_KEY = "&key=AIzaSyB8kchYPw50-ExmT0yW3yYVb1LFECU0fZE";
    public static final String SEARCH_URL = "https://maps.googleapis.com/maps/api/place/textsearch/json?query=";
    public static final String DETAIL_URL = "https://maps.googleapis.com/maps/api/place/details/json?placeid=";
    public static final String PHOTO_URL = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&photoreference=";
    public static final String GET_RESULT = "get result";
    public static final String GET_DETAIL = "get detail";

    AllPlaces allPlaces;
    DetailPlace detailPlace;

    public MapAPI(String name) {
        super(name);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("Intent Service", "onHandleIntent");
        if(intent != null){
            final String intent_action = intent.getAction();
            if(GET_RESULT.equals(intent_action)){
               // String search_url =

            }
            else if (GET_DETAIL.equals((intent_action))){

            }
        }

    }


}
