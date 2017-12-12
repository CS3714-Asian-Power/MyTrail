package com.example.guhao.mytrail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.guhao.mytrail.R;
import com.example.guhao.mytrail.api.DownloadHelper;
import com.example.guhao.mytrail.api.GoogleAPIService;
import com.example.guhao.mytrail.data.DetailPlace;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DetailActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  Intent intent = getIntent();
//        String place_id = intent.getStringExtra("place_id");
//
//        Log.d("Place ID:", place_id);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }


    private class JSONReciever extends ResultReceiver{

        public JSONReciever(Handler handler) {
            super(handler);
        }

        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            Log.d("Json",resultData.getString("place_detail"));

            // Show a toast message if an address was found.
            if (resultCode == GoogleAPIService.SUCCESS_RESULT) {
              //  address_view.setText(resultData.getString(Constants.RESULT_DATA_KEY));

                //readData();
            }

        }
    }

    private void parsePlaceDetail( String jsonData) {
        JSONObject jsonResponse = null;
        try {

            jsonResponse = new JSONObject(jsonData);

            JSONArray places = jsonResponse.getJSONArray("results");

            int places_list_size = places.length();


            for (int i = 0; i < places_list_size; i++) {

                JSONObject jsonPlace = places.getJSONObject(i);

                double rating = 0;
                String photo_reference = "null";
                String name = jsonPlace.getString("name");
                String place_id = jsonPlace.getString("place_id");
                double longitude = 0;
                double latitude = 0;

                try {
                    rating = jsonPlace.getDouble("rating");

                    JSONArray photoArray = jsonPlace.getJSONArray("photos");
                    JSONObject photo = photoArray.getJSONObject(0);
                    photo_reference = photo.getString("photo_reference");

                    JSONObject Geometry = jsonPlace.getJSONObject("geometry");
                    JSONObject location = Geometry.getJSONObject("location");
                    longitude = location.getDouble("lng");
                    latitude = location.getDouble("lat");

                } catch (Exception e) {
                    Log.d("Error ", e.toString());
                }

                if (name != null && !name.equals("null")) {
                    //manager.insertMovieInfo(title,dateString,(float) rating );
                    //manager.insertPlaceInfo(name, place_id, (float) rating, photo_reference, (float) longitude, (float) latitude);

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

//    public DetailPlace getPlaceDetail(String place_id){
//        DownloadHelper downloadHelper = new DownloadHelper();
//        String detail_url = downloadHelper.getUrlPlaceDetail(place_id);
//        try{
//            String Data = downloadHelper.getResponses(detail_url);
//        } catch (Exception e){
//            Log.d("Get Detail Error:", e.toString());
//        }
//
//
//
//    }
}
